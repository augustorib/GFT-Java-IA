package com.agustin.service.impl;

import com.agustin.domain.model.LeaseContract;
import com.agustin.domain.repository.LeaseContractRepository;
import com.agustin.service.LeaseContractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

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
    public LeaseContract update(Long id, LeaseContract leaseContract)
    {
        var leaseContractDB = leaseContractRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Lease Contract id " + id + " not founded"));

        leaseContractDB.setStart_date(leaseContract.getStart_date());
        leaseContractDB.setEnd_date(leaseContract.getEnd_date());
        leaseContractDB.setSecurity_deposit(leaseContract.getSecurity_deposit());
        leaseContractDB.setTenant(leaseContract.getTenant());
        leaseContractDB.setProperty(leaseContract.getProperty());

        return leaseContractDB;
    }

    @Transactional
    public void delete(Long id) {
        var leaseContractToDelete = leaseContractRepository.findById(id).orElseThrow(NoSuchElementException::new);
        leaseContractRepository.delete(leaseContractToDelete);
    }
}
