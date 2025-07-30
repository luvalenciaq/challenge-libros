package com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer anioNacimiento;
    private Integer anioMuerte;


    //DEBO CREAR ESTE CONSTRUCTOR SI O SI PARA PODER CONVERTIR LOS DATOS
    public Autor(DatosAutor datos){
        this.nombre = datos.nombre();
        this.anioNacimiento = datos.anioNacimiento();
        this.anioMuerte = datos.anioMuerte();
    }

    public Autor() {
    }

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
