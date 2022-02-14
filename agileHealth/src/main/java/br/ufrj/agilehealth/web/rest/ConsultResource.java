package br.ufrj.agilehealth.web.rest;

import br.ufrj.agilehealth.repository.ConsultRepository;
import br.ufrj.agilehealth.service.ConsultService;
import br.ufrj.agilehealth.service.dto.ConsultDTO;
import br.ufrj.agilehealth.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link br.ufrj.agilehealth.domain.Consult}.
 */
@RestController
@RequestMapping("/api")
public class ConsultResource {

    private final Logger log = LoggerFactory.getLogger(ConsultResource.class);

    private final ConsultService consultService;

    private final ConsultRepository consultRepository;

    public ConsultResource(ConsultService consultService, ConsultRepository consultRepository) {
        this.consultService = consultService;
        this.consultRepository = consultRepository;
    }

    /**
     * {@code GET  /consults} : get all the consults.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of consults in body.
     */
    @GetMapping("/consults")
    public List<ConsultDTO> getAllConsults() {
        log.debug("REST request to get all Consults");
        return consultService.findAll();
    }

    /**
     * {@code GET  /consults/:id} : get the "id" consult.
     *
     * @param id the id of the consultDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the consultDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/consults/{id}")
    public ResponseEntity<ConsultDTO> getConsult(@PathVariable Long id) {
        log.debug("REST request to get Consult : {}", id);
        Optional<ConsultDTO> consultDTO = consultService.findOne(id);
        return ResponseUtil.wrapOrNotFound(consultDTO);
    }
}
