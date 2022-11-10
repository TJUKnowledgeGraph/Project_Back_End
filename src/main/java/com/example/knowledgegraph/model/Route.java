package com.example.knowledgegraph.model;

import java.util.HashMap;

public class Route extends HashMap<String, Object> {

    public Route() {
    }

    public static Route getRoute(Object startNode, Object edge, Object endNode) {
        Route route = new Route();
        route.put("startNode", startNode);
        route.put("edge", edge);
        route.put("endNode", endNode);
        return route;
    }
}
