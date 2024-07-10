/*
package com.lifepill.possystem.service;

// EmployerServiceImplTest.java
import com.lifepill.possystem.dto.*;
import com.lifepill.possystem.dto.requestDTO.EmployerUpdate.*;
import com.lifepill.possystem.entity.Branch;
import com.lifepill.possystem.entity.Employer;
import com.lifepill.possystem.entity.EmployerBankDetails;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

*/
/**
 * The type Employer service impl test.
 *//*

class EmployerServiceImplTest {

    @Mock
    private EmployerRepository employerRepository;

    @Mock
    private EmployerBankDetailsRepository employerBankDetailsRepository;

    @Mock
    private EmployerBankDetailsRepository cashierBankDetailsRepo;

    @Mock
    private BranchRepository branchRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private EmployerServiceIMPL employerService;

    private Employer employer;
    private Branch branch;
    private EmployerBankDetails bankDetails;
    private EmployerUpdateBankAccountDTO employerUpdateBankAccountDTO;

    */
/**
     * Sets up.
     *//*

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        branch = new Branch();
        branch.setBranchId(1L);

        employer = new Employer();
        employer.setEmployerId(1L);
        employer.setBranch(branch);

        bankDetails = new EmployerBankDetails();
        bankDetails.setEmployerBankDetailsId(1L);
        bankDetails.setEmployerId(1L);

        employerUpdateBankAccountDTO = new EmployerUpdateBankAccountDTO();
        employerUpdateBankAccountDTO.setEmployerId(1L);
        employerUpdateBankAccountDTO.setEmployerBankDetailsId(1L);
        employerUpdateBankAccountDTO.setBankName("Test Bank");
        employerUpdateBankAccountDTO.setBankBranchName("Test Branch");
        employerUpdateBankAccountDTO.setBankAccountNumber("123456789");
        employerUpdateBankAccountDTO.setEmployerDescription("Test Description");
        employerUpdateBankAccountDTO.setMonthlyPayment(1000.0);
        employerUpdateBankAccountDTO.setMonthlyPaymentStatus(true);
    }

    */
/**
     * Test save employer.
     *//*

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

    */
/**
     * Test save employer without image.
     *//*

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

    */
/**
     * Test update employer account details.
     *//*

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

    */
/**
     * Test update employer password.
     *//*

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

    */
/**
     * Test update recent pin.
     *//*

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

    @Test
    void testUpdateEmployerBankAccountDetails_EmployerExists_BankDetailsExists() {
        // Arrange
        when(employerRepository.existsById(employer.getEmployerId())).thenReturn(true);
        when(employerRepository.getReferenceById(employer.getEmployerId())).thenReturn(employer);
        when(cashierBankDetailsRepo.findById(employerUpdateBankAccountDTO.getEmployerBankDetailsId()))
                .thenReturn(Optional.of(bankDetails));
        EmployerWithBankDTO expectedEmployerWithBankDTO = new EmployerWithBankDTO();
        when(modelMapper.map(employer, EmployerWithBankDTO.class)).thenReturn(expectedEmployerWithBankDTO);

        // Act
        EmployerWithBankDTO result = employerService.updateEmployerBankAccountDetails(employerUpdateBankAccountDTO);

        // Assert
        assertNotNull(result);
        assertEquals(expectedEmployerWithBankDTO, result);
        verify(employerRepository, times(1)).save(employer);
    }

    @Test
    void testUpdateEmployerBankAccountDetails_EmployerExists_BankDetailsNotExists() {
        // Arrange
        when(employerRepository.existsById(employer.getEmployerId())).thenReturn(true);
        when(employerRepository.getReferenceById(employer.getEmployerId())).thenReturn(employer);
        when(cashierBankDetailsRepo.findById(employerUpdateBankAccountDTO.getEmployerBankDetailsId()))
                .thenReturn(Optional.empty());
        EmployerWithBankDTO expectedEmployerWithBankDTO = new EmployerWithBankDTO();
        when(modelMapper.map(employer, EmployerWithBankDTO.class)).thenReturn(expectedEmployerWithBankDTO);

        // Act
        EmployerWithBankDTO result = employerService.updateEmployerBankAccountDetails(employerUpdateBankAccountDTO);

        // Assert
        assertNotNull(result);
        assertEquals(expectedEmployerWithBankDTO, result);
        verify(employerRepository, times(1)).save(employer);

    }

    @Test
    void testUpdateEmployerBankAccountDetails_EmployerNotExists() {
        // Arrange
        when(employerRepository.existsById(employer.getEmployerId())).thenReturn(false);

        // Act & Assert
        assertThrows(NotFoundException.class, () -> employerService.updateEmployerBankAccountDetails(employerUpdateBankAccountDTO));
        verify(employerRepository, times(1)).existsById(employer.getEmployerId());
        verify(cashierBankDetailsRepo, never()).findById(any());
        verify(cashierBankDetailsRepo, never()).save(any(EmployerBankDetails.class));
        verify(employerRepository, never()).save(any(Employer.class));
    }
}*/
