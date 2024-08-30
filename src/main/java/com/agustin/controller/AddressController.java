package com.agustin.controller;

import com.agustin.controller.dto.address.AddressRequestDto;
import com.agustin.domain.model.Address;
import com.agustin.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get all addresses", description = "Retrieve a list of all registered addresses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful")
    })
    public ResponseEntity<List<Address>> findAll()
    {
        var address = addressService.findAll();

        return ResponseEntity.ok(address);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get address by Id", description = "Retrieve address according to requested Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Response successful"),
            @ApiResponse(responseCode = "422", description = "address id not processable")
    })
    public ResponseEntity<Address> findById(@PathVariable Long id)
    {
        var address = addressService.findById(id);

        return ResponseEntity.ok(address);
    }

    @PostMapping
    @Operation(summary = "Create address", description = "Create address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "address created"),
            @ApiResponse(responseCode = "422", description = "Id not processable"),
    })
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
    @Operation(summary = "Update address", description = "Update the data of an existing address according with Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "address updated successfully"),
            @ApiResponse(responseCode = "422", description = "Id not processable")
    })
    public ResponseEntity<Address> update(@PathVariable Long id, @RequestBody AddressRequestDto addressToUpdate)
    {
        var address = addressService.update(id, addressToUpdate.toModel());

        return ResponseEntity.ok(address);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a address", description = "Delete an existing address based on its Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "address deleted successfully"),
            @ApiResponse(responseCode = "404", description = "address not found")
    })
    public ResponseEntity<Address> delete(@PathVariable Long id)
    {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
