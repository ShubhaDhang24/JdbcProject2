package model;

import Data.People;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Person implements People {
    private Collection<Person> person;
    private int person_id;
    private String firstName;
    private String lastName;

    public Person( String firstName, String lastName) {
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
        Person person1=new Person(person_id,firstName,lastName);
        return person1;
    }

    @Override
    public Collection<Person> findAll() {
        return new ArrayList<>(person);
    }

    @Override
    public Person findById(int id) {
        for (Person p:person) {
            if(p.person_id==id)
                return p;
        }
        return null;
    }

    @Override
    public Collection<Person> findByName(String name) {
        List<Person> newArray=new ArrayList<>();
        for (Person p:person) {
            if(p.getFirstName().contains(name))
                newArray.add(p);
        }
        return newArray;
    }

    @Override
    public Person update(Person person) {


        return null;
    }

    @Override
    public boolean deleteById(int id) {
        for (Person p:person) {
            if(p.person_id==id)
                person.remove(p);
            return true;
        }
        return false;
    }
}
