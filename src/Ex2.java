import Imp.Graph;
import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
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
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        // ****** Add your code here ******
        //paintComponent(Graphics g) {
        //            super.paintComponent(g);
        //            Point2D prev=null;
        //            for (Point2D p:points) {
        //                g.setColor(new Color(234, 26, 171));
        //                g.fillOval((int)p.getX()-10,(int)p.getY()-10,20,20);
        //                if(prev!=null){
        //                    Double dist = p.distance(prev);
        //                    String distS = dist.toString().substring(0,dist.toString().indexOf(".")+2);
        //                    g.drawLine((int)p.getX(),(int)p.getY(),(int)prev.getX(),(int)prev.getY());
        //                    g.drawString(distS, (int)((p.getX()+prev.getX())/2),(int)((p.getY()+prev.getY())/2));
        //                }
        //                prev=p;
        // ********************************
    }
}