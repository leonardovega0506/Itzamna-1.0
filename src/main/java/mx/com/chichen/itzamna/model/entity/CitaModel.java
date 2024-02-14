package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "tbl_cita")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CitaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Long idCita;

    @Column(name = "fecha_cita",nullable = false)
    private LocalDate fechaCita;

    @Column(name = "hora_cita")
    private LocalTime horaCita;

    @Column(name = "estatus_cita",nullable = false,length = 40)
    private String estatusCita;

    @ManyToOne
    @JoinColumn(name = "paciente",nullable = false)
    private PacienteModel paciente;

    @ManyToOne
    @JoinColumn(name = "servicio",nullable = false)
    private ServicioModel servicio;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CitaModel citaModel = (CitaModel) o;
        return getIdCita() != null && Objects.equals(getIdCita(), citaModel.getIdCita());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
