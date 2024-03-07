package mx.com.chichen.itzamna.controller;

import mx.com.chichen.itzamna.model.dto.HistoricoDTO;
import mx.com.chichen.itzamna.response.HistoricoResponse;
import mx.com.chichen.itzamna.response.ListDetalleHistoricoResponse;
import mx.com.chichen.itzamna.response.ListHistoricoResponse;
import mx.com.chichen.itzamna.service.interfaces.IHistoricoService;
import mx.com.chichen.itzamna.util.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/chichen/itzamna/historico")
public class HistoricoController {

    @Autowired
    private IHistoricoService sHistorico;

    @GetMapping
    public ResponseEntity<ListHistoricoResponse> obtenerHistorico(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idItem") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir){
        return new ResponseEntity<>(sHistorico.findAllHistorico(pageNumber,pageSize,orderBy,sortDir), HttpStatus.OK);
    }

    @GetMapping("/date")
    public ResponseEntity<ListHistoricoResponse> obtenerHistoricoDate(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idItem") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam(value = "date")LocalDate dia){
        return new ResponseEntity<>(sHistorico.findAllByDate(pageNumber,pageSize,orderBy,sortDir,dia),HttpStatus.OK);
    }

    @GetMapping("/mes")
    public ResponseEntity<ListHistoricoResponse> obtenerHistoricoMes(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idItem") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam(value = "mes") int mes){
        return new ResponseEntity<>(sHistorico.findAllByMonth(pageNumber,pageSize,orderBy,sortDir,mes),HttpStatus.OK);
    }

    @GetMapping("/week")
    public ResponseEntity<ListHistoricoResponse> obtenerHistoricoWeek(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idItem") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam(value = "week") int week){
        return new ResponseEntity<>(sHistorico.findAllByWeek(pageNumber,pageSize,orderBy,sortDir,week),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoricoResponse> obtenerHistoricoId(@PathVariable Long idHistorico){
        return new ResponseEntity<>(sHistorico.findByHistorico(idHistorico),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HistoricoResponse> guardarHistorico(@RequestBody HistoricoDTO historicoDTO){
        return new ResponseEntity<>(sHistorico.saveHistorico(historicoDTO),HttpStatus.CREATED);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<ListDetalleHistoricoResponse> obtenerHistoricoDetalles(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idItem") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @PathVariable Long idHistorio){
        return new ResponseEntity<>(sHistorico.findAllDetallesHistorico(pageNumber,pageSize,orderBy,sortDir,idHistorio),HttpStatus.OK);
    }
}
