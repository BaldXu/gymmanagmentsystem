package com.rj1172.service.impl;

import com.rj1172.dao.IEquipmentDao;
import com.rj1172.entity.Equipment;
import com.rj1172.entity.Equipmentorder;
import com.rj1172.entity.Equipmentprice;
import com.rj1172.service.IEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("EquipmentService")
@Transactional(propagation = Propagation.SUPPORTS,readOnly = false)
public class EquipmentServiceImpl implements IEquipmentService {

    @Autowired
    private IEquipmentDao EquitmentDao;

    @Override
    public String EquipmentAdd(String name,int num){
        String result = EquitmentDao.EquipmentAdd(name,num);
        return result;
    }

    @Override
    public String EquipmentDelete(String Id) {
        String result = EquitmentDao.EquipmentDelete(Id);
        return result;
    }

    @Override
    public Page<Equipment> FindAllLike(String name,String status,Pageable pageable) {
        return EquitmentDao.FindAllLike(name,status,pageable);
    }

    @Override
    public Page<Equipment> FindAll(Pageable pageable) {
        return EquitmentDao.FindAll(pageable);
    }

    @Override
    public Equipment FindById(String Id) {
        return EquitmentDao.FindById(Id);
    }

    @Override
    public String EquipmentFix(String Id) {
        return EquitmentDao.EquipmentFix(Id);
    }

    @Override
    public Page<Equipmentprice> FindAllStandard(Pageable pageable) {
        return EquitmentDao.FindAllStandard(pageable);
    }

    @Override
    public List<Equipmentprice> GetEquipment() {
        return EquitmentDao.GetEquipment();
    }

    @Override
    public Page<Equipmentorder> GetOrders(Pageable pageable) {
        return EquitmentDao.GetOrders(pageable);
    }

    @Override
    public Page<Equipmentorder> FindOrderById(Pageable pageable,String OrderId) {
        return EquitmentDao.FindOrderById(pageable,OrderId);
    }

    @Override
    public Page<Equipmentorder> FindOrderByStatus(Pageable pageable,String OrderStatus) {
        return EquitmentDao.FindOrderByStatus(pageable,OrderStatus);
    }

    @Override
    public Page<Equipmentorder> Find_NOrderByUsername(Pageable pageable, String Username) {
        return EquitmentDao.Find_NOrderByUsername(pageable,Username);
    }

    @Override
    public Page<Equipmentorder> Find_COrderByUsername(Pageable pageable, String Username) {
        return EquitmentDao.Find_COrderByUsername(pageable,Username);
    }

    @Override
    public Equipmentprice FindStandardByName(String name) {
        return EquitmentDao.FindStandardByName(name);
    }

    @Override
    public void ModifyStandard(Equipmentprice equipmentprice) {
        EquitmentDao.ModifyStandard(equipmentprice);
    }

    @Override
    public Map<String,Object> FindRecoverOrderById(String OrderId) {
        return EquitmentDao.FindRecoverOrderById(OrderId);
    }

    @Override
    public void ConfirmRecoverOrder(String OrderId) {
        EquitmentDao.ConfirmRecoverOrder(OrderId);
    }

    @Override
    public void ReturnEquipment(String OrderId) {
        EquitmentDao.ReturnEquipment(OrderId);
    }

    @Override
    public Integer FindUserByName(String Username) {
        return EquitmentDao.FindUserByName(Username);
    }

    @Override
    public void PostOrder(String EquipmentId,Integer UserId) {
        EquitmentDao.PostOrder(EquipmentId,UserId);
    }

    @Override
    public void ExamineOrder(String OrderId) {
        EquitmentDao.ExamineOrder(OrderId);
    }

    @Override
    public void AddStandard(Equipmentprice equipmentprice) {
        EquitmentDao.AddStandard(equipmentprice);
    }
}
