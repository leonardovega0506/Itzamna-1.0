package mx.com.chichen.itzamna.model.dto;

import jakarta.persistence.Column;
import lombok.Data;
import mx.com.chichen.itzamna.model.entity.ProveedorModel;

@Data
public class ProductoDTO {

    private Long idProducto;
    private String nombreProducto;
    private String categoriaProducto;
    private String claveProducto;
    private Double costoProducto;
    private Double precioProducto;
    private Integer cantidadProducto;
    private ProveedorModel proveedor;
}
