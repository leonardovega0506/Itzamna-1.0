package mx.com.chichen.itzamna.repositories;

import mx.com.chichen.itzamna.model.entity.ResponsivaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IResponsivaRepository extends JpaRepository<ResponsivaModel,Long> {
    Page<ResponsivaModel> findByServicio_CategoriaServicio(String categoriaServicio, Pageable pageable);
    Page<ResponsivaModel> findByPaciente_IdPaciente(Long idPaciente, Pageable pageable);
}
