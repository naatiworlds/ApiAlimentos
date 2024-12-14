package daw2a.alimentos.controllers;

import daw2a.alimentos.entities.Existencias;
import daw2a.alimentos.services.ExistenciasService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/existencias")
public class ExistenciasController {

    private final ExistenciasService existenciasService;

    public ExistenciasController(ExistenciasService existenciasService) {
        this.existenciasService = existenciasService;
    }

    @GetMapping
    public ResponseEntity<List<Existencias>> listarExistencias() {
        List<Existencias> existencias = existenciasService.listarExistencias();
        return ResponseEntity.ok(existencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Existencias> obtenerExistencia(@PathVariable Long id) {
        try {
            Existencias existencia = existenciasService.obtenerExistenciaPorId(id)
                    .orElseThrow(() -> new NoSuchElementException("Existencia no encontrada con id " + id));
            return ResponseEntity.ok(existencia);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Existencias> agregarExistencia(@RequestBody @Valid Existencias existencia) {
        Existencias nuevaExistencia = existenciasService.agregarExistencia(existencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaExistencia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarExistencia(@PathVariable Long id) {
        try {
            existenciasService.borrarExistencia(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Existencias> actualizarExistencia(@PathVariable Long id, @RequestBody @Valid Existencias existencia) {
        try {
            Existencias existenciaActualizada = existenciasService.actualizarExistencia(id, existencia);
            return ResponseEntity.ok(existenciaActualizada);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}