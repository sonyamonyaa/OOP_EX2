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

        addButton.setBounds(10, 50, 125, 25);
        removeButton.setBounds(10, 100, 125, 25);
        connectButton.setBounds(10, 150, 125, 25);
        disconnectButton.setBounds(10,200,125,25);

        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        connectButton.addActionListener(this);
        disconnectButton.addActionListener(this);



        this.add(addButton);;
        this.add(removeButton);
        this.add(connectButton);
        this.add(disconnectButton);
        setLayout(null);

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
