package com.rj1172.controller;

import com.rj1172.entity.Equipment;
import com.rj1172.entity.Equipmentorder;
import com.rj1172.entity.Equipmentprice;
import com.rj1172.service.IEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/equipment")
public class EquipmentHandler {

    @Autowired
    private IEquipmentService EquipmentService;

    @PostMapping("/equipmentedit/equipmentadd")
    public String equipmentAdd(@RequestBody Map<String,Object> data){
        String name = (String) data.get("name");
        int num = (int) data.get("num");
        String result = EquipmentService.EquipmentAdd(name,num);
        return result;
    }

    @DeleteMapping("/equipmentedit/equipmentdelete/{EquipmentId}")
    public String equipmentDelete(@PathVariable("EquipmentId") String EquipmentId){
        String result = EquipmentService.EquipmentDelete(EquipmentId);
        return result;
    }

    @GetMapping("/findAll/{page}/{size}/{name}/{status}")
    public Page<Equipment> findAllLike(@PathVariable("page") Integer page,@PathVariable("size") Integer size,@PathVariable("name") String name,@PathVariable("status") String status){
        Pageable p = PageRequest.of(page-1,size);
        return EquipmentService.FindAllLike(name,status,p);
    }

    @GetMapping("/findAll/{page}/{size}")
    public Page<Equipment> findAll(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        Pageable p = PageRequest.of(page-1,size);
        return EquipmentService.FindAll(p);
    }

    @GetMapping("/findById/{Id}")
    public Equipment findById(@PathVariable("Id") String Id){
        return EquipmentService.FindById(Id);
    }

    @PutMapping("/equipmentedit/equipmentfix")
    public String equipmentFix(@RequestBody HashMap<String,String> map){
        String Id = map.get("Id");
        return EquipmentService.EquipmentFix(Id);
    }

    @GetMapping("/findAllStandard/{page}/{size}")
    public Page<Equipmentprice> findAllStandard(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        Pageable p = PageRequest.of(page-1,size);
        return EquipmentService.FindAllStandard(p);
    }

    @GetMapping("/getequipment")
    public List<Equipmentprice> getEquipment(){
        List<Equipmentprice> list = EquipmentService.GetEquipment();
        return list;
    }

    @GetMapping("/equipmentrecover/getorders/{page}/{size}")
    public Page<Equipmentorder> getOrders(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        Pageable p = PageRequest.of(page-1,size);
        return EquipmentService.GetOrders(p);
    }

    @GetMapping("/equipmentrecover/findorderbyid/{page}/{size}/{orderid}")
    public Page<Equipmentorder> findAllOrderById(@PathVariable("page") Integer page,@PathVariable("size") Integer size,@PathVariable("orderid") String Id){
        Pageable p = PageRequest.of(page-1,size);
        return EquipmentService.FindOrderById(p,Id);
    }

    @GetMapping("/equipmentrecover/findorderbystatus/{page}/{size}/{orderstatus}")
    public Page<Equipmentorder> findAllOrderByStatus(@PathVariable("page") Integer page,@PathVariable("size") Integer size,@PathVariable("orderstatus") String status){
        Pageable p = PageRequest.of(page-1,size);
        return EquipmentService.FindOrderByStatus(p,status);
    }

    @GetMapping("/find_NOrderByUsername/{page}/{size}/{username}")
    public Page<Equipmentorder> find_NOrderByUsername(@PathVariable("page") Integer page,@PathVariable("size") Integer size,@PathVariable("username") String username){
        Pageable p = PageRequest.of(page-1,size);
        return EquipmentService.Find_NOrderByUsername(p,username);
    }

    @GetMapping("/find_COrderByUsername/{page}/{size}/{username}")
    public Page<Equipmentorder> find_COrderByUsername(@PathVariable("page") Integer page,@PathVariable("size") Integer size,@PathVariable("username") String username){
        Pageable p = PageRequest.of(page-1,size);
        return EquipmentService.Find_COrderByUsername(p,username);
    }

    @GetMapping("/findStandardByName/{name}")
    public Equipmentprice findStandardByName(@PathVariable("name") String name){
        return EquipmentService.FindStandardByName(name);
    }

    @PutMapping("/ModifyStandard")
    public void ModifyStandard(@RequestBody Equipmentprice equipmentprice){
        EquipmentService.ModifyStandard(equipmentprice);
    }

    @GetMapping("/getequipmentOrder/{Id}")
    public Map<String,Object> findOrderById(@PathVariable("Id") String Id){
        return EquipmentService.FindRecoverOrderById(Id);
    }

    @PutMapping("/equipmentOrderRecoverConfirm/{Id}")
    public void confirmRecoverOrder(@PathVariable("Id") String Id){
        EquipmentService.ConfirmRecoverOrder(Id);
    }

    @PutMapping("/equipmentReturn/{Id}")
    public void equipmentReturn(@PathVariable("Id") String Id){
        EquipmentService.ReturnEquipment(Id);
    }

    @GetMapping("/findUserByName/{username}")
    public Integer findUserByName(@PathVariable("username") String Username){
        return EquipmentService.FindUserByName(Username);
    }

    @PostMapping("/postOrder")
    public void postOrder(@RequestBody Map<String,Object> data){
        String EquipmentId = (String) data.get("EquipmentId");
        Integer UserId = Integer.parseInt(data.get("UserId").toString());
        EquipmentService.PostOrder(EquipmentId,UserId);
    }

    @PutMapping("/equipmentOrderRentConfirm/{Id}")
    public void equipmentOrderRentConfirm(@PathVariable("Id") String Id){
        EquipmentService.ExamineOrder(Id);
    }

    @PostMapping("/AddStandard")
    public void addStandard(@RequestBody Equipmentprice equipmentprice){
        EquipmentService.AddStandard(equipmentprice);
    }
}
