package daw2a.alimentos.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "existencias")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Existencias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "alimento_id", nullable = false)
    private Alimentos alimento;

    @ManyToOne
    @JoinColumn(name = "ubicacion_id", nullable = false)
    private Ubicaciones ubicacion;

    @Column(nullable = false)
    private int cantidad;

    @Column(name = "fecha_entrada", nullable = false)
    private Date fechaEntrada;
}
