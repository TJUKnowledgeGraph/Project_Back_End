package com.example.knowledgegraph.service;

import com.example.knowledgegraph.bean.Person;
import com.example.knowledgegraph.dao.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPerson(){
        return personRepository.getPersonList();
    }

    public Person getPersonByName(String name){
        return personRepository.getPersonByName(name);
    }
}
