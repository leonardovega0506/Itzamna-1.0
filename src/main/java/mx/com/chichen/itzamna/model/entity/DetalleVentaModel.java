package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "tbl_detalle_venta")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class DetalleVentaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_venta")
    private Long idDetalleVenta;

    @ManyToOne
    @JoinColumn(name = "id_venta",nullable = false)
    private VentaModel venta;

    @ManyToOne
    @JoinColumn(name = "producto")
    private ProductoModel producto;

    @Column(name = "total_detalle_venta",precision = 8, scale = 2,nullable = false)
    private Double totalDetalleVenta;


    @ManyToOne
    @JoinColumn(name = "servicio")
    private ServicioPacienteModel servicio;

    @ManyToOne
    @JoinColumn(name = "paciente")
    private PacienteModel paciente;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DetalleVentaModel that = (DetalleVentaModel) o;
        return getIdDetalleVenta() != null && Objects.equals(getIdDetalleVenta(), that.getIdDetalleVenta());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
