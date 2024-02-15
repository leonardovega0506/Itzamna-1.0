package mx.com.chichen.itzamna.repositories;

import mx.com.chichen.itzamna.model.entity.VentaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentaRepository extends JpaRepository<VentaModel,Long> {
}
