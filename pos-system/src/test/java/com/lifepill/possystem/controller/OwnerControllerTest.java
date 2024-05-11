package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.EmployerDTO;
import com.lifepill.possystem.entity.enums.Gender;
import com.lifepill.possystem.entity.enums.Role;
import com.lifepill.possystem.service.EmployerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class OwnerControllerTest {

    @Mock
    private EmployerService employerService;

    @InjectMocks
    private OwnerController ownerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployerByRole_Success() {
        // Arrange
        Role role = Role.OWNER;
        List<EmployerDTO> ownerDTOs = createOwnerDTOs();
        when(employerService.getAllEmployerByRole(role)).thenReturn(ownerDTOs);

        // Act
        ResponseEntity<List<EmployerDTO>> responseEntity = ownerController.getAllEmployerByRole(role);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(ownerDTOs, responseEntity.getBody());
    }

    @Test
    void testGetAllEmployerByRole_NoOwners() {
        // Arrange
        Role role = Role.OWNER;
        List<EmployerDTO> ownerDTOs = new ArrayList<>();
        when(employerService.getAllEmployerByRole(role)).thenReturn(ownerDTOs);

        // Act
        ResponseEntity<List<EmployerDTO>> responseEntity = ownerController.getAllEmployerByRole(role);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(ownerDTOs, responseEntity.getBody());
    }

    @Test
    void testGetAllEmployerByRole_InvalidRole() {
        // Arrange
        Role role = Role.OWNER;
        when(employerService.getAllEmployerByRole(role)).thenReturn(null);

        // Act
        ResponseEntity<List<EmployerDTO>> responseEntity = ownerController.getAllEmployerByRole(role);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
       // assertEquals(new ArrayList<>(), responseEntity.getBody());
    }

    @Test
    void testGetAllEmployerByRole_ServiceThrowsException() {
        // Arrange
        Role role = Role.OWNER;
        when(employerService.getAllEmployerByRole(role)).thenThrow(new RuntimeException("Error occurred"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> ownerController.getAllEmployerByRole(role));
    }

    // Add more tests here...

    @Test
    void testGetAllEmployerByRole_NullServiceResponse() {
        // Arrange
        Role role = Role.OWNER;
        when(employerService.getAllEmployerByRole(role)).thenReturn(null);

        // Act
        ResponseEntity<List<EmployerDTO>> responseEntity = ownerController.getAllEmployerByRole(role);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        //assertEquals(new ArrayList<>(), responseEntity.getBody());
    }

    @Test
    void testGetAllEmployerByRole_EmptyServiceResponse() {
        // Arrange
        Role role = Role.OWNER;
        List<EmployerDTO> emptyList = new ArrayList<>();
        when(employerService.getAllEmployerByRole(role)).thenReturn(emptyList);

        // Act
        ResponseEntity<List<EmployerDTO>> responseEntity = ownerController.getAllEmployerByRole(role);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(emptyList, responseEntity.getBody());
    }

    @Test
    void testGetAllEmployerByRole_MultipleOwners() {
        // Arrange
        Role role = Role.OWNER;
        List<EmployerDTO> ownerDTOs = createOwnerDTOs();
        ownerDTOs.addAll(createOwnerDTOs());
        when(employerService.getAllEmployerByRole(role)).thenReturn(ownerDTOs);

        // Act
        ResponseEntity<List<EmployerDTO>> responseEntity = ownerController.getAllEmployerByRole(role);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(ownerDTOs, responseEntity.getBody());
    }

    @Test
    void testGetAllEmployerByRole_EmptyOwnerList() {
        // Arrange
        Role role = Role.OWNER;
        List<EmployerDTO> emptyList = new ArrayList<>();
        when(employerService.getAllEmployerByRole(role)).thenReturn(emptyList);

        // Act
        ResponseEntity<List<EmployerDTO>> responseEntity = ownerController.getAllEmployerByRole(role);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(emptyList, responseEntity.getBody());
    }


    private List<EmployerDTO> createOwnerDTOs() {
        List<EmployerDTO> ownerDTOs = new ArrayList<>();
        ownerDTOs.add(new EmployerDTO(
                1L,
                1L,
                "John",
                "Doe",
                "nayan",
                "password",
                "john@example.com",
                "1234567890",
                "123 Main St",
                5000.0,
                "232142412124142",
                true,
                Gender.MALE,
                null,
                Role.OWNER,
                1234,
                null
        ));
        ownerDTOs.add(new EmployerDTO(
                2L,
                2L,
                "Jane",
                "Smith",
                "suman",
                "password",
                "jane@example.com",
                "9876543210",
                "456 Elm St",
                6000.0,
                "987654321V",
                true,
                Gender.MALE,
                null,
                Role.OWNER,
                5678,
                null
        ));
        return ownerDTOs;
    }
}