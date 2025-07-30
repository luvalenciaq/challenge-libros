package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name = "libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne
    private Autor autor;
    private String idioma;
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
        if (datos.idioma() != null && !datos.idioma().isEmpty()) {
            this.idioma = datos.idioma().get(0);
        } else {
            this.idioma = "Idioma desconocido";
        }
        this.descargas = datos.descargas();
    }

    public Libro(Optional<DatosLibro> datos) {
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
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return  "\n -------- LIBRO --------"+
                "\nTítulo='" + titulo + '\'' +
                "\nAutor=" + autor.getNombre() +
                "\nIdioma='" + idioma + '\'' +
                "\nDescargas=" + descargas +
                "\n ------------------------";
    }
}
