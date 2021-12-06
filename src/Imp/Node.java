package Imp;

public class Node implements api.NodeData {
    private int key;
    private api.GeoLocation geoLocation;
    private double weight;
    private int tag;
    private String info;

    public Node(int key, api.GeoLocation p, double weight, String info) {
        this.setKey(key);
        this.setLocation(p);
        this.setWeight(weight);
        this.setInfo(info);
        this.setTag(0);
    }

    public Node(api.NodeData n) {
        this.setKey(n.getKey());
        this.setLocation(n.getLocation());
        this.setWeight(n.getWeight());
        this.setTag(n.getTag());
        this.setInfo(n.getInfo());
    }

    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public api.GeoLocation getLocation() {
        return this.geoLocation;
    }

    @Override
    public void setLocation(api.GeoLocation p) {
        this.geoLocation = new geoLocation(p);
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;
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
        if (obj == null || getClass() != obj.getClass()) return false;
        Node n = (Node) obj;
        boolean ans = this.key == n.getKey() && Double.compare(n.getWeight(), this.weight) == 0 && this.tag == n.getTag();
        return ans && this.geoLocation.equals(n.getLocation()) && this.info.equals(n.getInfo());
    }
}
