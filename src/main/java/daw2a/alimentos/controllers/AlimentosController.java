package daw2a.alimentos.controllers;

import daw2a.alimentos.entities.Alimentos;
import daw2a.alimentos.services.AlimentosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("alimentos")
@CrossOrigin
public class AlimentosController {
    private final AlimentosService alimentosService;

    public AlimentosController(AlimentosService alimentosService) {
        this.alimentosService = alimentosService;
    }

    @GetMapping
    public ResponseEntity<List<Alimentos>> listarAlimentos() {
        List<Alimentos> beers = alimentosService.listarAlimentos();
        return ResponseEntity.ok(beers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alimentos> obtenerAlimento(@PathVariable Long id) {
        try {
            Alimentos alimentos = alimentosService.obtenerAlimentoPorId(id)
                    .orElseThrow(() -> new NoSuchElementException("Cerveza no encontrada con id " + id));
            return ResponseEntity.ok(alimentos);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Alimentos> agregarAlimento(@RequestBody @Valid Alimentos beer) {
        Alimentos nuevoAlimento = alimentosService.agregarAlimento(beer);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAlimento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Alimentos> borrarAlimento(@PathVariable Long id) {
        try {
            alimentosService.borrarAlimento(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alimentos> actualizarCerveza(@PathVariable Long id, @RequestBody @Valid Alimentos beer) {
        try {
            Alimentos alimentoActualizado = alimentosService.actualizarAlimento(id, beer);
            return ResponseEntity.ok(alimentoActualizado);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
