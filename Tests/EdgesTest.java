import Imp.Edge;
import Imp.Graph;
import Imp.Node;
import Imp.geoLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EdgesTest {private Edge e1 = new Edge(0,1,1.232037506070033);
    private Edge e2 = new Edge(1,2,1.8015954015822042);
    private Edge e3 = new Edge(1,0,1.8635670623870366);
    private Node n1 = new Node(0,new geoLocation(35.19589389346247,32.10152879327731,0.0));
    private Node n2 = new Node(1,new geoLocation(35.20319591121872,32.10318254621849,0.0));
    private Node n3 = new Node(2,new geoLocation(35.20752617756255,32.1025646605042,0.0));
    private Graph g = new Graph(3);
    /*
     * This test is specifically for the Edges class, as it is in a protected package we are using Graph
     * as its edge methods are directly tied to the Edges class
     */
    @BeforeEach
    void setUp(){
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
    }
    @Test
    void getEdge() {
        g.connect(e1.getSrc(),e1.getDest(),e1.getWeight());
        g.connect(e2.getSrc(),e2.getDest(),e2.getWeight());
        g.connect(e3.getSrc(),e3.getDest(),e3.getWeight());
        assertEquals(e1,g.getEdge(0,1));
        assertEquals(e2,g.getEdge(1,2));
        assertEquals(e3,g.getEdge(1,0));
    }

    @Test
    void connect() {
        g.connect(e1.getSrc(),e1.getDest(),e1.getWeight());
        assertEquals(1,g.edgeSize());
        g.connect(e1.getSrc(),e1.getDest(),3);
        assertEquals(1,g.edgeSize()); //should stay the same even tho the weight changed
    }

    @Test
    void edgeIter() {
    }

    @Test
    void testEdgeIter() {
    }


    @Test
    void removeEdge() {
        g.connect(e1.getSrc(),e1.getDest(),e1.getWeight());
        g.connect(e2.getSrc(),e2.getDest(),e2.getWeight());
        g.connect(e3.getSrc(),e3.getDest(),e3.getWeight());
        assertEquals(3,g.edgeSize());
        Edge e = (Edge) g.removeEdge(0,1);
        assertEquals(e1,e);
        assertEquals(2,g.edgeSize());
        e = (Edge) g.removeEdge(0,1);
        assertNull(e);
    }

}