package com.agustin.controller;

import com.agustin.controller.dto.property.PropertyRequestDto;
import com.agustin.controller.dto.property.PropertyResponseDto;
import com.agustin.domain.model.Property;
import com.agustin.service.OwnerService;
import com.agustin.service.PropertyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/property")
@Tag(name = "Property Controller", description = "Managing properties .")
public class PropertyController {

    private final PropertyService propertyService;
    private final OwnerService ownerService;

    public PropertyController(PropertyService propertyService, OwnerService ownerService)
    {
        this.propertyService = propertyService;
        this.ownerService = ownerService;
    }


    @GetMapping
    public ResponseEntity<List<PropertyResponseDto>> findAll()
    {
        var properties = propertyService.findAll();

        var propertiesDto = properties.stream().map(PropertyResponseDto::new).collect(Collectors.toList());

        return ResponseEntity.ok(propertiesDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyResponseDto> findById(@PathVariable Long id)
    {
        var property = propertyService.findById(id);

        return  ResponseEntity.ok(new PropertyResponseDto(property));
    }

    @PostMapping
    public ResponseEntity<PropertyResponseDto> create(@RequestBody PropertyRequestDto propertyToCreate)
    {

        var owner = ownerService.findById(propertyToCreate.owner_id());

        var propertyModel = propertyToCreate.toModel(owner);

        var propertyCreated = new PropertyResponseDto(propertyService.create(propertyModel));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(propertyCreated.id())
                .toUri();

        return  ResponseEntity.created(location).body(propertyCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyResponseDto> update(@PathVariable Long id, @RequestBody PropertyRequestDto property )
    {
        var owner = ownerService.findById(property.owner_id());

        var propertyToUpdate = propertyService.update(id, property.toModel(owner));

        return  ResponseEntity.ok(new PropertyResponseDto(propertyToUpdate));


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Property> delete(@PathVariable Long id)
    {
        propertyService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
