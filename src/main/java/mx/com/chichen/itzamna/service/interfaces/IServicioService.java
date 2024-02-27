package mx.com.chichen.itzamna.service.interfaces;

import mx.com.chichen.itzamna.model.dto.ServicioDTO;
import mx.com.chichen.itzamna.response.ListServicioResponse;
import mx.com.chichen.itzamna.response.ServicioResponse;

public interface IServicioService {

    ListServicioResponse findAllPaciente(int numPage, int sizePage, String orderBy, String sortDir);
    ListServicioResponse findAllPacienteByClave(int numPage, int sizePage, String orderBy, String sortDir,String clave);
    ServicioResponse saveServicio(ServicioDTO servicioDTO);
    ServicioResponse updateServicio(ServicioDTO servicioDTO);
    ServicioResponse deleteService(Long idServicio);

}
