package com.agustin.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone_number;

    @JsonIgnore
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Property> properties;

}
