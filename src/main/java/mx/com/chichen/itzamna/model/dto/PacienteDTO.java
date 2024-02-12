package mx.com.chichen.itzamna.model.dto;

import jakarta.persistence.Column;
import lombok.Data;
import mx.com.chichen.itzamna.model.entity.PropietarioModel;
import mx.com.chichen.itzamna.model.entity.ServicioPacienteModel;

import java.time.LocalDate;
import java.util.List;

@Data
public class PacienteDTO {

    private Long idPaciente;
    private String nombrePaciente;
    private String tipoPaciente;
    private String razaPaciente;
    private String colorPaciente;
    private Integer edadPaciente;
    private String sexoPaciente;
    private Integer medidaPaciente;
    private LocalDate fechaAltaPaciente;
    private String observacionesPaciente;
    private Double peso_paciente;
    private PropietarioModel propietario;
    private List<ServicioPacienteModel> servicios;

}
