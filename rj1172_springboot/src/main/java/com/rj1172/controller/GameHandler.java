package com.rj1172.controller;

import com.rj1172.entity.Game;
import com.rj1172.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gameData")
public class GameHandler {
    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/findAll")
    public List<Game>findAll(){
        return gameRepository.findAll();
    }

    @PostMapping("/gameCreate")
    public String gameCreate(@RequestBody Game game){
        Game result = gameRepository.save(game);
        if(result != null){//不等于null表示访问成功了
            return "success";
        }else {
            return "error";
        }
    }

    @GetMapping("/findById/{id}")
    public Game findById(@PathVariable("id") Integer id){
        return gameRepository.findById(id).get();
    }

    @DeleteMapping("deleteById/{id}")
    public void gameDelete(@PathVariable("id") Integer id){
        gameRepository.deleteById(id);
    }

//    公告
    @GetMapping("/Intro/{id}/{intro}")
    public Game introUpdata(@PathVariable("id") Integer id,@PathVariable("intro") String intro){
        gameRepository.gameIntroUpdata(intro,id);
        return gameRepository.findById(id).get();
    }
    //    场地
    @GetMapping("/placeUpdata/{id}/{place}")
    public Game placeUpdata(@PathVariable("id") Integer id,@PathVariable("place") String place){
        gameRepository.gamePlaceUpdata(place,id);
        return gameRepository.findById(id).get();
    }

}
