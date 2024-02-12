package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "tbl_proveedor")
@Data
public class ProveedorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Long idProveedor;

    @Column(name = "nombre_proveedor")
    private String nombreProveedor;

    @Column(name = "categoria_proveedor")
    private String categoriaProveedor;

    @Column(name = "clave_proveedor")
    private String claveProveedor;

    @Column(name = "telefono_proveedor")
    private String telefonoProveedor;

    @Column(name = "rfc_proveedor")
    private String rfcProveedor;

    @Column(name = "email_proveedor")
    private String emailProveedor;

    @OneToMany(mappedBy = "proveedor",cascade = CascadeType.ALL)
    private List<ProductoModel> producto;
}
