package mx.com.chichen.itzamna.model.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;
import mx.com.chichen.itzamna.model.entity.DetalleDiarioModel;

import java.time.LocalDate;
import java.util.List;

@Data
public class DiarioDTO {

    private Long idDiario;
    private Double totalDiario;
    private LocalDate fechaDiario;
    private String estatusDiario;
    List<DetalleDiarioModel> detalleDiario;
}
