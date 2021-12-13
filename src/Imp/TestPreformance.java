package Imp;

import api.DirectedWeightedGraphAlgorithms;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

public class TestPreformance {

    static String path = "C:\\Users\\ישראל\\IdeaProjects\\Ex2\\data\\1000Nodes.json";
    static String path2 = "C:\\Users\\ישראל\\IdeaProjects\\Ex2\\data\\10000Nodes.json";
    static String path3 = "C:\\Users\\ישראל\\IdeaProjects\\Ex2\\data\\100000Nodes.json";
    static Graph g = new Graph();

    public static void main(String[] args) {

        System.out.println("1000NODES");
        Instant start = Instant.now();
        g.load(path);
        Instant end = Instant.now();
        long time = Duration.between(start, end).toMillis();
        System.out.println("load: " +time);

        start = Instant.now();
        g.center();
        end = Instant.now();
        time = Duration.between(start, end).toMillis();
        System.out.println("center: " +time);

        start = Instant.now();
        g.isConnected();
        end = Instant.now();
        time = Duration.between(start, end).toMillis();
        System.out.println("isConnected: " +time);

        start = Instant.now();
        g.shortestPathDist(20,500);
        end = Instant.now();
        time = Duration.between(start, end).toMillis();
        System.out.println("shortestPath: " +time);


        System.out.println("10000NODES");
        start = Instant.now();
        g.load(path2);
        end = Instant.now();
        time = Duration.between(start, end).toMillis();
        System.out.println("load: " +time);

        start = Instant.now();
        g.isConnected();
        end = Instant.now();
        time = Duration.between(start, end).toMillis();
        System.out.println("isConnected: " +time);

        start = Instant.now();
        g.shortestPathDist(20,500);
        end = Instant.now();
        time = Duration.between(start, end).toMillis();
        System.out.println("shortestPath: " +time);

        System.out.println("100000NODES");
        start = Instant.now();
        g.load(path3);
        end = Instant.now();
        time = Duration.between(start, end).toMillis();
        System.out.println("load: " +time);

        start = Instant.now();
        g.isConnected();
        end = Instant.now();
        time = Duration.between(start, end).toMillis();
        System.out.println("isConnected: " +time);

        start = Instant.now();
        g.shortestPathDist(20,500);
        end = Instant.now();
        time = Duration.between(start, end).toMillis();
        System.out.println("shortestPath: " +time);
    }
}
