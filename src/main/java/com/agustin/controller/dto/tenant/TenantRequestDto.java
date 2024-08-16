package com.agustin.controller.dto.tenant;

import com.agustin.domain.model.Tenant;

public record TenantRequestDto(String name, String email, String phone_number) {

    public TenantRequestDto(Tenant model)
    {
        this(
             model.getName(),
             model.getEmail(),
             model.getPhone_number()
        );
    }

    public Tenant toModel()
    {
        Tenant model = new Tenant();

        model.setName(this.name);
        model.setEmail(this.email);
        model.setPhone_number(this.phone_number);

        return model;
    }
}
