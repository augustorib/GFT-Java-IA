package com.agustin.controller;

import com.agustin.controller.dto.tenant.TenantRequestDto;
import com.agustin.domain.model.Tenant;
import com.agustin.service.TenantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tenant")
@Tag(name = "Tenant Controller", description = "Managing tenant .")
public class TenantController {

    private final TenantService tenantService;

    public TenantController (TenantService tenantService)
    {
        this.tenantService = tenantService;
    }

    @GetMapping
    public ResponseEntity<List<Tenant>> findAll()
    {
        var tenants = tenantService.findAll();

        return ResponseEntity.ok(tenants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tenant> findById(@PathVariable Long id)
    {
        var tenant = tenantService.findById(id);

        return ResponseEntity.ok(tenant);
    }

    @PostMapping
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
    public ResponseEntity<Tenant> update(@PathVariable Long id, @RequestBody TenantRequestDto tenant)
    {
        var tenantUpdated = tenantService.update(id, tenant.toModel());

        return ResponseEntity.ok(tenantUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tenant> delete(@PathVariable Long id)
    {
        tenantService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
