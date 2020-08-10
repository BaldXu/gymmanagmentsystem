package com.rj1172.repository;

import com.rj1172.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface NewsRepository extends JpaRepository<News, Integer> {

    @Query
    News findById(int id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value ="update news set news.msg=?1 where news.type=?2", nativeQuery=true)
    int update(String msg,String type);

    @Query
    int countAllBy();
}
