package mx.com.chichen.itzamna.service.implementation;

import lombok.RequiredArgsConstructor;
import mx.com.chichen.itzamna.model.dto.PacienteDTO;
import mx.com.chichen.itzamna.model.dto.ProveedorDTO;
import mx.com.chichen.itzamna.model.entity.PacienteModel;
import mx.com.chichen.itzamna.model.entity.ProveedorModel;
import mx.com.chichen.itzamna.repositories.IProveedorRepository;
import mx.com.chichen.itzamna.response.ListPacienteResponse;
import mx.com.chichen.itzamna.response.ListProveedorResponse;
import mx.com.chichen.itzamna.response.ProveedorResponse;
import mx.com.chichen.itzamna.service.interfaces.IProveedorService;
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
public class ProveedorServiceImpl implements IProveedorService {

    @Autowired
    private IProveedorRepository iProveedor;

    @Autowired
    private MapperServiceImpl modelMapper;

    @Override
    public ListProveedorResponse findAllProveedor(int numPage, int sizePage, String orderBy, String sortDir) {
        ListProveedorResponse response = new ListProveedorResponse();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);
        Page<ProveedorModel> proveedores = iProveedor.findAll(pageable);

        List<ProveedorModel> listaProveedor = proveedores.getContent();
        List<ProveedorDTO> contenido = listaProveedor.stream().map(proveedor -> modelMapper.mapear(proveedor,ProveedorDTO.class)).collect(Collectors.toList());

        if(contenido.size()>0){
            response.setCode(0);
            response.setMessage("Response exitoso");
        }
        else{
            response.setCode(1);
            response.setMessage("Not Found");
        }
        response.setContent(contenido);
        response.setNumPage(proveedores.getNumber());
        response.setSizePage(proveedores.getSize());
        response.setTotalElements(proveedores.getTotalElements());
        response.setTotalPages(proveedores.getTotalPages());
        response.setIsLast(proveedores.isLast());
        return response;
    }

    @Override
    public ListProveedorResponse findAllProveedorByNombre(int numPage, int sizePage, String orderBy, String sortDir, String nombre) {
        ListProveedorResponse response = new ListProveedorResponse();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);
        Page<ProveedorModel> proveedores = iProveedor.findByNombreProveedorLike(nombre,pageable);

        List<ProveedorModel> listaProveedor = proveedores.getContent();
        List<ProveedorDTO> contenido = listaProveedor.stream().map(proveedor -> modelMapper.mapear(proveedor,ProveedorDTO.class)).collect(Collectors.toList());

        if(contenido.size()>0){
            response.setCode(0);
            response.setMessage("Response exitoso");
        }
        else{
            response.setCode(1);
            response.setMessage("Not Found");
        }
        response.setContent(contenido);
        response.setNumPage(proveedores.getNumber());
        response.setSizePage(proveedores.getSize());
        response.setTotalElements(proveedores.getTotalElements());
        response.setTotalPages(proveedores.getTotalPages());
        response.setIsLast(proveedores.isLast());
        return response;
    }

    @Override
    public ProveedorResponse saveProveedor(ProveedorDTO proveedorDTO) {
        ProveedorModel proveedorNuevo;
        proveedorNuevo = iProveedor.save(modelMapper.mapear(proveedorDTO,ProveedorModel.class));
        ProveedorResponse response = new ProveedorResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(modelMapper.mapear(proveedorNuevo,ProveedorDTO.class));
        return response;
    }

    @Override
    public ProveedorResponse findById(Long idProveedor) {
        ProveedorDTO proveedorBuscar = modelMapper.mapear(iProveedor.findById(idProveedor).orElseThrow(),ProveedorDTO.class);
        ProveedorResponse response = new ProveedorResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(proveedorBuscar);
        return response;
    }

    @Override
    public ProveedorResponse updateProveedor(ProveedorDTO proveedorDTO) {
        ProveedorModel proveedorNuevo;
        proveedorNuevo = iProveedor.save(modelMapper.mapear(proveedorDTO,ProveedorModel.class));
        ProveedorResponse response = new ProveedorResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(modelMapper.mapear(proveedorNuevo,ProveedorDTO.class));
        return response;
    }

    @Override
    public void sendMessageProveedor(String mensaje, Long idProveedor) {

    }

    @Override
    public void deleteProveedor(Long idProveedor) {
        iProveedor.deleteById(idProveedor);
    }

}
