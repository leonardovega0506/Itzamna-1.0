package mx.com.chichen.itzamna.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mx.com.chichen.itzamna.model.dto.HistoricoDTO;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class HistoricoResponse {

    private HistoricoDTO response;
    private Integer code;
    private String message;
}
