package mx.com.chichen.itzamna.repositories;

import mx.com.chichen.itzamna.model.entity.DiarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDiarioRepository extends JpaRepository<DiarioModel,Long> {
}
