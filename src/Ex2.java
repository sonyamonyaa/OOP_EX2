import Imp.Graph;
import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;

import javax.swing.*;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    static final int WIDTH = 1080;
    static final int HEIGHT = (int) (WIDTH / 1.6);
    static String json;
    public  static   DirectedWeightedGraphAlgorithms alg;
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
        alg = getGrapgAlgo(json_file);
        // ****** Add your code here ******
        json = json_file;
        // ********************************
    }

    public static void main(String[] args) {
        String nodes = JOptionPane.showInputDialog("Please enter \"source,destination\" ");
            //int src = Integer.parseInt(nodes.substring(0,nodes.indexOf(",")-1));
            //int dest = Integer.parseInt(nodes.substring(nodes.indexOf(",")+1));
        System.out.println("src:"+nodes.substring(0,nodes.indexOf(",")));
        System.out.println("dest:"+nodes.substring(nodes.indexOf(",")+1));
    }
}

