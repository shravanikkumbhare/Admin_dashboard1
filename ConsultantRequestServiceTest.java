package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.model.ConsultantRequest;
import com.example.demo.model.RequestStatus;
import com.example.demo.repository.ConsultantRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;

public class ConsultantRequestServiceTest {

    @InjectMocks
    private ConsultantRequestService consultantRequestService;

    @Mock
    private ConsultantRequestRepository consultantRequestRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllConsultantRequests() {
        ConsultantRequest request1 = new ConsultantRequest();
        request1.setId(1L);
        request1.setName("John Doe");

        ConsultantRequest request2 = new ConsultantRequest();
        request2.setId(2L);
        request2.setName("Jane Smith");

        List<ConsultantRequest> requests = Arrays.asList(request1, request2);

        when(consultantRequestRepository.findAll()).thenReturn(requests);

        List<ConsultantRequest> result = consultantRequestService.getAllConsultantRequests();

        verify(consultantRequestRepository, times(1)).findAll();
        verifyNoMoreInteractions(consultantRequestRepository);

        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Smith", result.get(1).getName());
    }

    @Test
    public void testApproveConsultantRequest() {
        ConsultantRequest request = new ConsultantRequest();
        request.setId(1L);
        request.setName("John Doe");

        when(consultantRequestRepository.findById(1L)).thenReturn(java.util.Optional.of(request));

        consultantRequestService.approveConsultantRequest(1L);

        verify(consultantRequestRepository, times(1)).findById(1L);
        verify(consultantRequestRepository, times(1)).save(request);
        verifyNoMoreInteractions(consultantRequestRepository);

        assertEquals(RequestStatus.APPROVED, request.getStatus());
    }

    @Test
    public void testRejectConsultantRequest() {
        ConsultantRequest request = new ConsultantRequest();
        request.setId(1L);
        request.setName("John Doe");

        when(consultantRequestRepository.findById(1L)).thenReturn(java.util.Optional.of(request));

        consultantRequestService.rejectConsultantRequest(1L);

        verify(consultantRequestRepository, times(1)).findById(1L);
        verify(consultantRequestRepository, times(1)).save(request);
        verifyNoMoreInteractions(consultantRequestRepository);

        assertEquals(RequestStatus.REJECTED, request.getStatus());
    }

    // Add more test methods for other service methods
}
