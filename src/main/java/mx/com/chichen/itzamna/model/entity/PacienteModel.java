package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tbl_paciente")
@Data
public class PacienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Long idPaciente;

    @Column(name = "nombre_paciente")
    private String nombrePaciente;

    @Column(name = "tipo_paciente")
    private String tipoPaciente;

    @Column(name = "raza_paciente")
    private String razaPaciente;

    @Column(name = "color_paciente")
    private String colorPaciente;

    @Column(name = "edad_paciente")
    private Integer edadPaciente;

    @Column(name = "sexo_paciente")
    private String sexoPaciente;

    @Column(name = "medida_paciente")
    private Integer medidaPaciente;

    @Column(name = "fecha_alta_paciente")
    private LocalDate fechaAltaPaciente;

    @Column(name = "observaciones_paciente")
    private String observacionesPaciente;

    @Column(name = "peso_paciente")
    private Double peso_paciente;

    @ManyToOne
    @JoinColumn(name = "propietario")
    private PropietarioModel propietario;

    @OneToMany(mappedBy = "paciente",cascade = CascadeType.ALL)
    private List<ServicioPacienteModel> servicios;
}
