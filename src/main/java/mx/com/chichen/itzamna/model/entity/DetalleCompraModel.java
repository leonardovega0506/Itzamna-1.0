package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_detalle_compra")
@Data
public class DetalleCompraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_compra")
    private Long idDetalleCompra;

    @ManyToOne
    @JoinColumn(name = "compra")
    private CompraModel compra;

    @ManyToOne
    @JoinColumn(name = "producto")
    private ProductoModel producto;

    @ManyToOne
    @JoinColumn(name = "servicio")
    private ServicioModel servicio;

}
