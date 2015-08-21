package client;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import javax.swing.LayoutStyle.ComponentPlacement;

public class LoginPanel extends ImagePanel{
	
	//Variable declarations For JPANEL
	private JPanel jPanel;
	public JTextField tf_uname;
	public JPasswordField passFieldPass;
	private JLabel lblUname, lblPass;
	public JButton btnLogin, btnNewUser;
		
	
	
	
	public LoginPanel() {
		setSize(new Dimension(400, 200));
        initComponents();
    }
	
	private void initComponents(){
		
		setMinimumSize(new Dimension(200, 300));
		
		jPanel = new JPanel();		
		jPanel.setOpaque(false);
		//jPanel.setBorder(new TitledBorder(null, "LOGIN", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(255,0,0)));
		
		lblUname = new JLabel("USER NAME");
		lblUname.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUname.setForeground(new Color(255,0,0));
		lblUname.setMinimumSize(new Dimension(60, 15));
		lblUname.setMaximumSize(new Dimension(60, 15));
		
		lblPass = new JLabel("PASSWORD");
		lblPass.setMinimumSize(new Dimension(60, 15));
		lblPass.setMaximumSize(new Dimension(60, 15));
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPass.setForeground(new Color(255,0,0));
		
		tf_uname = new JTextField();
		tf_uname.setColumns(10);
		
		passFieldPass = new JPasswordField();
		passFieldPass.setColumns(10);
		
		btnLogin = new JButton("LOGIN");
		btnLogin.setPreferredSize(new Dimension(70, 30));
		btnLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
		});
		btnLogin.setMinimumSize(new Dimension(70, 30));
		btnLogin.setMaximumSize(new Dimension(70, 30));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		btnNewUser = new JButton("NEW USER");
		btnNewUser.setPreferredSize(new Dimension(120, 30));
		btnNewUser.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnNewUser.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
		});
		
		GroupLayout gl_jPanel = new GroupLayout(jPanel);
		gl_jPanel.setHorizontalGroup(
			gl_jPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPass, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_jPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jPanel.createSequentialGroup()
							.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
							.addComponent(btnNewUser, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
						.addComponent(tf_uname, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
						.addComponent(passFieldPass, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_jPanel.setVerticalGroup(
			gl_jPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jPanel.createSequentialGroup()
					.addGroup(gl_jPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUname, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(tf_uname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(gl_jPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPass, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(passFieldPass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(gl_jPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewUser, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		jPanel.setLayout(gl_jPanel);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jPanel, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(40)
					.addComponent(jPanel, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
					.addContainerGap())
		);
		setLayout(groupLayout);	
	}
}