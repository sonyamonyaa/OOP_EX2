import Imp.Graph;
import Imp.Node;
import Imp.geoLocation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VerticesTest {
    /*
     * This test is specifically for the Vertices class, as it is in a protected package, we are using Graph
     * as its node methods are directly tied to the Vertices class
     */

    private Graph graph = new Graph(2);
    private geoLocation g = new geoLocation(1, 1, 0);
    private Node n = new Node(0, g, 0, "og node");

    @Test
    void getNode() {
        graph.addNode(n);
        geoLocation g1 = new geoLocation(1,2,0);
        Node n1 = new Node(1,g1,2,"second node");
        graph.addNode(n1);
        Node temp = (Node) graph.getNode(0);
        assertEquals(n,temp);
        temp = (Node) graph.getNode(1);
        assertNotEquals(n, temp);
        assertEquals(n1, temp);

    }

    @Test
    void addNode() {
        assertEquals(0, graph.nodeSize());
        graph.addNode(n);
        assertEquals(1, graph.nodeSize());
        //duplicates by geolocation shouldn't be added?
        //graph.addNode(n);
        //assertEquals(1,graph.nodeSize()); //hopefully wouldn't change
    }

    @Test
    void nodeIter() {
    }

    @Test
    void removeNode() {
        graph.addNode(n);
        assertEquals(1, graph.nodeSize());
        Node r = (Node) graph.removeNode(0);
        assertEquals(n, r);
        assertEquals(0, graph.nodeSize());
    }
}