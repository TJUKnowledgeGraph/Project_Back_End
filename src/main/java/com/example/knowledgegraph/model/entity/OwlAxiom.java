package com.example.knowledgegraph.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Data
@Node(labels = {"owl__Axiom","Resource"})
public class OwlAxiom {
    @Id
    @GeneratedValue //设置主键自增
    private Long id;

    @Property
    private String ns1__SWO_0000425;

    @Property
    private String rdfs__comment;

    @Property
    private String uri;

    public OwlAxiom(String ns1__SWO_0000425, String rdfs__comment, String uri) {
        this.ns1__SWO_0000425 = ns1__SWO_0000425;
        this.rdfs__comment = rdfs__comment;
        this.uri = uri;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNs1__SWO_0000425() {
        return ns1__SWO_0000425;
    }

    public void setNs1__SWO_0000425(String ns1__SWO_0000425) {
        this.ns1__SWO_0000425 = ns1__SWO_0000425;
    }

    public String getRdfs__comment() {
        return rdfs__comment;
    }

    public void setRdfs__comment(String rdfs__comment) {
        this.rdfs__comment = rdfs__comment;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
