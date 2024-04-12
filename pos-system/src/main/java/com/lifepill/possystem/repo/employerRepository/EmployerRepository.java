package com.lifepill.possystem.repo.employerRepository;

import com.lifepill.possystem.entity.Branch;
import com.lifepill.possystem.entity.Employer;
import com.lifepill.possystem.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface EmployerRepository extends JpaRepository<Employer,Long> {

    List<Employer> findByIsActiveStatusEquals(boolean activeState);

    boolean existsAllByEmployerEmail(String employerEmail);

    List<Employer> findAllByBranch(Branch branch);

    List<Employer> findAllByRole(Role role);

   // List<Employer> findByEmployerEmail(String username);
    Employer findByEmployerEmail(String email);
}
