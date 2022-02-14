package br.ufrj.agilehealth.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConsultMapperTest {

    private ConsultMapper consultMapper;

    @BeforeEach
    public void setUp() {
        consultMapper = new ConsultMapperImpl();
    }
}
