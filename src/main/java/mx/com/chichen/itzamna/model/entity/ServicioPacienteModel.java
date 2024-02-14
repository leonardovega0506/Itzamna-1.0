package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "tbl_servicio_paciente")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ServicioPacienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio_paciente")
    private Long idServicioPaciente;

    @ManyToOne
    @JoinColumn(name = "servicio",nullable = false)
    private ServicioModel servicio;

    @ManyToOne
    @JoinColumn(name = "paciente",nullable = false)
    private PacienteModel paciente;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ServicioPacienteModel that = (ServicioPacienteModel) o;
        return getIdServicioPaciente() != null && Objects.equals(getIdServicioPaciente(), that.getIdServicioPaciente());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
