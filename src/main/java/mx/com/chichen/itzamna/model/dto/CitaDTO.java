package mx.com.chichen.itzamna.model.dto;

import jakarta.persistence.Column;
import lombok.Data;
import mx.com.chichen.itzamna.model.entity.PacienteModel;
import mx.com.chichen.itzamna.model.entity.ServicioModel;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CitaDTO {

    private Long idCita;
    private LocalDate fechaCita;
    private String horaCita;
    private String estatusCita;
    private PacienteModel paciente;
    private ServicioModel servicio;
}
