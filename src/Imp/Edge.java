package Imp;

public class Edge implements api.EdgeData {

    private int src;
    private int dest;
    private double weight;
    private int tag;
    private String info;

    public Edge(int src, int dest, double weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
        this.setTag(0); //default
        this.info = "";
    }

    public Edge(Edge edge) {

        this.src = edge.getSrc();
        this.dest = edge.getDest();
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Edge e = (Edge) obj;
        return this.src == e.getSrc() && this.dest == e.getDest() && this.weight == e.getWeight();
    }

    @Override
    public String toString() {
        return "Edge{" +
                "src=" + src +
                ", dest=" + dest +
                ", weight=" + weight +
                '}';
    }
}
