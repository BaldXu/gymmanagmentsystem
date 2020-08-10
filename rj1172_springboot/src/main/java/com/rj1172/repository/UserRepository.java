package com.rj1172.repository;

import com.rj1172.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query
    User findByUsername(String username);
    int deleteAllById(Integer id);


    @Query
    int countByUsername(String username);
    @Modifying
    @Query(value = "SELECT DISTINCT User.username FROM User user",nativeQuery = true)
    List findAlluser(String username,String sex,String phone,String address);

}
