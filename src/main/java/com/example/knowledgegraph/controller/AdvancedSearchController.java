package com.example.knowledgegraph.controller;

import com.example.knowledgegraph.response.Result;
import com.example.knowledgegraph.utils.Neo4jUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Api(tags = "advanced search")
@RestController
@RequestMapping(value = "advanced")
public class AdvancedSearchController extends BaseController{
    @ApiOperation(value = "度中心性算法")
    @GetMapping("/getDegreeCentrality")
    public Result getDegreeCentrality(){
        try{
            String cql = "CALL gds.degree.stream('mygraph') " +
                    "YIELD nodeId, score " +
                    "RETURN gds.util.asNode(nodeId).rdfs__label AS ProteinName, score AS number " +
                    "ORDER BY number DESCENDING, ProteinName LIMIT 5";
            Set<Map<String ,Object>> set = new HashSet<>();
            Neo4jUtil.getResult(cql, set);
            return Result.successData(set);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error();
        }
    }

    @ApiOperation(value = "dfs算法")
    @GetMapping("/getDfs")
    public Result getDfs(@RequestParam(value = "rdfs1") String rdfs1, @RequestParam(value = "rdfs2") String rdfs2){
        try{
            String cql = "MATCH (a:owl__Class{rdfs__label:\"" + rdfs1 + "\"}), (b:owl__Class{rdfs__label:\""+ rdfs2 + "\"}) " +
                    "WITH id(a) AS source, id(b) AS target " +
                    "CALL gds.dfs.stream('my-graph', { " +
                    "sourceNode:source, " +
                    "targetNodes:target " +
                    "}) " +
                    "YIELD path " +
                    "RETURN path";
            Map<String, Object> retMap = new HashMap<>();
            Set<Map<String ,Object>> nodeList = new HashSet<>();
            Set<Map<String ,Object>> edgeList = new HashSet<>();
            Neo4jUtil.getPathList(cql,nodeList,edgeList);
            retMap.put("node",nodeList);
            retMap.put("edge",edgeList);
            return Result.successData(retMap);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error();
        }
    }

    @ApiOperation(value = "快速随机投影算法")
    @GetMapping("/getFastRP")
    public Result getFastRP(){
        try{
            String cql = "CALL gds.fastRP.stream('mygraph', {embeddingDimension:16, randomSeed:7474}) " +
                    "YIELD nodeId, embedding " +
                    "WITH gds.util.asNode(nodeId) AS n, embedding " +
                    "WHERE n:owl__Class " +
                    "RETURN id(n), n.rdfs__label, embedding LIMIT 5";
            Set<Map<String ,Object>> set = new HashSet<>();
            Neo4jUtil.getResult(cql, set);
            return Result.successData(set);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error();
        }
    }
}
