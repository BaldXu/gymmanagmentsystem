package com.rj1172.repository;

import com.rj1172.entity.Equipment;
import com.rj1172.entity.Equipmentorder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipmentOrderRepository extends JpaRepository<Equipmentorder,String> {
    @Query
    List<Equipmentorder> findByOrderidLike(String str);

}
