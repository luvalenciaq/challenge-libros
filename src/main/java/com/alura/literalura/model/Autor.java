package com.alura.literalura.model;

public class Autor {
    private Long id;
    private String nombre;
    private Integer anioNacimiento;
    private Integer anioMuerte;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return anioNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.anioNacimiento = fechaNacimiento;
    }

    public Integer getFechaMuerte() {
        return anioMuerte;
    }

    public void setFechaMuerte(Integer fechaMuerte) {
        this.anioMuerte = fechaMuerte;
    }

    @Override
    public String toString() {
        return  "Nombre='" + nombre +
                ", \nAño de nacimiento=" + anioNacimiento +
                ", \nAño de fallecimiento=" + anioMuerte;
    }
}
