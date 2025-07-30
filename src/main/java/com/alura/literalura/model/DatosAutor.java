package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year")Integer anioNacimiento,
        @JsonAlias("death_year")Integer anioMuerte) {

    @Override
    public String toString() {
        return "Nombre='" + nombre +
                "Año de Nacimiento=" + anioNacimiento +
                "Año de Muerte=" + anioMuerte;
    }
}
