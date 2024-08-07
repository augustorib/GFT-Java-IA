package com.agustin.controller.dto;

import com.agustin.domain.model.Owner;

public record OwnerDto(String name, String email, String phone_number) {

    public OwnerDto(Owner model)
    {
       this(
               model.getName(),
               model.getEmail(),
               model.getPhone_number()
            );

    }

    public Owner toModel()
    {
        Owner model = new Owner();

        model.setName(this.name);
        model.setEmail(this.email);
        model.setPhone_number(this.phone_number);

        return model;
    }

}
