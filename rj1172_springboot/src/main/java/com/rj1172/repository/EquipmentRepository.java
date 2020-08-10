package com.rj1172.repository;

import com.rj1172.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment , String> {
    @Query
    List<Equipment> findByIdLike(String str);
}
