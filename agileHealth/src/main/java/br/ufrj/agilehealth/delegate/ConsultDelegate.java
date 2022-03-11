package br.ufrj.agilehealth.delegate;

import br.ufrj.agilehealth.service.DoctorService;
import br.ufrj.agilehealth.service.dto.ConsultProcessDTO;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultDelegate implements JavaDelegate {

    @Autowired
    DoctorService doctorService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        ConsultProcessDTO consultProcess = (ConsultProcessDTO) delegateExecution.getVariable("processInstance");
        doctorService.confirmConsult(consultProcess.getConsult().getDate());
    }
}
