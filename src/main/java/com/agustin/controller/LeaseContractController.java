package com.agustin.controller;

import com.agustin.controller.dto.leaseContract.LeaseContractRequestDto;
import com.agustin.domain.model.LeaseContract;
import com.agustin.domain.model.Property;
import com.agustin.domain.model.Tenant;
import com.agustin.service.LeaseContractService;
import com.agustin.service.PropertyService;
import com.agustin.service.TenantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/leaseContracts")
@Tag(name = "Lease Contract Controller", description = "Managing contracts .")
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
    public ResponseEntity<List<LeaseContract>> findAll()
    {
        var leaseContracts = leaseContractService.findAll();

        return ResponseEntity.ok(leaseContracts);

    }
    @GetMapping("/{id}")
    public ResponseEntity<LeaseContract> findById(@RequestParam Long id)
    {
        var leaseContracts = leaseContractService.findById(id);

        return ResponseEntity.ok(leaseContracts);

    }

    @PostMapping
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
    public ResponseEntity<LeaseContract> update(@RequestParam Long id, @RequestBody LeaseContractRequestDto leaseContract)
    {
        Property property = propertyService.findById(leaseContract.property_id());

        Tenant tenant = tenantService.findById(leaseContract.tenant_id());

        var leaseContractToUpdate = leaseContractService.update(id, leaseContract.toModel(property, tenant));

        return  ResponseEntity.ok(leaseContractToUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Property> delete(@PathVariable Long id)
    {
        leaseContractService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
