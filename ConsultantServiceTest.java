package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.model.Consultant;
import com.example.demo.repository.ConsultantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;

public class ConsultantServiceTest {

    @InjectMocks
    private ConsultantService consultantService;

    @Mock
    private ConsultantRepository consultantRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllConsultants() {
        Consultant consultant1 = new Consultant();
        consultant1.setId(1L);
        consultant1.setName("John Doe");

        Consultant consultant2 = new Consultant();
        consultant2.setId(2L);
        consultant2.setName("Jane Smith");

        List<Consultant> consultants = Arrays.asList(consultant1, consultant2);

        when(consultantRepository.findAll()).thenReturn(consultants);

        List<Consultant> result = consultantService.getAllConsultants();

        verify(consultantRepository, times(1)).findAll();
        verifyNoMoreInteractions(consultantRepository);

        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Smith", result.get(1).getName());
    }

    // Add more test methods for other service methods
}
