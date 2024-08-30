package com.agustin.controller;

import com.agustin.controller.dto.property.PropertyRequestDto;
import com.agustin.controller.dto.property.PropertyResponseDto;
import com.agustin.domain.model.Property;
import com.agustin.service.AddressService;
import com.agustin.service.OwnerService;
import com.agustin.service.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/property")
@Tag(name = "Property Controller", description = "Managing properties.")
public class PropertyController {

    private final PropertyService propertyService;
    private final OwnerService ownerService;

    private final AddressService addressService;

    public PropertyController(PropertyService propertyService, OwnerService ownerService, AddressService addressService)
    {
        this.propertyService = propertyService;
        this.ownerService = ownerService;
        this.addressService = addressService;
    }


    @GetMapping
    @Operation(summary = "Get all properties", description = "Retrieve a list of all registered properties")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful")
    })
    public ResponseEntity<List<PropertyResponseDto>> findAll()
    {
        var properties = propertyService.findAll();

        var propertiesDto = properties.stream().map(PropertyResponseDto::new).collect(Collectors.toList());

        return ResponseEntity.ok(propertiesDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get property by Id", description = "Retrieve property according to requested Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Response successful"),
            @ApiResponse(responseCode = "422", description = "Property id not processable")
    })
    public ResponseEntity<PropertyResponseDto> findById(@PathVariable Long id)
    {
        var property = propertyService.findById(id);

        return  ResponseEntity.ok(new PropertyResponseDto(property));
    }

    @PostMapping
    @Operation(summary = "Create property", description = "Create Property with Address and Owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Property created"),
            @ApiResponse(responseCode = "422", description = "Id not processable"),
    })
    public ResponseEntity<PropertyResponseDto> create(@RequestBody PropertyRequestDto propertyToCreate)
    {

        var owner = ownerService.findById(propertyToCreate.owner_id());

        var address = addressService.findById(propertyToCreate.address_id());

        var propertyModel = propertyToCreate.toModel(owner, address);

        var propertyCreated = new PropertyResponseDto(propertyService.create(propertyModel));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(propertyCreated.id())
                .toUri();

        return  ResponseEntity.created(location).body(propertyCreated);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update property", description = "Update the data of an existing property according with Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Property updated successfully"),
            @ApiResponse(responseCode = "422", description = "Id not processable")
    })
    public ResponseEntity<PropertyResponseDto> update(@PathVariable Long id, @RequestBody PropertyRequestDto property )
    {
        var owner = ownerService.findById(property.owner_id());

        var address = addressService.findById(property.address_id());

        var propertyToUpdate = propertyService.update(id, property.toModel(owner, address));

        return  ResponseEntity.ok(new PropertyResponseDto(propertyToUpdate));


    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a property", description = "Delete an existing property based on its Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Property deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Property not found")
    })
    public ResponseEntity<Property> delete(@PathVariable Long id)
    {
        propertyService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
