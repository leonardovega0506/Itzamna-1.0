package mx.com.chichen.itzamna.service.interfaces;

import mx.com.chichen.itzamna.model.dto.ProveedorDTO;
import mx.com.chichen.itzamna.response.ListProveedorResponse;
import mx.com.chichen.itzamna.response.ProveedorResponse;

public interface IProveedorService {

    ListProveedorResponse findAllProveedor(int numPage, int sizePage, String orderBy, String sortDir);
    ListProveedorResponse findAllProveedorByNombre(int numPage, int sizePage, String orderBy, String sortDir,String nombre);
    ProveedorResponse saveProveedor(ProveedorDTO proveedorDTO);
    ProveedorResponse findById(Long idProveedor);
    ProveedorResponse updateProveedor(ProveedorDTO proveedorDTO);
    void sendMessageProveedor(String mensaje, Long idProveedor);
    void deleteProveedor(Long idProveedor);
}
