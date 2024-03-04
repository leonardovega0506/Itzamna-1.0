package mx.com.chichen.itzamna.repositories;

import mx.com.chichen.itzamna.model.entity.CompraModel;
import mx.com.chichen.itzamna.model.entity.VentaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface IVentaRepository extends JpaRepository<VentaModel,Long> {
    Page<VentaModel> findByFechaVenta(LocalDate fechaVenta, Pageable pageable);
    Page<VentaModel> findByWeek(Integer week, Pageable pageable);

    @Query("SELECT c FROM VentaModel c WHERE MONTH(c.fechaVenta) = :mes")
    Page<VentaModel> findByMes(int mes, Pageable pageable);
}
