package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.BranchDTO;
import com.lifepill.possystem.dto.EmployerDTO;
import com.lifepill.possystem.dto.requestDTO.BranchUpdateDTO;
import com.lifepill.possystem.service.BranchService;
import com.lifepill.possystem.service.EmployerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BranchControllerTest {

    @Mock
    private BranchService branchService;

    @Mock
    private EmployerService employerService;

    @InjectMocks
    private BranchController branchController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(branchController).build();
    }

    @Test
    void saveBranch() throws Exception {
        MockMultipartFile image = new MockMultipartFile("image", "test.jpg", MediaType.IMAGE_JPEG_VALUE, "test image".getBytes());
        mockMvc.perform(multipart("/lifepill/v1/branch/save")
                        .file(image)
                        .param("branchDTO", "branchDTO"))
                .andExpect(status().isOk())
                .andExpect(content().string("saved"));
        verify(branchService, times(1)).saveBranch(any(BranchDTO.class), any());
    }

    @Test
    void viewImage() throws Exception {
        byte[] imageData = "test image data".getBytes();
        when(branchService.getImageData(1)).thenReturn(imageData);
        mockMvc.perform(get("/lifepill/v1/branch/view-image/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.IMAGE_JPEG))
                .andExpect(content().bytes(imageData));
    }

    @Test
    void getBranchById() throws Exception {
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setBranchId(1L);
        branchDTO.setBranchName("Test Branch");
        when(branchService.getBranchById(1)).thenReturn(branchDTO);
        mockMvc.perform(get("/lifepill/v1/branch/get-by-id?id=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.branchId").value(1))
                .andExpect(jsonPath("$.branchName").value("Test Branch"));
    }

    @Test
    void deleteBranch() throws Exception {
        when(branchService.deleteBranch(1)).thenReturn("deleted");
        mockMvc.perform(delete("/lifepill/v1/branch/delete-branch/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("deleted"));
    }

    @Test
    void getAllCashiersByBranchId() throws Exception {
        List<EmployerDTO> employerDTOS = Collections.singletonList(new EmployerDTO());
        when(employerService.getAllEmployerByBranchId(1)).thenReturn(employerDTOS);
        mockMvc.perform(get("/lifepill/v1/branch/employer/by-branch/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    void testEmployer() throws Exception {
        mockMvc.perform(get("/lifepill/v1/branch/branch-test"))
                .andExpect(status().isOk())
                .andExpect(content().string("Branch test successful"));
    }
}
