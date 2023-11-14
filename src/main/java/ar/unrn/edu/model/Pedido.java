package ar.unrn.edu.model;

public class Pedido implements Comparable<Pedido> {
    private int numeroTicket;
    private int numeroComensal;
    private String platoPedido;

    public Pedido(int numeroComensal
            , int numeroTicket
            , String platoPedido) {
        this.numeroComensal = numeroComensal;
        this.numeroTicket = numeroTicket;
        this.platoPedido = platoPedido;
    }

    public int getNumeroTicket() {
        return numeroTicket;
    }

    public int getNumeroComensal() {
        return numeroComensal;
    }

    public String getPlatoPedido(){
        return platoPedido;

    }

    @Override
    public int compareTo(Pedido otro) {
        return Integer.compare(this.getNumeroTicket(), otro.getNumeroTicket());
    }

}
