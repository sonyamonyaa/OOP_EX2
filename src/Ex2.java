import Imp.Graph;
import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import gui.Gframe;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
//    static final int WIDTH = 1080;
//    static final int HEIGHT = (int) (WIDTH / 1.6);
    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return directed graph
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        DirectedWeightedGraph ans;
        // ****** Add your code here ******
        Graph g = new Graph();
        g.load(json_file);
        ans = g;
        // ********************************
        return ans;
    }

    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return directed graph
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans;
        // ****** Add your code here ******
        Graph g = new Graph();
        g.load(json_file);
        ans = g;
        // ********************************
        return ans;
    }

    /**
     * This static function will run your GUI using the json fime.
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        // ****** Add your code here ******
         new Gframe(alg);
        // ********************************
    }

    public static void main(String[] args) {
        //a simple scan of the json file
        //later the args argument goes to run gui
        String fileName;
        try {
            fileName = args[0];
            runGUI(fileName);
        }catch (Exception e){
            fileName = "data/G1.json";
            runGUI(fileName);
        }

    }
}

