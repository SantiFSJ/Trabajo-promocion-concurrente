package ar.unrn.edu.model;

import java.util.Random;
import java.util.concurrent.Semaphore;

class Comedor{
    private Semaphore semAtenderPedido = new Semaphore(1);
    private Semaphore semEntregarPedido = new Semaphore(1);
    private Semaphore semRecibirBandeja = new Semaphore(1);
    private String[] menu = {"Clasico", "Saludable", "Vegetariano"};

    private int nroTicket = 0;

    public void atenderComensal(int numeroComensal) throws InterruptedException {
        semAtenderPedido.acquire();
        System.out.println("Comensal " + numeroComensal + ": Realizando pedido");
        // Lógica para tomar el pedido y dar el ticket
        // Simular la toma del pedido asignando un plato aleatorio
        String platoPedido = menu[new Random().nextInt(menu.length)];
        // Imprimir el ticket
        System.out.println("Comensal " + numeroComensal + ": Ticket generado - Número: " + generarNumeroTicket() + ", Plato: " + platoPedido);
        // Lógica para dar el ticket al comensal
        semAtenderPedido.release();    }

    public void entregarPedido(int numeroComensal) throws InterruptedException {
        semEntregarPedido.acquire();
        System.out.println("Comensal " + numeroComensal + ": Recibiendo pedido");
        // Simular tiempo de preparación del pedido
        Thread.sleep(2000);
        // Lógica para entregar el pedido al comensal
        System.out.println("Comensal " + numeroComensal + ": Pedido entregado");

        semRecibirBandeja.release();
    }

    public void recibirBandeja(int numeroComensal) throws InterruptedException {
        semRecibirBandeja.acquire();
        System.out.println("Empleado: Recibiendo bandeja del Comensal " + numeroComensal);
        // Lógica para recibir la bandeja del comensal que terminó
        // Simular tiempo de limpieza o procesamiento de la bandeja
        Thread.sleep(1000);
        System.out.println("Empleado: Bandeja del Comensal " + numeroComensal + " recibida y procesada");
        semEntregarPedido.release(); // Permitir que la empleada atienda a otro comensal
        semRecibirBandeja.release();
    }

    private int generarNumeroTicket() {
        // Puedes implementar la lógica para generar números de ticket únicos aquí
        this.nroTicket += 1;
        if(this.nroTicket > 999){
            this.nroTicket = 1;
        }
        return this.nroTicket - 1;
    }

}






