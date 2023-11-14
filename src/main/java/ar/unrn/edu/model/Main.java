package ar.unrn.edu.model;

public class Main {
    public static void main(String[] args) {
        Comedor comedor = new Comedor();
        int numComensales = 5;
        for (int i = 0; i < numComensales; i++) {
            Thread comensalThread =
                    new Thread(new Comensal(comedor));
            comensalThread.start();
        }
    }

}

