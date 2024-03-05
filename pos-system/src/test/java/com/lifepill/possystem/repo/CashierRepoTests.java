package com.lifepill.possystem.repo;

import com.lifepill.possystem.entity.Cashier;
import com.lifepill.possystem.entity.enums.Role;
import com.lifepill.possystem.repo.cashierRepo.CashierRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static com.lifepill.possystem.entity.enums.Gender.Male;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CashierRepoTests {
    @Autowired
    private CashierRepo cashierRepo;

    @BeforeEach
    public void setUp() {
        // Clean the database or reset test data
        cashierRepo.deleteAll();
    }

    @Test
    @DisplayName("Junit test for save cashier operation")
    public void givenCashier_whenSave_thenReturnSavedCashier() {
        // Given Cashier data
        Cashier cashier = Cashier.builder()
                .cashierNicName("JohnDoe")
                .cashierFirstName("John")
                .cashierLastName("Doe")
                .cashierPassword("password")
                .cashierEmail("john.doe@example.com")
                .cashierPhone("1234567890")
                .cashierAddress("123 Main Street")
                .cashierSalary(50000.0)
                .cashierNic("123456789012")
                .isActiveStatus(true)
                .pin(1234)
                .gender(Male)
                .dateOfBirth(new Date())
                .role(Role.Cashier)
                .build();

        // When save cashier data
        Cashier savedCashier = cashierRepo.save(cashier);

        // Then return Cashier data
        assertThat(savedCashier).isNotNull();
        assertNotNull(savedCashier.getCashierId());
        assertEquals("JohnDoe", savedCashier.getCashierNicName());
        assertEquals("John", savedCashier.getCashierFirstName());
        assertEquals("Doe", savedCashier.getCashierLastName());
        assertEquals("password", savedCashier.getCashierPassword());
        assertEquals("john.doe@example.com", savedCashier.getCashierEmail());
        assertEquals("1234567890", savedCashier.getCashierPhone());
        assertEquals("123 Main Street", savedCashier.getCashierAddress());
        assertEquals(50000.0, savedCashier.getCashierSalary());
        assertEquals("123456789012", savedCashier.getCashierNic());
        assertEquals(true, savedCashier.isActiveStatus());
        assertEquals(1234, savedCashier.getPin());
        assertEquals(Male,savedCashier.getGender());
        assertEquals(Role.Cashier,savedCashier.getRole());

        // fetch the saved cashier from the database and assert its values
        Cashier retrievedCashier = cashierRepo.findById(savedCashier.getCashierId()).orElse(null);
        assertNotNull(retrievedCashier);
    }

    @Test
    @DisplayName("Junit test for get all the cashiers operation")
    public void givenNoCashiers_whenFindAll_thenReturnEmptyList() {
        // When finding all Cashiers
        List<Cashier> allCashiers = cashierRepo.findAll();

        // Then the list should be empty
        assertThat(allCashiers).isEmpty();
    }


}
