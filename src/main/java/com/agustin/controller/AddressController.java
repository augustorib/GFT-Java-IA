package com.agustin.controller;

import com.agustin.domain.model.Address;
import com.agustin.domain.model.LeaseContract;
import com.agustin.service.AddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/address")
@Tag(name = "Address Controller", description = "Managing address .")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService)
    {
        this.addressService = addressService;
    }


    @GetMapping
    public ResponseEntity<List<Address>> findAll()
    {
        var address = addressService.findAll();

        return ResponseEntity.ok(address);
    }

    @PostMapping
    public ResponseEntity<Address> create(@RequestBody Address addressToCreate)
    {
        var addressCreated = addressService.create(addressToCreate);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addressCreated)
                .toUri();

        return  ResponseEntity.created(location).body(addressCreated);
    }
}
