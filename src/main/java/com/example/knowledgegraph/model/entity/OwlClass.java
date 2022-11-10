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
@Node(labels = {"owl__Class", "Resource"})
public class OwlClass {

    @Id
    @GeneratedValue //设置主键自增
    private Long id;

    @Property
    private String ns0__id;

    @Property
    private String rdfs__comment;

    @Property
    private String rdfs__label;

    @Property
    private String uri;

    @Relationship(type = "owl__annotatedSource", direction = Relationship.Direction.INCOMING)
    private List<OwlAxiom> owlAxiomSourceList = new ArrayList<>();

    @Relationship(type = "owl__annotatedTarget", direction = Relationship.Direction.INCOMING)
    private List<OwlAxiom> owlAxiomTargetList = new ArrayList<>();

    public OwlClass(Long id, String ns0__id, String rdfs__comment, String rdfs__label, String uri) {
        this.id = id;
        this.ns0__id = ns0__id;
        this.rdfs__comment = rdfs__comment;
        this.rdfs__label = rdfs__label;
        this.uri = uri;
    }


    public List<OwlAxiom> getOwlAxiomSourceList() {
        return owlAxiomSourceList;
    }

    public void setOwlAxiomSourceList(List<OwlAxiom> owlAxiomSourceList) {
        this.owlAxiomSourceList = owlAxiomSourceList;
    }

    public List<OwlAxiom> getOwlAxiomTargetList() {
        return owlAxiomTargetList;
    }

    public void setOwlAxiomTargetList(List<OwlAxiom> owlAxiomTargetList) {
        this.owlAxiomTargetList = owlAxiomTargetList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNs0__id() {
        return ns0__id;
    }

    public void setNs0__id(String ns0__id) {
        this.ns0__id = ns0__id;
    }

    public String getRdfs__comment() {
        return rdfs__comment;
    }

    public void setRdfs__comment(String rdfs__comment) {
        this.rdfs__comment = rdfs__comment;
    }

    public String getRdfs__label() {
        return rdfs__label;
    }

    public void setRdfs__label(String rdfs__label) {
        this.rdfs__label = rdfs__label;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
