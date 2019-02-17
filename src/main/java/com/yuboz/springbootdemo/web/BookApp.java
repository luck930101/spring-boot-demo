package com.yuboz.springbootdemo.web;

import com.yuboz.springbootdemo.domain.Book;
import com.yuboz.springbootdemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookApp {

    @Autowired
    private BookService bookService;

    /**
     * find all list in the database
     * @return
     */

//    @GetMapping("/books")
//    public List<Book> getAll(){
//        return bookService.findall();
//    }
    @GetMapping("/books")
//    public Page<Book> getAll(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size){
    public Page<Book> getAll(@PageableDefault(size = 5,sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable){

//        Sort sort = new Sort(Sort.Direction.DESC,"id");
//        Pageable pageable = PageRequest.of(page,size,sort);

        return bookService.findAllByPage(pageable);
    }


    /**
     * add one  to the database
     * @return
     */

//    @PostMapping("/books")
//    public Book post(@RequestParam String name,
//                     @RequestParam String author,
//                     @RequestParam String description,
//                     @RequestParam int status){
//
//        Book book = new Book();
//        book.setName(name);
//        book.setAuthor(author);
//        book.setDescription(description);
//        book.setStatus(status);
//
//        return bookService.save(book);
//    }
    @PostMapping("/books")
    public Book post(Book book){


        return bookService.save(book);
    }

    /**
     * get one from the database
     * @return
     */

    @GetMapping("/books/{id}")
    public Book getOne(@PathVariable long id){
        return bookService.findOne(id);

    }

    /**
     * update a book list
     * @param id
     * @param name
     * @param author
     * @param description
     * @param status
     * @return
     */


    @PutMapping("/books")
    public Book update(@RequestParam long id,
                       @RequestParam String name,
                       @RequestParam String author,
                       @RequestParam String description,
                       @RequestParam int status){

            Book book = new Book();
            book.setId(id);
            book.setName(name);
            book.setAuthor(author);
            book.setDescription(description);
            book.setStatus(status);


            return bookService.save(book);

    }

    /**
     * Delete a book
     * @param id
     */

    @DeleteMapping("/books/{id}")
    public void DeleteOne(@PathVariable long id){
        bookService.deleteOne(id);
    }

//    @PostMapping("/books/by")
//    public List<Book> findByAuthor(@RequestParam String author){
//        return bookService.findByAuthor(author);
//    }


    @PostMapping("/books/by")
    public int findBy(@RequestParam long id, @RequestParam int status, @RequestParam long uid){
//        return bookService.findByAuthor(author);
//        return bookService.findByAuthorAndStatus(author,status);
//        return bookService.findByDescriptionContains(description);
//        return  bookService.findByJPQL(length);
//        return bookService.updateByJPQL(status,id);
        return bookService.deleteAndUpdate(id,status,uid);
    }



}
