import dao.BDVecino;
import model.Vecino;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Vecino v1 = new Vecino(1,"Paola Cueva");
        Vecino v2 = new Vecino(2,"Don Gato");
        Vecino v3 = new Vecino(3,"Tambor");


        List<Vecino> lista1 = new ArrayList<>();
        lista1.add(v1);
        lista1.add(v2);
        lista1.add(v3);

        try {
            BDVecino.grabarVecinos(lista1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Vecino> lista2 = new ArrayList<>();
        try {
            lista2 = BDVecino.obtenerVecinos();

            for (Vecino v:lista2) {
                System.out.println(v.getCodigo() + "->" + v.getNombres());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
