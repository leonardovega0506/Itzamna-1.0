package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tbl_responsiva")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ResponsivaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_responsiva")
    private Long idResponsiva;

    @Column(name = "fecha_responsiva",nullable = false)
    private LocalDate fechaResponsiva;

    @Column(name = "estatus_responsiva",nullable = false,length = 40)
    private String estatusResponsiva;

    @Column(name = "conformidad_Responsiva")
    private Boolean conformidadResponsiva;

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
        ResponsivaModel that = (ResponsivaModel) o;
        return getIdResponsiva() != null && Objects.equals(getIdResponsiva(), that.getIdResponsiva());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
