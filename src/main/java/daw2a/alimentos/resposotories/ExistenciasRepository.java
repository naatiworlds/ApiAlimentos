package daw2a.alimentos.resposotories;

import daw2a.alimentos.entities.Existencias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExistenciasRepository extends JpaRepository<Existencias, Long> {

}
