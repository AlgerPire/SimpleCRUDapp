package com.example.person_account_adress.repositories;

import com.example.person_account_adress.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
    @Query("Select p.name,p.age, p.email From Person p where p.age>18 ")
    List<Object> findAllAbove18Years();
}
