package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tbl_paciente")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PacienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Long idPaciente;

    @Column(name = "nombre_paciente",length = 100,nullable = false)
    private String nombrePaciente;

    @Column(name = "tipo_paciente",length = 30,nullable = false)
    private String tipoPaciente;

    @Column(name = "raza_paciente",length = 60)
    private String razaPaciente;

    @Column(name = "color_paciente")
    private String colorPaciente;

    @Column(name = "edad_paciente")
    private Integer edadPaciente;

    @Column(name = "sexo_paciente",nullable = false,length = 15)
    private String sexoPaciente;

    @Column(name = "medida_paciente")
    private Integer medidaPaciente;

    @Column(name = "fecha_alta_paciente",nullable = false)
    private LocalDate fechaAltaPaciente;

    @Column(name = "observaciones_paciente")
    private String observacionesPaciente;

    @Column(name = "peso_paciente",precision = 3,scale = 2)
    private Double peso_paciente;

    @ManyToOne
    @JoinColumn(name = "propietario")
    private PropietarioModel propietario;

    @OneToMany(mappedBy = "paciente",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ServicioPacienteModel> servicios;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PacienteModel that = (PacienteModel) o;
        return getIdPaciente() != null && Objects.equals(getIdPaciente(), that.getIdPaciente());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
