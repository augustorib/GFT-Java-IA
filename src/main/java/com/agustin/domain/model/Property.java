package com.agustin.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;


import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class Property {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String description;

    private  int bedroom;

    private  int bathroom;

    private BigDecimal rental_price;

    private Boolean availability;

    @ManyToOne
    @JoinColumn(name ="owner_id")
    @JsonBackReference
    private Owner owner;







}
