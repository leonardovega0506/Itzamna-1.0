package mx.com.chichen.itzamna.repositories;

import mx.com.chichen.itzamna.model.entity.CitaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ICitaRepository extends JpaRepository<CitaModel,Long> {
    List<CitaModel> getByFechaCita(LocalDate fechaCita);
    Page<CitaModel> findByPaciente_IdPaciente(Long idPaciente, Pageable pageable);
    Page<CitaModel> findByEstatusCita(String estatusCita, Pageable pageable);
    Page<CitaModel> findByFechaCita(LocalDate fechaCita, Pageable pageable);
}
