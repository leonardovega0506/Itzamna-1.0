package mx.com.chichen.itzamna.service.interfaces;

import mx.com.chichen.itzamna.model.dto.CitaDTO;
import mx.com.chichen.itzamna.response.CitaResponse;
import mx.com.chichen.itzamna.response.ListCitaResponse;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ICitaService {

    ListCitaResponse findAllCitas(int numPage, int sizePage, String orderBy, String sortDir);
    ListCitaResponse findCitasByFecha(int numPage, int sizePage, String orderBy, String sortDir, LocalDate fecha);
    ListCitaResponse findCitaByEstatus(int numPage, int sizePage, String orderBy, String sortDir, String estatus);
    ListCitaResponse findCitaByPaciente(int numPage, int sizePage, String orderBy, String sortDir, Long idPaciente);
    CitaResponse findCitaById(Long idCita);
    CitaResponse verificarDisponibilidad(LocalDate fecha, LocalTime hora);
    CitaResponse cancelarCita(Long idCita);
    CitaResponse saveCita(CitaDTO cita);
    CitaResponse updateCita(CitaDTO cita);
    void deleteCita(Long idCita);
}
