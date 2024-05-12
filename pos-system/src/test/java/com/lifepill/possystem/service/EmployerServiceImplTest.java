package com.lifepill.possystem.service;

// EmployerServiceImplTest.java
import com.lifepill.possystem.dto.*;
import com.lifepill.possystem.dto.requestDTO.EmployerUpdate.*;
import com.lifepill.possystem.entity.Branch;
import com.lifepill.possystem.entity.EmployerBankDetails;
import com.lifepill.possystem.entity.Employer;
import com.lifepill.possystem.entity.enums.Gender;
import com.lifepill.possystem.entity.enums.Role;
import com.lifepill.possystem.exception.EntityDuplicationException;
import com.lifepill.possystem.exception.EntityNotFoundException;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.repo.branchRepository.BranchRepository;
import com.lifepill.possystem.repo.employerRepository.EmployerBankDetailsRepository;
import com.lifepill.possystem.repo.employerRepository.EmployerRepository;
import com.lifepill.possystem.service.impl.EmployerServiceIMPL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The type Employer service impl test.
 */
class EmployerServiceImplTest {

    @Mock
    private EmployerRepository employerRepository;

    @Mock
    private EmployerBankDetailsRepository employerBankDetailsRepository;

    @Mock
    private BranchRepository branchRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private EmployerServiceIMPL employerService;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test save employer.
     */
    @Test
    void testSaveEmployer() {
        EmployerDTO employerDTO = new EmployerDTO();
        Branch branch = new Branch();
        Employer employer = new Employer();
        when(employerRepository.existsById(employerDTO.getEmployerId())).thenReturn(false);
        when(employerRepository.existsAllByEmployerEmail(employerDTO.getEmployerEmail())).thenReturn(false);
        when(branchRepository.findById(employerDTO.getBranchId())).thenReturn(Optional.of(branch));
        when(modelMapper.map(employerDTO, Employer.class)).thenReturn(employer);

        String result = employerService.saveEmployer(employerDTO);

        assertEquals("Employer Saved", result);
        verify(employerRepository, times(1)).save(employer);
    }

    /**
     * Test save employer without image.
     */
    @Test
    void testSaveEmployerWithoutImage() {
        EmployerWithoutImageDTO employerWithoutImageDTO = new EmployerWithoutImageDTO();
        Branch branch = new Branch();
        Employer employer = new Employer();
        when(employerRepository.existsById(employerWithoutImageDTO.getEmployerId())).thenReturn(false);
        when(employerRepository.existsAllByEmployerEmail(employerWithoutImageDTO.getEmployerEmail())).thenReturn(false);
        when(branchRepository.findById(employerWithoutImageDTO.getBranchId())).thenReturn(Optional.of(branch));
        when(modelMapper.map(employerWithoutImageDTO, Employer.class)).thenReturn(employer);

        String result = employerService.saveEmployerWithoutImage(employerWithoutImageDTO);

        assertEquals("Employer Saved", result);
        verify(employerRepository, times(1)).save(employer);
    }

    /**
     * Test update employer account details.
     */
    @Test
    void testUpdateEmployerAccountDetails() {
        EmployerUpdateAccountDetailsDTO employerUpdateAccountDetailsDTO = new EmployerUpdateAccountDetailsDTO();
        Employer employer = new Employer();
        when(employerRepository.existsById(employerUpdateAccountDetailsDTO.getEmployerId())).thenReturn(true);
        when(employerRepository.getReferenceById(employerUpdateAccountDetailsDTO.getEmployerId())).thenReturn(employer);

        String result = employerService.updateEmployerAccountDetails(employerUpdateAccountDetailsDTO);

        assertEquals("Successfully Update employer account details", result);
        verify(employerRepository, times(1)).save(employer);
    }

    /**
     * Test update employer password.
     */
    @Test
    void testUpdateEmployerPassword() {
        EmployerPasswordResetDTO employerPasswordResetDTO = new EmployerPasswordResetDTO();
        Employer employer = new Employer();
        when(employerRepository.existsById(employerPasswordResetDTO.getEmployerId())).thenReturn(true);
        when(employerRepository.getReferenceById(employerPasswordResetDTO.getEmployerId())).thenReturn(employer);

        String result = employerService.updateEmployerPassword(employerPasswordResetDTO);

        assertEquals("Successfully Reset employer password", result);
        verify(employerRepository, times(1)).save(employer);
    }

    /**
     * Test update recent pin.
     */
    @Test
    void testUpdateRecentPin() {
        EmployerRecentPinUpdateDTO employerRecentPinUpdateDTO = new EmployerRecentPinUpdateDTO();
        Employer employer = new Employer();
        when(employerRepository.existsById(employerRecentPinUpdateDTO.getEmployerId())).thenReturn(true);
        when(employerRepository.getReferenceById(employerRecentPinUpdateDTO.getEmployerId())).thenReturn(employer);

        String result = employerService.updateRecentPin(employerRecentPinUpdateDTO);

        assertEquals("Successfully Reset employer PIN", result);
        verify(employerRepository, times(1)).save(employer);
    }
}