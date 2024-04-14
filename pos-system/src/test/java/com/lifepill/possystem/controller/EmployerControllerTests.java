/*
package com.lifepill.possystem.controller;//package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.EmployerDTO;
import com.lifepill.possystem.service.EmployerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployerControllerTests {

    @Mock
    private EmployerService cashierService;

    @InjectMocks
    private EmployerController cashierController;

    @Test
    public void testSaveEmployerWithImage() throws IOException {
        // Create a mock MultipartFile
        byte[] fileContent = "image content".getBytes();
        MultipartFile multipartFile = new MockMultipartFile("file", "test.jpg", "image/jpeg", fileContent);

        // Create a mock EmployerDTO
        EmployerDTO employerDTO = new EmployerDTO();
        // Set other properties if needed

        // Mock the behavior of cashierService.saveCashier method
        when(cashierService.saveEmployer(any(EmployerDTO.class))).thenReturn("saved");

        // Call the controller method
        String result = cashierController.saveEmployerWithImage(employerDTO, multipartFile);

    }

    @Test

    public void testSaveEmployerWithImages() throws IOException {
        // Create a mock MultipartFile
        byte[] fileContent = "image content".getBytes();
        MultipartFile multipartFile = new MockMultipartFile("file", "test.jpg", "image/jpeg", fileContent);

        // Create a mock EmployerDTO
        EmployerDTO employerDTO = new EmployerDTO();
        // Set other properties if needed

        // Mock the behavior of cashierService.saveCashier method
        when(cashierService.saveEmployer(any(EmployerDTO.class))).thenReturn("saved");

        // Call the controller method
        String result = cashierController.saveEmployerWithImage(employerDTO, multipartFile);

        // Verify the result
        assertEquals("saved", result);

        // Verify the behavior of saveCashier method
        verify(cashierService, times(1)).saveEmployer(employerDTO);
    }



}
*/
