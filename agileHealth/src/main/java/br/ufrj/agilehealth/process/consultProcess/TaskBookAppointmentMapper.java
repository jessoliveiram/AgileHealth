package br.ufrj.agilehealth.process.consultProcess;

import br.ufrj.agilehealth.domain.Consult;
import br.ufrj.agilehealth.domain.ConsultProcess;
import br.ufrj.agilehealth.service.dto.ConsultDTO;
import br.ufrj.agilehealth.service.dto.ConsultProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface TaskBookAppointmentMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    ConsultProcessDTO toConsultProcessDTO(ConsultProcess consultProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "mode", source = "mode")
    @Mapping(target = "medicalSpecialty", source = "medicalSpecialty")
    @Mapping(target = "local", source = "local")
    @Mapping(target = "date", source = "date")
    ConsultDTO toConsultDTO(Consult consult);
}
