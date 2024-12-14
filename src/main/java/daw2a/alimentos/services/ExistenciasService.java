package daw2a.alimentos.services;

import daw2a.alimentos.entities.Existencias;
import daw2a.alimentos.resposotories.ExistenciasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ExistenciasService {
    @Autowired
    private final ExistenciasRepository existenciasRepository;

    public ExistenciasService(ExistenciasRepository existenciasRepository) {
        this.existenciasRepository = existenciasRepository;
    }

    // Listar todas las existencias
    public List<Existencias> listarExistencias() {
        return existenciasRepository.findAll();
    }

    // Obtener existencia por Id
    public Optional<Existencias> obtenerExistenciaPorId(Long id) {
        return existenciasRepository.findById(id);
    }

    // Borrar existencia por Id
    public void borrarExistencia(Long id) {
        if (existenciasRepository.existsById(id)) {
            existenciasRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Existencia no encontrada");
        }
    }

    // Actualizar existencia por Id y establecer nuevos valores
    public Existencias actualizarExistencia(Long id, Existencias existenciaActualizada) {
        return existenciasRepository.findById(id).map(existencia -> {
            Optional.ofNullable(existenciaActualizada.getAlimento_id()).ifPresent(existencia::setAlimento_id);
            Optional.ofNullable(existenciaActualizada.getUbicaciones_id()).ifPresent(existencia::setUbicaciones_id);
            Optional.ofNullable(existenciaActualizada.getCantidad()).ifPresent(existencia::setCantidad);
            Optional.ofNullable(existenciaActualizada.getFecha_entrada()).ifPresent(existencia::setFecha_entrada);

            return existenciasRepository.save(existencia);
        }).orElseThrow(() -> new NoSuchElementException("Existencia no encontrada con id " + id));
    }

    // Agregar nueva existencia
    public Existencias agregarExistencia(Existencias existencia) {
        return existenciasRepository.save(existencia);
    }
}
