package com.rj1172.dao;

import com.rj1172.entity.Equipment;
import com.rj1172.entity.Equipmentorder;
import com.rj1172.entity.Equipmentprice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IEquipmentDao {
    String EquipmentAdd(String name,int num);

    String EquipmentDelete(String Id);

    Page<Equipment> FindAllLike(String name,String status,Pageable pageable);

    Page<Equipment> FindAll(Pageable pageable);

    Equipment FindById(String Id);

    String EquipmentFix(String Id);

    Page<Equipmentprice> FindAllStandard(Pageable pageable);

    List<Equipmentprice> GetEquipment();

    Page<Equipmentorder> GetOrders(Pageable pageable);

    Page<Equipmentorder> FindOrderById(Pageable pageable,String OrderId);

    Page<Equipmentorder> FindOrderByStatus(Pageable pageable,String OrderStatus);

    Page<Equipmentorder> Find_NOrderByUsername(Pageable pageable,String Username);

    Page<Equipmentorder> Find_COrderByUsername(Pageable pageable,String Username);

    Equipmentprice FindStandardByName(String name);

    void ModifyStandard(Equipmentprice equipmentprice);

    Map<String,Object> FindRecoverOrderById(String OrderId);

    void ConfirmRecoverOrder(String OrderId);

    void ReturnEquipment(String OrderId);

    Integer FindUserByName(String Username);

    void PostOrder(String EquipmentId,Integer UserId);

    void ExamineOrder(String OrderId);

    void AddStandard(Equipmentprice equipmentprice);
}
