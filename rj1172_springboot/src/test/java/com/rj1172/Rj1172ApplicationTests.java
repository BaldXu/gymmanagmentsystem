package com.rj1172;


import com.rj1172.entity.*;
import com.rj1172.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class Rj1172ApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GamePlaceRepository gamePlaceRepository;

    @Autowired
    private GameToolRepository gameToolRepository;

    @Autowired
    private EquipmentPriceRepository equipmentPriceRepository;

    @Test
    void login() {
        User user = userRepository.findByUsername("admin");
        System.out.println(user);
    }

    @Test
    void EquipmentAdd() throws ParseException {
        Equipment equipment = new Equipment();
        equipment.setName("篮球");
        equipment.setStatus("空闲");
        String str = "2011-06-19 21:32:53";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        date = sf.parse(str);
        Timestamp t = new Timestamp(date.getTime());
        //equipment.setRenttime(t);
        equipmentRepository.save(equipment);
    }

    @Test
    void PlaceAdd() {
        int num = placeRepository.countByPlacename("篮球场");
        int count = num+1;
        Place place = new Place();
        place.setPlacename("篮球场");
        place.setPlacenumber(""+count+"");
        System.out.println(placeRepository.save(place));
    }

    @Test
    void FindNews(){
        News news =newsRepository.findById(1);
        System.out.println(news);
    }

    @Test
    void updatenews(){
        int news =newsRepository.update("456","news");
        System.out.println(news);
    }

    @Test
    void addannouncement(){
        News news = new News();
        news.setType("announcement");
        news.setMsg("789798");
        News news1 =newsRepository.save(news);
        System.out.println(news1);
    }

    @Test
    void notices(){
        List news =newsRepository.findAll();
        System.out.println(news);
    }

    @Test
    void EquipmentDelete(){
        String Id = "2020061103";
        equipmentRepository.deleteById(Id);
    }

    @Test
    void GameFindAll(){
        List gameList = gameRepository.findAll();
        System.out.println(gameList);
    }

    @Test
    void GameFindById(){
        Game figame = gameRepository.findById(2).get();
        System.out.println(figame);
    }
    @Test
    void GameSave(){
        Game sgame = new Game();
        sgame.setGameid(3);
        sgame.setGamename("篮球");
        sgame.setGameplace("中央体育馆");
        sgame.setGameres("狗子2号负责人");
        sgame.setGplaceid(3);
        sgame.setGametime("7月22日");
        sgame.setRefid(3);
        sgame.setRefname("裁判3号");
        sgame.setRefintro("这是第三个裁判");
        sgame.setIntro("公告3333");
        sgame.setToolid(2);
        sgame.setToolname("篮球");

        Game game1 =  gameRepository.save(sgame);
        System.out.println(game1);
    }
    @Test
    void GameUpdata(){
        Game game = new Game();
        game.setGameid(4);
        game.setIntro("测试测试测试");
//        如果没有的话就保存
        Game game1 = gameRepository.save(game);
        System.out.println(game1);
    }
    @Test
    void GamePlaceUpdata(){
        Gameplace gp = new Gameplace();
        gp.setGameplaceid(2);
        gp.setApplyreason("666");
        gp.setApplytime("3月22日");
        gp.setGameplacename("火星体育馆");

        Gameplace gp1 = gamePlaceRepository.save(gp);
        System.out.println(gp1);
    }
    @Test
    void gameplacefindall(){
        List gpList = gamePlaceRepository.findAll();
        System.out.println(gpList);
    }

    @Test
    void EquipmentFindAll(){
        Equipment equipment = new Equipment();
        equipment.setName("羽毛球");
        equipment.setStatus("空闲");
        Pageable pageable = PageRequest.of(0,5);
        Example<Equipment> example = Example.of(equipment);
        Page<Equipment> page = equipmentRepository.findAll(example,pageable);
        System.out.println(page.getTotalElements());
    }


    @Test
    void EquipmentStandardFindAll(){
        System.out.println(equipmentPriceRepository.findAll());
    }

    @Test
    void getTime(){
        Date date = new Date();
        Timestamp t = new Timestamp(date.getTime());
        System.out.println(t);
    }
}
