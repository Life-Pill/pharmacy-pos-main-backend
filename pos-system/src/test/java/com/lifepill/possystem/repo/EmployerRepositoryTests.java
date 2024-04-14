package com.lifepill.possystem.repo;

import com.lifepill.possystem.entity.Employer;
import com.lifepill.possystem.entity.enums.Role;
import com.lifepill.possystem.repo.employerRepository.EmployerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static com.lifepill.possystem.entity.enums.Gender.MALE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmployerRepositoryTests {
    @Autowired
    private EmployerRepository employerRepository;

    @BeforeEach
    public void setUp() {
        employerRepository.deleteAll();
    }

    @Test
    @DisplayName("Save Employer Operation")
    public void givenCashier_whenSave_thenReturnSavedCashier() {
        // Given Employer data
        Employer employer = com.lifepill.possystem.entity.Employer.builder()
                .employerNicName("JohnDoe")
                .employerFirstName("John")
                .employerLastName("Doe")
                .employerPassword("password")
                .employerEmail("john.doe@example.com")
                .employerPhone("1234567890")
                .employerAddress("123 Main Street")
                .employerSalary(50000.0)
                .employerNic("123456789012")
                .isActiveStatus(true)
                .pin(1234)
                .gender(MALE)
                .dateOfBirth(new Date())
                .role(Role.CASHIER)
                .build();

        // When save employer data
        Employer savedEmployer = employerRepository.save(employer);

        // Then return Employer data
        assertNotNull(savedEmployer.getEmployerId());
        assertEquals("JohnDoe", savedEmployer.getEmployerNicName());
        assertEquals("John", savedEmployer.getEmployerFirstName());
        assertEquals("Doe", savedEmployer.getEmployerLastName());
        assertEquals("password", savedEmployer.getEmployerPassword());
        assertEquals("john.doe@example.com", savedEmployer.getEmployerEmail());
        assertEquals("1234567890", savedEmployer.getEmployerPhone());
        assertEquals("123 Main Street", savedEmployer.getEmployerAddress());
        assertEquals(50000.0, savedEmployer.getEmployerSalary());
        assertEquals("123456789012", savedEmployer.getEmployerNic());
        assertTrue(savedEmployer.isActiveStatus());
        assertEquals(1234, savedEmployer.getPin());
        assertEquals(MALE, savedEmployer.getGender());
        assertEquals(Role.CASHIER, savedEmployer.getRole());

        // fetch the saved employer from the database and assert its values
        Employer retrievedEmployer = employerRepository.findById(savedEmployer.getEmployerId()).orElse(null);
        assertNotNull(retrievedEmployer);
        assertEquals(savedEmployer, retrievedEmployer);
    }

    @Test
    @DisplayName("Get All Cashiers Operation")
    public void givenNoCashiers_whenFindAll_thenReturnEmptyList() {
        // When finding all Cashiers
        List<Employer> allEmployers = employerRepository.findAll();

        // Then the list should be empty
        assertThat(allEmployers).isEmpty();
    }


}
