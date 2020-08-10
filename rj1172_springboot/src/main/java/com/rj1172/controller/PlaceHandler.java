package com.rj1172.controller;

import com.rj1172.entity.Book;
import com.rj1172.entity.News;
import com.rj1172.entity.Place;
import com.rj1172.repository.BookRepository;
import com.rj1172.repository.NewsRepository;
import com.rj1172.repository.PlaceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/place")
public class PlaceHandler {
    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/placenews")
    public News findnews(){
        News news=newsRepository.findById(1);
        System.out.println(news);
        return news;
    }

    @PostMapping("/newsupdate")
    public  String newsupdate(@RequestBody Map<String,Object> data){
        String type = (String) data.get("type");
        String msg = (String) data.get("msg");
        int result ;
        result = newsRepository.update(msg,type);
        if(result != 0){
            return "success";
        }
        else {
            return "error";
        }
    }

    @GetMapping("/placeannouncement")
    public News findannouncement(){
        News news=newsRepository.findById(newsRepository.countAllBy());
        System.out.println(news);
        return news;
    }

    @GetMapping("/notices/{page}/{size}")
    public Page<News> findnotices(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        PageRequest pageable = PageRequest.of(page-1,size);
        return newsRepository.findAll(pageable);
    }

    @PostMapping("/announcementadd")
    public  String addannouncement(@RequestBody Map<String,Object> data){
        String type = (String) data.get("type");
        String msg = (String) data.get("msg");
        String title = (String) data.get("title");
        News result = null;
        News news = new News();
        news.setType(type);
        news.setMsg(msg);
        news.setTitle(title);
        result = newsRepository.save(news);
        if(result != null){
            return "success";
        }
        else {
            return "error";
        }
    }


    @PostMapping("/placeadd")
    public  String placeadd(@RequestBody Map<String,Object> data){
        String placename = (String) data.get("placename");
        int num = placeRepository.countByPlacename(placename);
        int count = num+1;
        Place result = null;
        Place place = new Place();
        place.setPlacename(placename);
        place.setPlacenumber(""+count+"");
        result = placeRepository.save(place);
        if(result != null){
            return "success";
        }
        else {
            return "error";
        }
    }

    @PostMapping("/placedelete")
    public  String placedelete(@RequestBody Map<String,Object> data){
        String placename = (String) data.get("placename");
        String num = (String) data.get("placenumber");
        int result = 0;
        result = placeRepository.deleteByPlacenameAndPlacenumber(placename,num);
        if(result != 0){
            return "success";
        }
        else {
            return "error";
        }
    }

    @GetMapping("/places/{page}/{size}")
    public Page<Place> findplaces(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        PageRequest pageable = PageRequest.of(page-1,size);
        return placeRepository.findAll(pageable);
    }

    @GetMapping("/placenames")
    public List<Place> findallplaces(){
        List places = placeRepository.findAllplace();
        System.out.println(places);
        return places;
    }

    @GetMapping("/places")
    public List<Place> findall(){
        return  placeRepository.findAll();
    }


    @PostMapping("/placeorder")
    public  String saveorder(@RequestBody Map<String,Object> data){
        String placename = (String) data.get("placename");
        String placenumber = (String) data.get("placenumber");
        String orderdate = (String) data.get("orderdate");
        String begintime = (String) data.get("begintime");
        String endtime = (String) data.get("endtime");
        String username = (String) data.get("username");
        String userpost = (String) data.get("userpost");
        String status = (String) data.get("status");

        Book news =new Book();
        news.setPlacename(placename);
        news.setPlacenumber(placenumber);
        news.setOrderdate(orderdate);
        news.setBegintime(begintime);
        news.setEndtime(endtime);
        news.setUsername(username);
        news.setUserpost(userpost);
        news.setStatus(status);

        Book result = null;

        List book=bookRepository.findAll(placename,placenumber,orderdate);
        System.out.println(book);
        if(book.size() == 0){
            result =  bookRepository.save(news);
        }
        else if(book.size() == 1){
            Book book1 = (Book) book.get(0);
            int result0 = endtime.compareTo(book1.getBegintime());
            int result1 = begintime.compareTo(book1.getEndtime());
            if (result0 <= 0) {
                result = bookRepository.save(news);
            }
            else if (result1 >= 0) {
                result = bookRepository.save(news);
            }
        }
        else {
            for (int i = 1; i < book.size(); i++) {
                Book book1 = (Book) book.get(0);
                Book book2 = (Book) book.get(i - 1);
                Book book3 = (Book) book.get(i);
                Book book4 = (Book) book.get(book.size() - 1);
                int result0 = endtime.compareTo(book1.getBegintime());
                int result1 = begintime.compareTo(book2.getEndtime());
                int result2 = endtime.compareTo(book3.getBegintime());
                int result3 = begintime.compareTo(book4.getEndtime());
                if (result0 <= 0) {
                    result = bookRepository.save(news);
                    break;
                } else if (result1 >= 0 && result2 <= 0) {
                    result = bookRepository.save(news);
                    break;
                } else if (result3 >= 0) {
                    result = bookRepository.save(news);
                    break;
                }
            }
        }
        System.out.println(result);
        if(result != null){
            return "success";
        }
        else {
            return "error";
        }
    }

    @GetMapping("/userorders/{page}/{size}")
    public Page<Book> finduserorders(@PathVariable("page") Integer page, @PathVariable("size") Integer size,
                                     @RequestParam("username") String username ){
        PageRequest pageable = PageRequest.of(page-1,size);
        return bookRepository.findAllByUsername(username,pageable);
    }

    @PostMapping("/orderdelete")
    public String deleteorder(@RequestBody Map<String,Object> data){
        int id = (int) data.get("id");
        int result = 0;
        result = bookRepository.deleteByID(id);
        System.out.println(result);
        if(result != 0){
            return "success";
        }
        else {
            return "error";
        }
    }

    @PostMapping("reformorder")
    public  String reformorder(@RequestBody Map<String,Object> data){
        int id = (int) data.get("id");
        String placename = (String) data.get("placename");
        String placenumber = (String) data.get("placenumber");
        String orderdate = (String) data.get("orderdate");
        String begintime = (String) data.get("begintime");
        String endtime = (String) data.get("endtime");
        String userpost = (String) data.get("userpost");

        int result = 0;

        List book=bookRepository.findAll(placename,placenumber,orderdate);
        System.out.println(book);
        if(book.size() == 0){
            result =  bookRepository.update(id,placename,placenumber,
                    orderdate,begintime,endtime,userpost);
        }
        else if(book.size() == 1){
            Book book1 = (Book) book.get(0);
            int result0 = endtime.compareTo(book1.getBegintime());
            int result1 = begintime.compareTo(book1.getEndtime());
            if (result0 <= 0) {
                result =  bookRepository.update(id,placename,placenumber,
                        orderdate,begintime,endtime,userpost);
            }
            else if (result1 >= 0) {
                result =  bookRepository.update(id,placename,placenumber
                        ,orderdate,begintime,endtime,userpost);
            }
        }
        else {
            for (int i = 1; i < book.size(); i++) {
                Book book1 = (Book) book.get(0);
                Book book2 = (Book) book.get(i - 1);
                Book book3 = (Book) book.get(i);
                Book book4 = (Book) book.get(book.size() - 1);
                int result0 = endtime.compareTo(book1.getBegintime());
                int result1 = begintime.compareTo(book2.getEndtime());
                int result2 = endtime.compareTo(book3.getBegintime());
                int result3 = begintime.compareTo(book4.getEndtime());
                if (result0 <= 0) {
                    result =  bookRepository.update(id,placename,placenumber,orderdate,
                            begintime,endtime,userpost);
                    break;
                } else if (result1 >= 0 && result2 <= 0) {
                    result =  bookRepository.update(id,placename,placenumber,orderdate,
                            begintime,endtime,userpost);
                    break;
                } else if (result3 >= 0) {
                    result =  bookRepository.update(id,placename,placenumber,orderdate,
                            begintime,endtime,userpost);
                    break;
                }
            }
        }
        System.out.println(result);
        if(result != 0){
            return "success";
        }
        else {
            return "error";
        }
    }

    @GetMapping("/orders/{page}/{size}")
    public Page<Book> findorders(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        PageRequest pageable = PageRequest.of(page-1,size);
        return bookRepository.findAll(pageable);
    }

    @GetMapping("/placeorders/{page}/{size}")
    public Page<Book> findplaceorders(@PathVariable("page") Integer page, @PathVariable("size") Integer size,
                                      @RequestParam("placename") String placename,
                                      @RequestParam("placenumber") String placenumber){
        PageRequest pageable = PageRequest.of(page-1,size);
        return bookRepository.findAllplaceorders(placename,placenumber,pageable);
    }

    @GetMapping("/dateorders/{page}/{size}")
    public Page<Book> finddateorders(@PathVariable("page") Integer page, @PathVariable("size") Integer size,
                                     @RequestParam("orderdate") String orderdate){
        PageRequest pageable = PageRequest.of(page-1,size);
        return bookRepository.findAlldateorders(orderdate,pageable);
    }

    @GetMapping("/placeanddateorders/{page}/{size}")
    public Page<Book> findplaceanddateorders(@PathVariable("page") Integer page, @PathVariable("size") Integer size,
                                             @RequestParam("orderdate") String orderdate, @RequestParam("placename") String placename,
                                             @RequestParam("placenumber") String placenumber){
        PageRequest pageable = PageRequest.of(page-1,size);
        return bookRepository.findAllplaceanddateorders(orderdate,placename,placenumber,pageable);
    }

    @GetMapping("/postorders/{page}/{size}")
    public Page<Book> findpostorders(@PathVariable("page") Integer page, @PathVariable("size") Integer size,
                                     @RequestParam("userpost") String userpost){
        PageRequest pageable = PageRequest.of(page-1,size);
        Page book = bookRepository.findAllpostbook(userpost,pageable);
        System.out.println(book);
        return book;
    }

    @GetMapping("/payorders/{page}/{size}")
    public Page<Book> findpayorders(@PathVariable("page") Integer page, @PathVariable("size") Integer size,
                                    @RequestParam("status") String status,
                                    @RequestParam("username") String username){
        PageRequest pageable = PageRequest.of(page-1,size);
        Page book = bookRepository.findAllpaybook(status,username,pageable);
        System.out.println(book);
        return book;
    }

    @GetMapping("/refunds/{page}/{size}")
    public Page<Book> refunds(@PathVariable("page") Integer page, @PathVariable("size") Integer size,
                              @RequestParam("status") String status,
                              @RequestParam("username") String username){
        PageRequest pageable = PageRequest.of(page-1,size);
        Page book = bookRepository.findrefundbook(status,username,pageable);
        System.out.println(book);
        return book;
    }

    @GetMapping("/refundorders/{page}/{size}")
    public Page<Book> findrefundorders(@PathVariable("page") Integer page, @PathVariable("size") Integer size,
                                       @RequestParam("status") String status){
        PageRequest pageable = PageRequest.of(page-1,size);
        Page book = bookRepository.findAllrefundbook(status,pageable);
        System.out.println(book);
        return book;
    }

    @PostMapping("/applyrefund")
    public String updatepayorders(@RequestBody Map<String,Object> data){
        int id =(int) data.get("id");
        String reason = (String) data.get("reason");
        String status = (String) data.get("status");

        int result=0;
        result=bookRepository.updatepay(id,reason,status);
        System.out.println(result);
        if(result != 0){
            return "success";
        }
        else {
            return "error";
        }
    }

    @PostMapping("/refund")
    public String refund(@RequestBody Map<String,Object> data){
        int id =(int) data.get("id");
        String status = (String) data.get("status");

        int result=0;
        result=bookRepository.refund(id,status);
        System.out.println(result);
        if(result != 0){
            return "success";
        }
        else {
            return "error";
        }
    }
}
