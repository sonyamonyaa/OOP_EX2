package Imp;

import api.NodeData;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/*
A dynamic array to allow obtaining and deleting a node in constant time
adding a node takes a constant amortised/average time

uses streams to implement the Iterable interface
 */


class Vertices implements Iterable{

    private NodeData[] arr;

    /*
    if a node has been deleted we want to remember that its place
    is available. that's what the list is for
     */
    private List<Integer> FreeMemory;

    /*
      that's why sometimes the length of the
      array wouldn't be the real size of the structure,
      so we will keep a size parameter
     */
    private int size;

    public int getSize() {
        return size;
    }

    public NodeData get(int key){
        return arr[key];
    }

    public Vertices(int initialSize){
        size = 0;
        arr = new NodeData[initialSize];
        FreeMemory = new LinkedList<>();
        FreeMemory.add(0);
    }

    public int add(NodeData n){

        int last = FreeMemory.get(0);
        //n.setKey(last)
        FreeMemory.remove(0);
        if (FreeMemory.isEmpty()){
            FreeMemory.add(last +1);
        }

        int k = n.getKey();
        if (arr.length <= k){
            this.increase();
        }
        arr[k] = n;

        size++;
        return k;
    }

    private void increase() {
        int size = (int) (arr.length * 1.2);
        NodeData[] NewArr = new NodeData[size];

        System.arraycopy(arr, 0, NewArr, 0, arr.length);

        arr = NewArr;
    }

    public NodeData remove(int key){
        NodeData n = arr[key];
        arr[key] = null;
        FreeMemory.add(0, key);

        size--;
        return n;
    }

    @Override
    public Iterator iterator() {
        return Arrays.stream(arr).iterator();
    }
}
