package com.example.knowledgegraph.dao;

import com.example.knowledgegraph.bean.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends Neo4jRepository<Person, Long> {
    @Query("MATCH (N:Person) RETURN N ")
    public List<Person> getPersonList();

    @Query("MATCH (N:Person{name:$name}) RETURN N ")
    public Person getPersonByName(@Param("name") String name);

}
