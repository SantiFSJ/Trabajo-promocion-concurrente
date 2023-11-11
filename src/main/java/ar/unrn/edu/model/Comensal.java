package ar.unrn.edu.model;

class Comensal implements Runnable {
    private static int contadorComensales = 0;
    private int numeroComensal;
    private int nroTicket;

    private Comedor comedor;

    public Comensal(Comedor comedor) {
        this.comedor = comedor;
        this.numeroComensal = ++contadorComensales;
    }

    public void setTicket(int nroTicket){
        this.nroTicket = nroTicket;
    }

    public void run() {
        try {
            comedor.atenderComensal(numeroComensal);
            // Simular tiempo de comida
            Thread.sleep(2000);
            comedor.entregarPedido(numeroComensal);
            // Simular tiempo después de comer
            Thread.sleep(1000);
            comedor.recibirBandeja(numeroComensal);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}