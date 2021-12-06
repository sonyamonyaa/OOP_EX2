import Imp.Node;
import Imp.geoLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class NodeTest {

    private Node n;
    geoLocation g;
    @BeforeEach
    void setUp(){
        g = new geoLocation(1,1,0);
        n = new Node(1,g,0,"og node");
    }
    @Test
    void Key() {
        assertEquals(1,n.getKey());
        n.setKey(2);
        assertEquals(2,n.getKey());
    }

    @Test
    void Location() {
        geoLocation p1 = (geoLocation) n.getLocation();
        assertTrue(p1.x() == g.x() && p1.y() == g.y() && p1.z() == g.z());
        geoLocation p2 = new geoLocation(2,2,2);
        n.setLocation(p2);
        p1 = (geoLocation) n.getLocation();
        assertTrue(p1.x() == p2.x() && p1.y() == p2.y() && p1.z() == p2.z());
    }

    @Test
    void Weight() {
        assertEquals(0.0,n.getWeight());//default
        n.setWeight(2.1);
        assertEquals(2.1,n.getWeight());
    }

    @Test
    void Info() {
        String str = "og node";
        assertEquals(str,n.getInfo());
        str = "new info";
        n.setInfo(str);
        assertEquals(str,n.getInfo());
    }

    @Test
    void Tag() {
        assertEquals(0,n.getTag());
        n.setTag(1);
        assertEquals(1,n.getTag());
    }

}