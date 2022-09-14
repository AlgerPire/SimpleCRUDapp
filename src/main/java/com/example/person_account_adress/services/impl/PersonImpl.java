package com.example.person_account_adress.services.impl;

import com.example.person_account_adress.entities.Person;
import com.example.person_account_adress.repositories.PersonRepository;
import com.example.person_account_adress.services.PersonService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class PersonImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;


    @Override
    public List<Person> listAll() {
        return personRepository.findAll();
    }

    @Override
    public Person getPersonById(Long id) {
        return personRepository.findById(id).get();
    }

    @Override
    public Person updatePerson(Long id, Person person) {
        Person person1=personRepository.findById(id).get();
        person1.setName(person.getName());
        person1.setAdress(person.getAdress());
        person1.setEmail(person.getEmail());
        person1.setAge(person.getAge());
        personRepository.save(person1);
        return person1;

    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);

    }

    @Override
    public Person addNewPerson(Person person) {
        personRepository.save(person);
        return person;
    }

   public List<Object> findAllAbove18(){
        return personRepository.findAllAbove18Years();
   }
}
