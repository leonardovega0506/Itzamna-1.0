package mx.com.chichen.itzamna.controller;

import mx.com.chichen.itzamna.model.dto.ProveedorDTO;
import mx.com.chichen.itzamna.response.ListProveedorResponse;
import mx.com.chichen.itzamna.response.ProveedorResponse;
import mx.com.chichen.itzamna.service.interfaces.IProveedorService;
import mx.com.chichen.itzamna.util.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chichen/itzamna/proveedor")
public class ProveedorController {

    @Autowired
    private IProveedorService sProveedor;

    @GetMapping
    public ResponseEntity<ListProveedorResponse> obtenerProveedor(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idItem") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir){
        return new ResponseEntity<>(sProveedor.findAllProveedor(pageNumber,pageSize,orderBy,sortDir), HttpStatus.OK);
    }

    @GetMapping("/nombre")
    public ResponseEntity<ListProveedorResponse> obtenerProveedorByName(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idItem") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam(value = "nombre") String nombre){
        return new ResponseEntity<>(sProveedor.findAllProveedorByNombre(pageNumber,pageSize,orderBy,sortDir,nombre),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProveedorResponse> guardarProveedor(@RequestBody ProveedorDTO proveedorDTO){
        return new ResponseEntity<>(sProveedor.saveProveedor(proveedorDTO),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorResponse> obtenerProveedorById(@PathVariable Long idProveedor){
        return new ResponseEntity<>(sProveedor.findById(idProveedor),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ProveedorResponse> actualizarProveedor(@RequestBody ProveedorDTO proveedorDTO){
        return new ResponseEntity<>(sProveedor.updateProveedor(proveedorDTO),HttpStatus.OK);
    }

    @PostMapping("/enviar-proveedor")
    public ResponseEntity<String> enviarProveedor(@RequestParam String mensaje,
                                                  @RequestParam Long idProveedor){
        sProveedor.sendMessageProveedor(mensaje,idProveedor);
        return new ResponseEntity<>("Mensaje enviado",HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProveedor(@PathVariable Long idProveedor){
        sProveedor.deleteProveedor(idProveedor);
        return new ResponseEntity<>("Eliminar proveedor",HttpStatus.NO_CONTENT);
    }
}
