package daw2a.alimentos.services;

import daw2a.alimentos.entities.Alimentos;
import daw2a.alimentos.resposotories.AlimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AlimentosService {
    @Autowired
    private final AlimentoRepository alimentoRepository;

    public AlimentosService(AlimentoRepository alimentoRepository) {
        this.alimentoRepository = alimentoRepository;
    }

    // Listar todos los alimentos
    public List<Alimentos> listarAlimentos() {
        return alimentoRepository.findAll();
    }

    // Obtener alimento por Id
    public Optional<Alimentos> obtenerAlimentoPorId(Long id) {
        return alimentoRepository.findById(id);
    }

    // Borrar alimento por Id
    public void borrarAlimento(Long id) {
        if (alimentoRepository.existsById(id)) {
            alimentoRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Alimento no encontrado");
        }
    }

    // Actualizar alimento por Id y establecer nuevos valores por separado.
    public Alimentos actualizarAlimento(Long id, Alimentos alimentoActualizada) {
        return alimentoRepository.findById(id).map(alimento -> {
            Optional.of(alimentoActualizada.getNombre()).ifPresent(alimento::setNombre);
            Optional.of(alimentoActualizada.getTipo()).ifPresent(alimento::setTipo);
            Optional.of(alimentoActualizada.getEstado()).ifPresent(alimento::setEstado);
            Optional.of(alimentoActualizada.getCaducidad()).ifPresent(alimento::setCaducidad);

            return alimentoRepository.save(alimento);
        }).orElseThrow(() -> new NoSuchElementException("Alimento no encontrado con id " + id));
    }
    // Agregar alimento
    public Alimentos agregarAlimento(Alimentos alimento) {
        return alimentoRepository.save(alimento);
    }
}
