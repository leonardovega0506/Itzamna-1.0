package mx.com.chichen.itzamna.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mx.com.chichen.itzamna.model.dto.DetalleHistoricoDTO;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class DetalleHistoricoResponse {

    private DetalleHistoricoDTO response;
    private Integer code;
    private String message;

}
