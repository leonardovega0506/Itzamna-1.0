package mx.com.chichen.itzamna.repositories;

import mx.com.chichen.itzamna.model.entity.DetalleDiarioModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetalleDiarioRepository extends JpaRepository<DetalleDiarioModel,Long> {
    Page<DetalleDiarioModel> findByDiario_IdDiario(Long idDiario, Pageable pageable);
}
