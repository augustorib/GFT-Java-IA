package com.agustin.service.impl;

import com.agustin.domain.model.Address;
import com.agustin.domain.repository.AddressRepository;
import com.agustin.service.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AddressServiceImpl implements AddressService {


    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository)
    {
        this.addressRepository = addressRepository;

    }
    @Transactional(readOnly = true)
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Address findById(Long id) {
        return addressRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public Address create(Address addressToCreate) {
        return addressRepository.save(addressToCreate);
    }

    @Transactional
    public Address update(Long id, Address model) {
        return null;
    }

    @Transactional
    public void delete(Long id) {

    }
}
