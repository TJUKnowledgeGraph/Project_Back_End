package com.example.knowledgegraph.controller;

import com.example.knowledgegraph.service.PersonService;
import com.example.knowledgegraph.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "search")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/searchPersonByName")
    public Person getPersonByName(@RequestParam(value = "name") String name){
        Optional<Person> person = Optional.ofNullable(personService.getPersonByName(name));

        return person.orElse(null);
    }

    @GetMapping("/getAllPerson")
    public List<Person> getAllPerson(){
        return personService.getAllPerson();
    }
}
