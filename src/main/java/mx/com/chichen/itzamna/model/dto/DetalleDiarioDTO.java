package mx.com.chichen.itzamna.model.dto;

import jakarta.persistence.Column;
import lombok.Data;
import mx.com.chichen.itzamna.model.entity.CompraModel;
import mx.com.chichen.itzamna.model.entity.DiarioModel;
import mx.com.chichen.itzamna.model.entity.ServicioModel;
import mx.com.chichen.itzamna.model.entity.VentaModel;

@Data
public class DetalleDiarioDTO {

    private Long idDetalleDiario;
    private Double totalDetalleVenta;
    private VentaModel ventaDiario;
    private CompraModel compraDiario;
    private DiarioModel diario;

}
