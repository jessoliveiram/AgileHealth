package br.ufrj.agilehealth.service.mapper;

import br.ufrj.agilehealth.domain.*;
import br.ufrj.agilehealth.service.dto.ConsultProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ConsultProcess} and its DTO {@link ConsultProcessDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class, ConsultMapper.class })
public interface ConsultProcessMapper extends EntityMapper<ConsultProcessDTO, ConsultProcess> {
    @Mapping(target = "processInstance", source = "processInstance")
    @Mapping(target = "consult", source = "consult")
    ConsultProcessDTO toDto(ConsultProcess s);
}
