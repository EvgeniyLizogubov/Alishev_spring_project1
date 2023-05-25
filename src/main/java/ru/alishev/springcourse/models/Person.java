package ru.alishev.springcourse.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author Neil Alishev
 */
public class Person {
    private int personId;

    @NotEmpty(message = "Name should not be empty")
    @Pattern(regexp = "[A-ZА-Я][a-zа-я]+ [A-ZА-Я][a-zа-я]+ [A-ZА-Я][a-zа-я]+", message = "Full name should be this format: Surname Name Patronymic")
    private String fullName;

    @Min(value = 1901, message = "Year of birth should be greater than 1900")
    private int yearBirth;

    public Person() {

    }

    public Person(int personId, String fullName, int yearBirth) {
        this.personId = personId;
        this.fullName = fullName;
        this.yearBirth = yearBirth;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearBirth() {
        return yearBirth;
    }

    public void setYearBirth(int yearBirth) {
        this.yearBirth = yearBirth;
    }
}
