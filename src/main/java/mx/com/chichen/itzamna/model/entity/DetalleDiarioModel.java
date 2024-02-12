package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_detalle_diario")
@Data
public class DetalleDiarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_diario")
    private Long idDetalleDiario;

    @Column(name = "total_detalle_venta")
    private Double totalDetalleVenta;

    @ManyToOne
    @JoinColumn(name = "venta")
    private VentaModel ventaDiario;

    @ManyToOne
    @JoinColumn(name = "compra")
    private CompraModel compraDiario;

    @ManyToOne
    @JoinColumn(name = "diario")
    private DiarioModel diario;

}
