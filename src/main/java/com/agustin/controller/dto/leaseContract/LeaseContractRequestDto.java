package com.agustin.controller.dto.leaseContract;

import com.agustin.domain.model.LeaseContract;
import com.agustin.domain.model.Property;
import com.agustin.domain.model.Tenant;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public record LeaseContractRequestDto(String start_date, String end_date, BigDecimal security_deposit, Long property_id, Long tenant_id) {

    private static final DateTimeFormatter API_DATE = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public LeaseContractRequestDto(LeaseContract model)
    {
      this(
              model.getStart_date().format(API_DATE),
              model.getEnd_date().format(API_DATE),
              model.getSecurity_deposit(),
              model.getProperty().getId(),
              model.getTenant().getId()

      );
    }

    public LeaseContract toModel(Property property, Tenant tenant)
    {
        LeaseContract model = new LeaseContract();

        model.setStart_date(LocalDate.parse(this.start_date, API_DATE));
        model.setEnd_date(LocalDate.parse(this.end_date, API_DATE));
        model.setSecurity_deposit(this.security_deposit);
        model.setProperty(property);
        model.setTenant(tenant);

        return model;
    }

}
