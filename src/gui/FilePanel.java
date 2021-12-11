package gui;


import Imp.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FilePanel extends JPanel implements ActionListener {
    private Graph graph;
    private JButton saveButton,loadButton,runButton;
    private JTextField nameField;
    private JComboBox jsonBox, algoBox;
    FilePanel(Graph graph, JButton saveButton, JButton loadButton, JButton runButton, JTextField nameField, JComboBox jsonBox, JComboBox algoBox) {
        this.graph = graph;
        this.saveButton = saveButton;
        this.loadButton = loadButton;
        this.runButton = runButton;
        this.nameField = nameField;
        this.algoBox = algoBox;
        this.jsonBox = jsonBox;

        this.setLayout(new FlowLayout());
        this.saveButton.addActionListener(this);
        this.loadButton.addActionListener(this);
        this.runButton.addActionListener(this);

        this.add(this.saveButton);
        this.add(this.nameField);
        this.add(this.loadButton);
        this.add(this.jsonBox);
        this.add(this.runButton);
        this.add(this.algoBox);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==saveButton){
            this.graph.save("data/"+nameField.getText());
            //file chooser
        }
        if(e.getSource()==loadButton){
            this.graph.load("data/"+ jsonBox.getSelectedItem());
        }
        if (e.getSource()==runButton){
            System.out.println("hasn't been implemented yet");
        }
    }
}
