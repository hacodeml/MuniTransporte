package com.upc.TrabajoFinal_Grupo04;

public class Bus {
    private String numeroBus;
    private int capacidadMax;

    public Bus(String numeroBus, int capacidadMax) {
        this.numeroBus = numeroBus;
        this.capacidadMax = capacidadMax;
    }

    public String getNumeroBus() {
        return numeroBus;
    }

    public void setNumeroBus(String numeroBus) {
        this.numeroBus = numeroBus;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(int capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public String toString() {
        return "Bus{" +
                "numeroBus='" + numeroBus + '\'' +
                ", capacidadMax=" + capacidadMax +
                '}';
    }
}
