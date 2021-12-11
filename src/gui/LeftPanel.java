package gui;

import Imp.Graph;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel {
    private Graph graph;
    private JButton addButton,removeButton,connectButton,disconnectButton;
   public LeftPanel(Graph graph,JButton addButton,JButton removeButton,JButton connectButton,JButton disconnectButton){
       this.addButton = addButton;
       this.removeButton = removeButton;
       this.connectButton = connectButton;
       this.disconnectButton = disconnectButton;

       this.setLayout(new FlowLayout());

       this.add(addButton);
       this.add(removeButton);
       this.add(connectButton);
       this.add(disconnectButton);
   }
}
