package com.upc.TrabajoFinal_Grupo04;

public class AdultoMayor extends Vecino{

    public AdultoMayor(String nombre, String dni, String telefono,
                       String estadoCivil, int edad, String correoElectronico) {
        super(nombre, dni, telefono, estadoCivil, edad, correoElectronico);
    }

    public boolean obtenerObsequio() {
        if (getEdad()>75){
            return true;
        } else {
            return false;
        }
    }

    public String obsequio(){
        String premio="no tiene obsequio";
        if (getEdad()>75){
            premio="Bebida";
        }

        return premio;
    }

    public String toString() {
        return "AdultoMayor{} " + super.toString() + ",Obsequio: " + obsequio();
    }
}
