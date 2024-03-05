package com.lifepill.possystem.repo.branchRepo;

import com.lifepill.possystem.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface BranchRepo extends JpaRepository<Branch,Integer> {
    boolean existsByBranchEmail(String branchEmail);
}
