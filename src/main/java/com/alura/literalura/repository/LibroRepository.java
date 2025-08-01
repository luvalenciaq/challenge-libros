package com.alura.literalura.repository;

import com.alura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    //Optional<Libro> findFirstByTituloContainingIgnoreCase(String titulo);
    Optional<Libro> findByTitulo(String titulo);
    List<Libro> findByIdiomaIgnoreCase(String idioma);

}
