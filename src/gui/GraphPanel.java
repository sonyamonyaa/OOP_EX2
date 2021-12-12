package gui;

import Imp.Edge;
import Imp.Graph;
import Imp.Node;
import Imp.geoLocation;
import api.DirectedWeightedGraph;
import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

public class GraphPanel extends JPanel {
    private Graph graph;
    private double rangeX, rangeY,minX,minY,maxX,maxY;
    private Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private int width;
    private int height;

    public GraphPanel(DirectedWeightedGraph graph) {
        super();
        this.setBackground(new Color(153, 191, 224)); //change color of background
        this.graph = (Graph) graph;
        this.setPreferredSize(new Dimension((int) size.getWidth()-200, (int) size.getHeight() -200));
    }
    public void paint(Graphics g){
        setScales();
        paintComponents(g);
    }

    public void paintComponents(Graphics g) {
        Graph gg = (Graph) graph.getGraph();
        Iterator<NodeData> itern = gg.nodeIter();
        while (itern.hasNext()) {
            Node node = (Node) itern.next();
            Iterator<EdgeData> itre = gg.edgeIter(node.getKey());
            while (itre.hasNext()) {
                Edge e = (Edge) itre.next();
                g.setColor(Color.gray);
                drawEdge(e, g);
            }
            g.setColor(Color.blue);
            drawNode(node, 5, g);
        }
    }
    private void drawNode(Node n, int r, Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        g.setFont(new Font("name", Font.BOLD,15));
        geoLocation pos = (geoLocation) n.getLocation();
        geoLocation fp =  scale2frame(pos);
        g.fillOval((int)fp.x()-r,(int)fp.y()-r,10,10);
        g.drawString("id " + n.getKey(), (int) fp.x(), (int) fp.y() - 4 * r);
    }

    private void drawEdge(Edge e, Graphics g) {
        Graph gg = (Graph) graph.getGraph();
        geoLocation src = (geoLocation) gg.getNode(e.getSrc()).getLocation();
        geoLocation dest = (geoLocation) gg.getNode(e.getDest()).getLocation();
        geoLocation s = scale2frame(src);
        geoLocation d = scale2frame(dest);
        drawArrowLine(g,(int) s.x(), (int) s.y(), (int) d.x(), (int) d.y(),8,8);
        double w = e.getWeight();
        String ws = Double.toString(w).substring(0, Double.toString(w).indexOf(".")+4);
        g.drawString("w:" + ws, (int) (s.x() + d.x())/2, (int) (s.y() + d.y())/2  - 4);
    }
    private void setScales() {
        minX = minY = Integer.MAX_VALUE;
        maxX = maxY = Integer.MIN_VALUE;
        Iterator<NodeData> itn = graph.nodeIter();
        while (itn.hasNext()) {
            NodeData curr = itn.next();
            GeoLocation g = curr.getLocation();
            if (g.x() < minX) {
                minX = g.x();
            }
            if (g.y() < minY) {
                minY = g.y();
            }
            if (g.x() > maxX) {
                maxX = g.x();
            }
            if (g.y() > maxY) {
                maxY = g.y();
            }
        }
        rangeX = Math.abs(maxX - minX);
        rangeY = Math.abs(maxY - minY);
    }

    private double fromScale(double p, double range, double min) {
            double ans = p - min;
            ans /= range;
            return ans;
        }
        private double toScale(double p, double range, double min) {
            return min + p * range;
        }
    private geoLocation scale2frame(geoLocation p) {
        //graph
        double fx = fromScale(p.x(),rangeX,minX);
        double fy = fromScale(p.y(),rangeY,minY);
        //frame
        fx = toScale(fx,getWidth()*0.8,150);
		fy = toScale(fy,getHeight()*0.8,50);
		geoLocation ans = new geoLocation(fx,fy,0);
		return ans;
	}
    public void paintPath(List<NodeData> path){
        Node n0,n1;
        Edge e;
        int k0,k1;
        Graphics g = this.getGraphics();
        for(int i = 1; i <path.size();i++){
            n0 = (Node) path.get(i-1);
            g.setColor(Color.green);
            drawNode(n0,5,g);
            n1 = (Node) path.get(i);
            k0 = n0.getKey();
            k1 = n1.getKey();
            e = (Edge) this.graph.getEdge(k0,k1);
            drawEdge(e,g);
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


