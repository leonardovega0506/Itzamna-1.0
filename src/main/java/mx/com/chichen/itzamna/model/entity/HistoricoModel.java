package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tbl_historico")
@Data
public class HistoricoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historico")
    private Long idHistorico;

    @Column(name = "total_historico")
    private Double totalHistorico;

    @Column(name = "estatus_historico")
    private String estatusHistorico;

    @Column(name = "fecha_historico")
    private LocalDate fechaHistorico;

    @OneToMany(mappedBy = "historico",cascade = CascadeType.ALL)
    private List<DetalleHistoricoModel> detalleHistorico;
}
