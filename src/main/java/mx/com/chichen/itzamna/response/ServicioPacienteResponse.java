package mx.com.chichen.itzamna.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mx.com.chichen.itzamna.model.dto.ServicioPacienteDTO;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ServicioPacienteResponse {

    private ServicioPacienteDTO content;
    private Integer code;
    private String message;
}
