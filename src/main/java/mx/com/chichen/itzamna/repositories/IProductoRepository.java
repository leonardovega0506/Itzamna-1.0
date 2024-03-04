package mx.com.chichen.itzamna.repositories;

import mx.com.chichen.itzamna.model.entity.ProductoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoRepository extends JpaRepository<ProductoModel,Long> {
    List<ProductoModel> findByProveedor_IdProveedor(Long idProveedor);
    Page<ProductoModel> findByClaveProductoLike(String claveProducto, Pageable pageable);
    Page<ProductoModel> findByNombreProductoLike(String nombreProducto, Pageable pageable);
}
