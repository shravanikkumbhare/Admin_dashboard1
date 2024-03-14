package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.ConsultantRepository;
import com.example.demo.repository.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatisticsServiceTest {

    @InjectMocks
    private StatisticsService statisticsService;

    @Mock
    private ConsultantRepository consultantRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private SessionRepository sessionRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetTotalConsultants() {
        when(consultantRepository.count()).thenReturn(10L);

        long totalConsultants = statisticsService.getTotalConsultants();

        verify(consultantRepository, times(1)).count();
        verifyNoMoreInteractions(consultantRepository);

        assertEquals(10, totalConsultants);
    }

    @Test
    public void testGetTotalClients() {
        when(clientRepository.count()).thenReturn(20L);

        long totalClients = statisticsService.getTotalClients();

        verify(clientRepository, times(1)).count();
        verifyNoMoreInteractions(clientRepository);

        assertEquals(20, totalClients);
    }

    @Test
    public void testGetTotalSessions() {
        when(sessionRepository.count()).thenReturn(30L);

        long totalSessions = statisticsService.getTotalSessions();

        verify(sessionRepository, times(1)).count();
        verifyNoMoreInteractions(sessionRepository);

        assertEquals(30, totalSessions);
    }
}
