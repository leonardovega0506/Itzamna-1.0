package mx.com.chichen.itzamna.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mx.com.chichen.itzamna.model.dto.CompraDTO;

import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ListCompraResponse {

    private List<CompraDTO> content;
    private Integer code;
    private String message;
    private Integer numPage;
    private Integer sizePage;
    private Long totalElements;
    private Integer totalPages;
    private Boolean isLast;

}