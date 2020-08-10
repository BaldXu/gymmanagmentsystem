package com.rj1172.repository;

import com.rj1172.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface GameRepository extends JpaRepository<Game,Integer> {
//    根据ID查询
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value ="update Game g set g.intro=?1 where g.gameid=?2")
    int gameIntroUpdata(String intro,int id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value ="update Game g set g.gameplace=?1 where g.gameid=?2")
    int gamePlaceUpdata(String place,int id);
}
