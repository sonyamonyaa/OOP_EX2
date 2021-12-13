package Imp;

import api.NodeData;

import java.util.*;


/*
A dynamic array to allow obtaining and deleting a node in constant time
adding a node takes a constant amortised/average time

uses streams to implement the Iterable interface
 */


class Vertices implements Iterable<NodeData> {

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

    public int length() {
        return FreeMemory.get(FreeMemory.size() - 1);
    }

    public NodeData get(int key) {
        return arr[key];
    }

    public Vertices(int initialSize) {
        size = 0;
        arr = new NodeData[initialSize];
        FreeMemory = new LinkedList<>();
        FreeMemory.add(0);
    }

    public int add(NodeData n) {
        Node node = new Node(n);

        int last = FreeMemory.get(0);

        if (last != node.getKey()) {
            String message = "node " + node.getKey() + " ";
            node.setKey(last);
            System.out.println(message + "is now node " + node.getKey());
        }

        FreeMemory.remove(0);
        if (FreeMemory.isEmpty()) {
            FreeMemory.add(last + 1);
        }

        int k = node.getKey();
        if (arr.length <= k) {
            this.increase();
        }
        arr[k] = node;

        size++;
        return k;
    }

    private void increase() {
        int size = (int) (arr.length * 1.2);
        NodeData[] NewArr = new NodeData[size];

        System.arraycopy(arr, 0, NewArr, 0, arr.length);

        arr = NewArr;
    }

    public NodeData remove(int key) {
        NodeData n = arr[key];
        FreeMemory.add(0, key);
        size--;
        Iterator<NodeData> nodeIt = this.iterator();
        NodeData i;
        while (nodeIt.hasNext()) {
            i = nodeIt.next();
            if (i.getKey() == key)
                nodeIt.remove();
        }
                arr[key] = null;
        return n;
    }

    public int getFirst() {
        int i = 0;
        while (i < arr.length) {
            if (arr[i] != null) {
                return i;
            }
            i++;
        }

        return -1;
    }


    //returns the last location in the
    //structure that contains a node
    public int max() {
        int n = FreeMemory.get(FreeMemory.size() - 1);
        if (n == arr.length) {
            return n - 1;
        } else {
            return n;
        }
    }

    @Override
    public Iterator<NodeData> iterator() {

        //return Arrays.stream(arr).iterator(); //didn't work
        //https://www.javacodeexamples.com/java-exception-java-lang-unsupportedoperationexception/238

        //fix sized unmodifiable list
        List<NodeData> list = Arrays.asList(arr);
        //create new ArrayList from fixed size list object
        list = new ArrayList(list);
        return list.listIterator();
    }
}
