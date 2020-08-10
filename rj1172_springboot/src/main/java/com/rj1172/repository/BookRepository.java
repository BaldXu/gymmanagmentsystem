package com.rj1172.repository;

import com.rj1172.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT b FROM Book b where b.placename=?1 and b.placenumber=?2 and b.orderdate=?3 order by b.begintime asc")
    List findAll(String placename , String placenumber ,String orderdate);

    @Query("SELECT b FROM Book b where b.username=?1")
    Page findAllByUsername( String username,PageRequest pageRequest);

    @Query("SELECT b FROM Book b where b.placename=?1 and b.placenumber=?2")
    Page findAllplaceorders( String placename,String placenumber,PageRequest pageRequest);

    @Query("SELECT b FROM Book b where b.orderdate=?1 ")
    Page findAlldateorders(String orderdate,PageRequest pageRequest);

    @Query("SELECT b FROM Book b where b.orderdate=?1 and b.placename=?2 and b.placenumber=?3")
    Page findAllplaceanddateorders(String orderdate,String placename,String placenumber,PageRequest pageRequest);

    @Query("SELECT book FROM Book book where book.userpost=?1")
    Page findAllpostbook(String userpost,PageRequest pageRequest);

    @Query("SELECT book FROM Book book where book.status=?1 and book.username=?2")
    Page findAllpaybook(String status,String username,PageRequest pageRequest);

    @Query("SELECT book FROM Book book where book.status<>?1 and book.username=?2")
    Page findrefundbook(String status,String username,PageRequest pageRequest);

    @Query("SELECT book FROM Book book where book.status=?1")
    Page findAllrefundbook(String status,PageRequest pageRequest);

    @Modifying
    @Transactional
    @Query("delete from Book b where b.id = ?1")
    int deleteByID(int id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Book b  set b.placename=?2,b.placenumber=?3,b.orderdate=?4,b.begintime=?5,b.endtime=?6,b.userpost=?7 where b.id=?1", nativeQuery=true)
    int update(int id , String placename ,String placenumber,String orderdate , String begintime ,String endtime ,String userpost );

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Book b  set b.reason=?2,b.status=?3 where b.id=?1", nativeQuery=true)
    int updatepay(int id ,String reason,String status);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Book b  set b.status=?2 where b.id=?1", nativeQuery=true)
    int refund(int id ,String status);
}
