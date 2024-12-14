package daw2a.alimentos.controllers;

import daw2a.alimentos.entities.Ubicaciones;
import daw2a.alimentos.services.UbicacionesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/ubicaciones")
public class UbicacionesController {

    private final UbicacionesService ubicacionesService;

    public UbicacionesController(UbicacionesService ubicacionesService) {
        this.ubicacionesService = ubicacionesService;
    }

    @GetMapping
    public ResponseEntity<List<Ubicaciones>> listarUbicaciones() {
        List<Ubicaciones> ubicaciones = ubicacionesService.listarUbicaciones();
        return ResponseEntity.ok(ubicaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ubicaciones> obtenerUbicacion(@PathVariable Long id) {
        try {
            Ubicaciones ubicacion = ubicacionesService.obtenerUbicacionPorId(id)
                    .orElseThrow(() -> new NoSuchElementException("Ubicación no encontrada con id " + id));
            return ResponseEntity.ok(ubicacion);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Ubicaciones> agregarUbicacion(@RequestBody @Valid Ubicaciones ubicacion) {
        Ubicaciones nuevaUbicacion = ubicacionesService.agregarUbicacion(ubicacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaUbicacion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarUbicacion(@PathVariable Long id) {
        try {
            ubicacionesService.borrarUbicacion(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ubicaciones> actualizarUbicacion(@PathVariable Long id, @RequestBody @Valid Ubicaciones ubicacion) {
        try {
            Ubicaciones ubicacionActualizada = ubicacionesService.actualizarUbicacion(id, ubicacion);
            return ResponseEntity.ok(ubicacionActualizada);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
