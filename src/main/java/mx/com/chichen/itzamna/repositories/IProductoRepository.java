package mx.com.chichen.itzamna.repositories;

import mx.com.chichen.itzamna.model.entity.ProductoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends JpaRepository<ProductoModel,Long> {
}