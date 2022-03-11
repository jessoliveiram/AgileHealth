package br.ufrj.agilehealth.service.mapper;

import br.ufrj.agilehealth.domain.*;
import br.ufrj.agilehealth.service.dto.ConsultDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Consult} and its DTO {@link ConsultDTO}.
 */
@Mapper(componentModel = "spring", uses = { DoctorMapper.class })
public interface ConsultMapper extends EntityMapper<ConsultDTO, Consult> {
    @Mapping(target = "doctor", source = "doctor", qualifiedByName = "name")
    ConsultDTO toDto(Consult s);
}
