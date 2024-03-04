package mx.com.chichen.itzamna.service.implementation;

import lombok.RequiredArgsConstructor;
import mx.com.chichen.itzamna.model.dto.PacienteDTO;
import mx.com.chichen.itzamna.model.dto.ServicioDTO;
import mx.com.chichen.itzamna.model.entity.PacienteModel;
import mx.com.chichen.itzamna.model.entity.ServicioModel;
import mx.com.chichen.itzamna.repositories.IServicioRepository;
import mx.com.chichen.itzamna.response.ListPacienteResponse;
import mx.com.chichen.itzamna.response.ListServicioResponse;
import mx.com.chichen.itzamna.response.ServicioResponse;
import mx.com.chichen.itzamna.service.interfaces.IServicioService;
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
public class ServicioServiceImpl implements IServicioService {

    @Autowired
    private IServicioRepository iServicio;

    @Autowired
    private MapperServiceImpl modelMapper;

    @Override
    public ListServicioResponse findAllPaciente(int numPage, int sizePage, String orderBy, String sortDir) {
        ListServicioResponse response = new ListServicioResponse();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);
        Page<ServicioModel> servicios = iServicio.findAll(pageable);

        List<ServicioModel> listaServicios = servicios.getContent();
        List<ServicioDTO> contenido = listaServicios.stream().map(servicio -> modelMapper.mapear(servicio,ServicioDTO.class)).collect(Collectors.toList());

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
        return response;
    }

    @Override
    public ListServicioResponse findAllPacienteByClave(int numPage, int sizePage, String orderBy, String sortDir, String clave) {
        ListServicioResponse response = new ListServicioResponse();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);
        Page<ServicioModel> servicios = iServicio.findByClaveServicio(clave,pageable);

        List<ServicioModel> listaServicios = servicios.getContent();
        List<ServicioDTO> contenido = listaServicios.stream().map(servicio -> modelMapper.mapear(servicio,ServicioDTO.class)).collect(Collectors.toList());

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
        return response;
    }

    @Override
    public ServicioResponse saveServicio(ServicioDTO servicioDTO) {
        ServicioModel servicioNuevo;
        servicioNuevo = iServicio.save(modelMapper.mapear(servicioDTO,ServicioModel.class));
        ServicioResponse response = new ServicioResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(modelMapper.mapear(servicioNuevo,ServicioDTO.class));
        return response;
    }

    @Override
    public ServicioResponse updateServicio(ServicioDTO servicioDTO) {
        ServicioModel servicioNuevo;
        servicioNuevo = iServicio.save(modelMapper.mapear(servicioDTO,ServicioModel.class));
        ServicioResponse response = new ServicioResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(modelMapper.mapear(servicioNuevo,ServicioDTO.class));
        return response;
    }

    @Override
    public void deleteService(Long idServicio) {
        iServicio.deleteById(idServicio);
    }
}
