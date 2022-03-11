package br.ufrj.agilehealth.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link br.ufrj.agilehealth.domain.Doctor} entity.
 */
public class DoctorDTO implements Serializable {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DoctorDTO)) {
            return false;
        }

        DoctorDTO doctorDTO = (DoctorDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, doctorDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DoctorDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
