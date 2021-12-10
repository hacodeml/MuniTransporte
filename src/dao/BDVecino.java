package dao;

import model.Vecino;
import util.JFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BDVecino {
    private static String BD_VECINOS = "BDVecinos.dat";

    public static List<Vecino> obtenerVecinos() throws IOException, ClassNotFoundException {
        try{
            List<Vecino> lbeVecinos = new ArrayList<>();
            lbeVecinos = JFile.leeFicheroVecino(BD_VECINOS);

            return lbeVecinos;

        }catch (Exception ex){
            throw  ex;
        }
    }

    public static void grabarVecinos(List<Vecino> lbeVecino) throws IOException {
        try{
            JFile.grabarFicheroVecino(BD_VECINOS,lbeVecino);
        }catch (Exception ex){
            throw ex;
        }

    }
}
