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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        geoLocation g = (geoLocation) obj;
        return this.x() == g.x() && this.y() == g.y() && this.z() == g.z();
    }

    @Override
    public double distance(api.GeoLocation g) {
        double dist = Math.pow((g.x() - this.x),2) + Math.pow((g.y() - this.y),2) + Math.pow((g.z() - this.z),2);
        return Math.sqrt(dist);
    }
}
