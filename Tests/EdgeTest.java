import Imp.Edge;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class EdgeTest {

    private Edge edge;
    @BeforeEach
    void setUp(){
        edge = new Edge(1,2,1.2);
    }
    @Test
    void Src() {
        assertEquals(1,edge.getSrc());
        edge.setSrc(3);
        assertEquals(3,edge.getSrc());
    }

    @Test
    void Dest() {
        assertEquals(2,edge.getDest());
        edge.setDest(3);
        assertEquals(3,edge.getDest());
    }

    @Test
    void Weight() {
        assertEquals(1.2,edge.getWeight());
        edge.setWeight(4.0);
        assertEquals(4.0,edge.getWeight());
    }

    @Test
    void Info() {
    assertEquals("",edge.getInfo());
    edge.setInfo("str");
    assertEquals("str",edge.getInfo());
    }

    @Test
    void Tag() {
        assertEquals(0,edge.getTag());
        edge.setTag(1);
        assertEquals(1,edge.getTag());
    }
}