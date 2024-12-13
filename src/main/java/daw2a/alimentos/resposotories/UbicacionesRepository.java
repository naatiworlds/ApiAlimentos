package daw2a.alimentos.resposotories;

import daw2a.alimentos.entities.Ubicaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbicacionesRepository extends JpaRepository<Ubicaciones, Long> {

}
