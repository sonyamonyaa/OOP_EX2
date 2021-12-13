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
        return this;
    }

    @Override
    public DirectedWeightedGraph copy() {
        Graph g = new Graph(this.nodeSize());
        Iterator<NodeData>  it = nodeIter();
        while (it.hasNext()){
            NodeData n = it.next();
            if (n != null){
                g.addNode(n);
                Iterator<EdgeData> it2 = edgeIter(n.getKey());
                while (it2.hasNext()){
                    EdgeData e = it2.next();
                    g.connect(e.getSrc(), e.getDest(), e.getWeight());
                }
            }
        }

        return g;
    }

    @Override
    public boolean isConnected() {
        return DFS() && transpose(this).DFS();
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        List path = shortestPath(src, dest);
        return weightOfPath(path);
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return Dijkstra(src, dest);
    }

    @Override
    public NodeData center() {
        if (! isConnected()){
            return null;
        }

        int ind  = vertices.getFirst();
        double MinMaxDist = Integer.MAX_VALUE;

        for (int i = ind; i < vertices.length(); i++){
            if (getNode(i) != null){
                double result = DijkstraAll(i);
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
        if (cities.size() < 2){
            return cities;
        }

        List ans = new LinkedList();
        Iterator<NodeData> it = cities.iterator();
        NodeData curr = it.next();
        NodeData next = it.next();

        boolean done = false;
        while (! done){
            List<NodeData> tmp = Dijkstra(curr.getKey(), next.getKey());

            if (tmp == null){
                Iterator<NodeData> it2 = cities.iterator();
                while (it2.hasNext()){
                    NodeData other = it2.next();
                    if (other != curr && other != next){
                        tmp = Dijkstra(curr.getKey(), other.getKey());
                        if (tmp != null){
                            next = other;
                            break;
                        }
                    }
                }
            }

            for (NodeData node : invert(tmp)){
                cities.remove(node);
                if (ans.size() > 0){
                    if (node != ans.get(0)){
                        ans.add(0, node);
                    }
                }else {
                    ans.add(0, node);
                }
            }
            curr = tmp.get(0);

            if (cities.size() == 0){
                done = true;
            }else {
                next = cities.get(0);
            }
        }

        return ans;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean save(String file) {
        //scan nodes
        JSONArray nodeList = new JSONArray();
        Node n;
        for (int i = 0; i<nodeSize(); i++) {
            JSONObject nodeDetails = new JSONObject();
            n = (Node) getNode(i);
            //each node put details
            nodeDetails.put("pos",n.getLocation().toString());
            nodeDetails.put("id",n.getKey());
            nodeList.add(nodeDetails); //add node objects to list
        }
        //scan edges
        JSONArray edgeList = new JSONArray();
        Iterator<EdgeData> edIT = this.edgeIter();
        Edge edge;
        while (edIT.hasNext()){
            edge = (Edge) edIT.next();
            //each edge put details
            JSONObject edgeDetails = new JSONObject();
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

    private List<NodeData> Dijkstra(int src, int dest){
        if (getNode(src) == null || getNode(dest) == null){
            return null;
        }
        if (src == dest){
            return new LinkedList<>();
        }

        clearTags();

        List path = new LinkedList();
        double[] dists = new double[vertices.length()];
        int[] prevs = new int[vertices.length()];
        for (int i = 0; i < dists.length; i++){
            dists[i] = Integer.MAX_VALUE;
        }
        dists[src] = 0;

        Iterator<EdgeData> it = edgeIter(src);
        double min = Integer.MAX_VALUE;
        int ind;
        if (it.hasNext()){
            EdgeData e = it.next();
            min = e.getWeight();
            ind = e.getDest();
            dists[e.getDest()] = e.getWeight();
            prevs[e.getDest()] = src;
        }else {
            return null;
        }
        while (it.hasNext()){
            EdgeData e = it.next();
            if (e.getWeight() < min){
                min = e.getWeight();
                ind = e.getDest();
            }
            dists[e.getDest()] = e.getWeight();
            prevs[e.getDest()] = src;
        }
        getNode(src).setTag(1);
        getNode(ind).setTag(1);

        int curr = ind;
        boolean finished = false;
        while (! finished){

            int lastViseted = curr;
            min = Integer.MAX_VALUE;
            it = edgeIter(curr);
            while (it.hasNext()){
                EdgeData e = it.next();
                if (dists[e.getDest()] > dists[e.getSrc()] + e.getWeight()){
                    dists[e.getDest()] = dists[e.getSrc()] + e.getWeight();
                    prevs[e.getDest()] = e.getSrc();
                }
            }

            boolean allDone = true;
            min = Integer.MAX_VALUE;
            for (int i = 0; i < dists.length; i++){
                if (getNode(i).getTag() == 0){
                    allDone = false;
                    if (dists[i] <= min){
                        min = dists[i];
                        ind = i;
                    }
                }
            }

            if (allDone){
                finished = true;
                break;
            }else {
                curr = ind;
                getNode(ind).setTag(1);
            }
        }

        if (dists[dest] == Integer.MAX_VALUE){
            return null;
        }

        curr = dest;
        path.add(getNode(dest));
        while (curr != src){
            path.add(getNode(prevs[curr]));
            curr = prevs[curr];
        }

        return path;
    }

    private double DijkstraAll(int src){
        clearTags();

        List path = new LinkedList();
        double[] dists = new double[vertices.length()];
        int[] prevs = new int[vertices.length()];
        for (int i = 0; i < dists.length; i++){
            dists[i] = Integer.MAX_VALUE;
        }
        dists[src] = 0;

        Iterator<EdgeData> it = edgeIter(src);
        double min = Integer.MAX_VALUE;
        int ind;
        if (it.hasNext()){
            EdgeData e = it.next();
            min = e.getWeight();
            ind = e.getDest();
            dists[e.getDest()] = e.getWeight();
            prevs[e.getDest()] = src;
        }else {
            return 0;
        }
        while (it.hasNext()){
            EdgeData e = it.next();
            if (e.getWeight() < min){
                min = e.getWeight();
                ind = e.getDest();
            }
            dists[e.getDest()] = e.getWeight();
            prevs[e.getDest()] = src;
        }
        getNode(src).setTag(1);
        getNode(ind).setTag(1);

        int curr = ind;
        boolean finished = false;
        while (! finished){

            int lastViseted = curr;
            min = Integer.MAX_VALUE;
            it = edgeIter(curr);
            while (it.hasNext()){
                EdgeData e = it.next();
                if (dists[e.getDest()] > dists[e.getSrc()] + e.getWeight()){
                    dists[e.getDest()] = dists[e.getSrc()] + e.getWeight();
                    prevs[e.getDest()] = e.getSrc();
                }
            }

            boolean allDone = true;
            min = Integer.MAX_VALUE;
            for (int i = 0; i < dists.length; i++){
                if (getNode(i).getTag() == 0){
                    allDone = false;
                    if (dists[i] <= min){
                        min = dists[i];
                        ind = i;
                    }
                }
            }

            if (allDone){
                finished = true;
                break;
            }else {
                curr = ind;
                getNode(ind).setTag(1);
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

    private double weightOfPath(List<NodeData> path){
        double sum = 0;
        if (path.size() < 2){
            return sum;
        }

        Iterator<NodeData> it = path.iterator();
        int src = it.next().getKey(), dest;

        while (it.hasNext()){
            dest = src; //the list is inverted
            if (it.hasNext()){
                src = it.next().getKey();
            }else {
                break;
            }

            sum += getEdge(src, dest).getWeight();
        }

        return sum;
    }

    private static List<NodeData> invert(List<NodeData> l){
        List ans = new LinkedList();
        Iterator it = l.iterator();
        while (it.hasNext()){
            ans.add(0, it.next());
        }

        return ans;
    }
}
