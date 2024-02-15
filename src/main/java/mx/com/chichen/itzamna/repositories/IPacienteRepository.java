package mx.com.chichen.itzamna.repositories;

import mx.com.chichen.itzamna.model.entity.PacienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPacienteRepository extends JpaRepository<PacienteModel,Long> {
}
