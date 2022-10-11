package com.s3437237.challenge.controller;

import com.s3437237.challenge.dao.PersonDAO;
import com.s3437237.challenge.model.Person;
import com.s3437237.challenge.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    PersonDAO dao;

    @RequestMapping("")
    public String viewHomePage() {
        return "Welcome to Funds Management - project by John Brown [ s3437237 ]";
    }

    // create a new person
    @PostMapping("/person")
    public ResponseEntity<Person> newPerson(@RequestBody Person person) {
        Person newPerson = dao
                .save(Person.builder()
                        .personId(person.getPersonId())
                        .name(person.getName())
                        .address(person.getAddress())
                        .postcode(person.getPostcode())
                        .age(person.getAge())
                        .job(person.getJob())
                        .email(person.getEmail())
                        .phoneno(person.getPhoneno())
                        .build());
        return new ResponseEntity<>(newPerson, HttpStatus.OK);
    }

    // Get all persons
    @GetMapping("/persons/person")
    public ResponseEntity<List<Person>> getPersons() {
        try {
            return new ResponseEntity<>(dao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get person by id
    @GetMapping("/item/person/{personId}")
    public ResponseEntity<Person> getPersonById(@PathVariable("personId") long personId) {
        try {
            //check if person exist in database
            Person personObj = getPerson(personId);

            if (personObj != null) {
                return new ResponseEntity<>(personObj, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // Update person
    @RequestMapping(value = "/persons/person", method = RequestMethod.PUT)
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {

        Person personObj = person;

        if (personObj != null) {
            personObj.setName(person.getName());
            personObj.setAddress(person.getAddress());
            personObj.setPostcode(person.getPostcode());
            personObj.setJob(person.getJob());
            personObj.setPhoneno(person.getPhoneno());
            return new ResponseEntity<>(dao.save(personObj), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete person
    @RequestMapping(value = "/persons/person", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deletePerson(@RequestBody Person person) {
        try {
            //check if teacher exist in database
            Person psn = person;

            if (psn != null) {
                dao.delete(person);
                return new ResponseEntity<>(HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Person getPerson(long personId) {
        Optional<Person> personObj = dao.findById(personId);

        if (personObj.isPresent()) {
            return personObj.get();
        }
        return null;
    }

}