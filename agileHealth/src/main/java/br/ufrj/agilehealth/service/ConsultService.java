package br.ufrj.agilehealth.service;

import br.ufrj.agilehealth.domain.Consult;
import br.ufrj.agilehealth.repository.ConsultRepository;
import br.ufrj.agilehealth.service.dto.ConsultDTO;
import br.ufrj.agilehealth.service.mapper.ConsultMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Consult}.
 */
@Service
@Transactional
public class ConsultService {

    private final Logger log = LoggerFactory.getLogger(ConsultService.class);

    private final ConsultRepository consultRepository;

    private final ConsultMapper consultMapper;

    public ConsultService(ConsultRepository consultRepository, ConsultMapper consultMapper) {
        this.consultRepository = consultRepository;
        this.consultMapper = consultMapper;
    }

    /**
     * Save a consult.
     *
     * @param consultDTO the entity to save.
     * @return the persisted entity.
     */
    public ConsultDTO save(ConsultDTO consultDTO) {
        log.debug("Request to save Consult : {}", consultDTO);
        Consult consult = consultMapper.toEntity(consultDTO);
        consult = consultRepository.save(consult);
        return consultMapper.toDto(consult);
    }

    /**
     * Partially update a consult.
     *
     * @param consultDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ConsultDTO> partialUpdate(ConsultDTO consultDTO) {
        log.debug("Request to partially update Consult : {}", consultDTO);

        return consultRepository
            .findById(consultDTO.getId())
            .map(
                existingConsult -> {
                    consultMapper.partialUpdate(existingConsult, consultDTO);
                    return existingConsult;
                }
            )
            .map(consultRepository::save)
            .map(consultMapper::toDto);
    }

    /**
     * Get all the consults.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ConsultDTO> findAll() {
        log.debug("Request to get all Consults");
        return consultRepository.findAll().stream().map(consultMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one consult by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ConsultDTO> findOne(Long id) {
        log.debug("Request to get Consult : {}", id);
        return consultRepository.findById(id).map(consultMapper::toDto);
    }

    /**
     * Delete the consult by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Consult : {}", id);
        consultRepository.deleteById(id);
    }
}
