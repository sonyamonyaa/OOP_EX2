package Imp;

public class Edge implements api.EdgeData {

    private int src;
    private int dest;
    private double weight;
    private int tag;
    private String info;

    public Edge(int src, int dest, double weight){
        this.src =src;
        this.dest =dest;
        this.weight =weight;
        this.setTag(0); //default
    }
    public Edge(Edge edge){

        this.src = edge.getSrc();
        this.dest =edge.getDest();
        this.weight = edge.getWeight();
        this.tag = edge.getTag();
        this.setInfo(edge.getInfo());
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public int getSrc() {
        return this.src;
    }

    @Override
    public int getDest() {
        return this.dest;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }
}
