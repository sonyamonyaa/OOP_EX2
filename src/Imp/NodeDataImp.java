package Imp;
import api.GeoLocation;
import api.NodeData;

public class NodeDataImp implements NodeData {
    private int key;
    private GeoLocation geoLocation;
    private double weight;
    private int tag;
    private String info;

    public NodeDataImp(int key, GeoLocation p,double weight,String info) {
        this.setKey(key);
        this.setLocation(p);
        this.setWeight(weight);
        this.setInfo(info);
        this.setTag(0);
    }

    public NodeDataImp(NodeData n) {
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
    public GeoLocation getLocation() {
        return this.geoLocation;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.geoLocation = new GeoLocationImp(p);
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
}
