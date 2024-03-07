package mx.com.chichen.itzamna.controller;

import mx.com.chichen.itzamna.model.dto.ProductoDTO;
import mx.com.chichen.itzamna.response.ListProductoResponse;
import mx.com.chichen.itzamna.response.ProductoResponse;
import mx.com.chichen.itzamna.service.interfaces.IProductoService;
import mx.com.chichen.itzamna.util.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chichen/itzmna/producto")
public class ProductoController {

    @Autowired
    private IProductoService sProducto;

    @GetMapping
    public ResponseEntity<ListProductoResponse> obtenerProductos(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idItem") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir){
        return new ResponseEntity<>(sProducto.findAll(pageNumber,pageSize,orderBy,sortDir), HttpStatus.OK);
    }

    @GetMapping("/nombre")
    public ResponseEntity<ListProductoResponse> obtenerProductosName(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idItem") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam(value = "") String nombre
    ){
        return new ResponseEntity<>(sProducto.findAllProductoByName(pageNumber,pageSize,orderBy,sortDir,nombre),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> obtenerProductosById(@PathVariable Long idProducto){
        return new ResponseEntity<>(sProducto.findById(idProducto),HttpStatus.OK);
    }

    @GetMapping("/clave")
    public ResponseEntity<ListProductoResponse> obtenerProductosByClave(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idItem") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam(value = "clave") String clave){
        return new ResponseEntity<>(sProducto.findAllProductoByClave(pageNumber,pageSize,orderBy,sortDir,clave),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductoResponse> guardarProducto(@RequestBody ProductoDTO productoDTO){
        return new ResponseEntity<>(sProducto.saveProducto(productoDTO),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProductoResponse> actualizarProducto(@RequestBody ProductoDTO productoDTO){
        return new ResponseEntity<>(sProducto.updateProducto(productoDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long idProducto){
        sProducto.deleteProducto(idProducto);
        return new ResponseEntity<>("Eliminar producto",HttpStatus.OK);
    }

    @GetMapping("/assign-producto")
    public ResponseEntity<ProductoResponse> asignarProducto(@RequestParam Long idProducto,
                                                            @RequestParam Long idProveedor){
        return new ResponseEntity<>(sProducto.assignProducto(idProveedor,idProducto),HttpStatus.OK);
    }

}
