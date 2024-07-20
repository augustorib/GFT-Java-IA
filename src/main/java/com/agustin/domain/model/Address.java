package com.agustin.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String street;

    private String city;

    private String state;

    private String postal_code;

    @OneToMany(mappedBy = "address")
    private List<Property> properties;

}
