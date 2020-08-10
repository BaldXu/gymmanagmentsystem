package com.rj1172.controller;

import com.rj1172.entity.Book;
import com.rj1172.entity.Place;
import com.rj1172.entity.User;
import com.rj1172.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String findByName(@RequestParam("username") String username,@RequestParam("password") String password){
        User user =  userRepository.findByUsername(username);
        if(user == null || !(user.getPassword().equals(password))){
            return "error";
        }else{
            return "success";
        }
    }
    @PostMapping("/userdelete")
    public  String userdelete(@RequestBody Map<String,Object> data){
        String username = (String) data.get("username");
        Integer num = (Integer) data.get("userid");
        int result = 0;
        result = userRepository.deleteAllById(num);
        if(result != 0){
            return "success";
        }
        else {
            return "error";
        }
    }
    @PostMapping("reformuser")
    public  String reformuser(@RequestBody Map<String,Object> data){
        int id = (int) data.get("id");
        String username = (String) data.get("username");
        String sex = (String) data.get("sex");
        String phone = (String) data.get("phone");
        String address = (String) data.get("address");


        List book=userRepository.findAlluser(username,sex,phone,address);
        System.out.println(book);

        if(book.size() != 0){
            return "success";
        }
        else {
            return "error";
        }
    }
    @PostMapping("/useradd")
    public  String useradd(@RequestBody Map<String,Object> data){
        String username = (String) data.get("username");
        int num = userRepository.countByUsername(username);
        int count = num+1;
        User result = null;
        User user = new User();
        user.setUsername(username);
        user.setId(count);
        result = userRepository.save(user);
        if(result != null){
            return "success";
        }
        else {
            return "error";
        }
    }
}
