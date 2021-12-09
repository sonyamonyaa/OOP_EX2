package gui;

import Imp.Graph;
import api.DirectedWeightedGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gframe extends JFrame implements ActionListener {
    GraphPanel graphPanel;
    UpperPanel filePanel;
    private Graph graph;

    Gframe(DirectedWeightedGraph graph) {
        super();
        this.graph = (Graph) graph;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(size);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        graphPanel = new GraphPanel(this.graph);
        filePanel = new UpperPanel(this.graph);
        filePanel.setPreferredSize(new Dimension(600,50));
//        graphPanel.setPreferredSize(new Dimension(600,600));
        this.add(filePanel,BorderLayout.NORTH);
        this.add(graphPanel,BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()=="Load"){
            repaint();
        }

    }

    public static void main(String[] args) {
        Graph g = new Graph();
        g.load("data/G3.json");
        new Gframe(g);
    }
}


