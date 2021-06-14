package com.sergeyrozhkov.crud.app.dao;

import com.sergeyrozhkov.crud.app.entity.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private static final String URL = "jdbc:mysql://localhost:3306/my_db";
    private static final String USERNAME = "bestuser";
    private static final String PASSWORD = "bestuser";
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public List<Person> index() {
        List<Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM person";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return people;
    }

    public Person show(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT FROM Person WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

//        return people.stream().filter(person -> person.getId() == id)
//                .findAny().orElse(null);
        return null;
    }

    public void save(Person person) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Person VALUES (1, ?,?,?)");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getName());
            preparedStatement.executeUpdate();

            ///////////////////Обычный statement заменент кодом выше///////////////////////

//            Statement statement = connection.createStatement();
//            String query = "INSERT INTO Person VALUES(" + 1 + ",'" + person.getName() +
//                    "'," + person.getAge() + ",'" + person.getEmail() + "')";;
//                    // получаем строку запрса: INSERT INTO Person VALUES(1, 'Tom','30','tom@mail.com')
//            statement.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

//        person.setId(++PEOPLE_COUNT);
//        people.add(person);
    }

    public void update(int id, Person updatedPerson) {
        Person personToUpdate = show(id);
        personToUpdate.setName(updatedPerson.getName());
        personToUpdate.setAge(updatedPerson.getAge());
        personToUpdate.setEmail(updatedPerson.getEmail());

    }


    public void delete(int id) {
//        people.removeIf(person -> person.getId() == id);
    }
}
