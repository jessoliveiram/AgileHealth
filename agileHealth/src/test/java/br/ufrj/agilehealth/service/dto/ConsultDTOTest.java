package br.ufrj.agilehealth.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import br.ufrj.agilehealth.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ConsultDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConsultDTO.class);
        ConsultDTO consultDTO1 = new ConsultDTO();
        consultDTO1.setId(1L);
        ConsultDTO consultDTO2 = new ConsultDTO();
        assertThat(consultDTO1).isNotEqualTo(consultDTO2);
        consultDTO2.setId(consultDTO1.getId());
        assertThat(consultDTO1).isEqualTo(consultDTO2);
        consultDTO2.setId(2L);
        assertThat(consultDTO1).isNotEqualTo(consultDTO2);
        consultDTO1.setId(null);
        assertThat(consultDTO1).isNotEqualTo(consultDTO2);
    }
}
