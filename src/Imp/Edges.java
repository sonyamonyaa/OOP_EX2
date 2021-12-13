package Imp;

import api.EdgeData;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;



//  A dynamic array of balanced Trees

class Edges {

    private TreeMap<Integer, EdgeData>[] arr;
    private int edgeCount;


    public int getEdgeCount(){return edgeCount;}

    public Edges(int initialSize){
        this.arr = new TreeMap[initialSize];
        edgeCount = 0;
    }

    public void add(int key){
        if (key >= arr.length){
            this.increase();
        }

        arr[key] = new TreeMap<Integer, EdgeData>();
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
        if (arr[src].containsKey(dest)){
            removeEdge(src, dest);
        }
        arr[src].put(dest, e);

        edgeCount++;
    }

    public Iterator<EdgeData> getIter() {
        List l = new LinkedList();

        for (int i = 0; i < arr.length; i++){
            if (arr[i] != null){
                Iterator it = getEdgeIter(i);
                while (it.hasNext()){
                    l.add(it.next());
                }
            }
        }

        return l.iterator();
    }

    public Iterator<EdgeData> getEdgeIter(int node_id) {
        return arr[node_id].values().iterator();
    }

    public EdgeData removeEdge(int src, int dest) {edgeCount--; return arr[src].remove(dest);}

    public void removeNode(int key){
        remove(key);
        edgeCount--;
        for(int i = 0; i < arr.length; i++){
            if (arr[i] != null){
                arr[i].remove(key);
                edgeCount--;
            }
        }
        Iterator<EdgeData> edgeIter = this.getEdgeIter(key);
        EdgeData edge;
        while (edgeIter.hasNext()){
            edge = edgeIter.next();
            if(edge.getDest() == key || edge.getSrc() == key)
                edgeIter.remove();
        }
    }

    public void remove(int key){ arr[key] = null;}
}
