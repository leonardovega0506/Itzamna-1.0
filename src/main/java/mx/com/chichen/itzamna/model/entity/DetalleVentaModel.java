package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "tbl_detalle_venta")
@Data
public class DetalleVentaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta_venta")
    private Long idDetalleVenta;

    @ManyToOne
    @JoinColumn(name = "id_venta")
    private VentaModel venta;

    @ManyToOne
    @JoinColumn(name = "producto")
    private ProductoModel producto;

    @Column(name = "total_detalle_venta")
    private Double totalDetalleVenta;


    @ManyToOne
    @JoinColumn(name = "servicio")
    private ServicioPacienteModel servicio;

    @ManyToOne
    @JoinColumn(name = "paciente")
    private PacienteModel paciente;

}
