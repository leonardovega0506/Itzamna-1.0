package mx.com.chichen.itzamna.repositories;

import mx.com.chichen.itzamna.model.entity.ProveedorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProveedorRepository extends JpaRepository<ProveedorModel,Long> {
}
