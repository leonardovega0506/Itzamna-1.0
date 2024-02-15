package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "tbl_servicio")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ServicioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Long idServicio;

    @Column(name = "nombre_servicio",nullable = false,length = 100)
    private String nombreServicio;

    @Column(name = "categoria_servicio",nullable = false,length = 40)
    private String categoriaServicio;

    @Column(name = "clave_servicio",nullable = false,unique = true,length = 6)
    private String claveServicio;

    @Column(name = "costo_servicio",precision = 8)
    private Double costoServicio;

    @Column(name = "precio_servicio",precision = 8)
    private Double precioServicio;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ServicioModel that = (ServicioModel) o;
        return getIdServicio() != null && Objects.equals(getIdServicio(), that.getIdServicio());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
