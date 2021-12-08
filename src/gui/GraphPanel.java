package gui;

import Imp.Edge;
import Imp.Graph;
import Imp.Node;
import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class GraphPanel extends JPanel {
    private Graph graph;

    public GraphPanel(DirectedWeightedGraph graph) {
        super();
        this.setBackground(new Color(153, 191, 224)); //change color of background
        this.graph = (Graph) graph;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("no name", Font.PLAIN, 20));
        g.setColor(Color.BLACK);
        double x1;
        double y1;
        Node node;
        Edge edge;
        for (Iterator<NodeData> n = this.graph.nodeIter(); n.hasNext(); ) {
            node = (Node) n.next();
            g.setColor(new Color(29, 83, 138));
            x1 = node.getLocation().x();
            y1 = node.getLocation().y();
            g.fillOval((int) x1-5, (int) y1-5, 5, 5);
        }
        for (Iterator<NodeData> n = this.graph.nodeIter(); n.hasNext(); ) {
            node = (Node) n.next();
            for (Iterator<EdgeData> e = this.graph.edgeIter(); e.hasNext(); ) {
                edge = (Edge) e.next();
                double weight = edge.getWeight();
                Node node2 = (Node) graph.getNode(edge.getDest());
                double x2 = node2.getLocation().x();
                double y2 = node2.getLocation().y();
                x1 = node.getLocation().x();
                y1 = node.getLocation().y();
                g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
                g.drawString("w: " + weight, (int) ((x1 + x2) / 2), (int) ((y1 + y2) / 2));
            }
        }
    }
}