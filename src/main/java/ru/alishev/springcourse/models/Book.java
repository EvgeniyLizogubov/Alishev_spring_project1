package ru.alishev.springcourse.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Book {
    private int id;

    @NotEmpty(message = "Enter book's title")
    private String title;

    @NotEmpty(message = "Enter book's author")
    @Pattern(regexp = "[A-ZА-Я][a-zа-я]+ [A-ZА-Я][a-zа-я]+", message = "Author's name should be this format: Name Surname")
    private String author;

    @NotNull(message = "Enter the year of book")
    private int year;

    private Integer personId;

    public Book() {
    }

    public Book(int id, String title, String author, int year, Integer personId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.personId = personId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }
}
