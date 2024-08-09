package com.agustin.service.impl;

import com.agustin.domain.model.Tenant;
import com.agustin.domain.repository.TenantRepository;
import com.agustin.service.TenantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public Tenant findById(Long aLong) {
        return null;
    }

    @Override
    public Tenant create(Tenant entity) {
        return null;
    }

    @Override
    public Tenant update(Long aLong, Tenant entity) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
