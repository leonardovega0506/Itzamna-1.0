package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tbl_venta")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class VentaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Long idVenta;

    @Column(name = "fecha_venta",nullable = false)
    private LocalDate fechaVenta;

    @Column(name = "hora_venta",nullable = false)
    private LocalTime horaVenta;

    @Column(name = "total_venta",nullable = false,precision = 8)
    private Double totalVenta;

    @Column(name = "estatus_venta",nullable = false,length = 40)
    private String estatusVenta;

    @Column(name = "tipo_pago",nullable = false,length = 40)
    private String tipoPago;

    @OneToMany(mappedBy = "venta")
    @ToString.Exclude
    private List<DetalleVentaModel> detalleVenta;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VentaModel that = (VentaModel) o;
        return getIdVenta() != null && Objects.equals(getIdVenta(), that.getIdVenta());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
