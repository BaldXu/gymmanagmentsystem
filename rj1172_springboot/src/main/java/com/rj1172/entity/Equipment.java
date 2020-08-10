package com.rj1172.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Equipment {
    @Id
    @Column(name = "id")
    private String id;
    private String name;
    private String status;
}
