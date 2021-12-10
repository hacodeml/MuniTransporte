import dao.BDVecino;
import model.AdultoMayor;
import model.ClubEcologia;
import model.Vecino;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Municipalidad {
    private static Municipalidad instancia;
    private List<Vecino> vecinos;

    private Municipalidad(){
        vecinos = new ArrayList<>();

        try {
            vecinos = BDVecino.obtenerVecinos();
        } catch (IOException e) {
            vecinos = new ArrayList<>();
        } catch (ClassNotFoundException e) {
            vecinos = new ArrayList<>();
        }
    }

    public static Municipalidad getInstancia(){
        if(instancia == null){
            instancia = new Municipalidad();
        }
        return instancia;
    }

    public void registrarVecino(Vecino vecino) throws Exception {
        if (vecino.getEdad()>=18) {
//            if (!this.vecinos.isEmpty()) {
                Vecino vecinoBuscado = buscarVecinoPorDni(vecino.getDni());

                if(vecinoBuscado !=null){
                    throw new Exception("Error !!! .. No se pudo registrar, DNI ya está registrado");
                }

                this.vecinos.add(vecino);

                BDVecino.grabarVecinos(vecinos);

//            }else{
//                throw new Exception("Error !!!! No existe lista");
//            }
        } else {
            throw new Exception("Error !!!! .... Vecino no es mayor de edad: " + vecino.toString());
        }
    }

    public Vecino buscarVecinoPorDni(String dni){
        for (Vecino v: vecinos) {
            if(v.getDni().equalsIgnoreCase(dni)){
                return v;
            }
        }
        return null;
    }

    public void listarVecinosAll(){
        System.out.format("%s\t%20s\t%15s\t%15s\t%25s\t%15s\t%20s\t%20s \n",
                "Nombres","DNI","Edad","Teléfono","Email","Estado Civil","Tipo","Obsequio");
        System.out.println("=======================================================================================================================================================");

        int registros = vecinos.size();

        if(registros == 0){
            System.out.println("No hay registros para mostrar");
        }

        for (Vecino v:vecinos) {
            String tipo = "";
            String obsequio = "";

            if(v instanceof ClubEcologia){
                tipo ="Club Ecología";
                obsequio = ((ClubEcologia)v).obsequio();
            }else{
                tipo ="Adulto Mayor";
                obsequio = ((AdultoMayor)v).obsequio();
            }

            System.out.format("%s\t%20s\t%15s\t%15s\t%25s\t%15s\t%20s\t%20s\n",
                    v.getNombre(),
                    v.getDni(),
                    v.getEdad(),
                    v.getTelefono(),
                    v.getCorreoElectronico(),
                    v.getEstadoCivil(),
                    tipo,
                    obsequio);
        }

        System.out.println("\n");

    }
}
