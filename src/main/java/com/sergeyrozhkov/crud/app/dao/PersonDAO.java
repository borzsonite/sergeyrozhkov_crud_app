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
        people.add(new Person(++PEOPLE_COUNT, "Tom", 30, "tom@mail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Mike", 34, "mike@mail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Jhon", 23, "jhon@mail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Mary", 21, "mari@mail.ru"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id)
                .findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatedPerson) {
        Person personToUpdate = show(id);
        personToUpdate.setName(updatedPerson.getName());
        personToUpdate.setAge(updatedPerson.getAge());
        personToUpdate.setEmail(updatedPerson.getEmail());

    }


    public void delete(int id) {
        people.removeIf(person -> person.getId() == id);
    }
}
