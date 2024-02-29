package mx.com.chichen.itzamna.service.implementation;

import lombok.RequiredArgsConstructor;
import mx.com.chichen.itzamna.model.dto.DetalleHistoricoDTO;
import mx.com.chichen.itzamna.model.dto.DiarioDTO;
import mx.com.chichen.itzamna.model.dto.HistoricoDTO;
import mx.com.chichen.itzamna.model.entity.DetalleHistoricoModel;
import mx.com.chichen.itzamna.model.entity.HistoricoModel;
import mx.com.chichen.itzamna.repositories.IDetalleHistoricoRepository;
import mx.com.chichen.itzamna.repositories.IHistoricoRepository;
import mx.com.chichen.itzamna.response.HistoricoResponse;
import mx.com.chichen.itzamna.response.ListDetalleHistoricoResponse;
import mx.com.chichen.itzamna.response.ListHistoricoResponse;
import mx.com.chichen.itzamna.service.interfaces.IHistoricoService;
import mx.com.chichen.itzamna.util.MapperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HistoricoServiceImpl implements IHistoricoService {

    @Autowired
    private final IHistoricoRepository iHistorico;

    @Autowired
    private final IDetalleHistoricoRepository iDetalleH;

    @Autowired
    private MapperServiceImpl modelMapper;

    @Override
    public ListHistoricoResponse findAllHistorico(int numPage, int sizePage, String orderBy, String sortDir) {
        ListHistoricoResponse response = new ListHistoricoResponse();
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage, sizePage, sort);
        Page<HistoricoModel> historicos = iHistorico.findAll(pageable);
        List<HistoricoModel> listaHistorico= historicos.getContent();
        List<HistoricoDTO> contenido = listaHistorico.stream().map((historico -> modelMapper.mapear(historico, HistoricoDTO.class))).collect(Collectors.toList());
        if (contenido.size() > 0) {
            response.setCode(0);
            response.setMessage("Response exitoso");
        } else {
            response.setCode(1);
            response.setMessage("Not Found");
        }
        response.setContent(contenido);
        response.setNumPage(historicos.getNumber());
        response.setSizePage(historicos.getSize());
        response.setTotalElements(historicos.getTotalElements());
        response.setTotalPages(historicos.getTotalPages());
        response.setIsLast(historicos.isLast());
        return response;
    }

    @Override
    public ListHistoricoResponse findAllByDate(int numPage, int sizePage, String orderBy, String sortDir, LocalDate dia) {
        ListHistoricoResponse response = new ListHistoricoResponse();
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage, sizePage, sort);
        Page<HistoricoModel> historicos = iHistorico.findAll(pageable);
        List<HistoricoModel> listaHistorico = historicos.getContent();
        List<HistoricoDTO> contenido = listaHistorico.stream().map((historico -> modelMapper.mapear(historico,HistoricoDTO.class))).collect(Collectors.toList());
        if (contenido.size() > 0) {
            response.setCode(0);
            response.setMessage("Response exitoso");
        } else {
            response.setCode(1);
            response.setMessage("Not Found");
        }
        response.setContent(contenido);
        response.setNumPage(historicos.getNumber());
        response.setSizePage(historicos.getSize());
        response.setTotalElements(historicos.getTotalElements());
        response.setTotalPages(historicos.getTotalPages());
        response.setIsLast(historicos.isLast());
        return response;
    }

    @Override
    public ListHistoricoResponse findAllByMonth(int numPage, int sizePage, String orderBy, String sortDir, int month) {
        ListHistoricoResponse response = new ListHistoricoResponse();
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage, sizePage, sort);
        Page<HistoricoModel> historicos = iHistorico.findByMes(month,pageable);
        List<HistoricoModel> listaHistorico = historicos.getContent();
        List<HistoricoDTO> contenido = listaHistorico.stream().map((historico -> modelMapper.mapear(historico,HistoricoDTO.class))).collect(Collectors.toList());
        if (contenido.size() > 0) {
            response.setCode(0);
            response.setMessage("Response exitoso");
        } else {
            response.setCode(1);
            response.setMessage("Not Found");
        }
        response.setContent(contenido);
        response.setNumPage(historicos.getNumber());
        response.setSizePage(historicos.getSize());
        response.setTotalElements(historicos.getTotalElements());
        response.setTotalPages(historicos.getTotalPages());
        response.setIsLast(historicos.isLast());
        return response;
    }

    @Override
    public ListHistoricoResponse findAllByWeek(int numPage, int sizePage, String orderBy, String sortDir, int week) {
        ListHistoricoResponse response = new ListHistoricoResponse();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);

        Page<HistoricoModel> historicos = iHistorico.findBySemanaHistorico(week,pageable);

        List<HistoricoModel> listaHistorico = historicos.getContent();
        List<HistoricoDTO> contenido = listaHistorico.stream().map(historico ->modelMapper.mapear(historico,HistoricoDTO.class)).collect(Collectors.toList());
        if(contenido.size()>0){
            response.setCode(0);
            response.setMessage("Response exitoso");
        }
        else{
            response.setCode(1);
            response.setMessage("Not Found");
        }
        response.setContent(contenido);
        response.setNumPage(historicos.getNumber());
        response.setSizePage(historicos.getSize());
        response.setTotalElements(historicos.getTotalElements());
        response.setTotalPages(historicos.getTotalPages());
        response.setIsLast(historicos.isLast());
        return response;
    }

    @Override
    public HistoricoResponse findByHistorico(Long idHistorico) {
        HistoricoDTO historicoBuscar = modelMapper.mapear(iHistorico.findById(idHistorico).orElseThrow(),HistoricoDTO.class);
        HistoricoResponse response = new HistoricoResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(historicoBuscar);
        return response;
    }

    @Override
    public HistoricoResponse saveHistorico(HistoricoDTO historicoDTO) {
        HistoricoModel historicoNuevo;
        List<DetalleHistoricoModel> detalle = historicoDTO.getDetalleHistorico();
        iDetalleH.saveAll(detalle);
        Calendar calendar = Calendar.getInstance();
        int numSem = calendar.get(Calendar.WEEK_OF_YEAR);
        historicoDTO.setSemanaHistorico(numSem);
        historicoDTO.setDetalleHistorico(detalle);
        historicoNuevo = iHistorico.save(modelMapper.mapear(historicoDTO,HistoricoModel.class));
        HistoricoResponse response = new HistoricoResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(modelMapper.mapear(historicoNuevo,HistoricoDTO.class));
        return response;
    }

    @Override
    public ListDetalleHistoricoResponse findAllDetallesHistorico(int numPage, int sizePage, String orderBy, String sortDir,Long idHistorico) {
        ListDetalleHistoricoResponse response = new ListDetalleHistoricoResponse();
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        if(idHistorico != 0){
            Pageable pageable = PageRequest.of(numPage,sizePage,sort);
            Page<DetalleHistoricoModel> detalles = iDetalleH.findByHistorico_IdHistorico(idHistorico,pageable);

            List<DetalleHistoricoModel> listaDetalles = detalles.getContent();
            List<DetalleHistoricoDTO> contenido = listaDetalles.stream().map(detalle -> modelMapper.mapear(detalle,DetalleHistoricoDTO.class)).collect(Collectors.toList());
            if(contenido.size()>0){
                response.setCode(0);
                response.setMessage("Response exitoso");
            }
            else{
                response.setCode(1);
                response.setMessage("Not Found");
            }
            response.setContent(contenido);
            response.setNumPage(detalles.getNumber());
            response.setSizePage(detalles.getSize());
            response.setTotalElements(detalles.getTotalElements());
            response.setTotalPages(detalles.getTotalPages());
            response.setIsLast(detalles.isLast());
        }
        else{
            response.setCode(2);
            response.setMessage("Bad request");
        }
        return response;
    }
}
