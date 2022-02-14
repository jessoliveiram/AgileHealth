package br.ufrj.agilehealth.domain;

import static org.assertj.core.api.Assertions.assertThat;

import br.ufrj.agilehealth.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ConsultTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Consult.class);
        Consult consult1 = new Consult();
        consult1.setId(1L);
        Consult consult2 = new Consult();
        consult2.setId(consult1.getId());
        assertThat(consult1).isEqualTo(consult2);
        consult2.setId(2L);
        assertThat(consult1).isNotEqualTo(consult2);
        consult1.setId(null);
        assertThat(consult1).isNotEqualTo(consult2);
    }
}
