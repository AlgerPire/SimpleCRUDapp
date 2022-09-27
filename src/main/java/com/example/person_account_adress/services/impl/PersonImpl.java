package com.example.person_account_adress.services.impl;

import com.example.person_account_adress.entities.Person;
import com.example.person_account_adress.repositories.PersonRepository;
import com.example.person_account_adress.services.PersonService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Service
// mund ti besh dhe implement PersonService dhe me pas ajo i ben override metodave per te arritur abstrakstionin
public class PersonImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    Person testPerson;


    public List<Person> listAll() {
        return personRepository.findAll();
    }


    public Person getPersonById(Long id) {

        return personRepository.findById(id).get();
    }

    @Override
    public Person updatePerson(Long id, Person person) {
        Person existingPerson=getPersonById(id);
        if(person.getAge()<=0){
            existingPerson.setAge(existingPerson.getAge());
        }
        else {
            existingPerson.setAge(person.getAge());
        }
        if(person.getEmail().equalsIgnoreCase(personRepository.findAllByEmail(personRepository.findAllByEmail(person.getEmail())))){
            existingPerson.setName(person.getName());
            existingPerson.setAdress(person.getAdress());
            return personRepository.save(existingPerson);
        }
        else{
          return personRepository.save(person);
        }
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);

    }


    public String addNewPerson(Person person) {
        if(person.getEmail().equalsIgnoreCase(personRepository.findAllByEmail(person.getEmail()))){
            return "Email exists";
        }
        if(person.getAge()<=0){
            return "Age not enough";
        }
        else {
            personRepository.save(person);
            return "Successfully created";
        }
    }


   public List<Object> findAllAbove18(){
        return personRepository.findAllAbove18Years();
   }

   public List<Object> findWithGivenName(String name){
        return personRepository.findAllWithGivenName(name);
   }

   private static final String GET_ALL_USERS_API="https://matchilling-chuck-norris-jokes-v1.p.rapidapi.com/jokes/search";

    RestTemplate restTemplate=new RestTemplate();

    public ResponseEntity<String> textRandomJokes(String query){
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.put("X-RapidAPI-Key", Collections.singletonList("3a9593658bmsh2ae43ba506ad25ep101582jsn762226628a7a"));
        HttpEntity<String> entity=new HttpEntity<>("parameters",headers);
        String linkTotal = GET_ALL_USERS_API + "?query=" + query;
        return restTemplate.exchange(linkTotal, HttpMethod.GET,entity,String.class);

    }


}
