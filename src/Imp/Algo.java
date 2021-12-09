package Imp;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;

import java.util.List;

public class Algo implements DirectedWeightedGraphAlgorithms {

    private Graph g;

    @Override
    public void init(DirectedWeightedGraph g) {
        this.g = (Graph) g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return g;
    }

    @Override
    public DirectedWeightedGraph copy() {
        return g.copy();
    }

    @Override
    public boolean isConnected() {
        return g.isConnected();
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return g.shortestPathDist(src, dest);
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return g.shortestPath(src, dest);
    }

    @Override
    public NodeData center() {
        return g.center();
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return g.tsp(cities);
    }

    @Override
    public boolean save(String file) {
        return g.save(file);
    }

    @Override
    public boolean load(String file) {
        return g.load(file);
    }
}
