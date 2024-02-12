package mx.com.chichen.itzamna.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tbl_propietario")
@Data
public class PropietarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_propietario")
    private Long idPropietario;

    @Column(name = "nombre_propietario")
    private String nombrePropietario;

    @Column(name = "telefono_propietario")
    private String telefonoPropietario;

    @Column(name = "segundo_telefono_propietario")
    private String segundoTelefonoPropietario;

    @Column(name = "domicilio_propietario")
    private String domicilioPropietario;

    @Column(name = "fecha_alta_propietario")
    private LocalDate fechaAltaPropietario;

    @OneToMany(mappedBy = "propietario",cascade = CascadeType.ALL)
    private List<PacienteModel> paciente;


}
