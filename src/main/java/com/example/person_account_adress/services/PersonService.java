package com.example.person_account_adress.services;

import com.example.person_account_adress.entities.Person;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonService {

    //find all
    List<Person> listAll();

    //find by id
    Person getPersonById(Long id);

    //update
    Person updatePerson(Long id,Person person);

    //delete person
    void deletePerson(Long id);

    //add new person
    String addNewPerson(Person person);




}
