package daw2a.alimentos.entities;

import daw2a.alimentos.entities.enums.Estado;
import daw2a.alimentos.entities.enums.Tipo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Table(name = "alimentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alimentos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Tipo tipo;
    private Estado estado;
    private Date caducidad;
}
