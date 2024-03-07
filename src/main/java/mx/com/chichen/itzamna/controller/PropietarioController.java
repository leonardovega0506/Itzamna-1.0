package mx.com.chichen.itzamna.controller;

import mx.com.chichen.itzamna.model.dto.PropietarioDTO;
import mx.com.chichen.itzamna.response.ListPropietarioResponse;
import mx.com.chichen.itzamna.response.PropietarioResponse;
import mx.com.chichen.itzamna.service.interfaces.IPropietarioService;
import mx.com.chichen.itzamna.util.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chichen/itzamna/propietario")
public class PropietarioController {

    @Autowired
    private IPropietarioService sPropietario;

    @GetMapping
    public ResponseEntity<ListPropietarioResponse> findAll(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idItem") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir) {
        return new ResponseEntity<>(sPropietario.findAll(pageNumber, pageSize, orderBy, sortDir), HttpStatus.OK);
    }

    @GetMapping("/nombre")
    public ResponseEntity<ListPropietarioResponse> findAllNombre(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idItem") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam(value = "nombre") String nombre) {
        return new ResponseEntity<>(sPropietario.findAllByNombre(pageNumber, pageSize, orderBy, sortDir, nombre), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PropietarioResponse> guardarPropietario(@RequestBody PropietarioDTO propietarioDTO) {
        return new ResponseEntity<>(sPropietario.savePropietario(propietarioDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropietarioResponse> obtenerPropietario(@PathVariable Long idPropietario) {
        return new ResponseEntity<>(sPropietario.findById(idPropietario), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PropietarioResponse> actualizarPropietario(@RequestBody PropietarioDTO propietarioDTO) {
        return new ResponseEntity<>(sPropietario.updatePropietario(propietarioDTO), HttpStatus.OK);
    }

    @PostMapping("/enviar-mensaje")
    public ResponseEntity<String> enviarMensaje(@RequestParam String mensaje,
                                                @RequestParam Long idPropietario){
        sPropietario.sendMessagePropietario(mensaje,idPropietario);
        return new ResponseEntity<>("Mensaje enviado",HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPropietario(@PathVariable Long idPropietario){
        sPropietario.deletePropietario(idPropietario);
        return new ResponseEntity<>("Propietario eliminado",HttpStatus.NO_CONTENT);
    }
}
