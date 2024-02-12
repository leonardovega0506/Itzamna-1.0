package mx.com.chichen.itzamna.model.dto;

import jakarta.persistence.Column;
import lombok.Data;
import mx.com.chichen.itzamna.model.entity.PacienteModel;
import mx.com.chichen.itzamna.model.entity.ServicioModel;

import java.time.LocalDate;

@Data
public class ResponsivaDTO {

    private Long idResponsiva;
    private LocalDate fechaResponsiva;
    private String estatusResponsiva;
    private Boolean conformidadResponsiva;
    private ServicioModel servicio;
    private PacienteModel pacienteModel;
}
