package br.ufrj.agilehealth.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.ufrj.agilehealth.IntegrationTest;
import br.ufrj.agilehealth.domain.Consult;
import br.ufrj.agilehealth.repository.ConsultRepository;
import br.ufrj.agilehealth.service.dto.ConsultDTO;
import br.ufrj.agilehealth.service.mapper.ConsultMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ConsultResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ConsultResourceIT {

    private static final String DEFAULT_MODE = "AAAAAAAAAA";
    private static final String UPDATED_MODE = "BBBBBBBBBB";

    private static final String DEFAULT_MEDICAL_SPECIALTY = "AAAAAAAAAA";
    private static final String UPDATED_MEDICAL_SPECIALTY = "BBBBBBBBBB";

    private static final String DEFAULT_LOCAL = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/consults";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ConsultRepository consultRepository;

    @Autowired
    private ConsultMapper consultMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restConsultMockMvc;

    private Consult consult;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Consult createEntity(EntityManager em) {
        Consult consult = new Consult()
            .mode(DEFAULT_MODE)
            .medicalSpecialty(DEFAULT_MEDICAL_SPECIALTY)
            .local(DEFAULT_LOCAL)
            .date(DEFAULT_DATE);
        return consult;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Consult createUpdatedEntity(EntityManager em) {
        Consult consult = new Consult()
            .mode(UPDATED_MODE)
            .medicalSpecialty(UPDATED_MEDICAL_SPECIALTY)
            .local(UPDATED_LOCAL)
            .date(UPDATED_DATE);
        return consult;
    }

    @BeforeEach
    public void initTest() {
        consult = createEntity(em);
    }

    @Test
    @Transactional
    void getAllConsults() throws Exception {
        // Initialize the database
        consultRepository.saveAndFlush(consult);

        // Get all the consultList
        restConsultMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(consult.getId().intValue())))
            .andExpect(jsonPath("$.[*].mode").value(hasItem(DEFAULT_MODE)))
            .andExpect(jsonPath("$.[*].medicalSpecialty").value(hasItem(DEFAULT_MEDICAL_SPECIALTY)))
            .andExpect(jsonPath("$.[*].local").value(hasItem(DEFAULT_LOCAL)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())));
    }

    @Test
    @Transactional
    void getConsult() throws Exception {
        // Initialize the database
        consultRepository.saveAndFlush(consult);

        // Get the consult
        restConsultMockMvc
            .perform(get(ENTITY_API_URL_ID, consult.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(consult.getId().intValue()))
            .andExpect(jsonPath("$.mode").value(DEFAULT_MODE))
            .andExpect(jsonPath("$.medicalSpecialty").value(DEFAULT_MEDICAL_SPECIALTY))
            .andExpect(jsonPath("$.local").value(DEFAULT_LOCAL))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingConsult() throws Exception {
        // Get the consult
        restConsultMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
