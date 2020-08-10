package com.rj1172.controller;

import com.rj1172.entity.Game;
import com.rj1172.entity.Gameplace;
import com.rj1172.entity.Gametool;
import com.rj1172.repository.GameToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gameTool")
public class GameToolHandler {
    @Autowired
    private GameToolRepository gameToolRepository;

    @GetMapping("/findAll")
    public List<Gametool> findAll(){
        return gameToolRepository.findAll();
    }

    @PostMapping("/toolAdd")
    public String placeApply(@RequestBody Gametool gametool){
        Gametool result = gameToolRepository.save(gametool);
        if(result != null){//不等于null表示访问成功了
            return "success";
        }else {
            return "error";
        }
    }
}
