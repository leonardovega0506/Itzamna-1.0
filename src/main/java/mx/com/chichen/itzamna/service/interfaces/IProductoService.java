package mx.com.chichen.itzamna.service.interfaces;

import mx.com.chichen.itzamna.model.dto.ProductoDTO;
import mx.com.chichen.itzamna.response.ListProductoResponse;
import mx.com.chichen.itzamna.response.ProductoResponse;

public interface IProductoService {

    ListProductoResponse findAll(int numPage, int sizePage, String orderBy, String sortDir);
    ListProductoResponse findAllProductoByName(int numPage, int sizePage, String orderBy, String sortDir, String nombre);
    ProductoResponse findById(Long idProducto);
    ListProductoResponse findAllProductoByClave(int numPage, int sizePage, String orderBy, String sortDir,String clave);
    ProductoResponse saveProducto(ProductoDTO productoDTO);
    ProductoResponse updateProducto(ProductoDTO productoDTO);
    void deleteProducto(Long idProducto);
    ProductoResponse assignProducto(Long idProveedor,Long idProducto);
}
