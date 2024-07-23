package cl.praxis.GestorBiblioteca.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import cl.praxis.GestorBiblioteca.service.LibraryService;

@RestController
@RequestMapping("/biblioteca")
public class AccionController {

    private static final Logger logger = LoggerFactory.getLogger(AccionController.class);
    private final LibraryService libraryService;

    public AccionController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/buscar")
    public String buscarLibro(@RequestParam String id) {
        String resultado = libraryService.buscarLibro(id);
        logger.info("Búsqueda de libro con ID: {}", id);
        return resultado;
    }

    @PostMapping("/prestamo")
    public String registrarPrestamo(@RequestParam String libroId, @RequestParam String usuarioId) {
        String resultado = libraryService.registrarPrestamo(libroId, usuarioId);
        logger.info("Préstamo registrado para el libro con ID {} por el usuario {}", libroId, usuarioId);
        return resultado;
    }

    @PostMapping("/devolucion")
    public String registrarDevolucion(@RequestParam String libroId, @RequestParam String usuarioId) {
        String resultado = libraryService.registrarDevolucion(libroId, usuarioId);
        logger.info("Devolución registrada para el libro con ID {} por el usuario {}", libroId, usuarioId);
        return resultado;
    }
}
