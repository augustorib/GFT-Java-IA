package com.agustin.controller;

import com.agustin.controller.dto.address.AddressRequestDto;
import com.agustin.domain.model.Address;
import com.agustin.service.AddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/address")
@Tag(name = "Address Controller", description = "Managing address.")
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

    @GetMapping("/{id}")
    public ResponseEntity<Address> findById(@PathVariable Long id)
    {
        var address = addressService.findById(id);

        return ResponseEntity.ok(address);
    }

    @PostMapping
    public ResponseEntity<Address> create(@RequestBody AddressRequestDto addressToCreate)
    {
        var addressCreated = addressService.create(addressToCreate.toModel());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addressCreated)
                .toUri();

        return  ResponseEntity.created(location).body(addressCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> update(@PathVariable Long id, @RequestBody AddressRequestDto addressToUpdate)
    {
        var address = addressService.update(id, addressToUpdate.toModel());

        return ResponseEntity.ok(address);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Address> delete(@PathVariable Long id)
    {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
