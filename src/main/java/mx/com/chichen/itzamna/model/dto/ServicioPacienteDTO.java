package mx.com.chichen.itzamna.model.dto;

import lombok.Data;
import mx.com.chichen.itzamna.model.entity.PacienteModel;
import mx.com.chichen.itzamna.model.entity.ServicioModel;

@Data
public class ServicioPacienteDTO {

    private Long idServicioPaciente;

    private ServicioModel servicio;

    private PacienteModel paciente;
}
