package com.rj1172.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
public class Equipmentorder {
    @Id
    @Column(name = "orderid")
    private String orderid;
    private String equipmentid;
    private String equipmentname;
    private Integer userid;
    private Timestamp renttime;
    private Timestamp endtime;
    private String orderstatus;
}
