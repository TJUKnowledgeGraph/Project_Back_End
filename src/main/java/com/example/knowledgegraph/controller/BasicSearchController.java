package com.example.knowledgegraph.controller;

import com.example.knowledgegraph.response.Result;
import com.example.knowledgegraph.utils.Neo4jUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Api(tags = "basic search")
@RestController
@RequestMapping(value = "basic")
public class BasicSearchController extends BaseController{


    @ApiOperation(value = "按属性值查找节点，返回节点，关联边和关联节点")
    @GetMapping("/getNodeDetail")
    public Result getNodeDetail(@RequestParam(value = "label") String label,
                                @RequestParam(value = "prop") String prop,
                                @RequestParam(value = "value") String value,
                                @RequestParam(value = "relation") String relation,
                                @RequestParam(value = "direction") String direction){
        try{
            //direction = 0 不规定方向 direction = 1 节点作为起点 direction = 2 节点作为终点
            Map<String, Object> retMap = new HashMap<>();
            //cql语句
            //String cql = "match (start:$label{$prop:$value})-[edge]->(end) return start,edge,end";
            String cql = null;
            switch (direction){
                case "0":
                    cql = "match (startnode:" + label + "{" + prop + ":\"" + value + "\"})-[edge:" + relation + "]-(endnode) return startnode,edge,endnode";
                    break;
                case "1":
                    cql = "match (startnode:" + label + "{" + prop + ":\"" + value + "\"})-[edge:" + relation + "]->(endnode) return startnode,edge,endnode";
                    break;
                case "2":
                    cql = "match (startnode:" + label + "{" + prop + ":\"" + value + "\"})<-[edge:" + relation + "]-(endnode) return startnode,edge,endnode";
            }

            //待返回的值，与cql return后的值顺序对应
            Set<Map<String ,Object>> edge = new HashSet<>();
            Set<Map<String ,Object>> node = new HashSet<>();
            Neo4jUtil.getRoute(cql, edge, node);

            retMap.put("edge", edge);
            retMap.put("node", node);

            return Result.successData(retMap);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error();
        }

    }

    @ApiOperation(value = "获得数据统计结果")
    @GetMapping("/getOverview")
    public Result getOverview(){
        try{
            String cql = "MATCH (n) WHERE rand() <= 0.1 " +
                    " RETURN " +
                    "DISTINCT labels(n)," +
                    "count(*) AS SampleSize," +
                    "avg(size(keys(n))) as Avg_PropertyCount," +
                    "size(keys(n)) as PropertyCount," +
                    "avg(size( (n)-[]-() ) ) as Avg_RelationshipCount," +
                    "min(size( (n)-[]-() ) ) as Min_RelationshipCount," +
                    "max(size( (n)-[]-() ) ) as Max_RelationshipCount";
            Set<Map<String ,Object>> set = new HashSet<>();
            Neo4jUtil.getResult(cql, set);
            return Result.successData(set);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error();
        }
    }

    @ApiOperation(value = "获得靶关联最高的蛋白质top5")
    @GetMapping("/getTargetTop5")
    public Result getTargetTop5(){
        try{
            String cql = "match (nodep:owl__Axiom)-[r:owl__annotatedTarget]->(nodeq:owl__Class) " +
                    "with distinct nodeq, COUNT(nodep) as total " +
                    "Order BY total desc " +
                    "RETURN nodeq, total " +
                    "LIMIT 5";
            Set<Map<String ,Object>> set = new HashSet<>();
            Neo4jUtil.getResult(cql, set);
            return Result.successData(set);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error();
        }
    }

    @ApiOperation(value = "获得源关联最高的蛋白质top5")
    @GetMapping("/getSourceTop5")
    public Result getSourceTop5(){
        try{

            String cql = "match (nodep:owl__Axiom)-[r:owl__annotatedSource]->(nodeq:owl__Class) " +
                    "with distinct nodeq, COUNT(nodep) as total " +
                    "Order BY total desc " +
                    "RETURN nodeq, total " +
                    "LIMIT 5";
            Set<Map<String ,Object>> set = new HashSet<>();
            Neo4jUtil.getResult(cql, set);
            return Result.successData(set);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error();
        }
    }
}
