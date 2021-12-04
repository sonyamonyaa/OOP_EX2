package Imp;

public class EdgeDataImp implements api.EdgeData {

    private int _src;
    private int _dest;
    private double _weight;
    private int _tag;
    private String _info;

    public EdgeDataImp(int src,int dest, int weight){
        this._src=src;
        this._dest=dest;
        this._weight=weight;
        this.setTag(0); //default
    }
    public EdgeDataImp(api.EdgeData edge){

        this._src = edge.getSrc();
        this._dest =edge.getDest();
        this._weight = edge.getWeight();
        this._tag = edge.getTag();
        this.setInfo(edge.getInfo());
    }
    @Override
    public int getSrc() {
        return this._src;
    }

    @Override
    public int getDest() {
        return this._dest;
    }

    @Override
    public double getWeight() {
        return this._weight;
    }

    @Override
    public String getInfo() {
        return this._info;
    }

    @Override
    public void setInfo(String s) {
        this._info = s;
    }

    @Override
    public int getTag() {
        return this._tag;
    }

    @Override
    public void setTag(int t) {
        this._tag = t;
    }
}
