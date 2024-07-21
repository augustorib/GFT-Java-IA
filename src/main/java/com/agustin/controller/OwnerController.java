package com.agustin.controller;

import com.agustin.domain.model.Owner;
import com.agustin.service.OwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService)
    {
        this.ownerService = ownerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> findById(@PathVariable Long id)
    {
        var owner = ownerService.findById(id);

        return  ResponseEntity.ok(owner);
    }

    @PostMapping
    public ResponseEntity<Owner> create(@RequestBody Owner ownerToCreate)
    {
        var ownerCreated = ownerService.create(ownerToCreate);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(ownerCreated.getId())
                        .toUri();

        return  ResponseEntity.created(location).body(ownerCreated);
    }
}
