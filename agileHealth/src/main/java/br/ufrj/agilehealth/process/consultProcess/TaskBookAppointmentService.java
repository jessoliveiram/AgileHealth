package br.ufrj.agilehealth.process.consultProcess;

import br.ufrj.agilehealth.repository.ConsultProcessRepository;
import br.ufrj.agilehealth.service.ConsultService;
import br.ufrj.agilehealth.service.dto.ConsultDTO;
import br.ufrj.agilehealth.service.dto.ConsultProcessDTO;
import br.ufrj.agilehealth.service.mapper.ConsultProcessMapper;
import org.akip.repository.TaskInstanceRepository;
import org.akip.service.TaskInstanceService;
import org.akip.service.dto.TaskInstanceDTO;
import org.akip.service.mapper.TaskInstanceMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskBookAppointmentService {

    private final TaskInstanceService taskInstanceService;

    private final ConsultService consultService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final ConsultProcessRepository consultProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskBookAppointmentMapper taskBookAppointmentMapper;

    private final ConsultProcessMapper consultProcessMapper;

    public TaskBookAppointmentService(
        TaskInstanceService taskInstanceService,
        ConsultService consultService,
        TaskInstanceRepository taskInstanceRepository,
        ConsultProcessRepository consultProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskBookAppointmentMapper taskBookAppointmentMapper,
        ConsultProcessMapper consultProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.consultService = consultService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.consultProcessRepository = consultProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskBookAppointmentMapper = taskBookAppointmentMapper;
        this.consultProcessMapper = consultProcessMapper;
    }

    public TaskBookAppointmentContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        ConsultProcessDTO consultProcess = consultProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskBookAppointmentMapper::toConsultProcessDTO)
            .orElseThrow();

        TaskBookAppointmentContextDTO taskBookAppointmentContext = new TaskBookAppointmentContextDTO();
        taskBookAppointmentContext.setTaskInstance(taskInstanceDTO);
        taskBookAppointmentContext.setConsultProcess(consultProcess);

        return taskBookAppointmentContext;
    }

    public TaskBookAppointmentContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskBookAppointmentContextDTO taskBookAppointmentContext) {
        ConsultDTO consultDTO = consultService.findOne(taskBookAppointmentContext.getConsultProcess().getConsult().getId()).orElseThrow();
        consultDTO.setMode(taskBookAppointmentContext.getConsultProcess().getConsult().getMode());
        consultDTO.setMedicalSpecialty(taskBookAppointmentContext.getConsultProcess().getConsult().getMedicalSpecialty());
        consultDTO.setLocal(taskBookAppointmentContext.getConsultProcess().getConsult().getLocal());
        consultDTO.setDate(taskBookAppointmentContext.getConsultProcess().getConsult().getDate());
        consultService.save(consultDTO);
    }

    public void complete(TaskBookAppointmentContextDTO taskBookAppointmentContext) {
        save(taskBookAppointmentContext);
        ConsultProcessDTO consultProcess = consultProcessRepository
            .findByProcessInstanceId(taskBookAppointmentContext.getConsultProcess().getProcessInstance().getId())
            .map(consultProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(taskBookAppointmentContext.getTaskInstance(), consultProcess);
    }
}
