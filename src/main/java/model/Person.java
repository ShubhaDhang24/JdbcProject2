package model;

import Data.People;
import com.shubha.database.MySql;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Person implements People {
    private Collection<Person> person;
    private int person_id;
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getPerson_id() {
        return person_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Person(int person_id, String firstName, String lastName) {
        this.person_id = person_id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "person_id=" + person_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public Person create(Person person) {
        String sql = "INSERT INTO persons (first_Name,last_Name ) VALUES (?, ?)";
        Connection connection = MySql.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, person.getPerson_id());
            preparedStatement.setString(2, person.getFirstName());
            preparedStatement.setString(3, person.getLastName());
            int rowInserted = preparedStatement.executeUpdate();
            if (rowInserted > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;
    }

    @Override
    public Collection<Person> findAll() {
        List<Person> allPerson = new ArrayList<>();
        String sql = "Select * from Person";
        Connection connection = MySql.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String lName = resultSet.getString(3);
                Person person1 = new Person(id, name, lName);
                allPerson.add(person1);
            }
        } catch (SQLException s) {
            System.out.println("Data Base not found exception");
        }
        return allPerson;
    }

    @Override
    public Person findById(int id) {
        String sql = "Select * from Person Where person_id=?";
        Person p1 = null;
        Connection connection = MySql.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int personId = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String lName = resultSet.getString(3);
                p1 = new Person(personId, name, lName);

            }
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
        }
        return p1;
    }

    @Override
    public Collection<Person> findByName(String name) {
        String sql = "Select name from Person Where first_Name=?";
        List<Person> personName = new ArrayList<>();
        Connection connection = MySql.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = null;
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(person_id);
                String firstnames = resultSet.getString(firstName);
                String lastnames = resultSet.getString(lastName);
                Person person1 = new Person(id, firstnames, lastnames);
                personName.add(person1);
            }
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
            ;
        }
        return personName;
    }

    @Override
    public Person update(Person person) {
        String sql = "UPDATE Todo_Item SET first_Name=?,last_Name=?,Where person_id=?";
        Connection connection = MySql.getConnection();
        int updateRow;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,getPerson_id());
            preparedStatement.setString(3, person.getFirstName());
            preparedStatement.setString(3, person.getLastName());
            updateRow = preparedStatement.executeUpdate();
            if (updateRow > 0)
                System.out.println("Update was Success full");
        } catch (SQLException e) {
            System.out.println("Data base does not exist");;
        }
        return person;
    }

    @Override
    public boolean deleteById(int id) {
        String sql="Delete from Person where id=?";
        int rowDeleted;
        Connection connection=MySql.getConnection();
        try {
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setInt(1,id);
            rowDeleted=statement.executeUpdate();
            return rowDeleted>0;

        }
        catch (SQLException e) {
            System.out.println("Data Base does not exist");};
        return false;
    }
}
