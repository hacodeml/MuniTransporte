package com.upc.TrabajoFinal_Grupo04;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        AdultoMayor vecino1 = new AdultoMayor("RRR", "11111111", "123123", "Solter"
                , 80, "asdasdasd");
        AdultoMayor vecino2 = new AdultoMayor("SSS", "22222222", "333", "Viuda"
                , 70, "asdasdasd");
        ClubEcologia vecino3 = new ClubEcologia("AAA", "33333333", "qwe12", "Soltero"
                , 40, "100sd");
        ClubEcologia vecino4 = new ClubEcologia("BBB", "44444444", "qwe12", "Soltero"
                , 50, "100sd");
        ClubEcologia vecino5 = new ClubEcologia("asdasd", "55555555", "qwe12", "Casado"
                , 40, "100sd");
        ClubEcologia vecino6 = new ClubEcologia("BarBB", "66666666", "qwe12", "Casado"
                , 30, "100sd");
        //ClubEcologia vecino7 = new ClubEcologia("BarBcB", "87874384", "qwe12", "Casado" , 17, "100sd");

        Bus bus1= new Bus("1",45);
        Bus bus2= new Bus("2",50);
        Bus bus3= new Bus("3",45);

        Reserva reserva1= new Reserva(vecino1,"20210101",1,bus1);
        Reserva reserva2= new Reserva(vecino2,"20210103",1,bus1);
        Reserva reserva3= new Reserva(vecino6,"20210101",1,bus1);

        Municipalidad municipalidad = new Municipalidad("San Borja");

        try {
            municipalidad.registrarVecino(vecino1);
            municipalidad.registrarVecino(vecino2);
            municipalidad.registrarVecino(vecino3);
            municipalidad.registrarVecino(vecino4);
            municipalidad.registrarVecino(vecino5);
            municipalidad.registrarVecino(vecino6);
            // municipalidad.registrarVecino(vecino7);
            municipalidad.registrarReserva(reserva1);
            municipalidad.registrarReserva(reserva2);
            municipalidad.registrarReserva(reserva3);

            Vecino vecinoBuscar = municipalidad.buscarPorDNI("33333333");
            System.out.println("VECINO ENCONTRADO: " + vecinoBuscar.toString());

            System.out.println("VECINOS ADULTO MAYOR REGISTRADOS");
            for (Vecino vecino : municipalidad.getVecinos()) {
                if (vecino instanceof AdultoMayor) {
                    System.out.println(vecino.toString());
                }
            }
            System.out.println("PROMEDIO EDADES ADULTO MAYOR");
            System.out.println(municipalidad.obtenerPromedioAMayor());

            for (Vecino vecino : municipalidad.getVecinos()) {
                if (vecino instanceof ClubEcologia && !vecino.obtenerObsequio()) {
                    System.out.println(vecino.toString());
                }
            }
            System.out.println("PROMEDIO EDADES CLUB ECO SIN OBSEQUIO");
            System.out.println(municipalidad.obtenerPromedioClubEcoSinObsequio());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            }

        System.out.println("BUSCAR POR FECHA Y BUS - MOSTRAR REGISTRADOS");
        List<Reserva> reservaconfechaybus = municipalidad.buscarReservasEnBus("20210101","1");
        for (Reserva reserva:reservaconfechaybus){
            System.out.println(reserva);
        }

        System.out.println("VECINOS QUE OBTUVIERON UN OBSEQUIO");
        for (Vecino vecino : municipalidad.getVecinos()) {
            if (vecino.obtenerObsequio()) {
                System.out.println(vecino.toString());
            }
        }

    }
}
