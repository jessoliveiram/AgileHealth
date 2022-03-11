package br.ufrj.agilehealth.service;

import br.ufrj.agilehealth.domain.Doctor;
import br.ufrj.agilehealth.repository.DoctorRepository;
import br.ufrj.agilehealth.service.dto.DoctorDTO;
import br.ufrj.agilehealth.service.mapper.DoctorMapper;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Doctor}.
 */
@Service
@Transactional
public class DoctorService {

    private final Logger log = LoggerFactory.getLogger(DoctorService.class);

    private final DoctorRepository doctorRepository;

    private final DoctorMapper doctorMapper;

    public DoctorService(DoctorRepository doctorRepository, DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
    }

    /**
     * Save a doctor.
     *
     * @param doctorDTO the entity to save.
     * @return the persisted entity.
     */
    public DoctorDTO save(DoctorDTO doctorDTO) {
        log.debug("Request to save Doctor : {}", doctorDTO);
        Doctor doctor = doctorMapper.toEntity(doctorDTO);
        doctor = doctorRepository.save(doctor);
        return doctorMapper.toDto(doctor);
    }

    /**
     * Partially update a doctor.
     *
     * @param doctorDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DoctorDTO> partialUpdate(DoctorDTO doctorDTO) {
        log.debug("Request to partially update Doctor : {}", doctorDTO);

        return doctorRepository
            .findById(doctorDTO.getId())
            .map(
                existingDoctor -> {
                    doctorMapper.partialUpdate(existingDoctor, doctorDTO);
                    return existingDoctor;
                }
            )
            .map(doctorRepository::save)
            .map(doctorMapper::toDto);
    }

    /**
     * Get all the doctors.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DoctorDTO> findAll() {
        log.debug("Request to get all Doctors");
        return doctorRepository.findAll().stream().map(doctorMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one doctor by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DoctorDTO> findOne(Long id) {
        log.debug("Request to get Doctor : {}", id);
        return doctorRepository.findById(id).map(doctorMapper::toDto);
    }

    /**
     * Delete the doctor by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Doctor : {}", id);
        doctorRepository.deleteById(id);
    }

    public void confirmConsult(LocalDate date) {
        System.out.println("##############" + date.toString());
    }
}
