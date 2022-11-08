package com.example.knowledgegraph.controller;

import com.example.knowledgegraph.response.BaseErrorEnum;
import com.example.knowledgegraph.response.BaseException;
import com.example.knowledgegraph.service.PersonService;
import com.example.knowledgegraph.bean.Person;
import com.example.knowledgegraph.response.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api(tags = "test")
@RestController
@RequestMapping(value = "test")
public class PersonController extends BaseController{
    @Autowired
    private PersonService personService;

    @ApiOperation(value = "按名搜索演员")
    //@ApiImplicitParam(name = "name", value = "演员姓名", paramType = "String")
    @PostMapping("/searchPersonByName")
    public Result getPersonByName(@RequestParam(value = "name") String name){
//        try{
//            Optional<Person> person = Optional.ofNullable(personService.getPersonByName(name));
//            return Result.successData(person);
//        }catch (Exception e){
//            e.printStackTrace();
//            return Result.error();
//        }
        if(name == null){
            throw new BaseException(BaseErrorEnum.REQUEST_DATA_NULL);
            //return Result.error(BaseErrorEnum.REQUEST_DATA_NULL);
        }else {
            Optional<Person> person = Optional.ofNullable(personService.getPersonByName(name));
            return Result.successData(person);
        }
    }

    @ApiOperation(value = "获取所有演员")
    @GetMapping("/getAllPerson")
    public Result getAllPerson(){
        try{
            return Result.successData(personService.getAllPerson());
        }catch (Exception e){
            e.printStackTrace();
            return Result.error();
        }
    }
}
