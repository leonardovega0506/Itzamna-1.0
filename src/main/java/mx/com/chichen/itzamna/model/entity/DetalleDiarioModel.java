package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "tbl_detalle_diario")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class DetalleDiarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_diario")
    private Long idDetalleDiario;

    @Column(name = "total_detalle_venta",nullable = false,precision = 8, scale = 2)
    private Double totalDetalleVenta;

    @ManyToOne
    @JoinColumn(name = "venta")
    private VentaModel ventaDiario;

    @ManyToOne
    @JoinColumn(name = "compra")
    private CompraModel compraDiario;

    @ManyToOne
    @JoinColumn(name = "diario",nullable = false)
    private DiarioModel diario;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DetalleDiarioModel that = (DetalleDiarioModel) o;
        return getIdDetalleDiario() != null && Objects.equals(getIdDetalleDiario(), that.getIdDetalleDiario());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
