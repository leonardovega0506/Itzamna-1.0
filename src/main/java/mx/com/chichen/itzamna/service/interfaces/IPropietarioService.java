package mx.com.chichen.itzamna.service.interfaces;

import mx.com.chichen.itzamna.model.dto.PropietarioDTO;
import mx.com.chichen.itzamna.response.ListPropietarioResponse;
import mx.com.chichen.itzamna.response.PropietarioResponse;

public interface IPropietarioService {

    ListPropietarioResponse findAll(int numPage, int sizePage, String orderBy, String sortDir);
    ListPropietarioResponse findAllByNombre(int numPage, int sizePage, String orderBy, String sortDir, String nombre);
    PropietarioResponse savePropietario(PropietarioDTO propietarioDTO);
    PropietarioResponse findById(Long idPropietario);
    PropietarioResponse updatePropietario(PropietarioDTO propietarioDTO);
    void deletePropietario(Long idPropietario);
}
