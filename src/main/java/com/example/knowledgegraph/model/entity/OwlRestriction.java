package com.example.knowledgegraph.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Data
@Node(labels = {"owl__Restriction","Resource"})
public class OwlRestriction {
    @Id
    @GeneratedValue //设置主键自增
    private Long id;

    @Property
    private String uri;

    @Relationship(type = "rdfs__subClassOf", direction = Relationship.Direction.INCOMING)
    private List<OwlClass> owlClassList = new ArrayList<>();

    public OwlRestriction(Long id, String uri) {
        this.id = id;
        this.uri = uri;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
