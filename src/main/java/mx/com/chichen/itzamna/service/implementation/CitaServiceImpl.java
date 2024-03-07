package mx.com.chichen.itzamna.service.implementation;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import mx.com.chichen.itzamna.model.dto.CitaDTO;
import mx.com.chichen.itzamna.model.dto.ProveedorDTO;
import mx.com.chichen.itzamna.model.entity.CitaModel;
import mx.com.chichen.itzamna.repositories.ICitaRepository;
import mx.com.chichen.itzamna.response.CitaResponse;
import mx.com.chichen.itzamna.response.ListCitaResponse;
import mx.com.chichen.itzamna.service.interfaces.ICitaService;
import mx.com.chichen.itzamna.util.MapperServiceImpl;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitaServiceImpl implements ICitaService {

    @Autowired
    private ICitaRepository iCita;

    @Autowired
    private MapperServiceImpl modelMapper;

    public static final String ACCOUNT_SID = "AC100130f2bf8f8a734e4b0ad0c5022812";
    public static final String AUTH_TOKEN = "d6b8c13640013c65762367dd83cf8f96";

    @Override
    public ListCitaResponse findAllCitas(int numPage, int sizePage, String orderBy, String sortDir) {
        ListCitaResponse response = new ListCitaResponse();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();

        Pageable pageable = PageRequest.of(numPage, sizePage, sort);
        Page<CitaModel> citas = iCita.findAll(pageable);

        List<CitaModel> listaCita = citas.getContent();
        List<CitaDTO> contenido = listaCita.stream().map(cita -> modelMapper.mapear(cita,CitaDTO.class)).collect(Collectors.toList());

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
        List<CitaDTO> contenido = listaCita.stream().map(cita -> modelMapper.mapear(cita,CitaDTO.class)).collect(Collectors.toList());

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
        List<CitaDTO> contenido = listaCita.stream().map(cita -> modelMapper.mapear(cita,CitaDTO.class)).collect(Collectors.toList());

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
        List<CitaDTO> contenido = listaCita.stream().map(cita -> modelMapper.mapear(cita,CitaDTO.class)).collect(Collectors.toList());

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
        Optional<CitaModel> citaBuscada = iCita.findById(idCita);
        if(!citaBuscada.isEmpty()){
            CitaResponse response = new CitaResponse();
            response.setCode(0);
            response.setMessage("Response existoso");
            response.setResponse(modelMapper.mapear(citaBuscada.get(),CitaDTO.class));
            return response;
        }
        else{
            CitaResponse response = new CitaResponse();
            response.setCode(1);
            response.setMessage("No encontrado");
            response.setResponse(null);
            return response;
        }

    }

    @Override
    public CitaResponse verificarDisponibilidad(LocalDate fecha, LocalTime hora) {
        CitaResponse response = new CitaResponse();
        List<CitaModel> citasBuscadas = iCita.getByFechaCita(fecha);
        if(citasBuscadas.size()>0){
            CitaDTO citaBuscada = modelMapper.mapear(citasBuscadas.stream().filter(cita -> cita.getHoraCita().equals(hora)).findFirst(),CitaDTO.class);
            if(citaBuscada.getIdCita()==null){
                response.setCode(0);
                response.setMessage("Se encontro disponibilidad");
            }
            else{
                response.setCode(1);
                response.setMessage("No se encontro disponibilidad");
                response.setResponse(citaBuscada);
            }
            return response;
        }
        else{
            response.setCode(0);
            response.setMessage("Se encontro disponibilidad");
            return response;
        }

    }

    @Override
    public CitaResponse cancelarCita(Long idCita) {
        CitaResponse response = new CitaResponse();
        Optional<CitaModel> citaBuscada = iCita.findById(idCita);
        if(citaBuscada.isEmpty()) {
            response.setCode(1);
            response.setMessage("Not found");
            return response;
        }
        else{
            CitaDTO cita = modelMapper.mapear(citaBuscada.get(),CitaDTO.class);
            cita.setEstatusCita("Cancelado");
            iCita.save(modelMapper.mapear(cita, CitaModel.class));

            response.setCode(0);
            response.setMessage("Response existoso");
            response.setResponse(cita);
            return response;
        }

    }

    @Override
    public CitaResponse saveCita(CitaDTO cita) {
        cita.setEstatusCita("Agendado");
        CitaModel citaNuevo = iCita.save(modelMapper.mapear(cita,CitaModel.class));
        CitaResponse response = new CitaResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(modelMapper.mapear(citaNuevo,CitaDTO.class));
        return response;
    }

    @Override
    public void sendMessageProveedor(String mensaje, Long idCita) {
        CitaDTO citaBuscar = modelMapper.mapear(iCita.findById(idCita),CitaDTO.class);
        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber("whatsapp:"+citaBuscar.getPaciente().getPropietario().getTelefonoPropietario()),
                new PhoneNumber("whatsapp:+14155238886"),mensaje).create();
    }

    @Override
    public CitaResponse updateCita(CitaDTO cita) {
        CitaModel citaNuevo = iCita.save(modelMapper.mapear(cita, CitaModel.class));
        CitaResponse response = new CitaResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(modelMapper.mapear(citaNuevo,CitaDTO.class));
        return response;
    }

    @Override
    public void deleteCita(Long idCita) {
        iCita.deleteById(idCita);
    }

}
