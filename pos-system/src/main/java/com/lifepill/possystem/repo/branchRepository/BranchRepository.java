package com.lifepill.possystem.repo.branchRepository;

import com.lifepill.possystem.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

/**
 * The interface Branch repository.
 */
@Repository
@EnableJpaRepositories
public interface BranchRepository extends JpaRepository<Branch,Long> {
    /**
     * Exists by branch email boolean.
     *
     * @param branchEmail the branch email
     * @return the boolean
     */
    boolean existsByBranchEmail(String branchEmail);
}
