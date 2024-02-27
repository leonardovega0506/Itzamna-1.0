package mx.com.chichen.itzamna.repositories;

import mx.com.chichen.itzamna.model.entity.HistoricoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IHistoricoRepository extends JpaRepository<HistoricoModel,Long> {
    @Query("SELECT c FROM HistoricoModel c WHERE MONTH(c.fechaHistorico) = :mes")
    Page<HistoricoModel> findByMes(int mes, Pageable pageable);
}
