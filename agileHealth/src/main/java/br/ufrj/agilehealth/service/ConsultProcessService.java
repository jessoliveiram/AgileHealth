package br.ufrj.agilehealth.service;

import br.ufrj.agilehealth.domain.ConsultProcess;
import br.ufrj.agilehealth.repository.ConsultProcessRepository;
import br.ufrj.agilehealth.repository.ConsultRepository;
import br.ufrj.agilehealth.service.dto.ConsultProcessDTO;
import br.ufrj.agilehealth.service.mapper.ConsultProcessMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.akip.domain.ProcessInstance;
import org.akip.service.ProcessInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ConsultProcess}.
 */
@Service
@Transactional
public class ConsultProcessService {

    public static final String BPMN_PROCESS_DEFINITION_ID = "ConsultProcess";

    private final Logger log = LoggerFactory.getLogger(ConsultProcessService.class);

    private final ProcessInstanceService processInstanceService;

    private final ConsultRepository consultRepository;

    private final ConsultProcessRepository consultProcessRepository;

    private final ConsultProcessMapper consultProcessMapper;

    public ConsultProcessService(
        ProcessInstanceService processInstanceService,
        ConsultRepository consultRepository,
        ConsultProcessRepository consultProcessRepository,
        ConsultProcessMapper consultProcessMapper
    ) {
        this.processInstanceService = processInstanceService;
        this.consultRepository = consultRepository;
        this.consultProcessRepository = consultProcessRepository;
        this.consultProcessMapper = consultProcessMapper;
    }

    /**
     * Save a consultProcess.
     *
     * @param consultProcessDTO the entity to save.
     * @return the persisted entity.
     */
    public ConsultProcessDTO create(ConsultProcessDTO consultProcessDTO) {
        log.debug("Request to save ConsultProcess : {}", consultProcessDTO);

        ConsultProcess consultProcess = consultProcessMapper.toEntity(consultProcessDTO);

        //Saving the domainEntity
        consultRepository.save(consultProcess.getConsult());

        //Creating the process instance in the Camunda and setting it in the process entity
        ProcessInstance processInstance = processInstanceService.create(
            BPMN_PROCESS_DEFINITION_ID,
            "Consult#" + consultProcess.getConsult().getId(),
            consultProcess
        );
        consultProcess.setProcessInstance(processInstance);

        //Saving the process entity
        consultProcess = consultProcessRepository.save(consultProcess);
        return consultProcessMapper.toDto(consultProcess);
    }

    /**
     * Get all the consultProcesss.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ConsultProcessDTO> findAll() {
        log.debug("Request to get all ConsultProcesss");
        return consultProcessRepository
            .findAll()
            .stream()
            .map(consultProcessMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one consultProcess by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ConsultProcessDTO> findOne(Long id) {
        log.debug("Request to get ConsultProcess : {}", id);
        return consultProcessRepository.findById(id).map(consultProcessMapper::toDto);
    }

    /**
     * Get one consultProcess by id.
     *
     * @param processInstanceId the id of the processInstance associated to the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ConsultProcessDTO> findByProcessInstanceId(Long processInstanceId) {
        log.debug("Request to get ConsultProcess by  processInstanceId: {}", processInstanceId);
        return consultProcessRepository.findByProcessInstanceId(processInstanceId).map(consultProcessMapper::toDto);
    }
}
