package ar.unrn.edu.model;

public class Main {
    public static void main(String[] args) {
        Comedor comedor = new Comedor();
        int numComensales = 5;

        // Crear e iniciar hilos para los comensales
        for (int i = 0; i < numComensales; i++) {
            Thread comensalThread = new Thread(new Comensal(comedor));
            comensalThread.start();
        }
    }
}
