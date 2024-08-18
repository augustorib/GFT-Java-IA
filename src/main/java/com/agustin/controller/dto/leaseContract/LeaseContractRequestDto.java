package com.agustin.controller.dto.leaseContract;

import com.agustin.domain.model.LeaseContract;
import com.agustin.domain.model.Property;
import com.agustin.domain.model.Tenant;

import java.math.BigDecimal;
import java.util.Date;

public record LeaseContractRequestDto(Date start_date, Date end_date, BigDecimal security_deposit, Long property_id, Long tenant_id) {

    public LeaseContractRequestDto(LeaseContract model)
    {
      this(
              model.getStart_date(),
              model.getEnd_date(),
              model.getSecurity_deposit(),
              model.getProperty().getId(),
              model.getTenant().getId()

      );
    }

    public LeaseContract toModel(Property property, Tenant tenant)
    {
        LeaseContract model = new LeaseContract();

        model.setStart_date(this.start_date);
        model.setEnd_date(this.end_date);
        model.setSecurity_deposit(this.security_deposit);
        model.setProperty(property);
        model.setTenant(tenant);

        return model;
    }

}
