package gui;


import Imp.Graph;
import Imp.Node;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class FilePanel extends JPanel implements ActionListener {
    private Graph graph;
    private JButton saveButton, loadButton, runButton;
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
        if (e.getSource() == saveButton) {
            //file chooser
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a json file to save");
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Json (.json)", "json"));
            fileChooser.setFileFilter(fileChooser.getChoosableFileFilters()[0]);
            fileChooser.setSelectedFile(new File("Json.json"));
            int userSelection = fileChooser.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                this.graph.save(fileToSave.getAbsolutePath());
            }
        }
        if (e.getSource() == loadButton) {
            this.graph.load("data/" + jsonBox.getSelectedItem());
        }
        if (e.getSource() == runButton) {
            //"Is Connected", "Shortest Path Distance", "Center", "TSP"
            int alg = algoBox.getSelectedIndex();
            switch (alg) {
                case 0://is connected
                    JOptionPane.showMessageDialog(this, this.graph.isConnected(), "Is Connected?", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 1://shortest path dist
                    String nodes = JOptionPane.showInputDialog("Please enter \"source destination\" ");
                    try {
                        int src = Integer.parseInt(nodes.substring(0, nodes.indexOf(" ")));
                        int dest = Integer.parseInt(nodes.substring(nodes.indexOf(" ") + 1));
                        double dist = this.graph.shortestPathDist(src, dest);
                        JOptionPane.showMessageDialog(this, src + " -> " + dest + "\nworth " + dist, "Shortest Path distance", JOptionPane.INFORMATION_MESSAGE);
                    } catch (NumberFormatException numberFormatException) {
                        numberFormatException.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        Node n = (Node) this.graph.center();
                        int id = n.getKey();
                        JOptionPane.showMessageDialog(this, "center id:" + id, "Center", JOptionPane.INFORMATION_MESSAGE);
                    } catch (HeadlessException headlessException) {
                        headlessException.printStackTrace();
                    }
            }
        }
    }
}
