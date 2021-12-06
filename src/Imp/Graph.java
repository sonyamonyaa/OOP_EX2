package Imp;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;

import java.util.*;

public class Graph implements DirectedWeightedGraph, DirectedWeightedGraphAlgorithms {

    /* Array of nodes & a corresponding array of balanced Trees
       that sorts( by destination) the edges from the vertex stored in the nodes array
       in the same location/index.

       Allows adding and searching an edge in logarithmic time
       And to access a specific node in constant time

       The class "synchronises" the states/activities of the 2 structures
     */

    /*
    The iterator should throw an exception when the graph has been tempered with (add/connect or remove)
    I think maybe we need to add/remove only through the iterator but idk - s
     */

    private Vertices vertices;
    private Edges edges;
    private int modeCount;
    public Graph() {
        this.vertices = null;
        this.edges = null;
        modeCount = 0;
    }

    public Graph(int InitSize){
        this.vertices = new Vertices(InitSize);
        this.edges = new Edges(InitSize);
    }

    @Override
    public NodeData getNode(int key) {
        return vertices.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return edges.getEdge(src, dest);
    }

    @Override
    public void addNode(NodeData n) {
        int loc = vertices.add(n);
        edges.add(loc);
        modeCount++;
    }

    @Override
    public void connect(int src, int dest, double w) {
        edges.connect(src, dest, w);
        modeCount++;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return vertices.iterator();
    }


    //puts all the edges in one big stream and return its iterator
    @Override
    public Iterator<EdgeData> edgeIter() {
        return edges.getIter();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return edges.getEdgeIter(node_id);
    }

    @Override
    public NodeData removeNode(int key) {
        edges.remove(key);
        return vertices.remove(key);
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        return edges.removeEdge(src, dest);
    }

    @Override
    public int nodeSize() {
        return vertices.getSize();
    }

    @Override
    public int edgeSize() {
        return edges.getSize();
    }

    @Override
    public int getMC() {
        return modeCount;
    }

    @Override
    public void init(DirectedWeightedGraph g) {

    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return null;
    }

    @Override
    public DirectedWeightedGraph copy() {
        return null;
    }

    @Override
    public boolean isConnected() {
        boolean b = DFS();
        return b && transpose(this).DFS();
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return DFS(src, dest);
    }

    @Override
    public NodeData center() {
        if (! isConnected()){
            return null;
        }

        int ind  = vertices.getFirst();
        double MinMaxDist = Integer.MAX_VALUE;

        for (int i = ind; i < vertices.leangth(); i++){
            if (getNode(i) != null){
                double result = DFS(i);
                if (result < MinMaxDist){
                    MinMaxDist = result;
                    ind = i;
                }
            }
        }

        return getNode(ind);
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
        return false;
    }

    private static Graph transpose(Graph g){
        Graph tg = new Graph(g.nodeSize());

        Iterator it = g.nodeIter();
        while (it.hasNext()){
            NodeData n = (NodeData) it.next();
            if (n != null){
                tg.addNode(new Node(n));
            }
        }

        it = g.edgeIter();
        while (it.hasNext()){
            EdgeData e = (EdgeData) it.next();
            if (e != null){
                tg.connect(e.getDest(), e.getSrc(), e.getWeight());
            }
        }

        return  tg;
    }

    private boolean DFS(){//all reachable?
        Stack<Integer> s = new Stack();
        s.push(vertices.getFirst());
        int NodesReached = 0;

        while (! s.isEmpty()){
            int loc = s.pop();
            vertices.get(loc).setTag(1);
            NodesReached++;

            Iterator<EdgeData> it = edgeIter(loc);
            while (it.hasNext()){
                EdgeData e = it.next();

                if (vertices.get(e.getDest()).getTag() != 1){
                    s.push(e.getDest());
                }
            }
        }

        if (NodesReached == nodeSize()){
            return true;
        }
        return false;
    }

    private List<NodeData> DFS(int src, int dest){//shortest path
        List best = new LinkedList();
        List tmp = new LinkedList();
        double min = Integer.MAX_VALUE,  curr = 0;

        Stack<EdgeData> S = new Stack<>();
        S.push(new Edge(-1, src, 0));

        while (! S.isEmpty()){

            EdgeData e = S.pop();
            if (e.getDest() == dest){
                curr += e.getWeight();
                tmp.add(0, getNode(e.getDest()));

                if (curr < min){
                    min = curr;
                    best = tmp.subList(0, tmp.size());//copy
                }
            }

            if(getNode(e.getDest()).getTag() != 1){
                Iterator<EdgeData> it = edgeIter(e.getDest());
                while (it.hasNext()){
                    S.push(it.next());
                }
            }else {
                tmp.remove(0);
            }
            getNode(e.getDest()).setTag(1);
        }

        return best;
    }

    private double DFS(int start){
        double curr = 0;
        double[] dists = new double[nodeSize()];

        Stack<EdgeData> S = new Stack<>();
        dists[start] = -1;
        Iterator<EdgeData> it = edgeIter(start);
        while (it.hasNext()){
            S.push(it.next());
        }

        while (! S.isEmpty()){

            EdgeData e = S.pop();
            curr += e.getWeight();
            if (dists[e.getDest()] == 0){
                it = edgeIter(e.getDest());
                while (it.hasNext()){
                    S.push(it.next());
                }
            }

            if (dists[e.getDest()] != 0){
                if (dists[e.getDest()] > curr){
                    dists[e.getDest()] = curr;
                    curr -= e.getWeight();
                }
            }else {
                dists[e.getDest()] = curr;
            }

        }

        return max(dists);
    }

    private static double max(double[] arr){
        double max = arr[0];
        for (int i = 1; i < arr.length; i++){
            if (arr[i] > max){
                max = arr[i];
            }
        }
        return max;
    }

}
