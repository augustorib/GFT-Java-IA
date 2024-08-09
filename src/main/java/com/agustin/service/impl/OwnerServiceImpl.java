package com.agustin.service.impl;

import com.agustin.domain.model.Owner;
import com.agustin.domain.repository.OwnerRepository;
import com.agustin.service.OwnerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository)
    {
        this.ownerRepository = ownerRepository;
    }

    @Transactional(readOnly = true)
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Owner findById(Long id) {
        return this.ownerRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public Owner create(Owner ownerToCreate) {
        if(ownerRepository.existsByEmail(ownerToCreate.getEmail()))
        {
            throw new IllegalArgumentException("This email already exists");
        }

        return ownerRepository.save(ownerToCreate);
    }

    @Transactional
    public Owner update(Long id, Owner ownerToUpdate) {

        Owner dbOwner = ownerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Onwer with " +id + " not founded"));

        dbOwner.setName(ownerToUpdate.getName());
        dbOwner.setEmail(ownerToUpdate.getEmail());
        dbOwner.setPhone_number(ownerToUpdate.getPhone_number());

        return ownerRepository.save(dbOwner);
    }

    @Transactional
    public void delete(Long id) {
        var ownerToDelete = ownerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("The owner is not founded"));
        ownerRepository.delete(ownerToDelete);
    }
}
