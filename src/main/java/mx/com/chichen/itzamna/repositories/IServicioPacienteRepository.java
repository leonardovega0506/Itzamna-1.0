package mx.com.chichen.itzamna.repositories;

import mx.com.chichen.itzamna.model.entity.ServicioPacienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IServicioPacienteRepository extends JpaRepository<ServicioPacienteModel,Long> {
}
