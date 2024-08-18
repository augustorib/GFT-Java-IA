package com.agustin.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private String city;

    private String state;

    private String postal_code;

    private int address_Number;

    @JsonIgnore
    @OneToMany(mappedBy = "address")
    private List<Property> properties;

}
