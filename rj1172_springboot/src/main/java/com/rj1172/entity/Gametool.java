package com.rj1172.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Gametool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gametoolid;
    private String gametoolname;
    private int gameid;
}
