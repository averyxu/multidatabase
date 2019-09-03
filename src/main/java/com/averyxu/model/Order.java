package com.averyxu.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "JPA_ORDER")
@Data
public class Order {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
}
