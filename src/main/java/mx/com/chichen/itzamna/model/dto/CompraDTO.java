package mx.com.chichen.itzamna.model.dto;

import jakarta.persistence.Column;
import lombok.Data;
import mx.com.chichen.itzamna.model.entity.DetalleCompraModel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class CompraDTO {

    private Long idCompra;
    private LocalDate fechaCompra;
    private LocalTime horaCompra;
    private String estatusCompra;
    private Double totalCompra;
    private String facturaCompra;
    private String numeroFacturaCompra;
    private String tipoPagoCompra;
    private LocalDate fechaRecepcionCompra;
    private LocalTime horaRecepcionCompra;
    private List<DetalleCompraModel> detallesCompra;
}
