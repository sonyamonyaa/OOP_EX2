package Imp;

import java.time.Duration;
import java.time.Instant;

public class TestPreformance {

    static String path = "C:\\Users\\ישראל\\IdeaProjects\\Ex2\\data\\1000Nodes.json";
    static String path2 = "C:\\Users\\ישראל\\IdeaProjects\\Ex2\\data\\10000Nodes.json";
    static Graph g = new Graph();
    static long time;

    public static void main(String[] args) {

        System.out.println("1000 NODES:");
        Instant start = Instant.now();
        g.load(path);
        Instant end = Instant.now();
        time = Duration.between(start, end).toMillis();
        System.out.println("loading: " +time);


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
        g.shortestPathDist(100, 200);
        end = Instant.now();
        time = Duration.between(start, end).toMillis();
        System.out.println("shortest path: " +time);

        System.out.println("10000 Nodes");
        start = Instant.now();
        g.load(path2);
        end = Instant.now();
        time = Duration.between(start, end).toMillis();
        System.out.println("loading: " +time);

        start = Instant.now();
        g.isConnected();
        end = Instant.now();
        time = Duration.between(start, end).toMillis();
        System.out.println("isConnected: " +time);

        start = Instant.now();
        //g.center();
        end = Instant.now();
        time = Duration.between(start, end).toMillis();
        System.out.println("center: unreasonable time, check if yr patient");

        start = Instant.now();
        g.shortestPathDist(1000, 2000);
        end = Instant.now();
        time = Duration.between(start, end).toMillis();
        System.out.println("shortest path: " +time);
    }
}

