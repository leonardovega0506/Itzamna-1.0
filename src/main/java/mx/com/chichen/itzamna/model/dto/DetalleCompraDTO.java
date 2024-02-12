package mx.com.chichen.itzamna.model.dto;

import lombok.Data;
import mx.com.chichen.itzamna.model.entity.CompraModel;
import mx.com.chichen.itzamna.model.entity.ProductoModel;
import mx.com.chichen.itzamna.model.entity.ServicioModel;

@Data
public class DetalleCompraDTO {

    private Long idDetalleCompra;
    private CompraModel compra;
    private ProductoModel producto;
    private ServicioModel servicio;
}
