package mx.com.chichen.itzamna.service.implementation;

import lombok.RequiredArgsConstructor;
import mx.com.chichen.itzamna.model.dto.DetalleDiarioDTO;
import mx.com.chichen.itzamna.model.dto.DiarioDTO;
import mx.com.chichen.itzamna.model.entity.DetalleDiarioModel;
import mx.com.chichen.itzamna.model.entity.DiarioModel;
import mx.com.chichen.itzamna.repositories.IDetalleDiarioRepository;
import mx.com.chichen.itzamna.repositories.IDiarioRepository;
import mx.com.chichen.itzamna.response.DiarioResponse;
import mx.com.chichen.itzamna.response.ListDetalleDiarioResponse;
import mx.com.chichen.itzamna.response.ListDiarioResponse;
import mx.com.chichen.itzamna.service.interfaces.IDiarioService;
import mx.com.chichen.itzamna.util.MapperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DiarioServiceImpl implements IDiarioService {

    @Autowired
    private IDiarioRepository iDiario;

    @Autowired
    private IDetalleDiarioRepository iDetalleD;

    @Autowired
    private MapperServiceImpl modelMapper;

    @Override
    public ListDiarioResponse findAll(int numPage, int sizePage, String orderBy, String sortDir) {
        ListDiarioResponse response = new ListDiarioResponse();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);
        Page<DiarioModel> diarios = iDiario.findAll(pageable);

        List<DiarioModel> listaDiario = diarios.getContent();
        List<DiarioDTO> contenido = listaDiario.stream().map(diario -> modelMapper.mapear(diario,DiarioDTO.class)).collect(Collectors.toList());

        if(contenido.size()>0){
            response.setCode(0);
            response.setMessage("Response exitoso");
        }
        else{
            response.setCode(1);
            response.setMessage("Not Found");
        }
        response.setContent(contenido);
        response.setNumPage(diarios.getNumber());
        response.setSizePage(diarios.getSize());
        response.setTotalElements(diarios.getTotalElements());
        response.setTotalPages(diarios.getTotalPages());
        response.setIsLast(diarios.isLast());
        return response;
    }

    @Override
    public DiarioResponse findById(Long idDiario) {
        DiarioDTO diarioBuscar = modelMapper.mapear(iDiario.findById(idDiario).orElseThrow(),DiarioDTO.class);
        DiarioResponse response = new DiarioResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(diarioBuscar);
        return response;
    }

    @Override
    public DiarioResponse saveDiario(DiarioDTO diarioDTO) {
        DiarioModel diarioNuevo;
        List<DetalleDiarioModel> detalle = diarioDTO.getDetalleDiario();
        iDetalleD.saveAll(detalle);
        diarioDTO.setDetalleDiario(detalle);
        diarioNuevo = iDiario.save(modelMapper.mapear(diarioDTO,DiarioModel.class));
        DiarioResponse response = new DiarioResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(modelMapper.mapear(diarioNuevo,DiarioDTO.class));
        return response;
    }

    @Override
    public DiarioResponse updateDiario(DiarioDTO diarioDTO) {
        DiarioModel diarioNuevo;
        List<DetalleDiarioModel> detalle = diarioDTO.getDetalleDiario();
        iDetalleD.saveAll(detalle);
        diarioDTO.setDetalleDiario(detalle);
        diarioNuevo = iDiario.save(modelMapper.mapear(diarioDTO,DiarioModel.class));
        DiarioResponse response = new DiarioResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(modelMapper.mapear(diarioNuevo,DiarioDTO.class));
        return response;
    }

    @Override
    public void deleteDiario(Long idDiario) {
        iDiario.deleteById(idDiario);
    }

    @Override
    public DiarioResponse cerrarDiario(Long idDiario) {
        DiarioDTO diarioBuscar = modelMapper.mapear(iDiario.findById(idDiario).orElseThrow(),DiarioDTO.class);
        DiarioResponse response = new DiarioResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(diarioBuscar);
        return response;
    }

    @Override
    public ListDetalleDiarioResponse findDetalleDiario(Long idDiario, int numPage, int sizePage, String orderBy, String sortDir) {
        ListDetalleDiarioResponse response = new ListDetalleDiarioResponse();
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        if(idDiario!=0){
            Pageable pageable = PageRequest.of(numPage,sizePage,sort);
            Page<DetalleDiarioModel> detalles = iDetalleD.findByDiario_IdDiario(idDiario,pageable);

            List<DetalleDiarioModel> listaDetalles = detalles.getContent();
            List<DetalleDiarioDTO> contenido = listaDetalles.stream().map(detalle -> modelMapper.mapear(detalle, DetalleDiarioDTO.class)).collect(Collectors.toList());
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
