package mx.com.chichen.itzamna.service.implementation;

import lombok.RequiredArgsConstructor;
import mx.com.chichen.itzamna.model.dto.PacienteDTO;
import mx.com.chichen.itzamna.model.dto.PropietarioDTO;
import mx.com.chichen.itzamna.model.entity.PacienteModel;
import mx.com.chichen.itzamna.model.entity.PropietarioModel;
import mx.com.chichen.itzamna.repositories.IPropietarioRepository;
import mx.com.chichen.itzamna.response.ListPacienteResponse;
import mx.com.chichen.itzamna.response.ListPropietarioResponse;
import mx.com.chichen.itzamna.response.PropietarioResponse;
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
public class PropietarioServiceImpl implements IPropietarioService {

    @Autowired
    private IPropietarioRepository iPropietario;

    @Autowired
    private MapperServiceImpl modelMapper;

    @Override
    public ListPropietarioResponse findAll(int numPage, int sizePage, String orderBy, String sortDir) {
        ListPropietarioResponse response = new ListPropietarioResponse();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);
        Page<PropietarioModel> propietarios = iPropietario.findAll(pageable);

        List<PropietarioModel> listaPropietario = propietarios.getContent();
        List<PropietarioDTO> contenido = listaPropietario.stream().map(propietario -> modelMapper.mapear(propietario,PropietarioDTO.class)).collect(Collectors.toList());

        if(contenido.size()>0){
            response.setCode(0);
            response.setMessage("Response exitoso");
        }
        else{
            response.setCode(1);
            response.setMessage("Not Found");
        }
        response.setResponse(contenido);
        response.setNumPage(propietarios.getNumber());
        response.setSizePage(propietarios.getSize());
        response.setTotalElements(propietarios.getTotalElements());
        response.setTotalPages(propietarios.getTotalPages());
        response.setIsLast(propietarios.isLast());
        return response;
    }

    @Override
    public ListPropietarioResponse findAllByNombre(int numPage, int sizePage, String orderBy, String sortDir, String nombre) {
        ListPropietarioResponse response = new ListPropietarioResponse();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);
        Page<PropietarioModel> propietarios = iPropietario.findByNombrePropietario(nombre,pageable);

        List<PropietarioModel> listaPropietario = propietarios.getContent();
        List<PropietarioDTO> contenido = listaPropietario.stream().map(propietario -> modelMapper.mapear(propietario,PropietarioDTO.class)).collect(Collectors.toList());

        if(contenido.size()>0){
            response.setCode(0);
            response.setMessage("Response exitoso");
        }
        else{
            response.setCode(1);
            response.setMessage("Not Found");
        }
        response.setResponse(contenido);
        response.setNumPage(propietarios.getNumber());
        response.setSizePage(propietarios.getSize());
        response.setTotalElements(propietarios.getTotalElements());
        response.setTotalPages(propietarios.getTotalPages());
        response.setIsLast(propietarios.isLast());
        return response;
    }

    @Override
    public PropietarioResponse savePropietario(PropietarioDTO propietarioDTO) {
        PropietarioModel propietarioNuevo;
        propietarioNuevo = iPropietario.save(modelMapper.mapear(propietarioDTO,PropietarioModel.class));
        PropietarioResponse response = new PropietarioResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(modelMapper.mapear(propietarioNuevo,PropietarioDTO.class));
        return response;
    }

    @Override
    public PropietarioResponse findById(Long idPropietario) {
        PropietarioDTO propietarioBuscar = modelMapper.mapear(iPropietario.findById(idPropietario).orElseThrow(),PropietarioDTO.class);
        PropietarioResponse response = new PropietarioResponse();
        response.setCode(0);
        response.setMessage("Response Exitoso");
        response.setResponse(propietarioBuscar);
        return response;
    }

    @Override
    public PropietarioResponse updatePropietario(PropietarioDTO propietarioDTO) {
        PropietarioModel propietarioNuevo;
        propietarioNuevo = iPropietario.save(modelMapper.mapear(propietarioDTO,PropietarioModel.class));
        PropietarioResponse response = new PropietarioResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(modelMapper.mapear(propietarioNuevo,PropietarioDTO.class));
        return response;
    }

    @Override
    public void deletePropietario(Long idPropietario) {
        iPropietario.deleteById(idPropietario);
    }
}
