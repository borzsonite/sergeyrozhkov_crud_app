package com.sergeyrozhkov.crud.app.dao;

import com.sergeyrozhkov.crud.app.entity.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Tom"));
        people.add(new Person(++PEOPLE_COUNT, "Mike"));
        people.add(new Person(++PEOPLE_COUNT, "Jhon"));
        people.add(new Person(++PEOPLE_COUNT, "Mary"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        //return people.get(id);
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }
}
