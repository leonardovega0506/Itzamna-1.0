package mx.com.chichen.itzamna.service.interfaces;

import mx.com.chichen.itzamna.model.dto.PacienteDTO;
import mx.com.chichen.itzamna.response.ListPacienteResponse;
import mx.com.chichen.itzamna.response.ListServicioPacienteResponse;
import mx.com.chichen.itzamna.response.PacienteResponse;

public interface IPacienteService {

    ListPacienteResponse findAllPaciente(int numPage, int sizePage, String orderBy, String sortDir);
    ListPacienteResponse findAllPacienteByNombre(int numPage, int sizePage, String orderBy, String sortDir,String nombre);
    PacienteResponse savePaciente(PacienteDTO pacienteDTO);
    PacienteResponse findById(Long idPaciente);
    PacienteResponse updatePaciente(PacienteDTO pacienteDTO);
    PacienteResponse deletePaciente(Long idPaciente);
    ListServicioPacienteResponse findServiciosPaciente(Long idPaciente);
    PacienteResponse assingPaciente(Long idPropietario);
    PacienteResponse assignServicio(Long idServicio);
    PacienteResponse deleteServicio(Long idServicio);

}
