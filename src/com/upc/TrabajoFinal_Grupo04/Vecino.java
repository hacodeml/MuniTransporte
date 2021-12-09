package com.upc.TrabajoFinal_Grupo04;

public abstract class Vecino {
    private String nombre;
    private String dni;
    private String telefono;
    private String estadoCivil;
    private int edad;
    private String correoElectronico;

    public Vecino(String nombre, String dni, String telefono, String estadoCivil, int edad,
                  String correoElectronico) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.estadoCivil = estadoCivil;
        this.edad = edad;
        this.correoElectronico = correoElectronico;
    }

    public abstract boolean obtenerObsequio();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String toString() {
        return "Vecino{" +
                "dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                ", estadoCivil='" + estadoCivil + '\'' +
                ", edad=" + edad +
                ", correoElectronico='" + correoElectronico + '\'' +
                '}';
    }
}
