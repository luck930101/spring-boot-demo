package com.yuboz.springbootdemo.web;


import com.yuboz.springbootdemo.domain.Book;
import com.yuboz.springbootdemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller
@RestController
@RequestMapping("/api/v2")
public class HelloController {

//    @RequestMapping(value = "/say",method = RequestMethod.GET)

//    @Value("${book.name}")
//    private String name;
//    @Value("${book.author}")
//    private String author;
//    @Value("${book.isbn}")
//    private String isbn;

//    @Autowired
//    private Book book;

    @PostMapping("/say")
    public String hello(){
        return "Hello Spring Boot";
    }

    @GetMapping("/books")
//    @ResponseBody
    public Object getAll(@RequestParam("page") int page, @RequestParam(value ="size",defaultValue="10") int size){

        Map<String,Object> book1 = new HashMap<>();
//        book1.put("name",name);
        book1.put("isbn","9872326324");
        book1.put("author","Goodman");

        Map<String,Object> book2 = new HashMap<>();
        book2.put("name","Intro to Python");
        book2.put("isbn","997137713");
//        book2.put("author",author);



        List<Map> contents = new ArrayList<>();
        contents.add(book1);
        contents.add(book2);

        Map<String,Object> pagemap = new HashMap<>();
        pagemap.put("page",page);
        pagemap.put("size",size);
        pagemap.put("content", contents);


        return pagemap;
    }

    @GetMapping("/authors")
    public String getAll1(){
        return "authors";
    }

    /**
     * regular expression:{veriablename:regularexpression}
     * @param id
     * @param
     * @return
     */
    @GetMapping("/books/{id}")
    public Object getOne(@PathVariable long id){

//        System.out.println("id:"+ id);
//
//        Map<String,Object> book = new HashMap<>();
//        book.put("name",name);
//        book.put("isbn","9872326324");
//        book.put("author","Goodman");
//        book.put("username",username);

        return null;

    }
    @PostMapping("books")
    public Object post(@RequestParam("name") String name,
                       @RequestParam("author") String author,
                       @RequestParam("isbn") String isbn){
        Map<String,Object> book = new HashMap<String, Object>();
        book.put("name",name);
        book.put("author",author);
        book.put("isbn",isbn);


        return book;
    }

}
