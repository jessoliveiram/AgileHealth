package br.ufrj.agilehealth.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.akip.service.dto.ProcessInstanceDTO;

/**
 * A DTO for the {@link br.ufrj.agilehealth.domain.ConsultProcess} entity.
 */
public class ConsultProcessDTO implements Serializable {

    private Long id;

    private ProcessInstanceDTO processInstance;

    private ConsultDTO consult;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProcessInstanceDTO getProcessInstance() {
        return processInstance;
    }

    public void setProcessInstance(ProcessInstanceDTO processInstance) {
        this.processInstance = processInstance;
    }

    public ConsultDTO getConsult() {
        return consult;
    }

    public void setConsult(ConsultDTO consult) {
        this.consult = consult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConsultProcessDTO)) {
            return false;
        }

        ConsultProcessDTO consultProcessDTO = (ConsultProcessDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, consultProcessDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ConsultProcessDTO{" +
            "id=" + getId() +
            ", processInstance=" + getProcessInstance() +
            ", consult=" + getConsult() +
            "}";
    }
}
