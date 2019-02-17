package com.yuboz.springbootdemo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

    Page<Book> findAll(Pageable pageable);

    List<Book> findByAuthor(String author);

    List<Book> findByAuthorAndStatus(String author, int status);

    List<Book> findByDescriptionContains(String description);


//    @Query("SELECT b FROM Book b WHERE length(b.name)>?1and b.author =?2")
//    @Query("SELECT book FROM Book book WHERE length(book.name)> ?1")
    @Query(value = "SELECT * FROM book WHERE length(book.name)> ?1",nativeQuery = true)
    List<Book> findByJPQL(int length);

    @Transactional
    @Modifying
    @Query("update Book b set b.status =?1 where id = ?2")
    int updateByJPQL(int status, long id);

    @Transactional
    @Modifying
    @Query("delete from Book b where id = ?1")
    int deleteByJPQL(long id);

}

