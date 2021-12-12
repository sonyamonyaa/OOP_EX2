package gui;

import Imp.Graph;
import Imp.Node;
import Imp.geoLocation;
import api.EdgeData;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditPanel extends JPanel implements ActionListener {
    private Graph graph;
    private JButton addButton, removeButton, connectButton, disconnectButton;

    public EditPanel(Graph graph, JButton add, JButton remove, JButton connect, JButton disconnect) {
        this.graph = graph;
        this.addButton = add;
        this.removeButton = remove;
        this.connectButton = connect;
        this.disconnectButton = disconnect;

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        addButton.setBounds(10, 50, 125, 25);
        removeButton.setBounds(10, 100, 125, 25);
        connectButton.setBounds(10, 150, 125, 25);
        disconnectButton.setBounds(10, 200, 125, 25);

        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        connectButton.addActionListener(this);
        disconnectButton.addActionListener(this);


        this.add(addButton);
        this.add(removeButton);
        this.add(connectButton);
        this.add(disconnectButton);
        setLayout(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String s = JOptionPane.showInputDialog("Please enter coordination of the node to be added \"x,y,z\"");
            String[] cs = s.split(",");
            try {
                double x = Double.parseDouble(cs[0]);
                double y = Double.parseDouble(cs[1]);
                double z = Double.parseDouble(cs[2]);
                int key = this.graph.nodeSize();
                NodeData n = new Node(key, new geoLocation(x, y, z));
                this.graph.addNode(n);
                JOptionPane.showMessageDialog(this,
                        "node " + key + "has been added",
                        "Node removal",
                        JOptionPane.INFORMATION_MESSAGE);
                this.getRootPane().repaint();
            } catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }
        }
        if (e.getSource() == connectButton) {
            String s = JOptionPane.showInputDialog("Please enter \"src,dest\" you'd like to connect");
            String[] es = s.split(",");
            String w = JOptionPane.showInputDialog("Please enter weight");
            try {
                int src = Integer.parseInt(es[0]);
                int dest = Integer.parseInt(es[1]);
                double weight = Double.parseDouble(w);
                this.graph.connect(src, dest, weight);
                if (this.graph.getEdge(src, dest) != null)
                    JOptionPane.showMessageDialog(this,
                            "edge (" + src + "," + dest + ") has been made",
                            "Edge Connection",
                            JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(this,
                            "edge (" + src + "," + dest + ")doesn't exist",
                            "Edge connection",
                            JOptionPane.ERROR_MESSAGE);
                this.getRootPane().repaint();
            } catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }
        }
        if (e.getSource() == removeButton) {
            String s = JOptionPane.showInputDialog("Please enter key of the node to remove");
            try {
                int key = Integer.parseInt(s);
                NodeData n = this.graph.removeNode(key);
                if (n != null)
                    JOptionPane.showMessageDialog(this,
                            "node " + key + "has been removed",
                            "Node removal",
                            JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(this,
                            "node " + key + "doesn't exist",
                            "Node removal",
                            JOptionPane.ERROR_MESSAGE);
                this.getRootPane().repaint();
            } catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }
        }
        if (e.getSource() == disconnectButton) {
            String s = JOptionPane.showInputDialog("Please enter \"src,dest\" you'd like to disconnect");
            String[] es = s.split(",");
            try {
                int src = Integer.parseInt(es[0]);
                int dest = Integer.parseInt(es[1]);
                EdgeData edge = this.graph.removeEdge(src, dest);
                if (edge != null)
                    JOptionPane.showMessageDialog(this,
                            "edge (" + src + "," + dest + ") has been removed",
                            "Edge removal",
                            JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(this,
                            "edge (" + src + "," + dest + ")doesn't exist",
                            "Edge removal",
                            JOptionPane.ERROR_MESSAGE);
                this.getRootPane().repaint();
            } catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }
        }
    }
}
