package mx.com.chichen.itzamna.repositories;

import mx.com.chichen.itzamna.model.entity.DetalleVentaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetalleVentaRepository extends JpaRepository<DetalleVentaModel,Long> {
}
