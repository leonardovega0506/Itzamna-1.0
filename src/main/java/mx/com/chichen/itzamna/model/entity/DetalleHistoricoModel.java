package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_detalle_historico")
@Data
public class DetalleHistoricoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_diario")
    private Long idDetalleDiario;

    @Column(name = "total_detalle_historico")
    private Double totalDetalleHistorico;

    @ManyToOne
    @JoinColumn(name = "compra")
    private CompraModel compraHistorico;

    @ManyToOne
    @JoinColumn(name = "venta")
    private VentaModel ventaHistorico;

    @ManyToOne
    @JoinColumn(name = "historico")
    private HistoricoModel historico;
}
