package model;

import model.Bus;
import model.Vecino;

import java.io.Serializable;

public class Reserva implements Serializable {
    private Vecino dniReserva;
    private String fecha;
    private int asiento;
    private Bus busAsignado;

    public Reserva(Vecino dniReserva, String fecha, int asiento, Bus busAsignado) {
        this.dniReserva = dniReserva;
        this.fecha = fecha;
        this.asiento = asiento;
        this.busAsignado = busAsignado;
    }

    public boolean validarDNI(){
        return false;
    }

    public boolean validarEdad(){
        return false;
    }

    public Vecino getDniReserva() {
        return dniReserva;
    }

    public void setDniReserva(Vecino dniReserva) {
        this.dniReserva = dniReserva;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getAsiento() {
        return asiento;
    }

    public void setAsiento(int asiento) {
        this.asiento = asiento;
    }

    public Bus getBusAsignado() {
        return busAsignado;
    }

    public void setBusAsignado(Bus busAsignado) {
        this.busAsignado = busAsignado;
    }

    public String toString() {
        return "Reserva{" +
                "dniReserva=" + dniReserva +
                ", fecha='" + fecha + '\'' +
                ", asiento=" + asiento +
                ", busAsignado=" + busAsignado +
                '}';
    }
}
