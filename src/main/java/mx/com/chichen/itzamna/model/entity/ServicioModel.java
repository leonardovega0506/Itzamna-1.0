package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_servicio")
@Data
public class ServicioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Long idServicio;

    @Column(name = "nombre_servicio")
    private String nombreServicio;

    @Column(name = "categoria_servicio")
    private String categoriaServicio;

    @Column(name = "clave_servicio")
    private String claveServicio;

    @Column(name = "costo_servicio")
    private Double costoServicio;

    @Column(name = "precio_servicio")
    private Double precioServicio;
}
