package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tbl_diario")
@Data
public class DiarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_diario")
    private Long idDiario;

    @Column(name = "total_diario")
    private Double totalDiario;

    @Column(name = "fecha_diario")
    private LocalDate fechaDiario;

    @Column(name = "estatus_diario")
    private String estatusDiario;

    @OneToMany(mappedBy = "diario",cascade = CascadeType.ALL)
    List<DetalleDiarioModel> detalleDiario;
}
