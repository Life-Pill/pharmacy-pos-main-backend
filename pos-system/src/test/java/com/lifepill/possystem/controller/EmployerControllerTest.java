package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.EmployerBankDetailsDTO;
import com.lifepill.possystem.dto.EmployerDTO;
import com.lifepill.possystem.dto.EmployerWithBankDTO;
import com.lifepill.possystem.dto.EmployerWithoutImageDTO;
import com.lifepill.possystem.dto.requestDTO.EmployerUpdate.EmployerAllDetailsUpdateDTO;
import com.lifepill.possystem.dto.requestDTO.EmployerUpdate.EmployerUpdateBankAccountDTO;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.service.EmployerService;
import com.lifepill.possystem.util.StandardResponse;
import com.lifepill.possystem.util.mappers.EmployerMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployerControllerTest {

    @Mock
    private EmployerService employerService;

    @Mock
    private EmployerMapper employerMapper;

    @InjectMocks
    private EmployerController employerController;

    @Test
    public void testUpdateEmployer_Success() {
        // Setup
        long employerId = 1;
        EmployerAllDetailsUpdateDTO employerAllDetailsUpdateDTO = new EmployerAllDetailsUpdateDTO();
        Mockito.when(employerService.updateEmployer(employerId, employerAllDetailsUpdateDTO)).thenReturn("Employer updated");

        // Test
        ResponseEntity<StandardResponse> response = employerController.updateEmployer(employerId, employerAllDetailsUpdateDTO);

        // Assertion
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(201, response.getBody().getCode());
        assertEquals("Employer updated", response.getBody().getMessage());
    }

    @Test
    public void testGetEmployerById_Success() {
        // Setup
        int employerId = 1;
        EmployerDTO employerDTO = new EmployerDTO();
        employerDTO.setEmployerId(employerId);
        Mockito.when(employerService.getEmployerById(employerId)).thenReturn(employerDTO);

        // Test
        EmployerDTO result = employerController.getEmployerById(employerId);

        // Assertion
        assertNotNull(result);
        assertEquals(employerId, result.getEmployerId());
    }

    @Test
    public void testViewImage_Success() {
        // Setup
        int employerId = 1;
        byte[] imageData = new byte[]{1, 2, 3};
        Mockito.when(employerService.getImageData(employerId)).thenReturn(imageData);

        // Test
        ResponseEntity<byte[]> response = employerController.viewImage(employerId);

        // Assertion
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertArrayEquals(imageData, response.getBody());
    }

    @Test
    public void testDeleteEmployer_Success() {
        // Setup
        int employerId = 1;
        Mockito.when(employerService.deleteEmployer(employerId)).thenReturn("Employer deleted");

        // Test
        ResponseEntity<StandardResponse> response = employerController.deleteEmployer(employerId);

        // Assertion
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(201, response.getBody().getCode());
        assertEquals("Employer deleted", response.getBody().getMessage());
    }

    @Test
    public void testGetAllEmployers_Success() {
        // Setup
        List<EmployerDTO> employerList = Arrays.asList(new EmployerDTO(), new EmployerDTO());
        Mockito.when(employerService.getAllEmployer()).thenReturn(employerList);

        // Test
        ResponseEntity<StandardResponse> response = employerController.getAllEmployers();

        // Assertion
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(201, response.getBody().getCode());
        assertEquals(employerList.size(), ((List<EmployerDTO>)response.getBody().getData()).size());
    }

    @Test
    public void testGetAllEmployerByActiveState_Success() {
        // Setup
        boolean activeState = true;
        List<EmployerDTO> employerList = Arrays.asList(new EmployerDTO(), new EmployerDTO());
        Mockito.when(employerService.getAllEmployerByActiveState(activeState)).thenReturn(employerList);

        // Test
        List<EmployerDTO> result = employerController.getAllEmployerByActiveState(activeState);

        // Assertion
        assertNotNull(result);
        assertEquals(employerList.size(), result.size());
    }

    @Test
    public void testGetAllEmployerBankDetails_Success() {
        // Setup
        List<EmployerUpdateBankAccountDTO> bankDetailsList = Arrays.asList(new EmployerUpdateBankAccountDTO(), new EmployerUpdateBankAccountDTO());
        Mockito.when(employerService.getAllEmployerBankDetails()).thenReturn(bankDetailsList);

        // Test
        ResponseEntity<StandardResponse> response = employerController.getAllEmployerBankDetails();

        // Assertion
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(201, response.getBody().getCode());
        assertEquals(bankDetailsList.size(), ((List<EmployerUpdateBankAccountDTO>)response.getBody().getData()).size());
    }

    @Test
    public void testGetEmployerBankDetailsById_Success() {
        // Setup
        long employerId = 1;
        EmployerBankDetailsDTO bankDetailsDTO = new EmployerBankDetailsDTO();
        Mockito.when(employerService.getEmployerBankDetailsById(employerId)).thenReturn(bankDetailsDTO);

        // Test
        ResponseEntity<StandardResponse> response = employerController.getEmployerBankDetailsById(employerId);

        // Assertion
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getBody().getCode());
        assertEquals(bankDetailsDTO, response.getBody().getData());
    }

    @Test
    public void testGetAllEmployersWithBankDetails_Success() {
        // Setup
        List<EmployerWithBankDTO> employerWithBankList = Arrays.asList(new EmployerWithBankDTO(), new EmployerWithBankDTO());
        Mockito.when(employerService.getAllEmployersWithBankDetails()).thenReturn(employerWithBankList);

        // Test
        ResponseEntity<StandardResponse> response = employerController.getAllEmployersWithBankDetails();

        // Assertion
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getBody().getCode());
        assertEquals(employerWithBankList.size(), ((List<EmployerWithBankDTO>)response.getBody().getData()).size());
    }

    @Test
    public void testGetEmployerWithBankDetailsById_Success() {
        // Setup
        long employerId = 1;
        EmployerWithBankDTO employerWithBankDTO = new EmployerWithBankDTO();
        Mockito.when(employerService.getEmployerWithBankDetailsById(employerId)).thenReturn(employerWithBankDTO);

        // Test
        EmployerWithBankDTO result = employerController.getEmployerWithBankDetailsById(employerId);

        // Assertion
        assertNotNull(result);
        assertEquals(employerWithBankDTO, result);
    }

    @Test
    public void testSaveEmployerWithoutImage_Failure() {
        // Setup
        EmployerWithoutImageDTO employerWithoutImageDTO = new EmployerWithoutImageDTO();
        Mockito.when(employerService.saveEmployerWithoutImage(employerWithoutImageDTO)).thenThrow(new NotFoundException("Employer not found"));

        // Test
        assertThrows(NotFoundException.class, () -> employerController.saveCashierWithoutImage(employerWithoutImageDTO));

    }

    @Test
    public void testGetEmployerById_NotFound() {
        // Setup
        int employerId = 1;
        Mockito.when(employerService.getEmployerById(employerId)).thenReturn(null);

        // Test
        EmployerDTO result = employerController.getEmployerById(employerId);

        // Assertion
        assertNull(result);
    }

    @Test
    public void testViewImage_NotFound() {
        // Setup
        int employerId = 1;
        Mockito.when(employerService.getImageData(employerId)).thenReturn(null);

        // Test
        ResponseEntity<byte[]> response = employerController.viewImage(employerId);

        // Assertion
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteEmployer_NotFound() {

        // Setup
        int employerId = 1;
        Mockito.when(employerService.deleteEmployer(employerId)).thenThrow(new NotFoundException("Employer not found"));

        // Test
        assertThrows(NotFoundException.class, () -> employerController.deleteEmployer(employerId));
    }

    @Test
    public void testGetAllEmployerByActiveState_EmptyList() {
        // Setup
        boolean activeState = true;
        Mockito.when(employerService.getAllEmployerByActiveState(activeState)).thenReturn(Collections.emptyList());

        // Test
        List<EmployerDTO> result = employerController.getAllEmployerByActiveState(activeState);

        // Assertion
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

}
