package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_servicio_paciente")
@Data
public class ServicioPacienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio_paciente")
    private Long idServicioPaciente;

    @ManyToOne
    @JoinColumn(name = "servicio")
    private ServicioModel servicio;

    @ManyToOne
    @JoinColumn(name = "paciente")
    private PacienteModel paciente;
}
