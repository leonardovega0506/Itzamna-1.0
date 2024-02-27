package mx.com.chichen.itzamna.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mx.com.chichen.itzamna.model.dto.DetalleVentaDTO;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class DetalleVentaResponse {

    private DetalleVentaDTO response;
    private Integer code;
    private String message;
}
