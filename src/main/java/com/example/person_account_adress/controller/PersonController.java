package com.example.person_account_adress.controller;

import com.example.person_account_adress.entities.Person;
import com.example.person_account_adress.services.impl.PersonImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonImpl personImpl;

    @GetMapping("/listAll")
    public List<Person> listAll(){
        return personImpl.listAll();
    }

    @PostMapping("/addPerson")
    public Person addNewPerson(Person person){
        personImpl.addNewPerson(person);
        return person;
    }

    @PostMapping("/updatePerson")
    public Person updatePerson(Long id, Person person){
        personImpl.updatePerson(id,person);
        return personImpl.getPersonById(id);
    }

    @DeleteMapping("/deletePerson")
    public void deletePerson(Long id){
        personImpl.deletePerson(id);
    }

    @GetMapping("/findAllAbove18")
    public List<Object> allAbove18(){
        return personImpl.findAllAbove18();
    }



}
