package ua.chistikov.onlinelibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.chistikov.onlinelibrary.dao.BookDAO;
import ua.chistikov.onlinelibrary.dao.LibraryDAO;
import ua.chistikov.onlinelibrary.dao.PersonDAO;
import ua.chistikov.onlinelibrary.models.Book;
import ua.chistikov.onlinelibrary.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookDAO bookDAO;
    private final LibraryDAO libraryDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, LibraryDAO libraryDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.libraryDAO = libraryDAO;
        this.personDAO = personDAO;
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

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("human") Person person) {
        try {
            model.addAttribute("person", personDAO.show(libraryDAO.whoTook(id).getPerson_id()));
        } catch (Error e){
            model.addAttribute("person", null);
        }

        model.addAttribute("people", personDAO.index());

        model.addAttribute("book", bookDAO.show(id));
        return "book/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, @PathVariable("id") int id) {
        bookDAO.update(id, book);
        return "redirect:/book";
    }

    @DeleteMapping("/{id}")
    public String delete(Model model, @PathVariable("id") int id){
        libraryDAO.deleteByBook(id);
        bookDAO.delete(id);
        return "redirect:/book";
    }

    @PutMapping("/{id}")
    public String takeBook(@ModelAttribute("human") @Valid Person person, @PathVariable("id") int book_id) {
        libraryDAO.save(person.getPerson_id(), book_id);
        return "redirect:/book/{id}";
    }
    @PostMapping("/{id}")
    public String freeBook(@PathVariable("id") int book_id){
        libraryDAO.deleteByBook(book_id);
        return "redirect:/book/{id}";
    }
}
