package com.yuboz.springbootdemo.service;

import com.yuboz.springbootdemo.domain.Book;
import com.yuboz.springbootdemo.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * find all list in the database
     * @return
     */
    public List<Book> findall(){
        return bookRepository.findAll();
    }

    /**
     * post a new book
     * @param
     * @return
     */

    public Book save(Book book){

        return bookRepository.save(book);

    }

    /**
     * get a new book
     * @param
     * @return
     */

    public Book findOne(long id){

        return bookRepository.findById(id).orElse(null);

    }

    /**
     * delete by id
     * @param id
     */

    public void deleteOne(long id){
        bookRepository.deleteById(id);

    }

    /**
     * find book by author
     * @param author
     */

    public List<Book> findByAuthor(String author){

        return bookRepository.findByAuthor(author);

    }

    /**
     * find book by author and status
     * @param author
     */

    public List<Book> findByAuthorAndStatus(String author, int status){

        return bookRepository.findByAuthorAndStatus(author,status);

    }

    /**
     * find book by descripyon
     * @param description
     */

    public List<Book> findByDescriptionContains(String description){

        return bookRepository.findByDescriptionContains(description);

    }

    /**
     * custom qurey
     * @param len
     * @return
     */

    public List<Book> findByJPQL(int len){
        return bookRepository.findByJPQL(len);
    }

    @Transactional
    public int updateByJPQL(int status, long id){
        return bookRepository.updateByJPQL(status,id);
    }

    @Transactional
    public int deleteByJPQL(long id){
        return bookRepository.deleteByJPQL(id);
    }

    /**
     * test transcations operationg
     * @param id
     * @param status
     * @param uid
     * @return
     */
    @Transactional
    public int deleteAndUpdate(long id,int status,long uid){

        int dcount = bookRepository.deleteByJPQL(id);

        int ucount = updateByJPQL(status,uid);
        return dcount + ucount;

    }


}
