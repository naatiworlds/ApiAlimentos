package daw2a.alimentos.resposotories;

import daw2a.alimentos.entities.Alimentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlimentoRepository extends JpaRepository<Alimentos, Long> {

}
