package mx.com.chichen.itzamna.service.interfaces;

import mx.com.chichen.itzamna.model.dto.DiarioDTO;
import mx.com.chichen.itzamna.response.DiarioResponse;
import mx.com.chichen.itzamna.response.ListDetalleDiarioResponse;
import mx.com.chichen.itzamna.response.ListDiarioResponse;

public interface IDiarioService {

    ListDiarioResponse findAll(int numPage, int sizePage, String orderBy, String sortDir);
    DiarioResponse findById(Long idDiario);
    DiarioResponse saveDiario(DiarioDTO diarioDTO);
    DiarioResponse updateDiario(DiarioDTO diarioDTO);
    void deleteDiario(Long idDiario);
    DiarioResponse cerrarDiario(Long idDiario);
        ListDetalleDiarioResponse findDetalleDiario(Long idDiario, int numPage, int sizePage, String orderBy, String sortDir);
}
