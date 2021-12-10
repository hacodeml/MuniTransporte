package dao;

import model.Bus;
import util.JFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BDBuses {
    private static String BD_BUSES = "BDBuses.dat";

    public static List<Bus> obtenerBuses() throws IOException, ClassNotFoundException {
        try{
            List<Bus> lbeBuses = new ArrayList<>();
            lbeBuses = JFile.leeFicheroBus(BD_BUSES);

            return lbeBuses;

        }catch (Exception ex){
            throw  ex;
        }
    }

    public static void grabarBuses(List<Bus> lbeBuses) throws IOException {
        try{
            JFile.grabarFicheroBus(BD_BUSES,lbeBuses);
        }catch (Exception ex){
            throw ex;
        }

    }
}
