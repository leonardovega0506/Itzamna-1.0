package mx.com.chichen.itzamna.repositories;

import mx.com.chichen.itzamna.model.entity.CompraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompraRepository extends JpaRepository<CompraModel,Long> {
}
