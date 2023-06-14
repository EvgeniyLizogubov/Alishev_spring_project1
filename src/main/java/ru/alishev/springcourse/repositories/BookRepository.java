package ru.alishev.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("select p from Person p join p.books b where b.id=:id")
    Person getBookOwner(@Param("id") int id);

    @Modifying
    @Query("update Book b set b.owner=null, b.takingTime=null where b.id=:id")
    void release(@Param("id") int id);

    @Modifying
    @Query("update Book b set b.owner=:selectedPerson, b.takingTime=:takingTime where b.id=:id")
    void assign(@Param("id") int id, @Param("selectedPerson") Person selectedPerson,
                @Param("takingTime") Date takingTime);

    List<Book> findByTitleStartingWith(String searchBook);
}
