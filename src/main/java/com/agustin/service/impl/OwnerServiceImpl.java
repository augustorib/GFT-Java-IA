package com.agustin.service.impl;

import com.agustin.domain.model.Owner;
import com.agustin.domain.repository.OwnerRepository;
import com.agustin.service.OwnerService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository)
    {
        this.ownerRepository = ownerRepository;
    }
    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Owner create(Owner ownerToCreate) {
        if(ownerToCreate.getId() != null && ownerRepository.existsById(ownerToCreate.getId()))
        {
            throw  new IllegalArgumentException("This id already exists");
        }

        return ownerRepository.save(ownerToCreate);
    }
}
