package com.s3437237.challenge.service;

import com.s3437237.challenge.dao.PersonDAO;
import com.s3437237.challenge.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired(required = false)
    private PersonDAO dao;

    // Add Person
    public String savePerson(Person person) {
        dao.save(person);
        return "New Person saved, person Name :" +person.getName();
    }

    // Get Person by id
    public Person getPerson(Long id) {
        return dao.findById(id).get();
    }

    // Get all persons
    public List<Person> getAllPersons() {
        List<Person> person = new ArrayList<>();
        dao.findAll().forEach(person1 -> person.add(person1));
        return person;
    }

    // Update person
    public String updatePerson(Person person) {
        dao.save(person);
        return "Person Update Successful :" +person.getName();
    }

    // Delete Person
    public String deletePerson(Long id) {
        Person person = dao.getById(id);
        dao.delete(person);
        return "Person deleted successfully";
    }
}