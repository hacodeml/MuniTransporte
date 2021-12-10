package model;

import java.io.Serializable;

public class Vecino implements Serializable {
    private int codigo;
    private String nombres;

    public Vecino(int codigo, String nombres) {
        this.codigo = codigo;
        this.nombres = nombres;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
}
