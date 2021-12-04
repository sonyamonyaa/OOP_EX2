package Imp;

public class geoLocation implements api.GeoLocation {
    private double x, y, z;

    public geoLocation(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public geoLocation(api.GeoLocation gl){
        this.x = gl.x();
        this.y = gl.y();
        this.z = gl.z();
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public double distance(api.GeoLocation g) {
        double dist = Math.pow((g.x() - this.x),2) + Math.pow((g.y() - this.y),2) + Math.pow((g.z() - this.z),2);
        return Math.sqrt(dist);
    }
}
