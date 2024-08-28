package com.agustin.domain.model;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class LeaseContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate start_date;

    private LocalDate end_date;

    private BigDecimal security_deposit;

    @ManyToOne
    @JoinColumn(name ="property_id")
    private Property property;

    @ManyToOne
    @JoinColumn(name ="tenant_id")
    private Tenant tenant;
}
