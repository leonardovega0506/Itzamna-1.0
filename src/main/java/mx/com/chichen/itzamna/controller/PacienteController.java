package mx.com.chichen.itzamna.controller;

import mx.com.chichen.itzamna.model.dto.PacienteDTO;
import mx.com.chichen.itzamna.response.ListPacienteResponse;
import mx.com.chichen.itzamna.response.ListServicioPacienteResponse;
import mx.com.chichen.itzamna.response.PacienteResponse;
import mx.com.chichen.itzamna.service.interfaces.IPacienteService;
import mx.com.chichen.itzamna.util.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chichen/itzamna/paciente")
public class PacienteController {

    @Autowired
    private IPacienteService sPaciente;

    @GetMapping
    public ResponseEntity<ListPacienteResponse> obtenerPacientes(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idItem") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir){
        return new ResponseEntity<>(sPaciente.findAllPaciente(pageNumber,pageSize,orderBy,sortDir), HttpStatus.OK);
    }

    @GetMapping("/nombre")
    public ResponseEntity<ListPacienteResponse> obtenerPacientesByNombre(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idItem") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam(value = "nombre") String nombre){
        return new ResponseEntity<>(sPaciente.findAllPacienteByNombre(pageNumber,pageSize,orderBy,sortDir,nombre),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PacienteResponse> guardarPaciente(@RequestBody PacienteDTO pacienteDTO){
        return new ResponseEntity<>(sPaciente.savePaciente(pacienteDTO),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> obtenerPacienteById(@PathVariable Long idPaciente){
        return new ResponseEntity<>(sPaciente.findById(idPaciente),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PacienteResponse> actualizarPaciente(@RequestBody PacienteDTO pacienteDTO){
        return new ResponseEntity<>(sPaciente.updatePaciente(pacienteDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long idPaciente){
        sPaciente.deletePaciente(idPaciente);
        return new ResponseEntity<>("Eliminar Paciente",HttpStatus.OK);
    }

    @GetMapping("/servicios")
    public ResponseEntity<ListServicioPacienteResponse> obtenerServicioPaciente(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idItem") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @PathVariable Long idPaciente){
        return new ResponseEntity<>(sPaciente.findServiciosPaciente(pageNumber,pageSize,orderBy,sortDir,idPaciente),HttpStatus.OK);
    }

    @PutMapping("/assign-paciente")
    public ResponseEntity<PacienteResponse> asignarPaciente(@RequestParam Long idPaciente,
                                                            @RequestParam Long idPropietario){
        return new ResponseEntity<>(sPaciente.assingPaciente(idPropietario,idPaciente),HttpStatus.OK);
    }

    @PutMapping("/assign-servicio")
    public ResponseEntity<PacienteResponse> asignarServicio(@RequestParam Long idServicio,
                                                            @RequestParam Long idPaciente){
        return new ResponseEntity<>(sPaciente.assignServicio(idServicio,idPaciente),HttpStatus.OK);
    }

    @DeleteMapping("/servicio/{id}")
    public ResponseEntity<String> borrarPacienteServicio(@PathVariable Long idServicio,
                                                         @RequestParam Long idPaciente){
        sPaciente.deleteServicio(idServicio,idPaciente);
        return new ResponseEntity<>("Eliminar servicio",HttpStatus.OK);
    }
}
