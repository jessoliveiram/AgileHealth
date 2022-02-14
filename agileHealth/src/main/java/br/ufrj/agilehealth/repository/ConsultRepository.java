package br.ufrj.agilehealth.repository;

import br.ufrj.agilehealth.domain.Consult;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Consult entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConsultRepository extends JpaRepository<Consult, Long> {}
