package com.rj1172.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gameid;
    private String gamename;
    private String gameres;
    private int resid;
    private String gametime;
    private int gplaceid;
    private String gameplace;
    private String toolname;
    private int toolid;
    private String refname;
    private int refid;
    private String refintro;
    private String intro;

}
