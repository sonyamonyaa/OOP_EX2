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
    JButton save, load, run;
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

        this.upperPanelSetUp();
        filePanel = new UpperPanel(this.graph,save,load,run,nameField,jsonBox,algoBox);
        filePanel.setPreferredSize(new Dimension(600,50));

        this.add(filePanel,BorderLayout.NORTH);
        this.add(graphPanel,BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }

    private void upperPanelSetUp(){
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
            algo = new String[]{"Is Connected", "Shortest Path Distance","Center","TSP"};
            algoBox = new JComboBox(algo);
            algoBox.setSelectedIndex(0);
        }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == load){
            repaint();
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        g.load("data/G3.json");
        new Gframe(g);
    }
}


