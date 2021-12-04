package Imp;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.Iterator;

public class Digraph implements DirectedWeightedGraph {
    //I believe we should use hashmaps, as we need to add/remove in O(1)
    //basically make a map of nodes, maps of their corresponding directed neighbors and opposite neighbors
    //and a map of edges, the key is their src and dest <- represented by obj / str not sure yet
    private int edgeSize, nodeSize, modeCount;
    public Digraph() {
        this.modeCount = 0;
    }
    @Override
    public NodeData getNode(int key) {
        return null;
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return null;
    }

    @Override
    public void addNode(NodeData n) {
        nodeSize++;
        modeCount++;
    }

    @Override
    public void connect(int src, int dest, double w) {
        edgeSize++;
        modeCount++;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return null;
    }

    @Override
    public NodeData removeNode(int key) {
        nodeSize--;
        modeCount++;
        return null;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        edgeSize--;
        modeCount++;
        return null;
    }

    @Override
    public int nodeSize() {
        return this.nodeSize;
    }

    @Override
    public int edgeSize() {
        return this.edgeSize;
    }

    @Override
    public int getMC() {
        return this.modeCount;
    }
}
