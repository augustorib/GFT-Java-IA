package com.agustin.controller;

import com.agustin.controller.dto.OwnerDto;
import com.agustin.domain.model.Owner;
import com.agustin.service.OwnerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/owners")
@Tag(name = "Owners Controller", description = "Managing owners .")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService)
    {
        this.ownerService = ownerService;
    }

    @GetMapping
    public ResponseEntity<List<Owner>> findAll()
    {
        var owners = ownerService.findAll();

        //var ownersDto = owners.stream().map(OwnerDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(owners);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> findById(@PathVariable Long id)
    {
        var owner = ownerService.findById(id);

        return  ResponseEntity.ok(owner);
    }

    @PostMapping
    public ResponseEntity<Owner> create(@RequestBody OwnerDto ownerToCreate)
    {
        var ownerCreated = ownerService.create(ownerToCreate.toModel());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(ownerCreated.getId())
                        .toUri();

        return  ResponseEntity.created(location).body(ownerCreated);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Owner> update(@PathVariable Long id, @RequestBody OwnerDto owner)
    {
        var ownerUpdated = ownerService.update(id, owner.toModel());
        return ResponseEntity.ok(ownerUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Owner> delete(@PathVariable Long id)
    {
        ownerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
