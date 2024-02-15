package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tbl_diario")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class DiarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_diario")
    private Long idDiario;

    @Column(name = "total_diario",nullable = false,precision = 8)
    private Double totalDiario;

    @Column(name = "fecha_diario")
    private LocalDate fechaDiario;

    @Column(name = "estatus_diario",length = 40,nullable = false)
    private String estatusDiario;

    @OneToMany(mappedBy = "diario",cascade = CascadeType.ALL)
    @ToString.Exclude
    List<DetalleDiarioModel> detalleDiario;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DiarioModel that = (DiarioModel) o;
        return getIdDiario() != null && Objects.equals(getIdDiario(), that.getIdDiario());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
