package com.lifepill.possystem.repo.branchRepository;

import com.lifepill.possystem.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface BranchRepository extends JpaRepository<Branch,Long> {
    boolean existsByBranchEmail(String branchEmail);
}
