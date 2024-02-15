package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "tbl_producto")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ProductoModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @Column(name = "nombre_producto",length = 100,nullable = false)
    private String nombreProducto;

    @Column(name = "categoria_producto",length = 40,nullable = false)
    private String categoriaProducto;

    @Column(name = "clave_producto",length = 6,nullable = false,unique = true)
    private String claveProducto;

    @Column(name = "costo_producto",precision = 8, nullable = false)
    private Double costoProducto;

    @Column(name = "precio_producto",nullable = false,precision = 8)
    private Double precioProducto;

    @Column(name = "cantidad_producto",nullable = false)
    private Integer cantidadProducto;

    @ManyToOne
    @JoinColumn(name = "proveedor")
    private ProveedorModel proveedor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductoModel that = (ProductoModel) o;
        return getIdProducto() != null && Objects.equals(getIdProducto(), that.getIdProducto());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
