package com.example.knowledgegraph.dao;

import com.example.knowledgegraph.model.entity.OwlClass;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwlRestrictionRepository {

    @Query("MATCH p=()-[r:rdfs__subClassOf]->() RETURN p LIMIT 25")
    List<OwlClass> getOwlClassList();
}
