package com.agustin.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

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





}
