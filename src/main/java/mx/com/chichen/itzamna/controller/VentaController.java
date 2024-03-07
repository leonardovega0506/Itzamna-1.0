package mx.com.chichen.itzamna.controller;

import mx.com.chichen.itzamna.model.dto.VentaDTO;
import mx.com.chichen.itzamna.response.ListVentaResponse;
import mx.com.chichen.itzamna.response.VentaResponse;
import mx.com.chichen.itzamna.service.interfaces.IVentaService;
import mx.com.chichen.itzamna.util.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/chichen/itzamna/venta")
public class VentaController {

    @Autowired
    private IVentaService sVenta;

    @GetMapping
    public ResponseEntity<ListVentaResponse> obtenerVentas(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idItem") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir){
        return new ResponseEntity<>(sVenta.findAllVentas(pageNumber,pageSize,orderBy,sortDir), HttpStatus.OK);
    }

    @GetMapping("/mes")
    public ResponseEntity<ListVentaResponse> obtenerVentasMes(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idItem") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam(value = "mes") int mes){
        return new ResponseEntity<>(sVenta.findAllVentasMonth(pageNumber,pageSize,orderBy,sortDir,mes),HttpStatus.OK);
    }

    @GetMapping("/week")
    public ResponseEntity<ListVentaResponse> obtenerVentasSemana(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idItem") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam(value = "week")int week){
        return new ResponseEntity<>(sVenta.findAllVentasWeek(pageNumber,pageSize,orderBy,sortDir,week),HttpStatus.OK);
    }

    @GetMapping("/date")
    public ResponseEntity<ListVentaResponse> obtenerVentasDate(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idItem") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam(value = "fecha")LocalDate fecha){
        return new ResponseEntity<>(sVenta.findAllVentasDate(pageNumber,pageSize,orderBy,sortDir,fecha),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VentaResponse> guardarVenta(@RequestBody VentaDTO ventaDTO){
        return new ResponseEntity<>(sVenta.saveVentas(ventaDTO),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<VentaResponse> actualizarVenta(@RequestBody VentaDTO ventaDTO){
        return new ResponseEntity<>(sVenta.updateVentas(ventaDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarVenta(@PathVariable Long idVenta){
        sVenta.deleteVentas(idVenta);
        return new ResponseEntity<>("Venta eliminada",HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponse> obtenerVentaById(@PathVariable Long idVenta){
        return new ResponseEntity<>(sVenta.findById(idVenta),HttpStatus.OK);
    }


}
