package mx.com.chichen.itzamna.repositories;

import mx.com.chichen.itzamna.model.entity.HistoricoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHistoricoRepository extends JpaRepository<HistoricoModel,Long> {
}
