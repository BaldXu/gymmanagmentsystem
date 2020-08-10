package com.rj1172.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Equipmentprice {
    @Id
    @Column(name = "name")
    private String name;
    private float price;
}
