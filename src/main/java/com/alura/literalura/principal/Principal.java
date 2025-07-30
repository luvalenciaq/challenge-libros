package com.alura.literalura.principal;

import com.alura.literalura.model.*;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;

import java.util.*;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos(); //para pasar de json a class
    private LibroRepository repositorio; //para poder usar las busquedas
    private AutorRepository repositorioAutor;
    private List<Libro> libros; //esto lo hago porque voy a usar varias veces una lista de libros
    private List<Autor> autores;

    public Principal(LibroRepository repository, AutorRepository autorRepository) { //esto se hace como inyeccion de dependencia
        //asi puedo acceder a la DB ya que el repositorio es una interfaz y no puedo crear una instancia de esto
        //de esta manera puedo usar los metodos del repo.
        this.repositorio = repository;
        this.repositorioAutor = autorRepository;
    }

    public void muestraElMenu() {
        var opcion = -1; //se pone asi como convencion para decir que ninguna opcion es valida aun

        while (opcion != 0) {
            var menu = """
                    \n********* MENÚ **********
                    Elegir una de las opciones a continuación:
                    1. Buscar libros por título
                    2. Listar libros registrados
                    3. Listar autores registrados
                    4. Listar autores vivos en un determinado año
                    5. Listar libros por idioma
                    6. Top 10 de libros más descargados
                                    
                    0. Salir
                    """;
            System.out.println(menu);
            String valorIngresado = teclado.nextLine(); // leer como un texto
            try {
                opcion = Integer.parseInt(valorIngresado); // lo convierto a numero
                switch (opcion) {
                    case 1:
                        buscarLibro();
                        break;
                    case 2:
                        listarLibrosRegistrados();
                        break;
                    case 3:
                        listarAutores();
                        break;
                    case 4:
                        int anio = pedirAnioValido();
                        listarAutoresVivosPorAnio(anio);
                        break;
                    case 5:
                        String idioma = pedirIdiomaValido();
                        listarPorIdioma(idioma);
                        break;
                    case 6:
                        listarTop10Descargas();
                        break;
                    case 0:
                        System.out.println("Saliendo del programa... ¡Gracias por usar nuestro servicio!");
                        break;
                    default:
                        System.out.println("Opción inválida. Intente nuevamente");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Porfavor escriba solo números");
            }
        }
    }

    //NECESITO CREAR UN METODO PARA ACCDER A LA API Y OBTENER LOS DATOS
    private Optional<DatosLibro> getDatosLibro() {
        System.out.println("Escriba el nombre del libro que desea buscar:");
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE
                + nombreLibro.replace(" ", "+"));
        ResultadoBusqueda datos = conversor.obtenerDatos(json, ResultadoBusqueda.class);
        //debo cambiar el tipo de dato en la variable porque ya recibe ese resultado busqueda
        if (datos.results().isEmpty()) {
            System.out.println("No se encontraron libros con ese título. Intente con otro.");
            return Optional.empty();
        }
        return Optional.of(datos.results().get(0));
    }

    private void buscarLibro() {
        Optional<DatosLibro> datosLibro = getDatosLibro();
        if (datosLibro.isPresent()) {
            DatosLibro datos = datosLibro.get(); //asi porque el optional evita el null

            // Verificar si el libro ya existe
            Optional<Libro> libroExistente = repositorio.findByTitulo(datos.titulo());
            if (libroExistente.isPresent()) {
                System.out.println("\nEl libro ya existe en la base de datos");
                System.out.println(libroExistente.get());
            } else {
                DatosAutor primerAutor = datos.autor().get(0);
                String nombreAutor = primerAutor.nombre();

                // Verificar si el autor ya existe
                Optional<Autor> autorExistente = repositorioAutor.findByNombreIgnoreCase(nombreAutor);
                Autor autor;

                if (autorExistente.isPresent()) {
                    autor = autorExistente.get();
                } else {
                    autor = new Autor(primerAutor);
                    autor = repositorioAutor.save(autor);
                }

                // Crear y asociar el libro
                Libro libro = new Libro(datos);
                libro.setAutor(autor);

                repositorio.save(libro);
                System.out.println("\nLibro guardado correctamente");
                System.out.println(libro);
            }
        }
    }

    private void listarLibrosRegistrados() {
        libros = repositorio.findAll();
        System.out.println("\n TOTAL DE LIBROS REGISTRADOS: " + libros.size());

        libros.stream().sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);
    }

    private void listarAutores() {
        autores = repositorioAutor.findAll();
        System.out.println("\nAutores registrados:");
        autores.stream().sorted(Comparator.comparing(Autor::getNombre))
                .forEach(System.out::println);
    }

    private int pedirAnioValido() {
        int anio = -1;

        while (true) {
            System.out.println("Ingrese el año que desea consultar (sólo números positivos de hasta 4 dígitos)");
            try {
                anio = teclado.nextInt();
                teclado.nextLine();

                if (anio > 0 && anio <= 9999) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida, Vuelva a intentarlo");
                teclado.nextLine();
            }
        }
        return anio;
    }

    private void listarAutoresVivosPorAnio(Integer anio) {
        autores = repositorioAutor.buscarAutoresVivosPorAnio(anio);
        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año: " + anio);
        } else {
            System.out.println("\nAutores vivos en el año: " + anio);
            autores.stream().forEach(System.out::println);
        }
    }

    public boolean esValido(String texto) {
        return texto.matches("^[a-zA-Z]{1,2}$");
    }

    private String pedirIdiomaValido() {
        String idioma;
        while (true) {
            System.out.println("Ingrese el código del idioma para buscar los libros:");
            System.out.println("""
                    es - Español
                    en - Inglés
                    fr - Francés
                    pt - Portugués
                    it - Italiano
                    """);

            idioma = teclado.nextLine().trim();

            if (!esValido(idioma)) {
                System.out.println("Idioma inválido. Vuelva a intentarlo\n");
            } else {
                return idioma;
            }
        }
    }

    public void listarPorIdioma(String idioma) {
         libros = repositorio.findByIdiomaIgnoreCase(idioma);

        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en este idioma: " + idioma);
        } else {
            System.out.println("\nSe econcontraron " + libros.size() + " libros en el idioma " + idioma);
            libros.forEach(System.out::println);
        }
    }

    private void listarTop10Descargas() {
        libros = repositorio.findAll();
        System.out.println("\nTop 10 de libros más descargados:");
        libros.stream()
                .sorted(Comparator.comparingInt(Libro::getDescargas).reversed())
                .limit(10)
                .forEach(libro -> System.out.println("Título: " +libro.getTitulo()
                + " | Descargas: " + libro.getDescargas()));
    }


}
