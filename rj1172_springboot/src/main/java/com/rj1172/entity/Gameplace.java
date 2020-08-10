package com.rj1172.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Gameplace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gameplaceid;
    private String gameplacename;
    private String applytime;
    private String starttime;
    private String endtime;
    private String applyreason;
}
