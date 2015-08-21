package server;

import java.awt.*;

import javax.swing.*;

import java.util.HashMap;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Aritra
 */

public class ServerGUI extends JFrame {
	//private static final long serialVersionUID = -2094183082091063123L;
	ServerManager serverManager;
    ServerStatusListener statusListener;
    ClientListener clientListener;
    static HashMap<String,String> hm = new HashMap<String,String>();
	
    //JFrame variables
	private ImagePanel jPanel1, jPanel2, list_panel;//, jPanel4;
	private JScrollPane jScrollPane1;
	public JTextArea ta_monitor_clients;
	public JButton btn_start, btn_stop;
	public JLabel lb_status;
	private JMenuBar jMenuBar;
	private JMenu jMenuFile,jMenuAbout;
	private JMenuItem mi_about_us;
	public javax.swing.DefaultListModel list_model;
    public javax.swing.DefaultListSelectionModel dlsm;
    public javax.swing.JList list_online_clients;
    Image icon;
	
	public ServerGUI(ServerManager getManager){
		setMinimumSize(new Dimension(640, 480));
		
        serverManager=getManager;
        statusListener=new MyStatusListener();
        clientListener=new MyClientListener();
        
        //setBackground(SystemColor.desktop);		
		prepareGUI();
		createListModel();
	}
	
	void createListModel(){
        list_model=new DefaultListModel();
        list_online_clients = new JList(list_model);
        list_online_clients.setBorder(BorderFactory.createTitledBorder("ONLINE CLIENTS"));

        dlsm=new DefaultListSelectionModel();
        dlsm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_online_clients.setSelectionModel(dlsm);
        list_panel.setLayout(new BorderLayout());
        list_panel.add(list_online_clients);
    }
	
	
	private class ImagePanel extends JPanel{
		/*the default image to use*/
		String imageFile = "res/mactut.png";

		public ImagePanel(){
			super();
		}
		public ImagePanel(String image)	{
			super();
			this.imageFile = image;
		}		

		public void paintComponent(Graphics g){
			/*create image icon to get image*/
			//ImageIcon imageicon = new ImageIcon(imageFile);
			ImageIcon imageicon = new ImageIcon(getClass().getResource(imageFile));
			Image image = imageicon.getImage();

			/*Draw image on the panel*/
			super.paintComponent(g);

			if (image != null)
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}
	}
	
	
	private void prepareGUI(){
		
		jPanel1 = new ImagePanel("res/3.gif");
        jPanel2 = new ImagePanel();
        jScrollPane1 = new JScrollPane();
        ta_monitor_clients = new JTextArea();
        list_panel = new ImagePanel();
        //jPanel4 = new JPanel();
        btn_start = new JButton();
        btn_start.setPreferredSize(new Dimension(50, 10));
        btn_stop = new JButton();
        btn_stop.setPreferredSize(new Dimension(50, 10));
        lb_status = new JLabel();
        jMenuBar = new JMenuBar();
        jMenuFile = new JMenu();
        jMenuAbout = new JMenu();
        mi_about_us=new JMenuItem();
        mi_about_us.setHorizontalTextPosition(SwingConstants.LEFT);
        mi_about_us.setBorder(null);
        mi_about_us.setIconTextGap(2);

        setBackground(SystemColor.desktop);	
        setSize(new Dimension(640, 480));
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FACEUP SERVER");
        
        Image icon = new ImageIcon(getClass().getResource("res/icon2.png")).getImage();
        //getClass().getResource("/icon40.gif");
        //setIconImage(Toolkit.getDefaultToolkit().getImage("Icon2.png"));
        setIconImage(icon);
        
        //jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        ta_monitor_clients.setColumns(20);
        ta_monitor_clients.setEditable(false);
        ta_monitor_clients.setRows(5);
        ta_monitor_clients.setBorder(javax.swing.BorderFactory.createTitledBorder("MONITOR CLIENTS"));
        jScrollPane1.setViewportView(ta_monitor_clients);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout list_panelLayout = new javax.swing.GroupLayout(list_panel);
        list_panel.setLayout(list_panelLayout);
        list_panelLayout.setHorizontalGroup(
            list_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 118, Short.MAX_VALUE)
        );
        list_panelLayout.setVerticalGroup(
            list_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 233, Short.MAX_VALUE)
        );

        btn_start.setText("Start");
        btn_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_startActionPerformed(evt);
            }
        });

        btn_stop.setText("Stop");
        btn_stop.setEnabled(false);
        btn_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_stopActionPerformed(evt);
            }
        });

        /*
        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_start)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_stop)
                .addContainerGap(259, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_start)
                    .addComponent(btn_stop))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        */

        lb_status.setFont(new java.awt.Font("Tahoma", Font.PLAIN, 20));
        lb_status.setForeground(Color.RED);
        lb_status.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_status.setText("Server is Stopped");
        //jPanel.setBorder(new TitledBorder(null, "LOGIN", TitledBorder.LEADING, TitledBorder.TOP, null,Color.RED));
        lb_status.setBorder(javax.swing.BorderFactory.createTitledBorder("Server Status"));
        lb_status.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);        

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
        			.addComponent(list_panel, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE))
        		.addGroup(Alignment.CENTER, jPanel1Layout.createSequentialGroup()        			
        			.addComponent(btn_start)
        			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        			.addComponent(btn_stop))        			
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addComponent(lb_status, GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
        			.addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(jPanel2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
        				.addComponent(list_panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btn_start)
        				.addComponent(btn_stop))
        			.addComponent(lb_status, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        			.addContainerGap())
        );
        jPanel1.setLayout(jPanel1Layout);

        //next two lines commented during 7th sem presentation
        //jMenuFile.setText("File");
        //jMenuBar.add(jMenuFile);

        jMenuAbout.setText("About");
        jMenuBar.add(jMenuAbout);
        
        jMenuAbout.add(mi_about_us);
        mi_about_us.setText("ABOUT US");
        //mi_about_us.setEnabled(true);
        setJMenuBar(jMenuBar);
        
        mi_about_us.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		 jMenuAboutFACEUPActionPerformed(evt);
        	}
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();	
	}
	
    private void btn_startActionPerformed(java.awt.event.ActionEvent evt) {	        
    	btn_stop.setEnabled(true);
	    btn_start.setEnabled(false);
	    serverManager.startServer(statusListener,clientListener);
	}

	private void btn_stopActionPerformed(java.awt.event.ActionEvent evt) {
		serverManager.stopServer(statusListener);
	    btn_stop.setEnabled(false);
	    btn_start.setEnabled(true);
	}
	
	private void jMenuAboutFACEUPActionPerformed(ActionEvent evt) {
		 JOptionPane.showMessageDialog(this,"Made By The FACEUP TEAM");		 
		 AboutFACEUP aboutUS=new AboutFACEUP();
		 aboutUS.setVisible(true);
	 }
	
	class MyStatusListener implements ServerStatusListener{
        public void status(String message){
            lb_status.setText(message);
        }
    }

    class MyClientListener implements ClientListener{
    	
        public void signIn(String userName){
            list_model.addElement((Object)userName);
        }
        public void signOut(String userName){
            list_model.removeElement((Object)userName);
        }
        public void clientStatus(String status){
            ta_monitor_clients.append(status+"\n");
        }
        public void mapped(String nam,String ip){
          if(hm.get(nam) == null){      
            hm.put(nam,ip);
            //System.out.print("In ServerGUI -> MyClientListener -> mapped");
            //System.out.println("User : "+nam+" from : "+ip);
          }     
        }
    }
}