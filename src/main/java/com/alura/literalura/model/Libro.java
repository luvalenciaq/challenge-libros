package com.alura.literalura.model;

public class Libro {
    private Long id;
    private String titulo;
    private Autor autor;
    private String idioma;
    private Integer descargas;

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
        return  "Título='" + titulo + '\'' +
                "\nAutor=" + autor +
                "\nIdioma='" + idioma + '\'' +
                "\nDescargas=" + descargas;
    }
}
