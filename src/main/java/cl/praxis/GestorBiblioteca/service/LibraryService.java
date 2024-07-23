package cl.praxis.GestorBiblioteca.service;

import cl.praxis.GestorBiblioteca.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LibraryService {

    private static final Logger logger = LoggerFactory.getLogger(LibraryService.class);
    private Map<String, Book> books = new HashMap<>();

    public LibraryService() {
        books.put("1", new Book("1", "La casa de los espíritus", "Isabel Allende", true));
        books.put("2", new Book("2", "Cien años de soledad", "Gabriel García Márquez", true));
    }

    public String buscarLibro(String id) {
        if (books.containsKey(id)) {
            Book book = books.get(id);
            logger.info("Consulta de libro exitosa: {}", book.getTitle());
            return book.getDetails();
        } else {
            logger.info("Consulta de libro fallida: No se encontró el libro con ID {}", id);
            return "Libro no encontrado";
        }
    }

    public String registrarPrestamo(String libroId, String usuarioId) {
        if (books.containsKey(libroId) && books.get(libroId).isAvailable()) {
            books.get(libroId).setAvailable(false);
            logger.info("Préstamo registrado para el libro: {}, usuario: {}", books.get(libroId).getTitle(), usuarioId);
            return "Préstamo registrado con éxito para el libro: " + books.get(libroId).getTitle();
        } else {
            logger.info("Error en el registro de préstamo: El libro con ID {} no está disponible", libroId);
            return "Error: El libro no está disponible";
        }
    }

    public String registrarDevolucion(String libroId, String usuarioId) {
        if (books.containsKey(libroId) && !books.get(libroId).isAvailable()) {
            books.get(libroId).setAvailable(true);
            logger.info("Devolución registrada para el libro: {}, usuario: {}", books.get(libroId).getTitle(), usuarioId);
            return "Devolución registrada con éxito para el libro: " + books.get(libroId).getTitle();
        } else {
            logger.info("Error en el registro de devolución: El libro con ID {} no estaba prestado", libroId);
            return "Error: El libro no estaba prestado";
        }
    }
}
