package com.example.knowledgegraph.controller;

import com.example.knowledgegraph.response.BaseErrorEnum;
import com.example.knowledgegraph.response.BaseException;
import com.example.knowledgegraph.service.PersonService;
import com.example.knowledgegraph.model.entity.Person;
import com.example.knowledgegraph.response.Result;
import com.example.knowledgegraph.utils.Neo4jUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(tags = "test")
@RestController
@RequestMapping(value = "test")
public class TestController extends BaseController{
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

    @ApiOperation(value = "获取25条边及节点")
    @GetMapping("/getRoute")
    public Result getRoute(){
        try{
            Map<String, Object> retMap = new HashMap<>();
            //cql语句
            String cql = "match (start)-[edge]->(end) return start,edge,end LIMIT 25";


            //待返回的值，与cql return后的值顺序对应
            Set<Map<String ,Object>> edge = new HashSet<>();
            Set<Map<String ,Object>> node = new HashSet<>();
            Neo4jUtil.getRoute(cql, edge, node);

            retMap.put("edge", edge);
            retMap.put("node", node);

//        //new way
//        Set<Route> res;
//        res = Neo4jUtil.getList(cql);

            return Result.successData(retMap);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error();
        }
    }
}
