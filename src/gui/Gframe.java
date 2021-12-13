package gui;

import Imp.Graph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Gframe extends JFrame implements ActionListener {
    GraphPanel graphPanel;
    FilePanel filePanel;
    EditPanel editPanel;
    InfoPanel infoPanel;
    private Graph graph;
    JButton save, load, run, add, remove, connect, disconnect, updateButton;
    JComboBox jsonBox, algoBox;

    public Gframe(DirectedWeightedGraphAlgorithms graph) {
        super();
        this.graph = (Graph) graph;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(size);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        graphPanel = new GraphPanel(this.graph);


        this.setFilePanel();
        filePanel = new FilePanel(this.graph, save, load, run, jsonBox, algoBox);
        filePanel.setPreferredSize(new Dimension(600, 50));
        filePanel.setBackground(Color.gray); //change color of background

        this.setEditPanel();
        editPanel = new EditPanel(this.graph,add, remove, connect, disconnect);
        editPanel.setPreferredSize(new Dimension(150,50));
        editPanel.setBackground(Color.lightGray);

        this.setInfoPanel();
        infoPanel = new InfoPanel(this.graph,updateButton);
        infoPanel.setPreferredSize(new Dimension(150,50));
        infoPanel.setBackground(Color.lightGray);

        this.add(infoPanel,BorderLayout.EAST);
        this.add(editPanel,BorderLayout.WEST);
        this.add(filePanel, BorderLayout.NORTH);
        this.add(graphPanel, BorderLayout.CENTER);
        //this.pack();
        this.setVisible(true);
    }

    private void setInfoPanel(){
        updateButton = new JButton("Update");
        updateButton.addActionListener(this);
    }
    private void setFilePanel() {
        String[] files, algo;
        //save button
        save = new JButton("Save");
        save.addActionListener(this);

        //Load button
        load = new JButton("Load");
        load.addActionListener(this);
        files = new String[]{"G1.json", "G2.json", "G3.json"};
        jsonBox = new JComboBox(files);
        jsonBox.setSelectedIndex(0);

        //Run button
        run = new JButton("Run");
        run.addActionListener(this);
        algo = new String[]{"Is Connected", "Shortest Path Distance","Shortest Path", "Center", "TSP"};
        algoBox = new JComboBox(algo);
        algoBox.setSelectedIndex(0);
    }

    private void setEditPanel() {
        add = new JButton("Add Node");
        remove = new JButton("Remove Node");
        connect = new JButton("Add Edge");
        disconnect = new JButton("Remove Edge");
        add.addActionListener(this);
        remove.addActionListener(this);
        connect.addActionListener(this);
        disconnect.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == load || e.getActionCommand() == "Update") {
            repaint();
        }
        if (e.getSource() == run) {
            if (this.algoBox.getSelectedIndex() == 2) {
                String nodes = JOptionPane.showInputDialog("Please enter \"source destination\" ");
                try {
                    int src = Integer.parseInt(nodes.substring(0, nodes.indexOf(" ")));
                    int dest = Integer.parseInt(nodes.substring(nodes.indexOf(" ") + 1));
                    List<NodeData> path = this.graph.shortestPath(src, dest);
                    graphPanel.paintPath(path);
                } catch (NumberFormatException numberFormatException) {
                    numberFormatException.printStackTrace();
                }
            }
            if (this.algoBox.getSelectedIndex() == 4){
                int key;
                NodeData n;
                List<NodeData> cities = new ArrayList<>();
                String nodes = JOptionPane.showInputDialog("Please enter keys \"city1 city2 city3 ...\" (seperated by spaces)");
                String[] keys = nodes.split(" ");
                for (String s : keys) {
                    key = Integer.parseInt(s);
                    n = this.graph.getNode(key);
                    cities.add(n);
                }
                List<NodeData> path = this.graph.tsp(cities);
                graphPanel.paintPath(path);
            }
        }
    }
}


