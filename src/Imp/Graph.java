import java.util.Iterator;
import java.util.List;

public class Graph implements DirectedWeightedGraph, DirectedWeightedGraphAlgorithms{

    /* Array of nodes & a corresponding array of balanced Trees
       that sorts( by weight) the edges from the vertex stored in the nodes array
       in the same location/index.

       Allows adding and searching( by weight) an edge in logarithmic time
       And to access a specific node in constant time

       The class "synchronises" the states/activities of the 2 structures
     */

    private Vertices vertices;
    private Edges edges;


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

    }


    @Override
    public void connect(int src, int dest, double w) {
        edges.connect(src, dest, w);
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
        return 0;
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
        return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
    }

    @Override
    public NodeData center() {
        return null;
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
}
