package com.agustin.service.impl;

import com.agustin.domain.model.LeaseContract;
import com.agustin.domain.repository.LeaseContractRepository;
import com.agustin.service.LeaseContractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LeaseContractServiceImpl implements LeaseContractService {

    private final LeaseContractRepository leaseContractRepository;

    public LeaseContractServiceImpl(LeaseContractRepository leaseContractRepository)
    {
        this.leaseContractRepository = leaseContractRepository;
    }

    @Transactional(readOnly = true)
    public List<LeaseContract> findAll() {
        return leaseContractRepository.findAll();
    }

    @Transactional(readOnly = true)
    public LeaseContract findById(Long id)
    {
        return leaseContractRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Lease Contract id " + id + " not founded"));
    }

    @Transactional
    public LeaseContract create(LeaseContract leaseContractToCreate)
    {
        return leaseContractRepository.save(leaseContractToCreate);
    }

    @Transactional
    public LeaseContract update(Long aLong, LeaseContract entity) {
        return null;
    }

    @Transactional
    public void delete(Long aLong) {

    }
}
