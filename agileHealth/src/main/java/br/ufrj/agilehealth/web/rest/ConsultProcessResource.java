package br.ufrj.agilehealth.web.rest;

import br.ufrj.agilehealth.service.ConsultProcessService;
import br.ufrj.agilehealth.service.dto.ConsultProcessDTO;
import br.ufrj.agilehealth.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link br.ufrj.agilehealth.domain.ConsultProcess}.
 */
@RestController
@RequestMapping("/api")
public class ConsultProcessResource {

    private final Logger log = LoggerFactory.getLogger(ConsultProcessResource.class);

    private static final String ENTITY_NAME = "consultProcess";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ConsultProcessService consultProcessService;

    public ConsultProcessResource(ConsultProcessService consultProcessService) {
        this.consultProcessService = consultProcessService;
    }

    /**
     * {@code POST  /consult-processes} : Create a new consultProcess.
     *
     * @param consultProcessDTO the consultProcessDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new consultProcessDTO, or with status {@code 400 (Bad Request)} if the consultProcess has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/consult-processes")
    public ResponseEntity<ConsultProcessDTO> create(@RequestBody ConsultProcessDTO consultProcessDTO) throws URISyntaxException {
        log.debug("REST request to save ConsultProcess : {}", consultProcessDTO);
        if (consultProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new consultProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConsultProcessDTO result = consultProcessService.create(consultProcessDTO);
        return ResponseEntity
            .created(new URI("/api/consult-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /consult-processes} : get all the consultProcesss.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of consultProcesss in body.
     */
    @GetMapping("/consult-processes")
    public List<ConsultProcessDTO> getAllConsultProcesss() {
        log.debug("REST request to get all ConsultProcesss");
        return consultProcessService.findAll();
    }

    /**
     * {@code GET  /consult-processes/:id} : get the "id" consultProcess.
     *
     * @param processInstanceId the id of the consultProcessDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the consultProcessDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/consult-processes/{processInstanceId}")
    public ResponseEntity<ConsultProcessDTO> getByProcessInstanceId(@PathVariable Long processInstanceId) {
        log.debug("REST request to get ConsultProcess by processInstanceId : {}", processInstanceId);
        Optional<ConsultProcessDTO> consultProcessDTO = consultProcessService.findByProcessInstanceId(processInstanceId);
        return ResponseUtil.wrapOrNotFound(consultProcessDTO);
    }
}
