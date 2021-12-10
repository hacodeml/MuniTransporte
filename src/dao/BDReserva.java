package dao;

import model.Reserva;
import util.JFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BDReserva {
    private static String BD_RESERVA = "BDReserva.dat";

    public static List<Reserva> obtenerReservas() throws IOException, ClassNotFoundException {
        try{
            List<Reserva> lbeReservas = new ArrayList<>();
            lbeReservas = JFile.leeFicheroReserva(BD_RESERVA);

            return lbeReservas;

        }catch (Exception ex){
            throw  ex;
        }
    }

    public static void grabarReservas(List<Reserva> lbeReserva) throws IOException {
        try{
            JFile.grabarFicheroReserva(BD_RESERVA,lbeReserva);
        }catch (Exception ex){
            throw ex;
        }

    }
}
