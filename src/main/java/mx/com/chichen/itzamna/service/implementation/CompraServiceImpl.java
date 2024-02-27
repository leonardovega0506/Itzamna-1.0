package mx.com.chichen.itzamna.service.implementation;

import lombok.RequiredArgsConstructor;
import mx.com.chichen.itzamna.model.dto.CompraDTO;
import mx.com.chichen.itzamna.model.dto.DetalleCompraDTO;
import mx.com.chichen.itzamna.model.entity.CompraModel;
import mx.com.chichen.itzamna.model.entity.DetalleCompraModel;
import mx.com.chichen.itzamna.repositories.ICompraRepository;
import mx.com.chichen.itzamna.repositories.IDetalleCompraRepository;
import mx.com.chichen.itzamna.response.CompraResponse;
import mx.com.chichen.itzamna.response.ListCompraResponse;
import mx.com.chichen.itzamna.response.ListDetalleCompraResponse;
import mx.com.chichen.itzamna.service.interfaces.ICompraService;
import mx.com.chichen.itzamna.util.MapperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompraServiceImpl implements ICompraService {

    @Autowired
    private final ICompraRepository iCompra;

    @Autowired
    private final IDetalleCompraRepository iDetalleC;

    @Autowired
    private MapperServiceImpl modelMapper;

    @Override
    public ListCompraResponse findAll(int numPage, int sizePage, String orderBy, String sortDir) {
        ListCompraResponse response = new ListCompraResponse();
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage, sizePage, sort);
        Page<CompraModel> compras = iCompra.findAll(pageable);
        List<CompraModel> listaCompra = compras.getContent();
        List<CompraDTO> contenido = listaCompra.stream().map((compra -> modelMapper.mapear(compra,CompraDTO.class))).collect(Collectors.toList());
        if (contenido.size() > 0) {
            response.setCode(0);
            response.setMessage("Response exitoso");
        } else {
            response.setCode(1);
            response.setMessage("Not Found");
        }
        response.setContent(contenido);
        response.setNumPage(compras.getNumber());
        response.setSizePage(compras.getSize());
        response.setTotalElements(compras.getTotalElements());
        response.setTotalPages(compras.getTotalPages());
        response.setIsLast(compras.isLast());
        return response;
    }

    @Override
    public ListCompraResponse findAllByFecha(int numPage, int sizePage, String orderBy, String sortDir, LocalDate fecha) {
        ListCompraResponse response = new ListCompraResponse();
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage, sizePage, sort);
        if (fecha != null) {
            Page<CompraModel> compras = iCompra.findByFechaCompra(fecha, pageable);
            List<CompraModel> listaCompra = compras.getContent();
            List<CompraDTO> contenido = listaCompra.stream().map((compra -> modelMapper.mapear(compra,CompraDTO.class))).collect(Collectors.toList());
            if (contenido.size() > 0) {
                response.setCode(0);
                response.setMessage("Response exitoso");
            } else {
                response.setCode(1);
                response.setMessage("Not Found");
            }
            response.setContent(contenido);
            response.setNumPage(compras.getNumber());
            response.setSizePage(compras.getSize());
            response.setTotalElements(compras.getTotalElements());
            response.setTotalPages(compras.getTotalPages());
            response.setIsLast(compras.isLast());
        } else {
            response.setCode(2);
            response.setMessage("Bad request");
        }
        return response;
    }

    @Override
    public ListCompraResponse findAllByMonth(int numPage, int sizePage, String orderBy, String sortDir, int mes) {
        ListCompraResponse response = new ListCompraResponse();
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage, sizePage, sort);
        if (mes != 0) {

            Page<CompraModel> compras = iCompra.findByMes(mes, pageable);
            List<CompraModel> listaCompra = compras.getContent();
            List<CompraDTO> contenido = listaCompra.stream().map((compra -> modelMapper.mapear(compra,CompraDTO.class))).collect(Collectors.toList());
            if (contenido.size() > 0) {
                response.setCode(0);
                response.setMessage("Response exitoso");
            } else {
                response.setCode(1);
                response.setMessage("Not Found");
            }
            response.setContent(contenido);
            response.setNumPage(compras.getNumber());
            response.setSizePage(compras.getSize());
            response.setTotalElements(compras.getTotalElements());
            response.setTotalPages(compras.getTotalPages());
            response.setIsLast(compras.isLast());
        } else {
            response.setCode(2);
            response.setMessage("Bad request");
        }
        return response;
    }

    @Override
    public CompraResponse findById(Long idCompra) {
        CompraDTO citaBuscada = modelMapper.mapear(iCompra.findById(idCompra).orElseThrow(),CompraDTO.class);
        CompraResponse response = new CompraResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(citaBuscada);
        return response;
    }

    @Override
    public CompraResponse saveCompra(CompraDTO compraDTO) {
        List<DetalleCompraModel> detallesNuevos = compraDTO.getDetallesCompra();
        iDetalleC.saveAll(detallesNuevos);

        CompraModel compraNueva = new CompraModel();
        compraNueva.setDetallesCompra(detallesNuevos);
        compraNueva =iCompra.save(modelMapper.mapear(compraDTO,CompraModel.class));
        CompraResponse response = new CompraResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(modelMapper.mapear(compraNueva,CompraDTO.class));
        return response;
    }

    @Override
    public CompraResponse updateCompra(CompraDTO compraDTO) {
        CompraModel compraActualizada = iCompra.save(modelMapper.mapear(compraDTO,CompraModel.class));
        CompraResponse response = new CompraResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(modelMapper.mapear(compraActualizada,CompraDTO.class));
        return response;
    }

    @Override
    public void deleteCompra(Long idCompra) {
        iCompra.deleteById(idCompra);
    }

    @Override
    public ListDetalleCompraResponse findDetallesCompra(Long idCompra, int numPage, int sizePage, String orderBy, String sortDir,Long idDetalle) {

        ListDetalleCompraResponse response = new ListDetalleCompraResponse();
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        if(idDetalle !=0){
            Pageable pageable = PageRequest.of(numPage, sizePage, sort);
            Page<DetalleCompraModel> detalles = iDetalleC.findByCompra_IdCompra(idCompra, pageable);

            List<DetalleCompraModel> listaDetalles = detalles.getContent();
            List<DetalleCompraDTO> contenido = listaDetalles.stream().map(detalle->modelMapper.mapear(detalle,DetalleCompraDTO.class)).collect(Collectors.toList());
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
            response.setMessage("Not Found");
        }
        return response;
    }

}
