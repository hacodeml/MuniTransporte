package util;

import model.Vecino;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JFile {
    public static void grabarFicheroVecino(String fichero, List<Vecino> vecinos) {
        FileOutputStream fos = null;
        ObjectOutputStream salida = null;

        try {
            String ruta = JFile.class.getResource("/files/").getPath();

            fos = new FileOutputStream(ruta + fichero);
            salida = new ObjectOutputStream(fos);

            for (Vecino v : vecinos) {
                salida.writeObject(v);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(fos!=null) fos.close();
                if(salida!=null) salida.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static List<Vecino> leeFicheroVecino(String fichero) throws IOException, ClassNotFoundException {
        FileInputStream fis = null;
        ObjectInputStream entrada = null;

        try {
            List<Vecino> vecinos = new ArrayList<>();

            String ruta = JFile.class.getResource("/files/").getPath();

            Vecino v;

            fis = new FileInputStream(ruta + fichero);
            entrada = new ObjectInputStream(fis);

            while (true) {
                try {
                    v = (Vecino) entrada.readObject();
                    vecinos.add(v);
                } catch (Exception ex) {
                    break;
                }
            }

            return vecinos;
        } catch (Exception ex) {
            throw  ex;
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
