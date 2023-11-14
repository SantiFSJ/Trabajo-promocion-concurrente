package ar.unrn.edu.model;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;

class Comedor {
    private Semaphore semEmpleada
            = new Semaphore(1);
    private PriorityBlockingQueue<Pedido> colaPedidos
            = new PriorityBlockingQueue<>();
    private int nroTicket = 0;
    private String[] menu
            = {"Clasico", "Saludable", "Vegetariano"};


    public Pedido atenderComensal(int numeroComensal)
            throws InterruptedException {
        semEmpleada.acquire();
        System.out.println("COMENSAL " + numeroComensal +
                ": REALIZANDO PEDIDO");
        String platoPedido = menu[new Random().nextInt(menu.length)];
        int numeroTicket = generarNumeroTicket();
        Pedido pedido
                = new Pedido(numeroComensal,numeroTicket, platoPedido);
        System.out.println("COMENSAL " + numeroComensal +
                ": NÚMERO DE TICKET: " + numeroTicket +
                ", MENU ELEGIDO: " + platoPedido);
        colaPedidos.add(pedido);
        semEmpleada.release();

        return pedido;
    }

    public void buscarBandeja(Pedido pedido)
            throws InterruptedException {
        int numeroTicket = pedido.getNumeroTicket();
        Pedido primerPedidoEnCola = colaPedidos.peek();
        while(numeroTicket > primerPedidoEnCola.getNumeroTicket()){
            Thread.sleep(100);
            primerPedidoEnCola = colaPedidos.peek();
        }
        semEmpleada.acquire();
        colaPedidos.poll();
        System.out.println("COMENSAL " + pedido.getNumeroComensal() +
                ": ESTA RECIBIENDO LA BANDEJA - NRO DE TICKET: " + numeroTicket);
        Thread.sleep(2000);
        System.out.println("COMENSAL " + pedido.getNumeroComensal() +
                ": RECIBIO LA BANDEJA - NRO DE TICKET: " + numeroTicket);
        semEmpleada.release();
    }


    public void devolverBandeja(Pedido pedido)
            throws InterruptedException {
        semEmpleada.acquire();
        Thread.sleep(1000);
        System.out.println("EMPLEADO: LA BANDEJA DEL COMENSAL NRO "
                + pedido.getNumeroComensal() + " FUE RECIBIDA");
        semEmpleada.release();
    }

    private int generarNumeroTicket() {
        synchronized (this) {
            this.nroTicket++;
            if (this.nroTicket > 999) {
                this.nroTicket = 1;
            }
            return this.nroTicket - 1;
        }
    }

}






