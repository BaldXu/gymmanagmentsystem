package com.rj1172.repository;

import com.rj1172.entity.Place;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Integer> {


    @Query
    int countByPlacename(String placename);

    @Query
    Place findByPlacename(String placename);

    @Modifying
    @Transactional
    @Query("delete from Place place where place.placename = ?1 and place.placenumber=?2")
    int deleteByPlacenameAndPlacenumber(String placename,String placenumber);


    @Modifying
    @Query("SELECT DISTINCT place.placename FROM Place place")
    List findAllplace();

    @Modifying
    @Query("SELECT place.placenumber FROM Place place where place.placename = ?1")
    List findplaceid(String placename);
}
