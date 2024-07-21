package com.agustin.service;

import com.agustin.domain.model.Owner;

public interface OwnerService {

    Owner findById(Long id);
    Owner create(Owner ownerToCreate);

}
