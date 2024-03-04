package mx.com.chichen.itzamna.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mx.com.chichen.itzamna.model.dto.CitaDTO;
import mx.com.chichen.itzamna.model.dto.VentaDTO;

import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ListVentaResponse {

    private List<VentaDTO> content;
    private Integer code;
    private String message;
    private Integer numPage;
    private Integer sizePage;
    private Long totalElements;
    private Integer totalPages;
    private Boolean isLast;
}
