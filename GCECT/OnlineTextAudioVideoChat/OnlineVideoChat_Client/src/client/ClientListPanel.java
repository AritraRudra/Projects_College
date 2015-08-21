package client;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;
import javax.swing.border.TitledBorder;


/**
 *
 * @author Aritra
 */
public class ClientListPanel extends ImagePanel {
	
	public DefaultListModel list_model;
    public DefaultListSelectionModel dlsm;
    public JList list_online_clients;

    /** Creates new form ClientListPanel */
    public ClientListPanel() {
        PrepareGUI();
        createListModel();
    }

    void createListModel()	{
    	//this.imageFile="loginpanel.jpg";
        list_model=new DefaultListModel();
        list_online_clients = new JList(list_model);
        list_online_clients.setOpaque(false);
        list_online_clients.setVisibleRowCount(10);
        list_online_clients.setBorder(BorderFactory.createTitledBorder("Buddy List"));

        dlsm=new DefaultListSelectionModel();
        dlsm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list_online_clients.setSelectionModel(dlsm);
        this.setLayout(new BorderLayout());
        this.add(list_online_clients,BorderLayout.CENTER);
    }    
    
    private void PrepareGUI() {
    	
		//imgPanel.setBorder(new TitledBorder(null, "LOGIN", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(255,0,0)));
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 182, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 339, Short.MAX_VALUE)
        );
    }
}