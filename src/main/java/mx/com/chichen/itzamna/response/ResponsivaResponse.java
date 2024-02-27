package mx.com.chichen.itzamna.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mx.com.chichen.itzamna.model.dto.ResponsivaDTO;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ResponsivaResponse {

    private ResponsivaDTO response;
    private Integer code;
    private String message;
}
