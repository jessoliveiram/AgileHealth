package br.ufrj.agilehealth.process.consultProcess;

import br.ufrj.agilehealth.service.dto.ConsultProcessDTO;
import org.akip.service.dto.TaskInstanceDTO;

public class TaskBookAppointmentContextDTO {

    private ConsultProcessDTO consultProcess;
    private TaskInstanceDTO taskInstance;

    public ConsultProcessDTO getConsultProcess() {
        return consultProcess;
    }

    public void setConsultProcess(ConsultProcessDTO consultProcess) {
        this.consultProcess = consultProcess;
    }

    public TaskInstanceDTO getTaskInstance() {
        return taskInstance;
    }

    public void setTaskInstance(TaskInstanceDTO taskInstance) {
        this.taskInstance = taskInstance;
    }
}
