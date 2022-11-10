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
@Node(labels = "Resource")
public class Resource {
    @Id
    @GeneratedValue //设置主键自增
    private Long id;

    @Property
    private String uri;

    @Relationship(type = "owl__someValuesFrom", direction = Relationship.Direction.INCOMING)
    private List<OwlRestriction> owlRestrictionValueList = new ArrayList<>();

    @Relationship(type = "owl__onProperty", direction = Relationship.Direction.INCOMING)
    private List<OwlRestriction> owlRestrictionPropertyList = new ArrayList<>();

    @Relationship(type = "owl__annotatedProperty", direction = Relationship.Direction.INCOMING)
    private List<OwlAxiom> owlAxiomList = new ArrayList<>();

    public Resource(String uri) {
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
