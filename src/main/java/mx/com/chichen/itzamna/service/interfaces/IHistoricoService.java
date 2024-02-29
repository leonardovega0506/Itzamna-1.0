package mx.com.chichen.itzamna.service.interfaces;

import mx.com.chichen.itzamna.model.dto.DiarioDTO;
import mx.com.chichen.itzamna.model.dto.HistoricoDTO;
import mx.com.chichen.itzamna.response.HistoricoResponse;
import mx.com.chichen.itzamna.response.ListDetalleHistoricoResponse;
import mx.com.chichen.itzamna.response.ListHistoricoResponse;

import java.time.LocalDate;

public interface IHistoricoService {

    ListHistoricoResponse findAllHistorico(int numPage, int sizePage, String orderBy, String sortDir);
    ListHistoricoResponse findAllByDate(int numPage, int sizePage, String orderBy, String sortDir, LocalDate dia);
    ListHistoricoResponse findAllByMonth(int numPage, int sizePage, String orderBy, String sortDir,int month);
    ListHistoricoResponse findAllByWeek(int numPage, int sizePage, String orderBy, String sortDir, int week);
    HistoricoResponse findByHistorico(Long idHistorico);
    HistoricoResponse saveHistorico(HistoricoDTO historicoDTO);
    ListDetalleHistoricoResponse findAllDetallesHistorico(int numPage, int sizePage, String orderBy, String sortDir,Long idHistorico);

}
