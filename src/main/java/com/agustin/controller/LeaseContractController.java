package com.agustin.controller;

import com.agustin.controller.dto.leaseContract.LeaseContractRequestDto;
import com.agustin.domain.model.LeaseContract;
import com.agustin.domain.model.Property;
import com.agustin.domain.model.Tenant;
import com.agustin.service.LeaseContractService;
import com.agustin.service.PropertyService;
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
@RequestMapping("/leaseContracts")
@Tag(name = "Lease Contract Controller", description = "Managing contracts.")
public class LeaseContractController {

    private final LeaseContractService leaseContractService;
    private final PropertyService propertyService;
    private final TenantService tenantService;

    public LeaseContractController(LeaseContractService leaseContractService, PropertyService propertyService, TenantService tenantService)
    {
        this.leaseContractService = leaseContractService;
        this.tenantService = tenantService;
        this.propertyService = propertyService;
    }

    @GetMapping
    @Operation(summary = "Get all lease contracts", description = "Retrieve a list of all registered lease contract")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful")
    })
    public ResponseEntity<List<LeaseContract>> findAll()
    {
        var leaseContracts = leaseContractService.findAll();

        return ResponseEntity.ok(leaseContracts);

    }
    @GetMapping("/{id}")
    @Operation(summary = "Get lease contract by Id", description = "Retrieve lease contract according to requested Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Response successful"),
            @ApiResponse(responseCode = "422", description = "lease contract id not processable")
    })
    public ResponseEntity<LeaseContract> findById(@RequestParam Long id)
    {
        var leaseContracts = leaseContractService.findById(id);

        return ResponseEntity.ok(leaseContracts);

    }

    @PostMapping
    @Operation(summary = "Create lease contract", description = "Create lease contract with property and tenant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "lease contract created"),
            @ApiResponse(responseCode = "422", description = "Id not processable"),
    })
    public ResponseEntity<LeaseContract> create(@RequestBody LeaseContractRequestDto leaseContractToCreate)
    {
        Property property = propertyService.findById(leaseContractToCreate.property_id());

        Tenant tenant = tenantService.findById(leaseContractToCreate.tenant_id());

        var leaseContractCreated = leaseContractService.create(leaseContractToCreate.toModel(property, tenant));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(leaseContractCreated.getId())
                .toUri();

        return  ResponseEntity.created(location).body(leaseContractCreated);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update lease contract", description = "Update the data of an existing lease contract according with Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "lease contract updated successfully"),
            @ApiResponse(responseCode = "422", description = "Id not processable")
    })
    public ResponseEntity<LeaseContract> update(@RequestParam Long id, @RequestBody LeaseContractRequestDto leaseContract)
    {
        Property property = propertyService.findById(leaseContract.property_id());

        Tenant tenant = tenantService.findById(leaseContract.tenant_id());

        var leaseContractToUpdate = leaseContractService.update(id, leaseContract.toModel(property, tenant));

        return  ResponseEntity.ok(leaseContractToUpdate);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a lease contract", description = "Delete an existing lease contract based on its Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "lease contract deleted successfully"),
            @ApiResponse(responseCode = "404", description = "lease contract not found")
    })
    public ResponseEntity<Property> delete(@PathVariable Long id)
    {
        leaseContractService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
