package com.rj1172.dao.impl;

import com.rj1172.dao.IEquipmentDao;
import com.rj1172.entity.Equipment;
import com.rj1172.entity.Equipmentorder;
import com.rj1172.entity.Equipmentprice;
import com.rj1172.entity.User;
import com.rj1172.repository.EquipmentOrderRepository;
import com.rj1172.repository.EquipmentPriceRepository;
import com.rj1172.repository.EquipmentRepository;
import com.rj1172.repository.UserRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository("EquipmentDao")
public class EquipmentDaoImpl implements IEquipmentDao {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private EquipmentPriceRepository equipmentPriceRepository;

    @Autowired
    private EquipmentOrderRepository equipmentOrderRepository;

    @Override
    public String EquipmentAdd(String name,int num){
        ArrayList<Equipment> result = new ArrayList<Equipment>();
        for(int i=0;i<num;i++){
            Equipment equipment = new Equipment();
            String Id = GetIndex();
            equipment.setId(Id);
            equipment.setName(name);
            equipment.setStatus("空闲");
            Equipment add = equipmentRepository.save(equipment);
            result.add(add);
        }
        if(result.size() == num){
            return "success";
        }
        else {
            return "error";
        }
    }

    public String GetIndex() {
        Date date = new Date();
        String result = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String Date = formatter.format(date);
        List<Equipment> list = equipmentRepository.findByIdLike(Date + "%");
        if(list.size() == 0){
            result = "01";
        }else{
            Equipment equipment = list.get(list.size()-1);
            String i = equipment.getId();
            String r = i.substring(i.length()-2);
            int index = Integer.parseInt(r);
            if(index >= 9){
                result = "" + ++index;
            }else{
                result = "0" + ++index;
            }
        }
        String Id = formatter.format(date) + result;
        return Id;
    }

    public String GetOrderIndex() {
        Date date = new Date();
        String result = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String Date = formatter.format(date);
        List<Equipmentorder> list = equipmentOrderRepository.findByOrderidLike(Date + "%");
        if(list.size() == 0){
            result = "01";
        }else{
            Equipmentorder equipmentorder = list.get(list.size()-1);
            String i = equipmentorder.getOrderid();
            String r = i.substring(i.length()-2);
            int index = Integer.parseInt(r);
            if(index >= 9){
                result = "" + ++index;
            }else{
                result = "0" + ++index;
            }
        }
        String Id = formatter.format(date) + result;
        return Id;
    }

    @Override
    public String EquipmentDelete(String Id) {
        Optional<Equipment> e = equipmentRepository.findById(Id);
        String result = "";
        if(e!=null && e.isPresent()){
            equipmentRepository.deleteById(Id);
            result = "success";
        }
        else{
            result = "error";
        }
        return result;
    }

    @Override
    public Page<Equipment> FindAllLike(String name,String status,Pageable pageable) {
        Equipment e = new Equipment();
        e.setName(name);
        e.setStatus(status);
        Example<Equipment> example = Example.of(e);
        return equipmentRepository.findAll(example,pageable);
    }

    @Override
    public Page<Equipment> FindAll(Pageable pageable) {
        return equipmentRepository.findAll(pageable);
    }

    @Override
    public Equipment FindById(String Id) {
        return equipmentRepository.findById(Id).get();
    }

    @Override
    public String EquipmentFix(String Id) {
        Optional<Equipment> e = equipmentRepository.findById(Id);
        String result = "";
        if(e!=null && e.isPresent()){
            Equipment equipment = e.get();
            equipment.setStatus("维修中");
            equipmentRepository.save(equipment);
            result = "success";
        }
        else{
            result = "error";
        }
        return result;
    }

    @Override
    public Page<Equipmentprice> FindAllStandard(Pageable pageable) {
        return equipmentPriceRepository.findAll(pageable);
    }

    @Override
    public List<Equipmentprice> GetEquipment() {
        return equipmentPriceRepository.findAll();
    }

    @Override
    public Page<Equipmentorder> GetOrders(Pageable pageable) {
        return equipmentOrderRepository.findAll(pageable);
    }

    @Override
    public Page<Equipmentorder> FindOrderById(Pageable pageable,String OrderId) {
        Equipmentorder e = new Equipmentorder();
        e.setOrderid(OrderId);
        Example<Equipmentorder> example = Example.of(e);
        return equipmentOrderRepository.findAll(example,pageable);
    }

    @Override
    public Page<Equipmentorder> FindOrderByStatus(Pageable pageable,String OrderStatus) {
        Equipmentorder e = new Equipmentorder();
        e.setOrderstatus(OrderStatus);
        Example<Equipmentorder> example = Example.of(e);
        return equipmentOrderRepository.findAll(example,pageable);
    }

    @Override
    public Page<Equipmentorder> Find_NOrderByUsername(Pageable pageable, String Username) {
        String name = Username.substring(1,Username.length()-1);
        User user = userRepository.findByUsername(name);
        Equipmentorder e = new Equipmentorder();
        e.setUserid(user.getId());
        e.setOrderstatus("租用中");
        Example<Equipmentorder> example = Example.of(e);
        return equipmentOrderRepository.findAll(example,pageable);
    }

    @Override
    public Page<Equipmentorder> Find_COrderByUsername(Pageable pageable, String Username) {
        String name = Username.substring(1,Username.length()-1);
        User user = userRepository.findByUsername(name);
        Equipmentorder e = new Equipmentorder();
        e.setUserid(user.getId());
        e.setOrderstatus("待确认");
        Example<Equipmentorder> example = Example.of(e);
        return equipmentOrderRepository.findAll(example,pageable);
    }

    @Override
    public Equipmentprice FindStandardByName(String name) {
        return equipmentPriceRepository.findById(name).get();
    }

    @Override
    public void ModifyStandard(Equipmentprice equipmentprice) {
        equipmentPriceRepository.save(equipmentprice);
    }

    @Override
    public Map<String,Object> FindRecoverOrderById(String OrderId) {
        Map<String,Object> map = new HashMap<>();
        Equipmentorder equipmentorder = equipmentOrderRepository.findById(OrderId).get();
        User user = userRepository.findById(equipmentorder.getUserid()).get();
        map.put("Orderid",equipmentorder.getOrderid());
        map.put("Equipmentname",equipmentorder.getEquipmentname());
        map.put("Userid",equipmentorder.getUserid());
        map.put("Username",user.getUsername());
        map.put("Renttime",equipmentorder.getRenttime());
        map.put("Endtime",equipmentorder.getEndtime());
        return map;
    }

    @Override
    public void ConfirmRecoverOrder(String OrderId) {
        Equipmentorder e = equipmentOrderRepository.findById(OrderId).get();
        Equipment equipment = equipmentRepository.findById(e.getEquipmentid()).get();
        equipment.setStatus("空闲");
        e.setOrderstatus("已确认");
        equipmentOrderRepository.save(e);
    }

    @Override
    public void ReturnEquipment(String OrderId) {
        Equipmentorder e = equipmentOrderRepository.findById(OrderId).get();
        Date date = new Date();
        Timestamp t = new Timestamp(date.getTime());
        e.setEndtime(t);
        e.setOrderstatus("待确认");
        equipmentOrderRepository.save(e);
    }

    @Override
    public Integer FindUserByName(String Username) {
        User user = userRepository.findByUsername(Username);
        return user.getId();
    }

    @Override
    public void PostOrder(String EquipmentId,Integer UserId) {
        Equipmentorder equipmentorder = new Equipmentorder();
        Equipment equipment = equipmentRepository.findById(EquipmentId).get();
        equipmentorder.setOrderid(GetOrderIndex());
        equipmentorder.setEquipmentid(EquipmentId);
        equipmentorder.setEquipmentname(equipment.getName());
        equipmentorder.setUserid(UserId);
        equipmentorder.setOrderstatus("待审核");
        equipment.setStatus("租用中");
        equipmentRepository.save(equipment);
        equipmentOrderRepository.save(equipmentorder);
    }

    @Override
    public void ExamineOrder(String OrderId) {
        Equipmentorder equipmentorder = equipmentOrderRepository.findById(OrderId).get();
        Date date = new Date();
        Timestamp t = new Timestamp(date.getTime());
        equipmentorder.setRenttime(t);
        equipmentorder.setOrderstatus("租用中");
        equipmentOrderRepository.save(equipmentorder);
    }

    @Override
    public void AddStandard(Equipmentprice equipmentprice) {
        equipmentPriceRepository.save(equipmentprice);
    }
}
