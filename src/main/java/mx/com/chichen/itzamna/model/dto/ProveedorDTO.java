package mx.com.chichen.itzamna.model.dto;

import jakarta.persistence.Column;
import lombok.Data;
import mx.com.chichen.itzamna.model.entity.ProductoModel;

import java.util.List;

@Data
public class ProveedorDTO {

    private Long idProveedor;
    private String nombreProveedor;
    private String categoriaProveedor;
    private String claveProveedor;
    private String telefonoProveedor;
    private String rfcProveedor;
    private String emailProveedor;
    private List<ProductoModel> producto;
}
