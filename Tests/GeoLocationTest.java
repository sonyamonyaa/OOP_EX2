import Imp.geoLocation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeoLocationTest {

    private geoLocation g = new geoLocation(32,21,7);
    private double EPS = 0.0001;


    @Test
    void x() {
        assertEquals(32,g.x());
    }

    @Test
    void y() { assertEquals(21,g.y());
    }

    @Test
    void z() { assertEquals(7,g.z());
    }

    //a calculator for ref - https://www.engineeringtoolbox.com/distance-relationship-between-two-points-d_1854.html
    @Test
    void distance() {
        geoLocation g1 = new geoLocation(g);
        assertEquals(0,g.distance(g1));
        geoLocation g2 = new geoLocation(21,7,32);
        assertEquals(30.692,g.distance(g2),EPS);
    }
}