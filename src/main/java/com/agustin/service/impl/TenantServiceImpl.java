package com.agustin.service.impl;

import com.agustin.domain.model.Owner;
import com.agustin.domain.model.Tenant;
import com.agustin.domain.repository.TenantRepository;
import com.agustin.service.TenantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;

    public TenantServiceImpl(TenantRepository tenantRepository)
    {
        this.tenantRepository = tenantRepository;
    }
    @Transactional(readOnly = true)
    public List<Tenant> findAll() {

        return tenantRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Tenant findById(Long id) {

        return tenantRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Tenant id " + id + " not founded"));
    }

    @Transactional
    public Tenant create(Tenant tenantToCreate)
    {
        return tenantRepository.save(tenantToCreate);
    }

    @Transactional
    public Tenant update(Long id, Tenant tenantToUpdate)
    {
        var dbTenant = tenantRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Tenant id " + id + " not founded"));

        dbTenant.setName(tenantToUpdate.getName());
        dbTenant.setEmail(tenantToUpdate.getEmail());
        dbTenant.setPhone_number(tenantToUpdate.getPhone_number());

        return  tenantRepository.save(dbTenant);
    }

    @Transactional
    public void delete(Long id)
    {
        var tenantToDelete = tenantRepository.findById(id).orElseThrow(NoSuchElementException::new);
        tenantRepository.delete(tenantToDelete);
    }
}
