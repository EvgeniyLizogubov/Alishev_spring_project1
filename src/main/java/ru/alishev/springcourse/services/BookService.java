package ru.alishev.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;
import ru.alishev.springcourse.repositories.BookRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll(Integer page, Integer booksPerPage, Boolean sortByYear) {
        List<Book> books;

        if (page != null && booksPerPage != null && Boolean.TRUE.equals(sortByYear))
            books = bookRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
        else if (page != null && booksPerPage != null)
            books = bookRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
        else if (Boolean.TRUE.equals(sortByYear))
            books = bookRepository.findAll(Sort.by("year"));
        else
            books = bookRepository.findAll();

        return books;
    }

    public Book findOne(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Person getBookOwner(int id) {
        return bookRepository.getBookOwner(id);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        bookRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void release(int id) {
        bookRepository.release(id);
    }

    @Transactional
    public void assign(int id, Person selectedPerson) {
        Date takingDate = new Date();
        bookRepository.assign(id, selectedPerson, takingDate);
    }

    public List<Book> findByTitle(String searchBook) {
        return bookRepository.findByTitleStartingWith(searchBook);
    }
}
