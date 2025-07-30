# 📚 Challenge libros - Literalura

Proyecto desarrollado como parte del programa **ONE | Tech Foundation - Especialización Back-End**, en el desafío propuesto en la formación de **Java y Spring Framework**.

## 🚀 **Descripción del proyecto**

Literalura es una aplicación de consola que permite consultar libros a través de la API pública [Gutendex](https://gutendex.com/) y gestionar un catálogo de libros y autores, guardando la información en una base de datos PostgreSQL.

A través de un menú interactivo, los usuarios pueden buscar libros, listar autores, filtrar por idioma o año, y consultar el top 10 de libros más descargados.

---

## 🛠️ **Tecnologías y dependencias utilizadas**

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Jackson Databind (para deserialización JSON)
- Maven  

## 📦 **Estructura del proyecto**

El proyecto está organizado por paquetes, respetando los principios de separación de responsabilidades:

- `model`
- `repository`
- `service`
- `principal` (donde se encuentra la clase `Principal.java` que contiene el menú)

---

## 📋 **Funcionalidades del menú**

********* MENÚ **********
1. Buscar libro por título
    - Realiza una consulta a la API Gutendex.
    - Guarda el libro en la base de datos si no existe.
2. Listar libros registrados
3. Listar autores registrados
4. Listar autores vivos en un año determinado
5. Listar libros por idioma (es, en, fr, pt, it)
6. Top 10 de libros más descargados
0. Salir


## 🔧 **Configuración**
La aplicación se conecta a una base de datos PostgreSQL. Para ejecutarla, asegúrate de tener PostgreSQL corriendo y de haber creado una base de datos con el nombre que definas en tu variable de entorno DB_NAME.

A continuación, la configuración segura del archivo application.properties utilizando variables de entorno:
```
spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver

hibernate.dialect=org.hibernate.dialect.HSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.format-sql=true
```
Debes configurar las siguientes variables de entorno en tu sistema:

- DB_HOST: host y puerto de tu base de datos (ejemplo: localhost:5432, localhost)
- DB_NAME: nombre de tu base de datos (ejemplo: literalura)
- DB_USER: usuario de la base de datos (ejemplo: postgres)
- DB_PASSWORD: contraseña del usuario (ejeplo: con123)

## ▶️ **Cómo ejecutar el proyecto**

1. Clona el repositorio:
```
git clone https://github.com/luvalenciaq/challenge-libros.git
```
2. Entra a la carpeta del proyecto:
```
cd challenge-libros
```
3. Asegúrate de tener una base de datos PostgreSQL creada y configura tus variables de entorno como se indica en la sección de configuración.

4. Ejecuta la aplicación desde tu IDE o con Maven:
```
./mvnw spring-boot:run
```
5. Interactúa con el menú desde la consola.

## 📚 **Fuente de datos**
Los datos de los libros provienen de la API pública de Gutendex:
👉 https://gutendex.com/
