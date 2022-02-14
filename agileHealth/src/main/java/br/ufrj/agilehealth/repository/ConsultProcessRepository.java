package br.ufrj.agilehealth.repository;

import br.ufrj.agilehealth.domain.ConsultProcess;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ConsultProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConsultProcessRepository extends JpaRepository<ConsultProcess, Long> {
    Optional<ConsultProcess> findByProcessInstanceId(Long processInstanceId);
}
