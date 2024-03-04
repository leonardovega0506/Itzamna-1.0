package mx.com.chichen.itzamna.service.implementation;

import lombok.RequiredArgsConstructor;
import mx.com.chichen.itzamna.model.dto.ProveedorDTO;
import mx.com.chichen.itzamna.model.entity.ProveedorModel;
import mx.com.chichen.itzamna.repositories.IProveedorRepository;
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
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
@RequiredArgsConstructor
public class ProveedorServiceImpl implements IProveedorService {

    @Autowired
    private IProveedorRepository iProveedor;

    @Autowired
    private MapperServiceImpl modelMapper;

    public static final String ACCOUNT_SID = "AC100130f2bf8f8a734e4b0ad0c5022812";
    public static final String AUTH_TOKEN = "d6b8c13640013c65762367dd83cf8f96";

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
        ProveedorDTO proveedorBuscar = modelMapper.mapear(iProveedor.findById(idProveedor),ProveedorDTO.class);
        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber("whatsapp:"+proveedorBuscar.getTelefonoProveedor()),
                new PhoneNumber("whatsapp:+14155238886"),mensaje).create();
    }

    @Override
    public void deleteProveedor(Long idProveedor) {
        iProveedor.deleteById(idProveedor);
    }

}
