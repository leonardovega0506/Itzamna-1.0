package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_responsiva")
@Data
public class ResponsivaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_responsiva")
    private Long idResponsiva;

    @Column(name = "fecha_responsiva")
    private LocalDate fechaResponsiva;

    @Column(name = "estatus_responsiva")
    private String estatusResponsiva;

    @Column(name = "conformidad_Responsiva")
    private Boolean conformidadResponsiva;

    @ManyToOne
    @JoinColumn(name = "servicio")
    private ServicioModel servicio;

    @ManyToOne
    @JoinColumn(name = "paciente")
    private PacienteModel paciente;


}
