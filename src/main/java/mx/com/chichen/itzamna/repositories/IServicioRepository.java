package mx.com.chichen.itzamna.repositories;

import mx.com.chichen.itzamna.model.entity.ServicioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IServicioRepository extends JpaRepository<ServicioModel,Long> {
}
