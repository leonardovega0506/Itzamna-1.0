package mx.com.chichen.itzamna.model.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import mx.com.chichen.itzamna.model.entity.*;

@Data
public class DetalleVentaDTO {

    private Long idDetalleVenta;
    private Double totalDetalleVenta;
    private VentaModel venta;
    private ProductoModel producto;
    private ServicioPacienteModel servicio;
    private PacienteModel paciente;
}
