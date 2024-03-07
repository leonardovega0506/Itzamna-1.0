package mx.com.chichen.itzamna.controller;

import mx.com.chichen.itzamna.model.dto.DiarioDTO;
import mx.com.chichen.itzamna.response.DiarioResponse;
import mx.com.chichen.itzamna.response.ListDetalleDiarioResponse;
import mx.com.chichen.itzamna.response.ListDiarioResponse;
import mx.com.chichen.itzamna.service.interfaces.IDiarioService;
import mx.com.chichen.itzamna.util.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chichen/itzamna/diario")
public class DiarioController {

    @Autowired
    private IDiarioService sDiario;

    @GetMapping
    public ResponseEntity<ListDiarioResponse> obtenerDiario(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idItem") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir
    ){
        return new ResponseEntity<>(sDiario.findAll(pageNumber,pageSize,orderBy,sortDir), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiarioResponse> obtenerDiarioId(@PathVariable Long idDiario){
        return new ResponseEntity<>(sDiario.findById(idDiario),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DiarioResponse> guardarDiario(@RequestBody DiarioDTO diarioDTO){
        return new ResponseEntity<>(sDiario.saveDiario(diarioDTO),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<DiarioResponse> actualizarDiario(@RequestBody DiarioDTO diarioDTO){
        return new ResponseEntity<>(sDiario.updateDiario(diarioDTO),HttpStatus.OK);
    }

    @PostMapping("/cerrar/{id}")
    public ResponseEntity<DiarioResponse> cerrarDiario(@PathVariable Long idDiario){
        return new ResponseEntity<>(sDiario.cerrarDiario(idDiario),HttpStatus.OK);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<ListDetalleDiarioResponse> obtenerDetalleDiario(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idItem") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @PathVariable Long idDiario){
        return new ResponseEntity<>(sDiario.findDetalleDiario(idDiario,pageNumber,pageSize,orderBy,sortDir),HttpStatus.OK);
    }

}
