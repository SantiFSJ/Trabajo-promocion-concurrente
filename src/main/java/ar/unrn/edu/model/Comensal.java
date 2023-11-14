package ar.unrn.edu.model;

class Comensal implements Runnable {
    private static int contadorComensales = 0;
    private int numeroComensal;
    private Comedor comedor;

    public Comensal(Comedor comedor) {
        this.comedor = comedor;
        this.numeroComensal = ++contadorComensales;
    }

    public void run() {
        try {
            Pedido miPedido =
                    comedor.atenderComensal(numeroComensal);
            Thread.sleep(2000);
            comedor.buscarBandeja(miPedido);
            Thread.sleep(1000);
            comedor.devolverBandeja(miPedido);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


}