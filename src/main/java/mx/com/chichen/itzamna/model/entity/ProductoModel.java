package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_producto")
@Data
public class ProductoModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @Column(name = "nombre_producto")
    private String nombreProducto;

    @Column(name = "categoria_producto")
    private String categoriaProducto;

    @Column(name = "clave_producto")
    private String claveProducto;

    @Column(name = "costo_producto")
    private Double costoProducto;

    @Column(name = "precio_producto")
    private Double precioProducto;

    @Column(name = "cantidad_producto")
    private Integer cantidadProducto;

    @ManyToOne
    @JoinColumn(name = "proveedor")
    private ProveedorModel proveedor;
}
