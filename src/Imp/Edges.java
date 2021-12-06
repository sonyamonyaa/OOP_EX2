package Imp;

import api.EdgeData;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.stream.Stream;



//  A dynamic array of balanced Trees

class Edges {

    private TreeMap<Integer, EdgeData>[] arr;
    private int size;

    public int getSize() {
        return size;
    }

    public Edges(int initialSize){
        this.arr = new TreeMap[initialSize];
        size = 0;
    }

    public void add(int key){
        if (key >= arr.length){
            this.increase();
        }

        arr[key] = new TreeMap<Integer, EdgeData>();
        size++;
    }

    private void increase() {
        int size = (int) (arr.length * 1.2);
        TreeMap<Integer,EdgeData>[] NewArr = new TreeMap[size];

        System.arraycopy(arr, 0, NewArr, 0, arr.length);

        arr = NewArr;
    }

    public TreeMap<Integer, EdgeData> getEdgesOf(int src){
        return arr[src];
    }

    public EdgeData getEdge(int src, int dest) {
        return getEdgesOf(src).get(dest);
    }

    public void connect(int src, int dest, double w) {
        EdgeData e = new Edge(src,dest,w);
        // if an edge of the same src and dest already exist
        //update the weight of that edge with w
        arr[src].put(dest, e);
    }

    public Iterator<EdgeData> getIter() {
        TreeMap<Double,EdgeData> t = new TreeMap<Double, EdgeData>();
        Stream s = t.entrySet().stream();

        for (int i = 0; i < arr.length; i++){
            if (arr[i] != null){
                Stream combined = Stream.concat(s, arr[i].entrySet().stream());
            }
        }

        return s.iterator();
    }

    public Iterator<EdgeData> getEdgeIter(int node_id) {
        return arr[node_id].values().iterator();
    }

    public EdgeData removeEdge(int src, int dest) { return arr[src].remove(dest);}

    public void remove(int key){ arr[key] = null; size--;}
}
