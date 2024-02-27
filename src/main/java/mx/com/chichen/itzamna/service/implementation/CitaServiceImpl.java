package mx.com.chichen.itzamna.service.implementation;

import mx.com.chichen.itzamna.model.dto.CitaDTO;
import mx.com.chichen.itzamna.model.entity.CitaModel;
import mx.com.chichen.itzamna.repositories.ICitaRepository;
import mx.com.chichen.itzamna.response.CitaResponse;
import mx.com.chichen.itzamna.response.ListCitaResponse;
import mx.com.chichen.itzamna.service.interfaces.ICitaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitaServiceImpl implements ICitaService {

    @Autowired
    private ICitaRepository iCita;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ListCitaResponse findAllCitas(int numPage, int sizePage, String orderBy, String sortDir) {
        ListCitaResponse response = new ListCitaResponse();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();

        Pageable pageable = PageRequest.of(numPage, sizePage, sort);
        Page<CitaModel> citas = iCita.findAll(pageable);

        List<CitaModel> listaCita = citas.getContent();
        List<CitaDTO> contenido = listaCita.stream().map(cita -> mapearDTO(cita)).collect(Collectors.toList());

        if (contenido.size() > 0) {
            response.setCode(0);
            response.setMessage("Response exitoso");
            response.setContent(contenido);
            response.setNumPage(citas.getNumber());
            response.setSizePage(citas.getSize());
            response.setTotalElements(citas.getTotalElements());
            response.setTotalPages(citas.getTotalPages());
            response.setIsLast(citas.isLast());
            return response;
        } else {
            response.setCode(1);
            response.setMessage("No hay contenido");
            response.setContent(contenido);
            response.setNumPage(citas.getNumber());
            response.setSizePage(citas.getSize());
            response.setTotalElements(citas.getTotalElements());
            response.setTotalPages(citas.getTotalPages());
            response.setIsLast(citas.isLast());
            return response;
        }
    }

    @Override
    public ListCitaResponse findCitasByFecha(int numPage, int sizePage, String orderBy, String sortDir, LocalDate fecha) {
        ListCitaResponse response = new ListCitaResponse();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();

        Pageable pageable = PageRequest.of(numPage, sizePage, sort);
        Page<CitaModel> citas = iCita.findByFechaCita(fecha,pageable);

        List<CitaModel> listaCita = citas.getContent();
        List<CitaDTO> contenido = listaCita.stream().map(cita -> mapearDTO(cita)).collect(Collectors.toList());

        if (contenido.size() > 0) {
            response.setCode(0);
            response.setMessage("Response exitoso");
            response.setContent(contenido);
            response.setNumPage(citas.getNumber());
            response.setSizePage(citas.getSize());
            response.setTotalElements(citas.getTotalElements());
            response.setTotalPages(citas.getTotalPages());
            response.setIsLast(citas.isLast());
            return response;
        } else {
            response.setCode(1);
            response.setMessage("No hay contenido");
            response.setContent(contenido);
            response.setNumPage(citas.getNumber());
            response.setSizePage(citas.getSize());
            response.setTotalElements(citas.getTotalElements());
            response.setTotalPages(citas.getTotalPages());
            response.setIsLast(citas.isLast());
            return response;
        }
    }

    @Override
    public ListCitaResponse findCitaByEstatus(int numPage, int sizePage, String orderBy, String sortDir, String estatus) {
        ListCitaResponse response = new ListCitaResponse();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();

        Pageable pageable = PageRequest.of(numPage, sizePage, sort);
        Page<CitaModel> citas = iCita.findByEstatusCita(estatus,pageable);

        List<CitaModel> listaCita = citas.getContent();
        List<CitaDTO> contenido = listaCita.stream().map(cita -> mapearDTO(cita)).collect(Collectors.toList());

        if (contenido.size() > 0) {
            response.setCode(0);
            response.setMessage("Response exitoso");
            response.setContent(contenido);
            response.setNumPage(citas.getNumber());
            response.setSizePage(citas.getSize());
            response.setTotalElements(citas.getTotalElements());
            response.setTotalPages(citas.getTotalPages());
            response.setIsLast(citas.isLast());
            return response;
        } else {
            response.setCode(1);
            response.setMessage("No hay contenido");
            response.setContent(contenido);
            response.setNumPage(citas.getNumber());
            response.setSizePage(citas.getSize());
            response.setTotalElements(citas.getTotalElements());
            response.setTotalPages(citas.getTotalPages());
            response.setIsLast(citas.isLast());
            return response;
        }
    }

    @Override
    public ListCitaResponse findCitaByPaciente(int numPage, int sizePage, String orderBy, String sortDir, Long idPaciente) {
        ListCitaResponse response = new ListCitaResponse();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();

        Pageable pageable = PageRequest.of(numPage, sizePage, sort);
        Page<CitaModel> citas = iCita.findByPaciente_IdPaciente(idPaciente,pageable);

        List<CitaModel> listaCita = citas.getContent();
        List<CitaDTO> contenido = listaCita.stream().map(cita -> mapearDTO(cita)).collect(Collectors.toList());

        if (contenido.size() > 0) {
            response.setCode(0);
            response.setMessage("Response exitoso");
            response.setContent(contenido);
            response.setNumPage(citas.getNumber());
            response.setSizePage(citas.getSize());
            response.setTotalElements(citas.getTotalElements());
            response.setTotalPages(citas.getTotalPages());
            response.setIsLast(citas.isLast());
            return response;
        } else {
            response.setCode(1);
            response.setMessage("No hay contenido");
            response.setContent(contenido);
            response.setNumPage(citas.getNumber());
            response.setSizePage(citas.getSize());
            response.setTotalElements(citas.getTotalElements());
            response.setTotalPages(citas.getTotalPages());
            response.setIsLast(citas.isLast());
            return response;
        }
    }

    @Override
    public CitaResponse findCitaById(Long idCita) {
        CitaDTO citaBuscada = mapearDTO(iCita.findById(idCita).orElseThrow());
        CitaResponse response = new CitaResponse();
        response.setCode(0);
        response.setMessage("Response existoso");
        response.setResponse(citaBuscada);
        return response;
    }

    @Override
    public CitaResponse verificarDisponibilidad(LocalDate fecha, LocalTime hora) {
        return null;
    }

    @Override
    public CitaResponse cancelarCita(Long idCita) {
        return null;
    }

    @Override
    public CitaResponse saveCita(CitaDTO cita) {
        CitaModel citaNuevo = iCita.save(mapearEntidad(cita));
        CitaResponse response = new CitaResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(mapearDTO(citaNuevo));
        return response;
    }

    @Override
    public CitaResponse updateCita(CitaDTO cita) {
        CitaModel citaNuevo = iCita.save(mapearEntidad(cita));
        CitaResponse response = new CitaResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(mapearDTO(citaNuevo));
        return response;
    }

    @Override
    public void deleteCita(Long idCita) {
        iCita.deleteById(idCita);
    }

    private CitaModel mapearEntidad(CitaDTO citaDTO) {
        CitaModel cita = modelMapper.map(citaDTO, CitaModel.class);
        return cita;
    }

    private CitaDTO mapearDTO(CitaModel citaModel) {
        CitaDTO cita = modelMapper.map(citaModel, CitaDTO.class);
        return cita;
    }
}
