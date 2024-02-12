package mx.com.chichen.itzamna.model.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ServicioDTO {

    private Long idServicio;
    private String nombreServicio;
    private String categoriaServicio;
    private String claveServicio;
    private Double costoServicio;
    private Double precioServicio;
}
