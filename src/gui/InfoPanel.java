package gui;

import Imp.Graph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoPanel extends JPanel implements ActionListener {
    JButton update;
    JLabel nodeSize, edgeSize, MC;
    private GraphPanel graphPanel;
    private Graph graph;

    public InfoPanel(Graph graph,JButton update) {
        this.graph = graph;
        //this.graphPanel = graphPanel;

        MC = new JLabel();
        nodeSize = new JLabel();
        edgeSize = new JLabel();

        MC.setBounds(20, 50, 250, 20);
        nodeSize.setBounds(20, 100, 250, 20);
        edgeSize.setBounds(20, 150, 250, 20);

        this.update = update;
        this.update.setBounds(20, 200, 95, 30);
        this.update.addActionListener(this);

        MC.setText("Mode Count: " + this.graph.getMC());
        nodeSize.setText("Node size :" + this.graph.nodeSize());
        edgeSize.setText("Edge size :" + this.graph.edgeSize());

        add(update);
        add(nodeSize);
        add(edgeSize);
        add(MC);

        this.setSize(400, 400);
        setLayout(null);
        setVisible(true);
    }
private void setUpdate(){
    MC.setText("Mode Count: " + this.graph.getMC());
    nodeSize.setText("Node size :" + this.graph.nodeSize());
    edgeSize.setText("Edge size :" + this.graph.edgeSize());
}
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == update){
            setUpdate();
        }
    }
}
