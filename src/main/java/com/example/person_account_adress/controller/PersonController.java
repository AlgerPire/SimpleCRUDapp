package com.example.person_account_adress.controller;

import com.example.person_account_adress.entities.Person;
import com.example.person_account_adress.services.impl.PersonImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonImpl personImpl;

    @GetMapping("/home")
    public String homeView(){
        return "homepage";
    }


    //listAll Person
    @GetMapping("/listAll")
    public String listAll(Model model){
        model.addAttribute("persons", personImpl.listAll());
        return "persons";
    }

    // shto nje person
    @GetMapping("/addPerson")
    public String createStudentForm(Model model){
        Person person=new Person();
        model.addAttribute("person",person);
        return "add_new_person";
    }

    // save person process
    @PostMapping("/personsSave")
    public String saveStudent(@ModelAttribute("person") Person person){
        personImpl.addNewPerson(person);
        return "redirect:/person/listAll";
    }

    //delete Person
    @GetMapping("/deletePerson/{id}")
    public String deleteStudentForm(@PathVariable Long id){
        personImpl.deletePerson(id);
        return "redirect:/person/listAll";
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

    // before thymeleaf methods

//    @GetMapping("/listAll")
//    public List<Person> listAll(){
//        model.addAttribute("persons", personImpl.listAll());
//        return "persons";
//    }

//    @PostMapping("/addPerson")
//    public Person addNewPerson(Person person){
//       personImpl.addNewPerson(person);
//        return person;
//    }

//    @DeleteMapping("/deletePerson")
//    public void deletePerson(Long id){
//        personImpl.deletePerson(id);
//    }


}
