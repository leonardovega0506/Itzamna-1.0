package mx.com.chichen.itzamna.service.interfaces;

import mx.com.chichen.itzamna.model.dto.VentaDTO;
import mx.com.chichen.itzamna.response.ListVentaResponse;
import mx.com.chichen.itzamna.response.VentaResponse;

import java.time.LocalDate;

public interface IVentaService {

    ListVentaResponse findAllVentas(int numPage, int sizePage, String orderBy, String sortDir);
    ListVentaResponse findAllVentasMonth(int numPage, int sizePage, String orderBy, String sortDir,int month);
    ListVentaResponse findAllVentasWeek(int numPage, int sizePage, String orderBy, String sortDir,int week);
    ListVentaResponse findAllVentasDate(int numPage, int sizePage, String orderBy, String sortDir, LocalDate fecha);
    VentaResponse saveVentas(VentaDTO ventaDTO);
    VentaResponse updateVentas(VentaDTO ventaDTO);
    VentaResponse deleteVentas(Long idVenta);
    VentaResponse findById(Long idVenta);

}
