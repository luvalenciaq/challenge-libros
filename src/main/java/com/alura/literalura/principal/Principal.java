package com.alura.literalura.principal;

import com.alura.literalura.model.DatosLibro;
import com.alura.literalura.model.Libro;
import com.alura.literalura.model.ResultadoBusqueda;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos(); //para pasar de json a class
    private LibroRepository repositorio; //para poder usar las busquedas

    public Principal(LibroRepository repository) { //esto se hace como inyeccion de dependencia
        //asi puedo acceder a la DB ya que el repositorio es una interfaz y no puedo crear una instancia de esto
        //de esta manera puedo usar los metodos del repo.
        this.repositorio = repository;
    }

    public void muestraElMenu(){
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    ********* MENÚ **********
                    1. Buscar libros por título
                    
                    0. Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine(); //para asegurarnos que si coja el valor

            switch (opcion){
                case 1:
                    buscarLibro();
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    //NECESITO CREAR UN METODO PARA ACCDER A LA API Y OBTENER LOS DATOS
    private DatosLibro getDatosLibro(){
        System.out.println("Escriba el nombre del libro que desea buscar:");
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE
                + nombreLibro.replace(" ", "+"));
        ResultadoBusqueda datos = conversor.obtenerDatos(json, ResultadoBusqueda.class);
        //debo cambiar el tipo de dato en la variable porque ya recibe ese resultado busqueda
        if (datos.results().isEmpty()){
            System.out.println("No se encontraron libros con ese título");
            return null;
        }
        return datos.results().get(0);

    }

    private void buscarLibro() {
        DatosLibro datos = getDatosLibro();
        Libro libro = new Libro(datos); // esto se puede hacer gracias
        // al constructor que hice en las respectivas clases de autor y libro
        repositorio.save(libro);
        System.out.println(datos);
    }

}
