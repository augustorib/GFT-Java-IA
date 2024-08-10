package com.agustin.controller;

import com.agustin.domain.model.Tenant;
import com.agustin.service.TenantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
