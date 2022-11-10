package com.example.knowledgegraph.dao;

import com.example.knowledgegraph.model.entity.OwlClass;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwlClassRepository extends Neo4jRepository<OwlClass, Long> {
    @Query("MATCH (N:owl__Class) RETURN N LIMIT 25")
    List<OwlClass> getOwlClassList();

    @Query("MATCH (N:owl__Class{rdfs__label:$rdfs}) RETURN N ")
    OwlClass getOwlClassByRdfs(@Param("rdfs") String rdfs);
}
