package mx.com.chichen.itzamna.service.implementation;

import lombok.RequiredArgsConstructor;
import mx.com.chichen.itzamna.model.dto.PacienteDTO;
import mx.com.chichen.itzamna.model.dto.ProductoDTO;
import mx.com.chichen.itzamna.model.dto.ProveedorDTO;
import mx.com.chichen.itzamna.model.entity.PacienteModel;
import mx.com.chichen.itzamna.model.entity.ProductoModel;
import mx.com.chichen.itzamna.model.entity.ProveedorModel;
import mx.com.chichen.itzamna.repositories.IProductoRepository;
import mx.com.chichen.itzamna.repositories.IProveedorRepository;
import mx.com.chichen.itzamna.response.ListPacienteResponse;
import mx.com.chichen.itzamna.response.ListProductoResponse;
import mx.com.chichen.itzamna.response.ProductoResponse;
import mx.com.chichen.itzamna.service.interfaces.IProductoService;
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
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private IProductoRepository iProducto;

    @Autowired
    private IProveedorRepository iProveedor;

    @Autowired
    private MapperServiceImpl modelMapper;

    @Override
    public ListProductoResponse findAll(int numPage, int sizePage, String orderBy, String sortDir) {
        ListProductoResponse response = new ListProductoResponse();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);
        Page<ProductoModel> productos = iProducto.findAll(pageable);

        List<ProductoModel> listaProducto = productos.getContent();
        List<ProductoDTO> contenido = listaProducto.stream().map(producto -> modelMapper.mapear(producto,ProductoDTO.class)).collect(Collectors.toList());

        if(contenido.size()>0){
            response.setCode(0);
            response.setMessage("Response exitoso");
        }
        else{
            response.setCode(1);
            response.setMessage("Not Found");
        }
        response.setContent(contenido);
        response.setNumPage(productos.getNumber());
        response.setSizePage(productos.getSize());
        response.setTotalElements(productos.getTotalElements());
        response.setTotalPages(productos.getTotalPages());
        response.setIsLast(productos.isLast());
        return response;
    }

    @Override
    public ListProductoResponse findAllProductoByName(int numPage, int sizePage, String orderBy, String sortDir, String nombre) {
        ListProductoResponse response = new ListProductoResponse();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);
        Page<ProductoModel> productos = iProducto.findByNombreProductoLike(nombre,pageable);

        List<ProductoModel> listaProducto = productos.getContent();
        List<ProductoDTO> contenido = listaProducto.stream().map(producto -> modelMapper.mapear(producto,ProductoDTO.class)).collect(Collectors.toList());

        if(contenido.size()>0){
            response.setCode(0);
            response.setMessage("Response exitoso");
        }
        else{
            response.setCode(1);
            response.setMessage("Not Found");
        }
        response.setContent(contenido);
        response.setNumPage(productos.getNumber());
        response.setSizePage(productos.getSize());
        response.setTotalElements(productos.getTotalElements());
        response.setTotalPages(productos.getTotalPages());
        response.setIsLast(productos.isLast());
        return response;
    }

    @Override
    public ProductoResponse findById(Long idProducto) {
        ProductoDTO productoBuscar = modelMapper.mapear(iProducto.findById(idProducto).orElseThrow(),ProductoDTO.class);
        ProductoResponse response = new ProductoResponse();
        response.setCode(0);
        response.setMessage("Response Exitoso");
        response.setResponse(productoBuscar);
        return response;
    }

    @Override
    public ListProductoResponse findAllProductoByClave(int numPage, int sizePage, String orderBy, String sortDir, String clave) {
        ListProductoResponse response = new ListProductoResponse();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);
        Page<ProductoModel> productos = iProducto.findByClaveProductoLike(clave,pageable);

        List<ProductoModel> listaProductos = productos.getContent();
        List<ProductoDTO> contenido = listaProductos.stream().map(producto -> modelMapper.mapear(producto,ProductoDTO.class)).collect(Collectors.toList());

        if(contenido.size()>0){
            response.setCode(0);
            response.setMessage("Response exitoso");
        }
        else{
            response.setCode(1);
            response.setMessage("Not Found");
        }
        response.setContent(contenido);
        response.setNumPage(productos.getNumber());
        response.setSizePage(productos.getSize());
        response.setTotalElements(productos.getTotalElements());
        response.setTotalPages(productos.getTotalPages());
        response.setIsLast(productos.isLast());
        return response;
    }

    @Override
    public ProductoResponse saveProducto(ProductoDTO productoDTO) {
        ProductoModel productoNuevo;
        productoNuevo = iProducto.save(modelMapper.mapear(productoDTO,ProductoModel.class));
        ProductoResponse response = new ProductoResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(modelMapper.mapear(productoNuevo,ProductoDTO.class));
        return response;
    }

    @Override
    public ProductoResponse updateProducto(ProductoDTO productoDTO) {
        ProductoModel productoNuevo;
        productoNuevo = iProducto.save(modelMapper.mapear(productoDTO,ProductoModel.class));
        ProductoResponse response = new ProductoResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(modelMapper.mapear(productoNuevo,ProductoDTO.class));
        return response;
    }

    @Override
    public void deleteProducto(Long idProducto) {
        iProducto.deleteById(idProducto);
    }

    @Override
    public ProductoResponse assignProducto(Long idProveedor,Long idProducto) {
        ProductoDTO productoBuscar = modelMapper.mapear(iProducto.findById(idProducto).orElseThrow(),ProductoDTO.class);
        ProveedorDTO proveedorBuscar = modelMapper.mapear(iProveedor.findById(idProducto).orElseThrow(),ProveedorDTO.class);
        List<ProductoModel> listaProductos = iProducto.findByProveedor_IdProveedor(idProveedor);
        listaProductos.add(modelMapper.mapear(productoBuscar,ProductoModel.class));
        productoBuscar.setProveedor(modelMapper.mapear(proveedorBuscar, ProveedorModel.class));
        ProductoDTO productoActualizado = modelMapper.mapear(iProducto.save(modelMapper.mapear(productoBuscar,ProductoModel.class)),ProductoDTO.class);
        proveedorBuscar.setProducto(listaProductos);
        iProveedor.save(modelMapper.mapear(proveedorBuscar,ProveedorModel.class));
        ProductoResponse response = new ProductoResponse();
        response.setCode(0);
        response.setMessage("Response exitoso");
        response.setResponse(productoActualizado);
        return response;
    }
}
