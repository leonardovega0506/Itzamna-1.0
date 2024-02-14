package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tbl_propietario")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PropietarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_propietario")
    private Long idPropietario;

    @Column(name = "nombre_propietario",nullable = false,length = 200)
    private String nombrePropietario;

    @Column(name = "telefono_propietario",nullable = false,length = 15)
    private String telefonoPropietario;

    @Column(name = "segundo_telefono_propietario",length = 15)
    private String segundoTelefonoPropietario;

    @Column(name = "domicilio_propietario")
    private String domicilioPropietario;

    @Column(name = "fecha_alta_propietario")
    private LocalDate fechaAltaPropietario;

    @OneToMany(mappedBy = "propietario",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<PacienteModel> paciente;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PropietarioModel that = (PropietarioModel) o;
        return getIdPropietario() != null && Objects.equals(getIdPropietario(), that.getIdPropietario());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
