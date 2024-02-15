package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tbl_compra")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CompraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Long idCompra;

    @Column(name = "fecha_compra",nullable = false)
    private LocalDate fechaCompra;

    @Column(name = "hora_compra")
    private LocalTime horaCompra;

    @Column(name = "estatus_compra",nullable = false,length = 40)
    private String estatusCompra;

    @Column(name = "total_Compra",nullable = false,precision = 8)
    private Double totalCompra;

    @Column(name = "factura_compra",nullable = false)
    private boolean facturaCompra;

    @Column(name = "numero_factura_compra",length = 100,unique = true)
    private String numeroFacturaCompra;

    @Column(name = "tipo_pago_compra",nullable = false,length = 40)
    private String tipoPagoCompra;

    @Column(name = "fecha_recepcion_compra")
    private LocalDate fechaRecepcionCompra;

    @Column(name = "hora_recepcion_compra")
    private LocalTime horaRecepcionCompra;

    @OneToMany(mappedBy = "compra",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<DetalleCompraModel> detallesCompra;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CompraModel that = (CompraModel) o;
        return getIdCompra() != null && Objects.equals(getIdCompra(), that.getIdCompra());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
