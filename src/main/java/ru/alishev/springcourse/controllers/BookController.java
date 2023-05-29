package ru.alishev.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.dao.BookDao;
import ru.alishev.springcourse.dao.PersonDAO;
import ru.alishev.springcourse.models.Book;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDao bookDao;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDao bookDao, PersonDAO personDAO) {
        this.bookDao = bookDao;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", bookDao.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        Book book = bookDao.show(id);
        model.addAttribute("book", book);
        if (book.getPersonId() != null)
            model.addAttribute("person", personDAO.show(book.getPersonId()));
        else
            model.addAttribute("people", personDAO.index());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute Book book) {
        return "books/new";
    }

    @PostMapping
    public String create(@ModelAttribute @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";

        bookDao.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable int id) {
        model.addAttribute("book", bookDao.show(id));
        return "books/edit";
    }

    @GetMapping("/{id}/change_owner")
    public String changeOwner(@PathVariable int id, @RequestParam Optional<Integer> personId) {
        bookDao.changeOwner(id, personId.orElse(null));
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute @Valid Book book, BindingResult bindingResult,
                         @PathVariable int id) {
        if (bindingResult.hasErrors())
            return "books/edit";

        bookDao.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        bookDao.delete(id);
        return "redirect:/books";
    }
}
