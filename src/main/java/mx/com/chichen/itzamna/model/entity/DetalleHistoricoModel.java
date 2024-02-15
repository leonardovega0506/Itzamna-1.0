package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "tbl_detalle_historico")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class DetalleHistoricoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_diario")
    private Long idDetalleDiario;

    @Column(name = "total_detalle_historico",nullable = false,precision = 8)
    private Double totalDetalleHistorico;

    @ManyToOne
    @JoinColumn(name = "compra")
    private CompraModel compraHistorico;

    @ManyToOne
    @JoinColumn(name = "venta")
    private VentaModel ventaHistorico;

    @ManyToOne
    @JoinColumn(name = "historico",nullable = false)
    private HistoricoModel historico;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DetalleHistoricoModel that = (DetalleHistoricoModel) o;
        return getIdDetalleDiario() != null && Objects.equals(getIdDetalleDiario(), that.getIdDetalleDiario());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
