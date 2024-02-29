package mx.com.chichen.itzamna.model.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;
import mx.com.chichen.itzamna.model.entity.DetalleHistoricoModel;

import java.time.LocalDate;
import java.util.List;

@Data
public class HistoricoDTO {

    private Long idHistorico;
    private Double totalHistorico;
    private LocalDate fechaHistorico;
    private Integer semanaHistorico;
    private String estatusHistorico;
    private List<DetalleHistoricoModel> detalleHistorico;
}
