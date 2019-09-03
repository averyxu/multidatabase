package com.averyxu.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "JPA_USER")
@Data
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
}
