package com.agustin.service.impl;

import com.agustin.domain.model.Address;
import com.agustin.domain.repository.AddressRepository;
import com.agustin.service.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return addressRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Address id " +id + " not founded"));
    }

    @Transactional
    public Address create(Address addressToCreate) {
        return addressRepository.save(addressToCreate);
    }

    @Transactional
    public Address update(Long id, Address addressToUpdate)
    {

        var addressDb = addressRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Address id " + id + " not founded"));

        addressDb.setStreet(addressToUpdate.getStreet());
        addressDb.setCity(addressToUpdate.getCity());
        addressDb.setState(addressToUpdate.getState());
        addressDb.setPostal_code(addressToUpdate.getPostal_code());
        addressDb.setAddress_Number(addressToUpdate.getAddress_Number());

        return addressDb;
    }

    @Transactional
    public void delete(Long id)
    {
        var addressToDelete = addressRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("The address is not founded"));
        addressRepository.delete(addressToDelete);
    }
}
