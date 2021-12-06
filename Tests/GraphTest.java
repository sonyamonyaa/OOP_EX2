import Imp.Edge;
import Imp.Node;
import Imp.geoLocation;
import org.junit.jupiter.api.Test;

class GraphTest {
    /*
    * We wanted to separate the Graph tests for readability and not test everything in bulk
    * For the Edges and Node method tests please refer to EdgesTest and VerticesTest accordingly.
     */
    private Edge e1 = new Edge(0,1,1.232037506070033);
    private Edge e2 = new Edge(1,2,1.8015954015822042);
    private Edge e3 = new Edge(1,0,1.8635670623870366);
    private Node n1 = new Node(0,new geoLocation(35.19589389346247,32.10152879327731,0.0));
    private Node n2 = new Node(1,new geoLocation(35.20319591121872,32.10318254621849,0.0));
    private Node n3 = new Node(2,new geoLocation(35.20752617756255,32.1025646605042,0.0));


    @Test
    void getMC() {
    }

    @Test
    void init() {
    }

    @Test
    void getGraph() {
    }

    @Test
    void copy() {
    }

    @Test
    void isConnected() {
    }

    @Test
    void shortestPathDist() {
    }

    @Test
    void shortestPath() {
    }

    @Test
    void center() {
    }

    @Test
    void tsp() {
    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }
}