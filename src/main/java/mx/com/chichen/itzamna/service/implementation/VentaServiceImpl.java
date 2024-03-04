package mx.com.chichen.itzamna.service.implementation;

import lombok.RequiredArgsConstructor;
import mx.com.chichen.itzamna.model.dto.HistoricoDTO;
import mx.com.chichen.itzamna.model.dto.PacienteDTO;
import mx.com.chichen.itzamna.model.dto.VentaDTO;
import mx.com.chichen.itzamna.model.entity.HistoricoModel;
import mx.com.chichen.itzamna.model.entity.PacienteModel;
import mx.com.chichen.itzamna.model.entity.VentaModel;
import mx.com.chichen.itzamna.repositories.IVentaRepository;
import mx.com.chichen.itzamna.response.ListHistoricoResponse;
import mx.com.chichen.itzamna.response.ListPacienteResponse;
import mx.com.chichen.itzamna.response.ListVentaResponse;
import mx.com.chichen.itzamna.response.VentaResponse;
import mx.com.chichen.itzamna.service.interfaces.IVentaService;
import mx.com.chichen.itzamna.util.MapperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements IVentaService {

    @Autowired
    private IVentaRepository iVenta;

    @Autowired
    private MapperServiceImpl modelMapper;


    @Override
    public ListVentaResponse findAllVentas(int numPage, int sizePage, String orderBy, String sortDir) {
        ListVentaResponse response = new ListVentaResponse();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);
        Page<VentaModel> ventas = iVenta.findAll(pageable);

        List<VentaModel> listaVentas = ventas.getContent();
        List<VentaDTO> contenido = listaVentas.stream().map(venta -> modelMapper.mapear(venta,VentaDTO.class)).collect(Collectors.toList());

        if(contenido.size()>0){
            response.setCode(0);
            response.setMessage("Response exitoso");
        }
        else{
            response.setCode(1);
            response.setMessage("Not Found");
        }
        response.setContent(contenido);
        response.setNumPage(ventas.getNumber());
        response.setSizePage(ventas.getSize());
        response.setTotalElements(ventas.getTotalElements());
        response.setTotalPages(ventas.getTotalPages());
        response.setIsLast(ventas.isLast());
        return response;
    }

    @Override
    public ListVentaResponse findAllVentasMonth(int numPage, int sizePage, String orderBy, String sortDir, int month) {
        ListVentaResponse response = new ListVentaResponse();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);
        Page<VentaModel> ventas = iVenta.findByMes(month,pageable);

        List<VentaModel> listaVentas = ventas.getContent();
        List<VentaDTO> contenido = listaVentas.stream().map(venta -> modelMapper.mapear(venta,VentaDTO.class)).collect(Collectors.toList());

        if(contenido.size()>0){
            response.setCode(0);
            response.setMessage("Response exitoso");
        }
        else{
            response.setCode(1);
            response.setMessage("Not Found");
        }
        response.setContent(contenido);
        response.setNumPage(ventas.getNumber());
        response.setSizePage(ventas.getSize());
        response.setTotalElements(ventas.getTotalElements());
        response.setTotalPages(ventas.getTotalPages());
        response.setIsLast(ventas.isLast());
        return response;
    }

    @Override
    public ListVentaResponse findAllVentasWeek(int numPage, int sizePage, String orderBy, String sortDir, int week) {
        ListVentaResponse response = new ListVentaResponse();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);

        Page<VentaModel> ventas = iVenta.findByWeek(week,pageable);

        List<VentaModel> listaVentas = ventas.getContent();
        List<VentaDTO> contenido = listaVentas.stream().map(venta ->modelMapper.mapear(venta,VentaDTO.class)).collect(Collectors.toList());
        if(contenido.size()>0){
            response.setCode(0);
            response.setMessage("Response exitoso");
        }
        else{
            response.setCode(1);
            response.setMessage("Not Found");
        }
        response.setContent(contenido);
        response.setNumPage(ventas.getNumber());
        response.setSizePage(ventas.getSize());
        response.setTotalElements(ventas.getTotalElements());
        response.setTotalPages(ventas.getTotalPages());
        response.setIsLast(ventas.isLast());
        return response;
    }

    @Override
    public ListVentaResponse findAllVentasDate(int numPage, int sizePage, String orderBy, String sortDir, LocalDate fecha) {
        ListVentaResponse response = new ListVentaResponse();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);

        Page<VentaModel> ventas = iVenta.findByFechaVenta(fecha,pageable);

        List<VentaModel> listaVentas = ventas.getContent();
        List<VentaDTO> contenido = listaVentas.stream().map(venta ->modelMapper.mapear(venta,VentaDTO.class)).collect(Collectors.toList());
        if(contenido.size()>0){
            response.setCode(0);
            response.setMessage("Response exitoso");
        }
        else{
            response.setCode(1);
            response.setMessage("Not Found");
        }
        response.setContent(contenido);
        response.setNumPage(ventas.getNumber());
        response.setSizePage(ventas.getSize());
        response.setTotalElements(ventas.getTotalElements());
        response.setTotalPages(ventas.getTotalPages());
        response.setIsLast(ventas.isLast());
        return response;
    }

    @Override
    public VentaResponse saveVentas(VentaDTO ventaDTO) {
        VentaModel ventaNueva;
        ventaNueva = iVenta.save(modelMapper.mapear(ventaDTO,VentaModel.class));
        VentaResponse response = new VentaResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(modelMapper.mapear(ventaNueva,VentaDTO.class));
        return response;
    }

    @Override
    public VentaResponse updateVentas(VentaDTO ventaDTO) {
        VentaModel ventaNueva;
        ventaNueva = iVenta.save(modelMapper.mapear(ventaDTO,VentaModel.class));
        VentaResponse response = new VentaResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(modelMapper.mapear(ventaNueva,VentaDTO.class));
        return response;
    }

    @Override
    public void deleteVentas(Long idVenta) {
        iVenta.deleteById(idVenta);
    }

    @Override
    public VentaResponse findById(Long idVenta) {
        VentaDTO ventaBuscar = modelMapper.mapear(iVenta.findById(idVenta).orElseThrow(),VentaDTO.class);
        VentaResponse response = new VentaResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(ventaBuscar);
        return response;
    }
}
