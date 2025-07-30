package com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne(cascade = CascadeType.ALL)
    private Autor autor;
    private String idiomas;
    private Integer descargas;

    public Libro() {
    }

    public Libro(DatosLibro datos) {
        this.titulo = datos.titulo();
        if (datos.autor() != null && !datos.autor().isEmpty()){
            this.autor = new Autor(datos.autor().get(0));//se hace la conversion asi
        }else {
            this.autor = new Autor(new DatosAutor("Autor desconocido", null, null));
        }
        if (datos.idiomas() != null && !datos.idiomas().isEmpty()){
            this.idiomas = datos.idiomas().get(0); //asi porque es una lista
        } else {
            this.idiomas = "Idioma desconocido";}
        this.idiomas = String.join(", ", datos.idiomas());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idiomas;
    }

    public void setIdioma(String idioma) {
        this.idiomas = idioma;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return  "Título='" + titulo + '\'' +
                "\nAutor=" + autor +
                "\nIdioma='" + idiomas + '\'' +
                "\nDescargas=" + descargas;
    }
}
