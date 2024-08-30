package com.agustin.controller;

import com.agustin.controller.dto.owner.OwnerRequestDto;
import com.agustin.domain.model.Owner;
import com.agustin.service.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/owners")
@Tag(name = "Owners Controller", description = "Managing owners.")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService)
    {
        this.ownerService = ownerService;
    }

    @GetMapping
    @Operation(summary = "Get all owners", description = "Retrieve a list of all registered owners")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful")
    })
    public ResponseEntity<List<Owner>> findAll()
    {
        var owners = ownerService.findAll();

        return ResponseEntity.ok(owners);
    }

    @Operation(summary = "Get owner by Id", description = "Retrieve owner according to requested Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Response successful"),
            @ApiResponse(responseCode = "422", description = "owner id not processable")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Owner> findById(@PathVariable Long id)
    {
        var owner = ownerService.findById(id);

        return  ResponseEntity.ok(owner);
    }

    @PostMapping
    @Operation(summary = "Create owner", description = "Create owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "owner created"),
            @ApiResponse(responseCode = "422", description = "Id not processable"),
    })
    public ResponseEntity<Owner> create(@RequestBody OwnerRequestDto ownerToCreate)
    {
        var ownerCreated = ownerService.create(ownerToCreate.toModel());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(ownerCreated.getId())
                        .toUri();

        return  ResponseEntity.created(location).body(ownerCreated);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update owner", description = "Update the data of an existing owner according with Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "owner updated successfully"),
            @ApiResponse(responseCode = "422", description = "Id not processable")
    })
    public ResponseEntity<Owner> update(@PathVariable Long id, @RequestBody OwnerRequestDto owner)
    {
        var ownerUpdated = ownerService.update(id, owner.toModel());
        return ResponseEntity.ok(ownerUpdated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a owner", description = "Delete an existing owner based on its Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "owner deleted successfully"),
            @ApiResponse(responseCode = "404", description = "owner not found")
    })
    public ResponseEntity<Owner> delete(@PathVariable Long id)
    {
        ownerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
