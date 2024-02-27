package mx.com.chichen.itzamna.service.interfaces;

import mx.com.chichen.itzamna.model.dto.CompraDTO;
import mx.com.chichen.itzamna.response.CompraResponse;
import mx.com.chichen.itzamna.response.ListCompraResponse;
import mx.com.chichen.itzamna.response.ListDetalleCompraResponse;

import java.time.LocalDate;

public interface ICompraService {

    ListCompraResponse findAll(int numPage, int sizePage, String orderBy, String sortDir);
    ListCompraResponse findAllByFecha(int numPage, int sizePage, String orderBy, String sortDir, LocalDate fecha);
    ListCompraResponse findAllByMonth(int numPage, int sizePage, String orderBy, String sortDir,int mes);
    CompraResponse findById(Long idCompra);
    CompraResponse saveCompra(CompraDTO compraDTO);
    CompraResponse updateCompra(CompraDTO compraDTO);
    void deleteCompra(Long idCompra);
    ListDetalleCompraResponse findDetallesCompra(Long idCompra,int numPage, int sizePage, String orderBy, String sortDir,Long idDetalle);
}
