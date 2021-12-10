import dao.BDVecino;
import model.AdultoMayor;
import model.ClubEcologia;
import model.Vecino;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Municipalidad {
    private List<Vecino> vecinos;

    public Municipalidad(){
        vecinos = new ArrayList<>();

        try {
            vecinos = BDVecino.obtenerVecinos();
        } catch (IOException e) {
            vecinos = new ArrayList<>();
        } catch (ClassNotFoundException e) {
            vecinos = new ArrayList<>();
        }
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
        System.out.format("%s %20s %15s %15s %25s %15s %25s %25s \n",
                "Nombres","DNI","Edad","Teléfono","Email","Estado Civil","Tipo","Obsequio");

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

            System.out.format("%s %20s %15s %15s %25s %15s %25s %25s\n",
                    v.getNombre(),
                    v.getDni(),
                    v.getEdad(),
                    v.getTelefono(),
                    v.getCorreoElectronico(),
                    v.getEstadoCivil(),
                    tipo,
                    obsequio);

        }
    }
}
