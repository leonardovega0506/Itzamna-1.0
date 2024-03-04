package mx.com.chichen.itzamna.service.implementation;

import lombok.RequiredArgsConstructor;
import mx.com.chichen.itzamna.model.dto.PacienteDTO;
import mx.com.chichen.itzamna.model.dto.ResponsivaDTO;
import mx.com.chichen.itzamna.model.entity.PacienteModel;
import mx.com.chichen.itzamna.model.entity.ResponsivaModel;
import mx.com.chichen.itzamna.repositories.IResponsivaRepository;
import mx.com.chichen.itzamna.response.ListPacienteResponse;
import mx.com.chichen.itzamna.response.ListResponsivaResponse;
import mx.com.chichen.itzamna.response.ResponsivaResponse;
import mx.com.chichen.itzamna.service.interfaces.IResponsivaService;
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
public class ResponsivaServiceImpl implements IResponsivaService {

    @Autowired
    private IResponsivaRepository iResponsiva;

    @Autowired
    private MapperServiceImpl modelMapper;

    @Override
    public ListResponsivaResponse findAllResponsiva(int numPage, int sizePage, String orderBy, String sortDir) {
        ListResponsivaResponse response = new ListResponsivaResponse();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);
        Page<ResponsivaModel> responsivas = iResponsiva.findAll(pageable);

        List<ResponsivaModel> listaResponsiva = responsivas.getContent();
        List<ResponsivaDTO> contenido = listaResponsiva.stream().map(responsiva -> modelMapper.mapear(responsiva,ResponsivaDTO.class)).collect(Collectors.toList());

        if(contenido.size()>0){
            response.setCode(0);
            response.setMessage("Response exitoso");
        }
        else{
            response.setCode(1);
            response.setMessage("Not Found");
        }
        response.setContent(contenido);
        response.setNumPage(responsivas.getNumber());
        response.setSizePage(responsivas.getSize());
        response.setTotalElements(responsivas.getTotalElements());
        response.setTotalPages(responsivas.getTotalPages());
        response.setIsLast(responsivas.isLast());
        return response;
    }

    @Override
    public ListResponsivaResponse findAllResponsivaByPaciente(int numPage, int sizePage, String orderBy, String sortDir, Long idPaciente) {
        ListResponsivaResponse response = new ListResponsivaResponse();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);
        Page<ResponsivaModel> responsivas = iResponsiva.findByPaciente_IdPaciente(idPaciente,pageable);

        List<ResponsivaModel> listaResponsiva = responsivas.getContent();
        List<ResponsivaDTO> contenido = listaResponsiva.stream().map(responsiva -> modelMapper.mapear(responsiva,ResponsivaDTO.class)).collect(Collectors.toList());

        if(contenido.size()>0){
            response.setCode(0);
            response.setMessage("Response exitoso");
        }
        else{
            response.setCode(1);
            response.setMessage("Not Found");
        }
        response.setContent(contenido);
        response.setNumPage(responsivas.getNumber());
        response.setSizePage(responsivas.getSize());
        response.setTotalElements(responsivas.getTotalElements());
        response.setTotalPages(responsivas.getTotalPages());
        response.setIsLast(responsivas.isLast());
        return response;
    }

    @Override
    public ListResponsivaResponse findAllResponsivaByCategoria(int numPage, int sizePage, String orderBy, String sortDir, String categoria) {
        ListResponsivaResponse response = new ListResponsivaResponse();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);
        Page<ResponsivaModel> responsivas = iResponsiva.findByServicio_CategoriaServicio(categoria,pageable);

        List<ResponsivaModel> listaResponsiva = responsivas.getContent();
        List<ResponsivaDTO> contenido = listaResponsiva.stream().map(responsiva -> modelMapper.mapear(responsiva,ResponsivaDTO.class)).collect(Collectors.toList());

        if(contenido.size()>0){
            response.setCode(0);
            response.setMessage("Response exitoso");
        }
        else{
            response.setCode(1);
            response.setMessage("Not Found");
        }
        response.setContent(contenido);
        response.setNumPage(responsivas.getNumber());
        response.setSizePage(responsivas.getSize());
        response.setTotalElements(responsivas.getTotalElements());
        response.setTotalPages(responsivas.getTotalPages());
        response.setIsLast(responsivas.isLast());
        return response;
    }

    @Override
    public ResponsivaResponse saveResponsiva(ResponsivaDTO responsivaDTO) {
        ResponsivaModel responsivaNueva;
        responsivaNueva = iResponsiva.save(modelMapper.mapear(responsivaDTO,ResponsivaModel.class));
        ResponsivaResponse response = new ResponsivaResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(modelMapper.mapear(responsivaNueva,ResponsivaDTO.class));
        return response;
    }

    @Override
    public void deleteResponsiva(Long idResponsiva) {
        iResponsiva.deleteById(idResponsiva);
    }
}
