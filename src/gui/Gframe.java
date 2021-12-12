package gui;

import Imp.Graph;
import api.DirectedWeightedGraph;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Gframe extends JFrame implements ActionListener {
    GraphPanel graphPanel;
    FilePanel filePanel;
    EditPanel editPanel;
    InfoPanel infoPanel;
    private Graph graph;
    JButton save, load, run, add, remove, connect, disconnect,confirm;
    JTextField nameField;
    JComboBox jsonBox, algoBox;

    Gframe(DirectedWeightedGraph graph) {
        super();
        this.graph = (Graph) graph;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(size);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        graphPanel = new GraphPanel(this.graph);


        this.setFilePanel();
        filePanel = new FilePanel(this.graph, save, load, run, nameField, jsonBox, algoBox);
        filePanel.setPreferredSize(new Dimension(600, 50));
        filePanel.setBackground(Color.gray); //change color of background

        this.setEditPanel();
        editPanel = new EditPanel(this.graph,confirm,add, remove, connect, disconnect);
        editPanel.setPreferredSize(new Dimension(150,50));
        editPanel.setBackground(Color.lightGray);

        infoPanel = new InfoPanel(this.graph);
        infoPanel.setPreferredSize(new Dimension(150,50));
        infoPanel.setBackground(Color.lightGray);

        this.add(infoPanel,BorderLayout.EAST);
        this.add(editPanel,BorderLayout.WEST);
        this.add(filePanel, BorderLayout.NORTH);
        this.add(graphPanel, BorderLayout.CENTER);
        //this.pack();
        this.setVisible(true);
    }

    private void setFilePanel() {
        String[] files, algo;
        //save button
        save = new JButton("Save");
        save.addActionListener(this);

        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(100, 25));
        nameField.setFont(new Font("Consolas", Font.PLAIN, 15));
        nameField.setForeground(Color.black);
        nameField.setBackground(Color.white);
        nameField.setCaretColor(Color.white);
        nameField.setText("temp");

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
        confirm = new JButton("Confirm");
        add.addActionListener(this);
        remove.addActionListener(this);
        connect.addActionListener(this);
        disconnect.addActionListener(this);
        confirm.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean modified = e.getSource() == load || e.getSource() == confirm;
        if (modified) {
            repaint();
        }
        if(e.getSource()==run && this.algoBox.getSelectedIndex() == 2)
        {
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
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        g.load("data/G3.json");
        new Gframe(g);
    }
}


