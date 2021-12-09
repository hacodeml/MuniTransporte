package com.upc.TrabajoFinal_Grupo04;

import java.util.ArrayList;
import java.util.List;

public class Municipalidad {
    private String nombreMunicipalidad;
    private List<Vecino> vecinos;
    private List<Reserva> reservas;

    public Municipalidad(String nombreMunicipalidad) {
        this.nombreMunicipalidad = nombreMunicipalidad;
        this.vecinos = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public void registrarVecino(Vecino vecino) throws Exception {
        if (18 <= vecino.getEdad()) {
            if (!this.vecinos.isEmpty()) {
                for (Vecino busqueda : this.vecinos) {
                    if (vecino.getDni().equalsIgnoreCase(busqueda.getDni())) {
                        throw new Exception (
                                "VECINO NO CUMPLE LOS REQUISITOS PARA SU REGISTRO - VECINO YA EXISTE: " +
                                        vecino.toString());
                    }
                }
                this.vecinos.add(vecino);

            } else {
                this.vecinos.add(vecino);
            }
        } else {
            throw new Exception(
                    "VECINO NO CUMPLE LOS REQUISITOS PARA SU REGISTRO - MENOR DE EDAD: " + vecino.toString());
        }
    }

    public Vecino buscarPorDNI(String dni) throws Exception {
        Vecino vecinoEncontrado = null;
        if (!this.vecinos.isEmpty()) {
            for (Vecino busqueda : this.vecinos) {
                if (dni.equalsIgnoreCase(busqueda.getDni())) {
                    vecinoEncontrado = busqueda;
                }
            }
            if (vecinoEncontrado == null) {
                throw new Exception(
                        "EL(LA) VECINO(A) CON DNI: " + dni + ", NO SE ENCUENTRA REGISTRADO(A).");
            }
        } else {
            throw new Exception(
                    "NO EXISTEN VECINOS REGISTRADOS.");
        }
        return vecinoEncontrado;
    }

    public void registrarReserva(Reserva reserva){
        reservas.add(reserva);
    }

    public List<Reserva> buscarReservasEnBus(String fecha, String busAsignado) {

        List<Reserva> listAux = new ArrayList<>();
        for (Reserva reserva : reservas){
            if (reserva.getFecha().equals(fecha) || reserva.getBusAsignado().equals(busAsignado)){
                listAux.add(reserva);
            }
        }
        return listAux;
    }

    public List<Vecino> obtenerVecinosObsequio() {

        List<Vecino> listAuxObsequio = new ArrayList<>();
        for (Vecino vecino : vecinos) {
            if (vecino.obtenerObsequio()) {
                listAuxObsequio.add(vecino);
            }
        }
        return listAuxObsequio;
    }

    public double obtenerPromedioAMayor() {
        double suma = 0;
        List<Vecino> vecinosAdultoM = new ArrayList<>();
        for (Vecino vecino : vecinos) {
            if (vecino instanceof AdultoMayor) {
                vecinosAdultoM.add(vecino);
            }
            if (vecino instanceof AdultoMayor) {
                suma += vecino.getEdad();
            }
        }
        return (suma / vecinosAdultoM.size());
    }

    public double obtenerPromedioClubEcoSinObsequio() {
        double suma = 0;
        List<Vecino> vecinosClubEco = new ArrayList<>();
        for (Vecino vecino : vecinos) {
            if (vecino instanceof ClubEcologia && !vecino.obtenerObsequio()) {
                vecinosClubEco.add(vecino);
            }
            if (vecino instanceof ClubEcologia && !vecino.obtenerObsequio()) {
                suma += vecino.getEdad();
            }
        }
        return (suma / vecinosClubEco.size());
    }

    public List<Reserva> obtenerAsientosDisponibles() {
        return null;
    }

    public String getNombreMunicipalidad() {
        return nombreMunicipalidad;
    }

    public void setNombreMunicipalidad(String nombreMunicipalidad) {
        this.nombreMunicipalidad = nombreMunicipalidad;
    }

    public List<Vecino> getVecinos() {
        return vecinos;
    }

    public void setVecinos(List<Vecino> vecinos) {
        this.vecinos = vecinos;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}
