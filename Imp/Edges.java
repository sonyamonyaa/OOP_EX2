package Imp;

import api.EdgeData;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.stream.Stream;



//  A dynamic array of balanced Trees

class Edges {

    private TreeMap<Double, EdgeData>[] arr;
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

        arr[key] = new TreeMap<Double, EdgeData>();
        size++;
    }

    private void increase() {
        int size = (int) (arr.length * 1.2);
        TreeMap[] NewArr = new TreeMap[size];

        System.arraycopy(arr, 0, NewArr, 0, arr.length);

        arr = NewArr;
    }

    public EdgeData getEdge(int src, int dest) {
        Iterator<EdgeData> it  = arr[src].values().iterator();
        EdgeData e = null;

        while (it.hasNext()){
            e = it.next();
            if (e.getDest() == dest){
                break;
            }
        }

        return e;
    }

    public void connect(int src, int dest, double w) {
        EdgeData e = null;// = new
        arr[src].put(w, e);
    }

    public Iterator<EdgeData> getIter() {
        TreeMap t = new TreeMap();
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

    public EdgeData removeEdge(int src, int dest) {
        EdgeData e = getEdge(src, dest);
        arr[src].remove(e);

        return  null;
    }

    public void remove(int key){
        arr[key] = null;
        size--;
    }
}
