import Imp.Graph;

class GraphJsonTest {
    static String Path = "data/G1.json";

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.load(Path);
        graph.save("data/temp.json");
    }
}