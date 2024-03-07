package mx.com.chichen.itzamna.controller;

import mx.com.chichen.itzamna.model.dto.CompraDTO;
import mx.com.chichen.itzamna.response.CitaResponse;
import mx.com.chichen.itzamna.response.CompraResponse;
import mx.com.chichen.itzamna.response.ListCompraResponse;
import mx.com.chichen.itzamna.response.ListDetalleCompraResponse;
import mx.com.chichen.itzamna.service.interfaces.ICompraService;
import mx.com.chichen.itzamna.util.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/chichen/itzamna/compras")
public class CompraController {

    @Autowired
    private ICompraService sCompra;

    @GetMapping
    public ResponseEntity<ListCompraResponse> obtenerCompras(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idCompra") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir
    ) {
        ListCompraResponse response = sCompra.findAll(pageNumber, pageSize, orderBy, sortDir);
        if (response.getCode() == 0) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/fecha")
    public ResponseEntity<ListCompraResponse> obtenerComprasFecha(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idCompra") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam(value = "fecha") LocalDate fecha) {
        ListCompraResponse response = sCompra.findAllByFecha(pageNumber, pageSize, orderBy, sortDir, fecha);
        if (response.getCode()==0){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/mes")
    public ResponseEntity<ListCompraResponse> obtenerCompraMes(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idCompra") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam(value = "mes") int mes) {
        return new ResponseEntity<>(sCompra.findAllByMonth(pageNumber, pageSize, orderBy, sortDir, mes), HttpStatus.OK);
    }

    @GetMapping("/{idCompra}")
    public ResponseEntity<CompraResponse> obtenerCompraByID(@PathVariable(value = "idCompra") Long idCompra) {
        return new ResponseEntity<>(sCompra.findById(idCompra), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CompraResponse> guardarCompra(@RequestBody CompraDTO compraDTO) {
        return new ResponseEntity<>(sCompra.saveCompra(compraDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CompraResponse> actualizarCompra(@RequestBody CompraDTO compraDTO) {
        return new ResponseEntity<>(sCompra.updateCompra(compraDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompra(@PathVariable Long idCompra) {
        sCompra.deleteCompra(idCompra);
        return new ResponseEntity<>("Compra eliminada", HttpStatus.OK);
    }

    @GetMapping("/detalles/{id}")
    public ResponseEntity<ListDetalleCompraResponse> obtenerDetallesCompra(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idItem") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @PathVariable Long idCompra
    ) {
        return new ResponseEntity<>(sCompra.findDetallesCompra(idCompra, pageNumber, pageSize, orderBy, sortDir), HttpStatus.OK);
    }
}
