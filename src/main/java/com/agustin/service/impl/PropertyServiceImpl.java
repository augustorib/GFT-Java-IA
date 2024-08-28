package com.agustin.service.impl;

import com.agustin.domain.model.Property;
import com.agustin.domain.repository.PropertyRepository;
import com.agustin.service.PropertyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository)
    {
        this.propertyRepository = propertyRepository;
    }

    @Transactional(readOnly = true)
    public List<Property> findAll() {

        return propertyRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Property findById(Long id) {
        return this.propertyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Proerty id " + id + " not founded"));
    }

    @Transactional
    public Property create(Property propertyToCreate) {

        return propertyRepository.save(propertyToCreate);
    }

    @Transactional
    public Property update(Long id, Property property) {

        var propertyDb = propertyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Proerty id " + id + " not founded"));

        propertyDb.setType(property.getType());
        propertyDb.setDescription(property.getDescription());
        propertyDb.setBedroom(property.getBedroom());
        propertyDb.setBathroom(property.getBathroom());
        propertyDb.setAvailability(property.getAvailability());
        propertyDb.setRental_price(property.getRental_price());
        propertyDb.setOwner(property.getOwner());

        return propertyRepository.save(propertyDb);
    }

    @Transactional
    public void delete(Long id) {
        var ownerToDelete = propertyRepository.findById(id).orElseThrow(NoSuchElementException::new);
        propertyRepository.delete(ownerToDelete);
    }
}
