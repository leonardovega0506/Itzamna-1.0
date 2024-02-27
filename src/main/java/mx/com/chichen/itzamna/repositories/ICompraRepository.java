package mx.com.chichen.itzamna.repositories;

import mx.com.chichen.itzamna.model.entity.CompraModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ICompraRepository extends JpaRepository<CompraModel,Long> {
    Page<CompraModel> findByFechaCompra(LocalDate fechaCompra, Pageable pageable);

    @Query("SELECT c FROM CompraModel c WHERE MONTH(c.fechaCompra) = :mes")
    Page<CompraModel> findByMes(int mes, Pageable pageable);
}
