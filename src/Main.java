import dao.BDVecino;
import model.Vecino;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Vecino> vecinos = new ArrayList<>();
        Vecino v1 = FactoryVecino.obtenerVecino(1,"Paola","47716733","954593542","Soltera","31","admin@java.com");
        Vecino v2 = FactoryVecino.obtenerVecino(2,"Don Gato","48856799","954593543","Viudo","61","admin2@java.com");

        vecinos.add(v1);
        vecinos.add(v2);

        try {
            BDVecino.grabarVecinos(vecinos);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

