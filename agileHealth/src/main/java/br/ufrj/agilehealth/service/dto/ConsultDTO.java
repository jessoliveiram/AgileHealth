package br.ufrj.agilehealth.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link br.ufrj.agilehealth.domain.Consult} entity.
 */
public class ConsultDTO implements Serializable {

    private Long id;

    private String mode;

    private String medicalSpecialty;

    private String local;

    private String doctorName;

    private LocalDate date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getMedicalSpecialty() {
        return medicalSpecialty;
    }

    public void setMedicalSpecialty(String medicalSpecialty) {
        this.medicalSpecialty = medicalSpecialty;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConsultDTO)) {
            return false;
        }

        ConsultDTO consultDTO = (ConsultDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, consultDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ConsultDTO{" +
            "id=" + getId() +
            ", mode='" + getMode() + "'" +
            ", medicalSpecialty='" + getMedicalSpecialty() + "'" +
            ", local='" + getLocal() + "'" +
            ", doctorName='" + getDoctorName() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }
}
