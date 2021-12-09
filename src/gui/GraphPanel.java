package gui;

import Imp.Graph;
import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class GraphPanel extends JPanel {
    private Graph graph;
    //private double scaleX, scaleY;
    //private Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private int width;
    private int height;

    public GraphPanel(DirectedWeightedGraph graph) {
        super();
        this.setBackground(new Color(153, 191, 224)); //change color of background
        this.graph = (Graph) graph;
        this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
    }
//    private void setScales(){
//        double minX,minY,maxX,maxY;
//        minX = minY = Integer.MAX_VALUE;
//        maxX = maxY = Integer.MIN_VALUE;
//        Iterator<NodeData> itn = graph.nodeIter();
//        while (itn.hasNext()) {
//            NodeData curr = itn.next();
//            GeoLocation g = curr.getLocation();
//            if(g.x() < minX) {minX = g.x();}
//            if(g.y() < minY) {minY = g.y();}
//            if(g.x() > maxX) {maxX = g.x();}
//            if(g.y() > maxY) {maxY = g.y();}
//        }
//        scaleX = Math.abs(maxX - minX);
//        scaleY = Math.abs(maxY-minY);
//    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        width = getWidth();
        height = getHeight();
        g.setFont(new Font("no name", Font.BOLD, 10));
        Iterator<EdgeData> ite = graph.edgeIter();
        while (ite.hasNext()) {
            EdgeData e = ite.next();
            g.setColor(Color.BLUE);
            double srcX = (graph.getNode(e.getSrc()).getLocation().x() * 20000) % 1000;
            double srcY = (graph.getNode(e.getSrc()).getLocation().y() * 20000) % 1000;
            double destX = (graph.getNode(e.getDest()).getLocation().x() * 10000) % 1000;
            double destY = (graph.getNode(e.getDest()).getLocation().y() * 10000) % 1000;
            drawArrowLine(g,(int) srcX, (int) srcY, (int) destX, (int) destY,8,8);
            Double weight = e.getWeight();
            String wS = weight.toString().substring(0,weight.toString().indexOf(".")+4);
            g.setColor(Color.black);
            g.drawString("w:" + wS, (int) (srcX + destX) / 2, (int) (srcY+ destY)/2) ;
        }
        Iterator<NodeData> itn = graph.nodeIter();
        while (itn.hasNext()) {
            NodeData curr = itn.next();
            int x = (int) (curr.getLocation().x() * 20000) % 1000;
            int y = (int) (curr.getLocation().y() * 10000) % 1000;
            g.setColor(Color.DARK_GRAY);
            g.fillOval(x - 5, y - 5, 10, 10);
            g.setColor(Color.black);
            g.drawString("id " + curr.getKey(), x + 5, y + 5);
        }
    }

    /*
     * https://stackoverflow.com/a/27461352
     *
     * Draw an arrow line between two points.
     *
     * @param g  the graphics component.
     * @param x1 x-position of first point.
     * @param y1 y-position of first point.
     * @param x2 x-position of second point.
     * @param y2 y-position of second point.
     * @param d  the width of the arrow.
     * @param h  the height of the arrow.
     */
    private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx * dx + dy * dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm * cos - ym * sin + x1;
        ym = xm * sin + ym * cos + y1;
        xm = x;

        x = xn * cos - yn * sin + x1;
        yn = xn * sin + yn * cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};

        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints, ypoints, 3);
    }
}


