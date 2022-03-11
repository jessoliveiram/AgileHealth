package br.ufrj.agilehealth.service.mapper;

import br.ufrj.agilehealth.domain.*;
import br.ufrj.agilehealth.service.dto.DoctorDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Doctor} and its DTO {@link DoctorDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DoctorMapper extends EntityMapper<DoctorDTO, Doctor> {
    @Named("name")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    DoctorDTO toDtoName(Doctor doctor);
}
