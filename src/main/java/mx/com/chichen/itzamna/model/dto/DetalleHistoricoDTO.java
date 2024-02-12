package mx.com.chichen.itzamna.model.dto;

import jakarta.persistence.Column;
import lombok.Data;
import mx.com.chichen.itzamna.model.entity.CompraModel;
import mx.com.chichen.itzamna.model.entity.HistoricoModel;
import mx.com.chichen.itzamna.model.entity.VentaModel;

@Data
public class DetalleHistoricoDTO {

    private Long idDetalleDiario;
    private Double totalDetalleHistorico;
    private CompraModel compraHistorico;
    private VentaModel ventaHistorico;
    private HistoricoModel historico;

}
