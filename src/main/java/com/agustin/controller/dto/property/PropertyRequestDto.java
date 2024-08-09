package com.agustin.controller.dto.property;

import com.agustin.domain.model.Owner;
import com.agustin.domain.model.Property;

import java.math.BigDecimal;

public record PropertyRequestDto(String type, String description, int bathroom, int bedroom, BigDecimal rental_price, Boolean availability, Long owner_id) {

    public PropertyRequestDto(Property model){
        this(
                model.getType(),
                model.getDescription(),
                model.getBathroom(),
                model.getBedroom(),
                model.getRental_price(),
                model.getAvailability(),
                model.getOwner().getId()
        );
    }

    public Property toModel(Owner owner)
    {
        Property model = new Property();

        model.setType(this.type);
        model.setDescription(this.description);
        model.setBathroom(this.bathroom);
        model.setBedroom(this.bedroom);
        model.setRental_price(this.rental_price);
        model.setAvailability(this.availability);
        model.setOwner(owner);

        return model;
    }
}
