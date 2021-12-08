package Imp;

import api.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Graph implements DirectedWeightedGraph, DirectedWeightedGraphAlgorithms {

    /* Array of nodes & a corresponding array of balanced Trees
       that sorts( by destination) the edges from the vertex stored in the nodes array
       in the same location/index.

       Allows adding and searching an edge in logarithmic time
       And to access a specific node in constant time

       The class "synchronises" the states/activities of the 2 structures
     */

    private Vertices vertices;
    private Edges edges;
    private int modeCount;

    public Graph(){
        this.vertices = null;
        this.edges = null;
    }

    public Graph(int InitSize){
        this.vertices = new Vertices(InitSize);
        this.edges = new Edges(InitSize);
        modeCount = 0;
    }

    @Override
    public NodeData getNode(int key) {
        return vertices.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return edges.getEdge(src, dest);
    }


    //In our implementation if a node of
    //was deleted a new node will take its key
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
        edges.removeNode(key);
        modeCount++;
        return vertices.remove(key);
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        modeCount++;
        return edges.removeEdge(src, dest);
    }

    @Override
    public int nodeSize() {
        return vertices.getSize();
    }

    @Override
    public int edgeSize() {
        return edges.getEdgeCount();
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
        return DFS() && transpose(this).DFS();
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        List path = shortestPath(src, dest);
        double sum = 0;

        Iterator<NodeData> it = path.iterator();
        while (it.hasNext()){
            dest = it.next().getKey(); //the list is inverted
            if (it.hasNext()){
                src = it.next().getKey();
            }

            sum += getEdge(src, dest).getWeight();
        }

        return sum;
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

        for (int i = ind; i < vertices.max(); i++){
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
    @SuppressWarnings("unchecked")
    public boolean save(String file) {
        //scan nodes
        JSONObject nodeDetails = new JSONObject();
        JSONArray nodeList = new JSONArray();
        Node n;
        for (int i = 0; i<nodeSize(); i++) {
            n = (Node) getNode(i);
            //each node put details
            nodeDetails.put("pos",n.getLocation().toString());
            nodeDetails.put("id",n.getKey());
            nodeList.add(nodeDetails); //add node objects to list
        }
        //scan edges
        JSONObject edgeDetails = new JSONObject();
        JSONArray edgeList = new JSONArray();
        Iterator<EdgeData> edIT = this.edgeIter();
        Edge edge;
        while (edIT.hasNext()){
            edge = (Edge) edIT.next();
            //each edge put details
            edgeDetails.put("src", edge.getSrc());
            edgeDetails.put("w", edge.getWeight());
            edgeDetails.put("dest", edge.getDest());
            edgeList.add(edgeDetails); //add edge objects to list
        }

        //finally - put both list to graph json obj
        JSONObject graph = new JSONObject();
        graph.put("Edges",edgeList);
        graph.put("Nodes",nodeList);

        try (FileWriter jsonFile = new FileWriter(file)) {
            jsonFile.write(graph.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean load(String file) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject Jobj = (JSONObject) parser.parse(new FileReader(file));
            JSONArray Jarr = (JSONArray) Jobj.get("Nodes");

            vertices = new Vertices(Jarr.size());
            edges = new Edges(Jarr.size());

            int i = 0;
            while (i < Jarr.size()){

                String s = Jarr.get(i).toString();
                s = s.substring(1, s.length() -1);

                String[] data = s.split(",");
                for (int j = 0; j < data.length; j++){
                    data[j] = data[j].substring(data[j].indexOf(':') +1);
                }
                data[0] = data[0].substring(1);
                data[2] = data[2].substring(0, data[2].length() -1);

                double x = Double.parseDouble(data[0]);
                double y = Double.parseDouble(data[1]);
                double z = Double.parseDouble(data[2]);
                GeoLocation g = new geoLocation(x, y, z);
                int id = Integer.parseInt(data[3]);

                addNode(new Node(id, g, 0, null));

                i++;
            }

            Jarr = (JSONArray) Jobj.get("Edges");
            i = 0;
            while (i < Jarr.size()){

                String s = Jarr.get(i).toString();
                s = s.substring(2, s.length() -1);

                String[] data = s.split(",");
                for (int j = 0; j < data.length; j++){
                    data[j] = data[j].substring(data[j].indexOf(':') +1);
                }

                int src = Integer.parseInt(data[0]);
                double weight = Double.parseDouble(data[1]);
                int dest = Integer.parseInt(data[2]);
                connect(src, dest, weight);

                i++;
            }

        }catch (IOException | ParseException e){
            e.printStackTrace();
            return false;
        }

        return true;
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
        clearTags();

        Stack<Integer> s = new Stack<>();
        int start = vertices.getFirst();
        getNode(start).setTag(1);
        s.push(start);

        int NodesReached = 0;

        while (! s.isEmpty()){
            int loc = s.pop();
            NodesReached++;

            Iterator<EdgeData> it = edgeIter(loc);
            while (it.hasNext()){
                EdgeData e = it.next();

                if (getNode(e.getDest()).getTag() != 1){
                    vertices.get(e.getDest()).setTag(1);
                    s.push(e.getDest());
                }
            }
        }

        return NodesReached == nodeSize();
    }

    private List<NodeData> DFS(int src, int dest){//shortest path
        clearTags();

        List<NodeData> best = new LinkedList<>();
        List<NodeData> tmp = new LinkedList<>();
        double min = Integer.MAX_VALUE,  curr = 0;

        Stack<EdgeData> S = new Stack<>();
        EdgeData start = new Edge(-1, src, 0);
        S.push(start);
        getNode(src).setTag(1);

        Iterator<EdgeData> it = edgeIter(src);
        while (it.hasNext()) {
            EdgeData son = it.next();
            getNode(son.getDest()).setTag(1);
            S.push(son);
        }
        tmp.add(getNode(src));

        while (! S.isEmpty()){

            EdgeData e = S.pop();
            curr += e.getWeight();
            tmp.add(0, getNode(e.getDest()));

            if (e.getDest() == dest){
                if (curr < min){
                    min = curr;
                    best = new LinkedList(tmp);
                }
            }

            boolean forward = false;
            it = edgeIter(e.getDest());
            while (it.hasNext()){
                EdgeData son = it.next();
                if (getNode(son.getDest()).getTag() != 1 || getNode(son.getDest()).getKey() == dest){
                    forward = true;
                    getNode(son.getDest()).setTag(1);
                    S.push(son);
                }
            }

            if (! forward){
                tmp.remove(0);
                curr -= e.getWeight();
            }
        }

        return best;
    }

    private double DFS(int start){
        clearTags();

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

    public void clearTags(){
        for (int i = 0; i < vertices.length(); i++){
            if(getNode(i) != null)
                getNode(i).setTag(0);
        }
    }

}
