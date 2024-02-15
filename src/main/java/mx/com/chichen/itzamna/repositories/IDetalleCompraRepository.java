package mx.com.chichen.itzamna.repositories;

import mx.com.chichen.itzamna.model.entity.DetalleCompraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetalleCompraRepository extends JpaRepository<DetalleCompraModel,Long> {
}
