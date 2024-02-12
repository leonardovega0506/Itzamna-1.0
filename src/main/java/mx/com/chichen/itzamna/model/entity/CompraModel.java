package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "tbl_compra")
@Data
public class CompraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Long idCompra;

    @Column(name = "fecha_compra")
    private LocalDate fechaCompra;

    @Column(name = "hora_compra")
    private LocalTime horaCompra;

    @Column(name = "estatus_compra")
    private String estatusCompra;

    @Column(name = "total_Compra")
    private Double totalCompra;

    @Column(name = "factura_compra")
    private String facturaCompra;

    @Column(name = "numero_factura_compra")
    private String numeroFacturaCompra;

    @Column(name = "tipo_pago_compra")
    private String tipoPagoCompra;

    @Column(name = "fecha_recepcion_compra")
    private LocalDate fechaRecepcionCompra;

    @Column(name = "hora_recepcion_compra")
    private LocalTime horaRecepcionCompra;

    @OneToMany(mappedBy = "compra",cascade = CascadeType.ALL)
    private List<DetalleCompraModel> detallesCompra;

}
