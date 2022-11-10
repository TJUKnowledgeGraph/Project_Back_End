package com.example.knowledgegraph.utils;

import com.example.knowledgegraph.model.Route;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Neo4jUtil {
    private static Driver driver;

    @Autowired
    public Neo4jUtil(Driver driver) {
        Neo4jUtil.driver = driver;
    }

    /**
     * cql的return返回查询结果
     * @param cql 查询语句
     */
    public static <T> void getResult(String cql, Set<T> set) {
        try {
            Session session = driver.session();
            Result result = session.run(cql);
            List<Record> list = result.list();
            //System.out.println(list);
            System.out.println(cql);

            for (Record record : list) {
                Map<String, Object> map = new HashMap<>();
                Map<String, Object> node = null;
                for (String index : record.keys()) {
                    if(index.contains("node")){
                        //节点上设置的属性
                        //外加一个固定属性
                        node = new HashMap<>(record.get(index).asMap());
                        node.put("id", record.get(index).asNode().id());
                        node.put("label", record.get(index).asNode().labels());
                        map.put("node", node);
                    }
                    else map.put(index, record.get(index).asObject());
                }
                set.add((T) map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * cql的return返回多种节点match (n)-[edge]-(n) return n,m,edge：限定返回关系时，关系的别名必须“包含”edge
     * @param cql 查询语句
     * @param edgeSet 和cql的return返回边顺序对应
     * @param nodeSet 和cql的return返回节点顺序对应
     */
    public static <T> void getRoute(String cql, Set<T> edgeSet, Set<T> nodeSet) {
        try {
            Session session = driver.session();
            Result result = session.run(cql);
            List<Record> list = result.list();

            System.out.println(cql);

            for (Record record : list) {
                Map<String, Object> edge = null;
                Map<String, Object> node = null;
                for (String index : record.keys()) {
                    //对于关系的封装
                    if (index.contains("edge")) {
                        //关系上设置的属性
                        //外加三个固定属性
                        edge = new HashMap<>(record.get(index).asMap());
                        edge.put("edgeType", record.get(index).asRelationship().type());
                        edge.put("edgeId", record.get(index).asRelationship().id());
                        edge.put("source", record.get(index).asRelationship().startNodeId());
                        edge.put("target", record.get(index).asRelationship().endNodeId());
                        edgeSet.add((T) edge);
                    }
                    //对于节点的封装
                    else if(index.contains("node")){
                        //节点上设置的属性
                        //外加一个固定属性
                        node = new HashMap<>(record.get(index).asMap());
                        node.put("id", record.get(index).asNode().id());
                        node.put("label", record.get(index).asNode().labels());
                        nodeSet.add((T) node);
                    }
                    else {

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> void getRoute(String cql, HashMap<String, Set<T>> hashMap) {
        try {
            Session session = driver.session();
            Result result = session.run(cql);
            List<Record> list = result.list();

            System.out.println(cql);
            System.out.println(list);

            for (Record record : list) {
                Map<String, Object> edge = null;
                Map<String, Object> node = null;
                for (String index : record.keys()) {
                    //对于关系的封装
                    if (index.contains("edge")) {
                        //关系上设置的属性
                        //外加三个固定属性
                        System.out.println(record.get(index));
                        edge = new HashMap<>(record.get(index).asMap());
                        edge.put("edgeType", record.get(index).asRelationship().type());
                        edge.put("edgeId", record.get(index).asRelationship().id());
                        edge.put("source", record.get(index).asRelationship().startNodeId());
                        edge.put("target", record.get(index).asRelationship().endNodeId());
                        hashMap.get("edge").add((T) edge);
                    }
                    //对于节点的封装
                    else if(index.contains("node")){
                        //节点上设置的属性
                        //外加一个固定属性
                        node = new HashMap<>(record.get(index).asMap());
                        node.put("id", record.get(index).asNode().id());
                        node.put("label", record.get(index).asNode().labels());
                        hashMap.get("node").add((T) node);
                    }
                    else {
                        //hashMap.get(index).add((T) )
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Set<Route> getRoute(String cql) {
        Set<Route> res = new HashSet<>();
        try {
            Session session = driver.session();
            Result result = session.run(cql);
            List<Record> list = result.list();
//            for (Record r : list) {
//                if (r.size() != lists.length) {
//                    System.out.println("节点数和lists数不匹配");
//                    return;
//                }
//            }
            System.out.println(list);
            for (Record record : list) {
                Route route;
                Map<String, Object> edge = null;
                Map<String, Object> start = null;
                Map<String, Object> end = null;
                for (String index : record.keys()) {
                    //对于关系的封装
                    if (index.contains("edge")) {
                        //关系上设置的属性
                        //外加三个固定属性
                        edge = new HashMap<>(record.get(index).asMap());
                        edge.put("edgeType", record.get(index).asRelationship().type());
                        edge.put("edgeId", record.get(index).asRelationship().id());
                        edge.put("edgeFrom", record.get(index).asRelationship().startNodeId());
                        edge.put("edgeTo", record.get(index).asRelationship().endNodeId());
                    }
                    //对于节点的封装
                    else if(index.contains("start")){
                        //节点上设置的属性
                        //外加一个固定属性
                        start = new HashMap<>(record.get(index).asMap());
                        start.put("nodeId", record.get(index).asNode().id());
                    }else if(index.contains("end")){
                        //节点上设置的属性
                        //外加一个固定属性
                        end = new HashMap<>(record.get(index).asMap());
                        end.put("nodeId", record.get(index).asNode().id());
                    }
                }
                route = Route.getRoute(start, edge, end);
                res.add(route);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
