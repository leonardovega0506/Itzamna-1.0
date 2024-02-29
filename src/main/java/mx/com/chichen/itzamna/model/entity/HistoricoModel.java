package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tbl_historico")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class HistoricoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historico")
    private Long idHistorico;

    @Column(name = "total_historico",nullable = false,precision = 8)
    private Double totalHistorico;

    @Column(name = "semana_historico")
    private Integer semanaHistorico;

    @Column(name = "estatus_historico")
    private String estatusHistorico;

    @Column(name = "fecha_historico")
    private LocalDate fechaHistorico;

    @OneToMany(mappedBy = "historico",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<DetalleHistoricoModel> detalleHistorico;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        HistoricoModel that = (HistoricoModel) o;
        return getIdHistorico() != null && Objects.equals(getIdHistorico(), that.getIdHistorico());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
