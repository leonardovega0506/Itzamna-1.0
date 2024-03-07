package mx.com.chichen.itzamna.controller;

import mx.com.chichen.itzamna.model.dto.ServicioDTO;
import mx.com.chichen.itzamna.response.ListServicioResponse;
import mx.com.chichen.itzamna.response.ServicioResponse;
import mx.com.chichen.itzamna.service.interfaces.IServicioService;
import mx.com.chichen.itzamna.util.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chichen/itzamna/servicio")
public class ServicioController {

    @Autowired
    private IServicioService sServicio;

    @GetMapping
    public ResponseEntity<ListServicioResponse> obtenerServicios(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idItem") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir){
        return new ResponseEntity<>(sServicio.findAllPaciente(pageNumber,pageSize,orderBy,sortDir), HttpStatus.OK);
    }

    @GetMapping("/clave")
    public ResponseEntity<ListServicioResponse> obtenerServiciosClave(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idItem") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam(value = "clave") String clave){
        return new ResponseEntity<>(sServicio.findAllPacienteByClave(pageNumber,pageSize,orderBy,sortDir,clave),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ServicioResponse> guardarServicio(@RequestBody ServicioDTO servicioDTO){
        return new ResponseEntity<>(sServicio.saveServicio(servicioDTO),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ServicioResponse> actualizarServicio(@RequestBody ServicioDTO servicioDTO){
        return new ResponseEntity<>(sServicio.updateServicio(servicioDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarServicio(@PathVariable Long idServicio){
        sServicio.deleteService(idServicio);
        return new ResponseEntity<>("Servicio eliminado",HttpStatus.NO_CONTENT);
    }
}
