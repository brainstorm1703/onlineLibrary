package ua.chistikov.onlinelibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.chistikov.onlinelibrary.dao.BookDAO;
import ua.chistikov.onlinelibrary.dao.LibraryDAO;
import ua.chistikov.onlinelibrary.dao.PersonDAO;
import ua.chistikov.onlinelibrary.models.Book;
import ua.chistikov.onlinelibrary.models.Library;
import ua.chistikov.onlinelibrary.models.Person;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;
    private final LibraryDAO libraryDAO;
    private final BookDAO bookDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO, LibraryDAO libraryDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.libraryDAO = libraryDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person) {
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        try {
            List<Book> b = new ArrayList<>();
            List<Library> libraries = libraryDAO.whoseBook(id);
            if (libraries.size() == 0){
                model.addAttribute("books", null);
            } else {
                for (int i = 0; i < libraries.size(); i++) {
                    b.add(bookDAO.show(libraries.get(i).getBook_id()));
                }
                model.addAttribute("books", b);
            }
        } catch (Error e){
            model.addAttribute("books", null);
        }

        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, @PathVariable("id") int id) {
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(Model model, @PathVariable("id") int id){
        libraryDAO.deleteByPerson(id);
        personDAO.delete(id);
        return "redirect:/people";
    }
}
