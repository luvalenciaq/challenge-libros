package com.alura.literalura.repository;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.DatosAutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombreIgnoreCase(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.anioNacimiento <= :anio AND a.anioMuerte >= :anio")
    List<Autor> buscarAutoresVivosPorAnio(Integer anio);
}
