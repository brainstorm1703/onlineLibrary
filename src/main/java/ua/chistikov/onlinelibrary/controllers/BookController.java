package ua.chistikov.onlinelibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.chistikov.onlinelibrary.dao.BookDAO;
import ua.chistikov.onlinelibrary.models.Book;

import javax.validation.Valid;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookDAO bookDAO;

    @Autowired
    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String hello(Model model){
        model.addAttribute("books", bookDAO.index());
        return "book/index";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("book") Book book) {
        return "book/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book) {
        bookDAO.save(book);
        return "redirect:/book";
    }
}
