package mx.com.chichen.itzamna.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import mx.com.chichen.itzamna.model.entity.DetalleVentaModel;
import mx.com.chichen.itzamna.model.entity.PacienteModel;
import mx.com.chichen.itzamna.model.entity.ServicioModel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class VentaDTO {

    private Long idVenta;
    private LocalDate fechaVenta;
    private LocalTime horaVenta;
    private Double totalVenta;
    private String estatusVenta;
    private String tipoPago;
    private List<DetalleVentaModel> detalleVenta;
}
