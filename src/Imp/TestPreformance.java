package Imp;

import api.DirectedWeightedGraphAlgorithms;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

public class TestPreformance {

    static String path = "C:\\Users\\ישראל\\IdeaProjects\\Ex2\\data\\1000Nodes.json";
    static Graph g = new Graph();

    public static void main(String[] args) {

        Instant start = Instant.now();
        g.load(path);
        Instant end = Instant.now();
        long time = Duration.between(start, end).toMillis();
        System.out.println(time);

        start = Instant.now();
        System.out.println(g.center());
        end = Instant.now();
        time = Duration.between(start, end).toMillis();
        System.out.println(time);

        start = Instant.now();
        System.out.println(g.shortestPathDist(34, 0));
        end = Instant.now();
        time = Duration.between(start, end).toMillis();
        System.out.println(time);


    }
}
