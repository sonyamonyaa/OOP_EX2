package gui;

import Imp.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyDialog extends JDialog implements ActionListener {
    private Graph graph;
    private JButton confirm;
    private JSpinner srcSpinner, destSpinner;
//    private int src, dest;
//    private double weight;
    public MyDialog(Graph graph,JButton confirm){
        super();
        this.graph = graph;
        this.setLocationRelativeTo(null);
        this.setSize(new Dimension (100,200));
    }

    public void setRemoveDialog() {
        this.setTitle("Remove Node");
        this.setLayout(new FlowLayout());
        SpinnerModel value = new SpinnerNumberModel(0, 0, graph.nodeSize() - 1, 1);
        srcSpinner = new JSpinner(value);
        JLabel label = new JLabel();
        label.setText("Key:");
        label.setAlignmentX(CENTER_ALIGNMENT);
        this.add(label);
        this.add(srcSpinner);
        this.confirm = new JButton("Remove Node");
        this.confirm.addActionListener(this);
        this.add(confirm);
        this.setVisible(true);
    }

    public void setDisconnectDialog() {
        this.setTitle("Remove Edge");
        this.setLayout(new FlowLayout());
        SpinnerModel value = new SpinnerNumberModel(0, 0, graph.nodeSize() - 1, 1);
        this.srcSpinner = new JSpinner(value);
        this.destSpinner = new JSpinner(value);
        JLabel src = new JLabel("Source:");
        JLabel dest = new JLabel("Destination:");
        this.add(src);
        this.add(srcSpinner);
        this.add(dest);
        this.add(destSpinner);
        this.confirm = new JButton("Remove Edge");
        this.confirm.addActionListener(this);
        this.add(confirm);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "Remove Node"){
            int id = (int) this.srcSpinner.getValue();
            this.graph.removeNode(id);
            JOptionPane.showMessageDialog(this, "Done!", "Node removal", JOptionPane.INFORMATION_MESSAGE);
            repaint();
        }
        if(e.getActionCommand() == "Remove Edge"){
            int src = (int) this.srcSpinner.getValue();
            int dest = (int) this.destSpinner.getValue();
            this.graph.removeEdge(src,dest);
            JOptionPane.showMessageDialog(this, "Done!", "Edge removal", JOptionPane.INFORMATION_MESSAGE);
            repaint();
        }
    }
}
