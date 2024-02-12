package mx.com.chichen.itzamna.model.dto;

import jakarta.persistence.Column;
import lombok.Data;
import mx.com.chichen.itzamna.model.entity.PacienteModel;

import java.time.LocalDate;
import java.util.List;

@Data
public class PropietarioDTO {

    private Long idPropietario;
    private String nombrePropietario;
    private String telefonoPropietario;
    private String segundoTelefonoPropietario;
    private String domicilioPropietario;
    private LocalDate fechaAltaPropietario;
    private List<PacienteModel> paciente;
}
