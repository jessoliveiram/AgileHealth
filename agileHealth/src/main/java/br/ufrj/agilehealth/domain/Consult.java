package br.ufrj.agilehealth.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Consult.
 */
@Entity
@Table(name = "consult")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Consult implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mode")
    private String mode;

    @Column(name = "medical_specialty")
    private String medicalSpecialty;

    @Column(name = "local")
    private String local;

    @Column(name = "doctor_name")
    private String doctorName;

    @Column(name = "date")
    private LocalDate date;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Consult id(Long id) {
        this.id = id;
        return this;
    }

    public String getMode() {
        return this.mode;
    }

    public Consult mode(String mode) {
        this.mode = mode;
        return this;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getMedicalSpecialty() {
        return this.medicalSpecialty;
    }

    public Consult medicalSpecialty(String medicalSpecialty) {
        this.medicalSpecialty = medicalSpecialty;
        return this;
    }

    public void setMedicalSpecialty(String medicalSpecialty) {
        this.medicalSpecialty = medicalSpecialty;
    }

    public String getLocal() {
        return this.local;
    }

    public Consult local(String local) {
        this.local = local;
        return this;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDoctorName() {
        return this.doctorName;
    }

    public Consult doctorName(String doctorName) {
        this.doctorName = doctorName;
        return this;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public Consult date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Consult)) {
            return false;
        }
        return id != null && id.equals(((Consult) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Consult{" +
            "id=" + getId() +
            ", mode='" + getMode() + "'" +
            ", medicalSpecialty='" + getMedicalSpecialty() + "'" +
            ", local='" + getLocal() + "'" +
            ", doctorName='" + getDoctorName() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }
}
