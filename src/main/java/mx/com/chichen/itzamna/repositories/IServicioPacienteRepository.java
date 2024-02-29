package mx.com.chichen.itzamna.repositories;

import mx.com.chichen.itzamna.model.entity.PacienteModel;
import mx.com.chichen.itzamna.model.entity.ServicioModel;
import mx.com.chichen.itzamna.model.entity.ServicioPacienteModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IServicioPacienteRepository extends JpaRepository<ServicioPacienteModel,Long> {
    long deleteByServicioAndPaciente(ServicioModel servicio, PacienteModel paciente);
    List<ServicioPacienteModel> getByPaciente_IdPaciente(Long idPaciente);
    Page<ServicioPacienteModel> findByPaciente_IdPaciente(Long idPaciente, Pageable pageable);
}
