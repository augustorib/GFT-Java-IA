package com.agustin.controller;

import com.agustin.controller.dto.tenant.TenantRequestDto;
import com.agustin.domain.model.Tenant;
import com.agustin.service.TenantService;
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
@RequestMapping("/tenant")
@Tag(name = "Tenant Controller", description = "Managing tenant.")
public class TenantController {

    private final TenantService tenantService;

    public TenantController (TenantService tenantService)
    {
        this.tenantService = tenantService;
    }

    @GetMapping
    @Operation(summary = "Get all tenants", description = "Retrieve a list of all registered tenants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful")
    })
    public ResponseEntity<List<Tenant>> findAll()
    {
        var tenants = tenantService.findAll();

        return ResponseEntity.ok(tenants);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get tenant by Id", description = "Retrieve tenant according to requested Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Response successful"),
            @ApiResponse(responseCode = "422", description = "tenant id not processable")
    })
    public ResponseEntity<Tenant> findById(@PathVariable Long id)
    {
        var tenant = tenantService.findById(id);

        return ResponseEntity.ok(tenant);
    }

    @PostMapping
    @Operation(summary = "Create tenant", description = "Create tenant with Address and Owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "tenant created"),
            @ApiResponse(responseCode = "422", description = "Id not processable"),
    })
    public ResponseEntity<Tenant> create(@RequestBody TenantRequestDto tenantToCreate)
    {
        var tenantCreated = tenantService.create(tenantToCreate.toModel());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tenantCreated.getId())
                .toUri();

        return ResponseEntity.created(location).body(tenantCreated);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update tenant", description = "Update the data of an existing tenant according with Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "tenant updated successfully"),
            @ApiResponse(responseCode = "422", description = "Id not processable")
    })
    public ResponseEntity<Tenant> update(@PathVariable Long id, @RequestBody TenantRequestDto tenant)
    {
        var tenantUpdated = tenantService.update(id, tenant.toModel());

        return ResponseEntity.ok(tenantUpdated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a tenant", description = "Delete an existing tenant based on its Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "tenant deleted successfully"),
            @ApiResponse(responseCode = "404", description = "tenant not found")
    })
    public ResponseEntity<Tenant> delete(@PathVariable Long id)
    {
        tenantService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
