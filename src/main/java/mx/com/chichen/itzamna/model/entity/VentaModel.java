package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "tbl_venta")
@Data
public class VentaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Long idVenta;

    @Column(name = "fecha_venta")
    private LocalDate fechaVenta;

    @Column(name = "hora_venta")
    private LocalTime horaVenta;

    @Column(name = "total_venta")
    private Double totalVenta;

    @Column(name = "estatus_venta")
    private String estatusVenta;

    @Column(name = "tipo_pago")
    private String tipoPago;

    @OneToMany(mappedBy = "venta")
    private List<DetalleVentaModel> detalleVenta;


}
