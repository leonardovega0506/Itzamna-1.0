package mx.com.chichen.itzamna.controller;

import mx.com.chichen.itzamna.model.dto.ResponsivaDTO;
import mx.com.chichen.itzamna.response.ListResponsivaResponse;
import mx.com.chichen.itzamna.response.ResponsivaResponse;
import mx.com.chichen.itzamna.service.interfaces.IResponsivaService;
import mx.com.chichen.itzamna.util.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chichen/itzamna/responsiva")
public class ResponsivaController {

    @Autowired
    private IResponsivaService sResponsiva;

    @GetMapping
    public ResponseEntity<ListResponsivaResponse> obtenerResponsiva(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idItem") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir){
        return new ResponseEntity<>(sResponsiva.findAllResponsiva(pageNumber,pageSize,orderBy,sortDir), HttpStatus.OK);
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<ListResponsivaResponse> obtenerResponsivaPaciente(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idItem") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam Long idPaciente){
        return new ResponseEntity<>(sResponsiva.findAllResponsivaByPaciente(pageNumber,pageSize,orderBy,sortDir,idPaciente),HttpStatus.OK);
    }

    @GetMapping("/categoria")
    public ResponseEntity<ListResponsivaResponse> obtenerResponsivaCategoria(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idItem") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam(value = "categoria")String categoria){
        return new ResponseEntity<>(sResponsiva.findAllResponsivaByCategoria(pageNumber,pageSize,orderBy,sortDir,categoria),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponsivaResponse> guardarResponsiva(@RequestBody ResponsivaDTO responsivaDTO){
        return new ResponseEntity<>(sResponsiva.saveResponsiva(responsivaDTO),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarResponsiva(@PathVariable Long idResponsiva){
        sResponsiva.deleteResponsiva(idResponsiva);
        return new ResponseEntity<>("Responsiva eliminada",HttpStatus.NO_CONTENT);
    }
}
