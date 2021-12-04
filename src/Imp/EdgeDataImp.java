package Imp;

public class EdgeDataImp implements api.EdgeData {

    private int src;
    private int dest;
    private double weight;
    private int tag;
    private String info;

    public EdgeDataImp(int src, int dest, int weight){
        this.src =src;
        this.dest =dest;
        this.weight =weight;
        this.setTag(0); //default
    }
    public EdgeDataImp(api.EdgeData edge){

        this.src = edge.getSrc();
        this.dest =edge.getDest();
        this.weight = edge.getWeight();
        this.tag = edge.getTag();
        this.setInfo(edge.getInfo());
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
