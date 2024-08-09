package com.agustin.controller.dto.owner;

import com.agustin.domain.model.Owner;

public record OwnerRequestDto(String name, String email, String phone_number) {

    public OwnerRequestDto(Owner model)
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
