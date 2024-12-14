package daw2a.alimentos.services;

import daw2a.alimentos.entities.Ubicaciones;
import daw2a.alimentos.resposotories.UbicacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UbicacionesService {
    @Autowired
    private final UbicacionesRepository ubicacionesRepository;

    public UbicacionesService(UbicacionesRepository ubicacionesRepository) {
        this.ubicacionesRepository = ubicacionesRepository;
    }

    // Listar todas las ubicaciones
    public List<Ubicaciones> listarUbicaciones() {
        return ubicacionesRepository.findAll();
    }

    // Obtener ubicación por Id
    public Optional<Ubicaciones> obtenerUbicacionPorId(Long id) {
        return ubicacionesRepository.findById(id);
    }

    // Borrar ubicación por Id
    public void borrarUbicacion(Long id) {
        if (ubicacionesRepository.existsById(id)) {
            ubicacionesRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Ubicación no encontrada");
        }
    }

    // Actualizar ubicación por Id y establecer nuevos valores
    public Ubicaciones actualizarUbicacion(Long id, Ubicaciones ubicacionActualizada) {
        return ubicacionesRepository.findById(id).map(ubicacion -> {
            Optional.ofNullable(ubicacionActualizada.getDescripcion()).ifPresent(ubicacion::setDescripcion);
            Optional.ofNullable(ubicacionActualizada.getTipoUbicacion()).ifPresent(ubicacion::setTipoUbicacion);
            Optional.ofNullable(ubicacionActualizada.getCapacidad()).ifPresent(ubicacion::setCapacidad);

            return ubicacionesRepository.save(ubicacion);
        }).orElseThrow(() -> new NoSuchElementException("Ubicación no encontrada con id " + id));
    }

    // Agregar nueva ubicación
    public Ubicaciones agregarUbicacion(Ubicaciones ubicacion) {
        return ubicacionesRepository.save(ubicacion);
    }
}
