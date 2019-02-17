package com.yuboz.springbootdemo.web;

import com.yuboz.springbootdemo.domain.Book;
import com.yuboz.springbootdemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Get Book List
 */
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
//    public String list( Model model){
    public String list(@PageableDefault(size = 5,sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                       Model model){
//        List<Book> books = bookService.findall();
//        model.addAttribute("books",books);
//        Sort sort = new Sort(Sort.Direction.DESC,"id");
//        Page<Book> paging =bookService.findAllByPage(PageRequest.of(page,size,sort));
        Page<Book> paging =bookService.findAllByPage(pageable);
        model.addAttribute("page",paging);
        return "books";
    }

    /**
     * Get Book Detail
     */

    @GetMapping("/books/{id}")
    public String detail(@PathVariable long id, Model model){
        Book book = bookService.findOne(id);

        if(book == null){
            book = new Book();
        }

        model.addAttribute("book",book);
        return "book";
    }

    /**
     * redirect to submit page
     * @return
     */
    @GetMapping("/books/addbook")
    public String AddBookPage(Model model){
        model.addAttribute("book", new Book());
        return "addbook";
    }

    /**
     * book List page
     * @return
     */
    @PostMapping("/books")
    public String post(Book book, final RedirectAttributes attributes){
        Book savedbook = bookService.save(book);
        if (savedbook !=null){
            attributes.addFlashAttribute("message","<"+savedbook.getName()+"> saved!");
        }



        return "redirect:/books";
        /** POST ----->redirect ---->GET
         * Two Requests, can not pass just one Model
         *
         * should use Flash Attribute
         */
    }


    /**
     * redirect to update page
     * @return
     */

    @GetMapping("/books/{id}/addbook")
    public String editPage(@PathVariable long id,Model model){
        Book book = bookService.findOne(id);
        model.addAttribute("book",book);

        return "addbook";
    }

    @GetMapping("/books/{id}/delete")
    public String delete(@PathVariable long id,final RedirectAttributes attributes){
        bookService.deleteOne(id);
        attributes.addFlashAttribute("message","deleted!");
        return  "redirect:/books";
    }
}