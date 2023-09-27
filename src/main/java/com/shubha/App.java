package com.shubha;

import model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;


public class App 
{
    public static void main( String[] args ) {
        List<Person>personList=new ArrayList<>();

        try {
            Connection connection = getConnection("jdbc:mysql://localHost:3306/todoit", "root", "2424");
           Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Person");
           while (resultSet.next()) {
               int id = resultSet.getInt(1);
               String name = resultSet.getString(2);
               String lastname = resultSet.getString(3);
               System.out.println("id : " + id + ", Name :" + name + ",lastName :" + lastname);

               Person person=new Person(id,name,lastname);
               personList.add(person);

            }
            System.out.println("____________________________________________________________");
            personList.forEach(System.out::println);

        }
         catch (SQLException e) {
            System.out.println("DataBase does not exist");
        }

    }
}
