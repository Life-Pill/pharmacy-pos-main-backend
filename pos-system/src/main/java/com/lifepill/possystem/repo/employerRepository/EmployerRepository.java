package com.lifepill.possystem.repo.employerRepository;

import com.lifepill.possystem.entity.Branch;
import com.lifepill.possystem.entity.Employer;
import com.lifepill.possystem.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface EmployerRepository extends JpaRepository<Employer,Long> {

    List<Employer> findByIsActiveStatusEquals(boolean activeState);

    boolean existsAllByEmployerEmail(String employerEmail);

    List<Employer> findAllByBranch(Branch branch);

    List<Employer> findAllByRole(Role role);

    Optional<Employer> findByEmployerEmail(String employerEmail);

    Employer findByBranch_BranchIdAndRole(Long branchId, Role role);

}
