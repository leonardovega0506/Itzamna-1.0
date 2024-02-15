package mx.com.chichen.itzamna.repositories;

import mx.com.chichen.itzamna.model.entity.CitaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICitaRepository extends JpaRepository<CitaModel,Long> {
}
