package mx.com.chichen.itzamna.service.implementation;

import lombok.RequiredArgsConstructor;
import mx.com.chichen.itzamna.model.dto.PacienteDTO;
import mx.com.chichen.itzamna.model.dto.PropietarioDTO;
import mx.com.chichen.itzamna.model.dto.ServicioDTO;
import mx.com.chichen.itzamna.model.dto.ServicioPacienteDTO;
import mx.com.chichen.itzamna.model.entity.PacienteModel;
import mx.com.chichen.itzamna.model.entity.PropietarioModel;
import mx.com.chichen.itzamna.model.entity.ServicioModel;
import mx.com.chichen.itzamna.model.entity.ServicioPacienteModel;
import mx.com.chichen.itzamna.repositories.IPacienteRepository;
import mx.com.chichen.itzamna.repositories.IPropietarioRepository;
import mx.com.chichen.itzamna.repositories.IServicioPacienteRepository;
import mx.com.chichen.itzamna.repositories.IServicioRepository;
import mx.com.chichen.itzamna.response.ListPacienteResponse;
import mx.com.chichen.itzamna.response.ListServicioPacienteResponse;
import mx.com.chichen.itzamna.response.ListServicioResponse;
import mx.com.chichen.itzamna.response.PacienteResponse;
import mx.com.chichen.itzamna.service.interfaces.IPacienteService;
import mx.com.chichen.itzamna.service.interfaces.IPropietarioService;
import mx.com.chichen.itzamna.util.MapperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements IPacienteService {

    @Autowired
    private IPacienteRepository iPaciente;

    @Autowired
    private IServicioPacienteRepository iServicio;

    @Autowired
    private MapperServiceImpl modelMapper;

    @Autowired
    private IPropietarioRepository iPropietario;

    @Autowired
    private IServicioPacienteRepository iServicioPaciente;

    @Override
    public ListPacienteResponse findAllPaciente(int numPage, int sizePage, String orderBy, String sortDir) {
        ListPacienteResponse response = new ListPacienteResponse();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);
        Page<PacienteModel> pacientes = iPaciente.findAll(pageable);

        List<PacienteModel> listaPacientes = pacientes.getContent();
        List<PacienteDTO> contenido = listaPacientes.stream().map(paciente -> modelMapper.mapear(paciente,PacienteDTO.class)).collect(Collectors.toList());

        if(contenido.size()>0){
            response.setCode(0);
            response.setMessage("Response exitoso");
        }
        else{
            response.setCode(1);
            response.setMessage("Not Found");
        }
        response.setContent(contenido);
        response.setNumPage(pacientes.getNumber());
        response.setSizePage(pacientes.getSize());
        response.setTotalElements(pacientes.getTotalElements());
        response.setTotalPages(pacientes.getTotalPages());
        response.setIsLast(pacientes.isLast());
        return response;
    }

    @Override
    public ListPacienteResponse findAllPacienteByNombre(int numPage, int sizePage, String orderBy, String sortDir, String nombre) {
        ListPacienteResponse response = new ListPacienteResponse();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);
        Page<PacienteModel> pacientes = iPaciente.findByNombrePacienteLike(nombre,pageable);

        List<PacienteModel> listaPaciente = pacientes.getContent();
        List<PacienteDTO> contenido = listaPaciente.stream().map(paciente -> modelMapper.mapear(paciente,PacienteDTO.class)).collect(Collectors.toList());

        if(contenido.size() > 0){
            response.setCode(0);
            response.setMessage("Response exitoso");
        }
        else{
            response.setCode(1);
            response.setMessage("Not Found");
        }
        response.setContent(contenido);
        response.setNumPage(pacientes.getNumber());
        response.setSizePage(pacientes.getSize());
        response.setTotalElements(pacientes.getTotalElements());
        response.setTotalPages(pacientes.getTotalPages());
        response.setIsLast(pacientes.isLast());
        return response;
    }

    @Override
    public PacienteResponse savePaciente(PacienteDTO pacienteDTO) {
        PacienteModel pacienteNuevo;
        pacienteNuevo = iPaciente.save(modelMapper.mapear(pacienteDTO,PacienteModel.class));
        PacienteResponse response = new PacienteResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(modelMapper.mapear(pacienteNuevo,PacienteDTO.class));
        return response;
    }

    @Override
    public PacienteResponse findById(Long idPaciente) {
        PacienteDTO pacienteBuscar = modelMapper.mapear(iPaciente.findById(idPaciente).orElseThrow(),PacienteDTO.class);
        PacienteResponse response = new PacienteResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(pacienteBuscar);
        return response;
    }

    @Override
    public PacienteResponse updatePaciente(PacienteDTO pacienteDTO) {
        PacienteModel pacienteActualizado;
        pacienteActualizado = iPaciente.save(modelMapper.mapear(pacienteDTO,PacienteModel.class));
        PacienteResponse response = new PacienteResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(modelMapper.mapear(pacienteActualizado,PacienteDTO.class));
        return response;
    }

    @Override
    public void deletePaciente(Long idPaciente) {
        iPaciente.deleteById(idPaciente);
    }

    @Override
    public ListServicioPacienteResponse findServiciosPaciente( int numPage, int sizePage, String orderBy, String sortDir,Long idPaciente) {
        ListServicioPacienteResponse response = new ListServicioPacienteResponse();
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        if(idPaciente!=0){
            Pageable pageable = PageRequest.of(numPage,sizePage,sort);
            Page<ServicioPacienteModel> servicios = iServicio.findByPaciente_IdPaciente(idPaciente,pageable);

            List<ServicioPacienteModel> listaServicios = servicios.getContent();
            List<ServicioPacienteDTO> contenido = listaServicios.stream().map(servicio-> modelMapper.mapear(servicio,ServicioPacienteDTO.class)).collect(Collectors.toList());
            if(contenido.size()>0){
                response.setCode(0);
                response.setMessage("Response exitoso");
            }
            else{
                response.setCode(1);
                response.setMessage("Not Found");
            }
            response.setContent(contenido);
            response.setNumPage(servicios.getNumber());
            response.setSizePage(servicios.getSize());
            response.setTotalElements(servicios.getTotalElements());
            response.setTotalPages(servicios.getTotalPages());
            response.setIsLast(servicios.isLast());
        }
        else{
            response.setCode(2);
            response.setMessage("Bad request");
        }
        return response;
    }

    @Override
    public PacienteResponse assingPaciente(Long idPropietario,Long idPaciente) {
        PacienteDTO pacienteBuscar = modelMapper.mapear(iPaciente.findById(idPaciente).orElseThrow(),PacienteDTO.class);
        PropietarioDTO propietarioBuscar = modelMapper.mapear(iPropietario.findById(idPropietario).orElseThrow(),PropietarioDTO.class);
        pacienteBuscar.setPropietario(modelMapper.mapear(propietarioBuscar, PropietarioModel.class));
        iPaciente.save(modelMapper.mapear(pacienteBuscar,PacienteModel.class));
        List<PacienteModel> listaPaciente = propietarioBuscar.getPaciente();
        listaPaciente.add(modelMapper.mapear(pacienteBuscar,PacienteModel.class));
        propietarioBuscar.setPaciente(listaPaciente);
        iPropietario.save(modelMapper.mapear(propietarioBuscar,PropietarioModel.class));

        PacienteResponse response = new PacienteResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(pacienteBuscar);

        return response;
    }

    @Override
    public PacienteResponse assignServicio(Long idServicio,Long idPaciente) {
        PacienteDTO pacienteBuscar = modelMapper.mapear(iPaciente.findById(idPaciente).orElseThrow(),PacienteDTO.class);
        ServicioDTO servicioBuscar = modelMapper.mapear(iServicio.findById(idPaciente).orElseThrow(),ServicioDTO.class);
        List<ServicioPacienteModel> servicioPacienteBuscar = iServicio.getByPaciente_IdPaciente(idPaciente);
        ServicioPacienteDTO servicioNuevo = new ServicioPacienteDTO();
        servicioNuevo.setPaciente(modelMapper.mapear(pacienteBuscar,PacienteModel.class));
        servicioNuevo.setServicio(modelMapper.mapear(servicioBuscar,ServicioModel.class));
        iServicioPaciente.save(modelMapper.mapear(servicioNuevo,ServicioPacienteModel.class));
        servicioPacienteBuscar.add(modelMapper.mapear(servicioPacienteBuscar,ServicioPacienteModel.class));
        pacienteBuscar.setServicios(servicioPacienteBuscar);
        PacienteDTO pacienteResponse = modelMapper.mapear(iPaciente.save(modelMapper.mapear(pacienteBuscar,PacienteModel.class)),PacienteDTO.class);
        PacienteResponse response = new PacienteResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(pacienteResponse);
        return response;
    }

    @Override
    public void deleteServicio(Long idServicio,Long idPaciente) {
        ServicioDTO servicioBuscar = modelMapper.mapear(iServicio.findById(idServicio).orElseThrow(),ServicioDTO.class);
        PacienteDTO pacienteBuscar = modelMapper.mapear(iPaciente.findById(idPaciente).orElseThrow(),PacienteDTO.class);
        iServicio.deleteByServicioAndPaciente(modelMapper.mapear(servicioBuscar,ServicioModel.class),modelMapper.mapear(pacienteBuscar,PacienteModel.class));
    }
}
