package gui;

import Imp.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditPanel extends JPanel implements ActionListener {
    private Graph graph;
    private JButton addButton, removeButton, connectButton, disconnectButton,confirmButton;
    private MyDialog dialog;

    public EditPanel(Graph graph, JButton confirm,JButton add,JButton remove,JButton connect,JButton disconnect) {
        this.graph = graph;
        this.addButton = add;
        this.removeButton = remove;
        this.connectButton = connect;
        this.disconnectButton =disconnect;
        this.confirmButton =confirm;

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        this.addButton.setSize(100, 25);
        this.connectButton.setSize(100, 25);
        this.removeButton.setSize(100,25);
        this.disconnectButton.setSize(100,25);

        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        connectButton.addActionListener(this);
        disconnectButton.addActionListener(this);

        //change to box layout
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(addButton,gbc);;

        gbc.gridy = 2;
        this.add(removeButton, gbc);

        gbc.gridy = 4;
        this.add(connectButton, gbc);

        gbc.gridy = 6;
        this.add(disconnectButton, gbc);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == removeButton) {
            dialog = new MyDialog(graph,confirmButton);
            dialog.setRemoveDialog();
        }
        if(e.getSource() == disconnectButton){
            dialog = new MyDialog(graph,confirmButton);
            dialog.setDisconnectDialog();
        }
    }
}
