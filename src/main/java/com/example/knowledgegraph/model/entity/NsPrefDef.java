package com.example.knowledgegraph.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Data
@Node(labels = {"_NsPrefDef"})
public class NsPrefDef {
    @Id
    @GeneratedValue //设置主键自增
    private Long id;

    @Property
    private String ns0;

    @Property
    private String ns1;

    @Property
    private String owl;

    @Property
    private String rdfs;

    public NsPrefDef(String ns0, String ns1, String owl, String rdfs) {
        this.ns0 = ns0;
        this.ns1 = ns1;
        this.owl = owl;
        this.rdfs = rdfs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNs0() {
        return ns0;
    }

    public void setNs0(String ns0) {
        this.ns0 = ns0;
    }

    public String getNs1() {
        return ns1;
    }

    public void setNs1(String ns1) {
        this.ns1 = ns1;
    }

    public String getOwl() {
        return owl;
    }

    public void setOwl(String owl) {
        this.owl = owl;
    }

    public String getRdfs() {
        return rdfs;
    }

    public void setRdfs(String rdfs) {
        this.rdfs = rdfs;
    }
}
