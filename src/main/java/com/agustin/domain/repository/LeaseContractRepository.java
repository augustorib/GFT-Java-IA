package com.agustin.domain.repository;

import com.agustin.domain.model.LeaseContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaseContractRepository extends JpaRepository<LeaseContract, Long> {
}
