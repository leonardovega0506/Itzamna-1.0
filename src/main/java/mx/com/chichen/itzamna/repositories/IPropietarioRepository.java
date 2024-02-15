package mx.com.chichen.itzamna.repositories;

import mx.com.chichen.itzamna.model.entity.PropietarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPropietarioRepository extends JpaRepository<PropietarioModel,Long> {
}
