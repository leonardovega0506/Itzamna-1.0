package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tbl_proveedor")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ProveedorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Long idProveedor;

    @Column(name = "nombre_proveedor",length = 100,nullable = false)
    private String nombreProveedor;

    @Column(name = "categoria_proveedor",nullable = false, length = 40)
    private String categoriaProveedor;

    @Column(name = "clave_proveedor",nullable = false, unique = true, length = 6)
    private String claveProveedor;

    @Column(name = "telefono_proveedor",length = 15)
    private String telefonoProveedor;

    @Column(name = "rfc_proveedor",length = 15)
    private String rfcProveedor;

    @Column(name = "email_proveedor",length = 100)
    private String emailProveedor;

    @OneToMany(mappedBy = "proveedor",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ProductoModel> producto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProveedorModel that = (ProveedorModel) o;
        return getIdProveedor() != null && Objects.equals(getIdProveedor(), that.getIdProveedor());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
