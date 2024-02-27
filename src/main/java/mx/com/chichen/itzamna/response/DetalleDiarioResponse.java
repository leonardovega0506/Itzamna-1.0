package mx.com.chichen.itzamna.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mx.com.chichen.itzamna.model.dto.DetalleDiarioDTO;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class DetalleDiarioResponse {

    private DetalleDiarioDTO response;
    private Integer code;
    private String message;
}
