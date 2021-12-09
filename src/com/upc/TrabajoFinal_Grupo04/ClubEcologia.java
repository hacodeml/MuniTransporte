package com.upc.TrabajoFinal_Grupo04;

public class ClubEcologia extends Vecino{

    public ClubEcologia(String nombre, String dni, String telefono,
                        String estadoCivil, int edad, String correoElectronico) {
        super(nombre, dni, telefono, estadoCivil, edad, correoElectronico);
    }

    public boolean obtenerObsequio() {
        if (getEstadoCivil().equals("Casado")){
            return true;
        } else {
            return false;
        }
    }

    public String obsequio(){
        String premio="no tiene obsequio";
        if(getEstadoCivil().equals("Casado")){
            premio="Chocolate sublime";
        }
        return premio;
    }

    public String toString() {
        return "ClubEcologia{} " + super.toString() + ",Obsequio: " + obsequio();
    }
}
