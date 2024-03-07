package mx.com.chichen.itzamna.controller;

import mx.com.chichen.itzamna.model.dto.CitaDTO;
import mx.com.chichen.itzamna.response.CitaResponse;
import mx.com.chichen.itzamna.response.ListCitaResponse;
import mx.com.chichen.itzamna.service.interfaces.ICitaService;
import mx.com.chichen.itzamna.util.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;

@RestController
@RequestMapping("chichen/itzamna/citas")
public class CitaController {

    @Autowired
    private ICitaService sCita;

    @GetMapping
    public ResponseEntity<ListCitaResponse> obtenerCitas(
            @RequestParam(value = "pageNo",defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO)int pageSize,
            @RequestParam(value = "orderBy",defaultValue = "idCita") String orderBy,
            @RequestParam(value = "sortDir",defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir){
        ListCitaResponse response = sCita.findAllCitas(pageNumber,pageSize,orderBy,sortDir);
        if(response.getContent().size()>0){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/fecha")
    public ResponseEntity<ListCitaResponse> obtenerCitasDate(
            @RequestParam(value = "pageNo",defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy",defaultValue = "idCita") String orderBy,
            @RequestParam(value = "sortDir",defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam(value = "fecha") LocalDate fecha) {
        ListCitaResponse response = sCita.findCitasByFecha(pageNumber,pageSize,orderBy,sortDir,fecha);
        if(response.getContent().size()>0){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/estatus")
    public ResponseEntity<ListCitaResponse> obtenerCitaEstatus(
            @RequestParam(value = "pageNo",defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy",defaultValue = "idCita") String orderBy,
            @RequestParam(value = "sortDir",defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam(value = "estatus") String estatus){
        ListCitaResponse response = sCita.findCitaByEstatus(pageNumber,pageSize,orderBy,sortDir,estatus);
        if(response.getContent().size()>0){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/paciente")
    public ResponseEntity<ListCitaResponse> obtenerCitaPaciente(
            @RequestParam(value = "pageNo",defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy",defaultValue = "idCita") String orderBy,
            @RequestParam(value = "sortDir",defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam(value = "idPaciente") Long idPaciente){
        ListCitaResponse response = sCita.findCitaByPaciente(pageNumber,pageSize,orderBy,sortDir,idPaciente);
        if(response.getContent().size()>0){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{idCita}")
    public ResponseEntity<CitaResponse> obtenerCitaById(@PathVariable(value = "idCita") Long idCita){
        CitaResponse response = sCita.findCitaById(idCita);
        if(response.getCode()==0){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/disponibilidad")
    public ResponseEntity<CitaResponse> verificarDisponibilidad(
            @RequestParam LocalDate fecha,
            @RequestParam String hora){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        CitaResponse response = sCita.verificarDisponibilidad(fecha,LocalTime.parse(hora,formatter));

        if(response.getCode() == 0){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(response,HttpStatus.FOUND);
        }
    }

    @PutMapping("/cancelar/{idCita}")
    public ResponseEntity<CitaResponse> cancelarCita(@PathVariable(value = "idCita") Long idCita){
        CitaResponse response = sCita.cancelarCita(idCita);
        if(response.getCode() == 0){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<CitaResponse> guardarCita(@RequestBody CitaDTO citaDTO){
        return new ResponseEntity<>(sCita.saveCita(citaDTO),HttpStatus.CREATED);
    }

    @PostMapping("/message/{id}")
    public ResponseEntity<String> enviarMensaje(@RequestParam String mensaje,@RequestParam Long idCita){
        sCita.sendMessageProveedor(mensaje,idCita);
        return new ResponseEntity<>("Mensaje enviado",HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CitaResponse> actualizarCita(@RequestBody CitaDTO citaDTO){
        return new ResponseEntity<>(sCita.updateCita(citaDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{idCita}")
    public ResponseEntity<String> eliminarCita(@PathVariable(value = "idCita") Long idCita){
        sCita.deleteCita(idCita);
        return new ResponseEntity<>("Cita Eliminada",HttpStatus.NO_CONTENT);
    }


}
