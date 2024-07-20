package com.agustin.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String type;

    private String description;

    private  int bedroom;

    private  int bathroom;

    private BigDecimal rental_price;

    private boolean availability;

    @ManyToOne
    @JoinColumn(name ="owner_id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name ="address_id")
    private Address address;


    @OneToMany(mappedBy = "property")
    private List<LeaseContract> lease_contracts;





}
