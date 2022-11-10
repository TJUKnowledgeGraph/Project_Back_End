package com.example.knowledgegraph.service;

import com.example.knowledgegraph.dao.OwlClassRepository;
import com.example.knowledgegraph.model.entity.OwlClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwlClassService {

    @Autowired
    private OwlClassRepository owlClassRepository;

    public List<OwlClass> getAllOwlClass(){
        return owlClassRepository.getOwlClassList();
    }

    public OwlClass getOwlClassByRdfs(String rdfs){
        return owlClassRepository.getOwlClassByRdfs(rdfs);
    }
}
