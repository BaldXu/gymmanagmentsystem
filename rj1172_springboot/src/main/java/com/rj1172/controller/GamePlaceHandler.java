package com.rj1172.controller;


import com.rj1172.entity.Gameplace;
import com.rj1172.repository.GamePlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gamePlace")
public class GamePlaceHandler {
    @Autowired
    private GamePlaceRepository gamePlaceRepository;

    @GetMapping("/findAll")
    public List<Gameplace>findAll(){
        return gamePlaceRepository.findAll();
    }

    @PostMapping("/placeApply")
    public String placeApply(@RequestBody Gameplace gameplace){
          Gameplace result = gamePlaceRepository.save(gameplace);
        if(result != null){//不等于null表示访问成功了
            return "success";
        }else {
            return "error";
        }
    }
}
