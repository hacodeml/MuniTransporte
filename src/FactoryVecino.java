import model.AdultoMayor;
import model.ClubEcologia;
import model.Vecino;

public class FactoryVecino {
    //tipo = 1 : retornar ClubEcologia | 2 : retornar Adulto Mayor
    public static Vecino obtenerVecino(int tipo, String... data){
        switch (tipo){
            case 1 : return new ClubEcologia(
                    data[0],
                    data[1],
                    data[2],
                    data[3],
                    Integer.parseInt(data[4]),
                    data[5]
            );
            case 2 : return new AdultoMayor(
                    data[0],
                    data[1],
                    data[2],
                    data[3],
                    Integer.parseInt(data[4]),
                    data[5]
            );
            default: return null;
        }

    }
}
