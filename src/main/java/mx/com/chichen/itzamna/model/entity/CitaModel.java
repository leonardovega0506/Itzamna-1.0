package mx.com.chichen.itzamna.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tbl_cita")
@Data
public class CitaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Long idCita;

    @Column(name = "fecha_cita")
    private LocalDate fechaCita;

    @Column(name = "hora_cita")
    private LocalTime horaCita;

    @Column(name = "estatus_cita")
    private String estatusCita;

    @ManyToOne
    @JoinColumn(name = "paciente")
    private PacienteModel paciente;

    @ManyToOne
    @JoinColumn(name = "servicio")
    private ServicioModel servicio;
}
