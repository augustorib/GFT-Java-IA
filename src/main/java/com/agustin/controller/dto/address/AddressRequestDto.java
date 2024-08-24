package com.agustin.controller.dto.address;

import com.agustin.domain.model.Address;
import com.agustin.domain.model.LeaseContract;

public record AddressRequestDto(String street, String city, String state, String postal_code, int address_number ) {

    public AddressRequestDto(Address model)
    {
        this(
                model.getStreet(),
                model.getCity(),
                model.getState(),
                model.getPostal_code(),
                model.getAddress_Number()

        );
    }


    public Address toModel()
    {
        var model = new Address();

        model.setStreet(this.street);
        model.setCity(this.city);
        model.setState(this.state);
        model.setPostal_code(this.postal_code);
        model.setAddress_Number(this.address_number);

        return model;
    }
}
