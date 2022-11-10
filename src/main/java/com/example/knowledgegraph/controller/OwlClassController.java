package com.example.knowledgegraph.controller;

import com.example.knowledgegraph.model.entity.OwlClass;
import com.example.knowledgegraph.response.Result;
import com.example.knowledgegraph.service.OwlClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api(tags = "Owl_Class")
@RestController
@RequestMapping(value = "test")
public class OwlClassController {
    @Autowired
    private OwlClassService owlClassService;

    @ApiOperation(value = "按rdfs搜索owl")
    //@ApiImplicitParam(name = "name", value = "演员姓名", paramType = "String")
    @PostMapping("/searchOwlByRdfs")
    public Result getPersonByName(@RequestParam(value = "rdfs") String rdfs){
//        try{
//            Optional<Person> person = Optional.ofNullable(personService.getPersonByName(name));
//            return Result.successData(person);
//        }catch (Exception e){
//            e.printStackTrace();
//            return Result.error();
//        }

//        if(rdfs == null){
//            throw new BaseException(BaseErrorEnum.REQUEST_DATA_NULL);
//            //return Result.error(BaseErrorEnum.REQUEST_DATA_NULL);
//        }else {
//            Optional<Owl_Class> owl_class = Optional.ofNullable(owlClassService.getOwlClassByRdfs(rdfs));
//            return Result.successData(owl_class);
//        }

        Optional<OwlClass> owl_class = Optional.ofNullable(owlClassService.getOwlClassByRdfs(rdfs));
        return Result.successData(owl_class);
    }

    @ApiOperation(value = "获取所有owl")
    @GetMapping("/getAllOwl")
    public Result getAllOwl(){
        try{
            return Result.successData(owlClassService.getAllOwlClass());
        }catch (Exception e){
            e.printStackTrace();
            return Result.error();
        }
    }
}
