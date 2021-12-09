package gui;


import Imp.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UpperPanel extends JPanel implements ActionListener {
    private Graph graph;
    JButton saveButton,loadButton,runButton;
    JTextField nameField;
    String[] files, algo;
    JComboBox jsonBox, algoBox;
    UpperPanel(Graph graph) {
        this.graph = graph;
        this.setLayout(new FlowLayout());

        //save button
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);

        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(100, 25));
        nameField.setFont(new Font("Consolas", Font.PLAIN, 15));
        nameField.setForeground(Color.black);
        nameField.setBackground(Color.white);
        nameField.setCaretColor(Color.white);
        nameField.setText("temp");

        this.add(saveButton);
        this.add(nameField);

        //Load button
        loadButton = new JButton("Load");
        loadButton.addActionListener(this);
        files = new String[]{"G1.json", "G2.json", "G3.json"};
        jsonBox = new JComboBox(files);
        jsonBox.setSelectedIndex(0);
        this.add(loadButton);
        this.add(jsonBox);

        //Run button
        runButton = new JButton("Run");
        runButton.addActionListener(this);
        algo = new String[]{"Is Connected", "Shortest Path Distance","Center","TSP"};
        algoBox = new JComboBox(algo);
        algoBox.setSelectedIndex(0);
        this.add(runButton);
        this.add(algoBox);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==saveButton){
            this.graph.save("data/"+nameField.getText());
        }
        if(e.getSource()==loadButton){
            this.graph.load("data/"+ jsonBox.getSelectedItem());
            System.out.println("loaded");
            repaint();
        }
        if (e.getSource()==runButton){
            System.out.println("hasn't been implemented yet");
        }
    }
}
