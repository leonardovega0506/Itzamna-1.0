package mx.com.chichen.itzamna.service.interfaces;

import mx.com.chichen.itzamna.model.dto.ResponsivaDTO;
import mx.com.chichen.itzamna.response.ListResponsivaResponse;
import mx.com.chichen.itzamna.response.ResponsivaResponse;

public interface IResponsivaService {

    ListResponsivaResponse findAllResponsiva(int numPage, int sizePage, String orderBy, String sortDir);
    ListResponsivaResponse findAllResponsivaByPaciente(int numPage, int sizePage, String orderBy, String sortDir, Long idPaciente);
    ListResponsivaResponse findAllResponsivaByCategoria(int numPage, int sizePage, String orderBy, String sortDir,String categoria);
    ResponsivaResponse saveResponsiva(ResponsivaDTO responsivaDTO);
    void deleteResponsiva(Long idResponsiva);

}
