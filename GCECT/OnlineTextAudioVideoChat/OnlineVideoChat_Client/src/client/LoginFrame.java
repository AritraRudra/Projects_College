package client;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.StringTokenizer;

import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import static client.ClientConstants.*;


public class LoginFrame extends JFrame{
	//private static final long serialVersionUID = -3309702619736358249L;
	//For	JFRAME
	private JMenu jMenu1;
    //private JMenu jMenu2;
    private JMenuBar jMenuBar1;
    public JLabel lb_status;
    private JMenuItem mi_sign_in;
    private JMenuItem mi_sign_out;    
    private JButton btnHelp;    
    private JPanel myPanel;    
    
    private AboutFACEUP aboutUS;	
	LoginPanel loginP;	
	ClientListPanel buddyList;
	ClientManager clientManager;
	ClientStatusListener clientStatus;
	ClientListListener clientListListener;
	ClientWindowListener clientWindowListener;
	String userName;
	int chatboxFrameNo=0;
	ChatBoxFrame [] chatboxFrames;	
	
	public LoginFrame(ClientManager getClientManager) {					
		clientStatus=new myClientStatus();
        clientListListener=new myClientListListener();
        clientWindowListener=new MyClientWindowListener();        
        chatboxFrames=new ChatBoxFrame[10000];
        prepareGUI();
        clientManager=getClientManager;
        myPanel.setLayout(new BorderLayout());
        addLogInPanel();
	}
	
	void addLogInPanel(){
        loginP=new LoginPanel();
        myPanel.add(loginP,BorderLayout.CENTER);
        setVisible(true);
        loginP.btnLogin.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                btn_signinActionPerformed();
            }
        });
        /*loginP=new LoginPanel();
        myPanel.add(loginP,BorderLayout.CENTER);
        setVisible(true);*/
        
        loginP.btnNewUser.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                btn_NewUserActionPerformed();
            }
        });
        
    }
	
	private void btn_signinActionPerformed() {
		if(!loginP.tf_uname.getText().isEmpty() && !loginP.passFieldPass.getText().isEmpty()) {
			 
			userName=loginP.tf_uname.getText();
			String passwd=loginP.passFieldPass.getText();
			
			clientManager.connect(clientStatus);
			MessageBean loginMsg = new MessageBean(LOGIN, passwd, userName); //ADDED LATER
	        clientManager.sendMessage(loginMsg);
	        
	        
	        myPanel.remove(loginP);	        
	        addBuddyList();
	        clientManager.receiveMessage(clientListListener, clientWindowListener);
	        
	        setTitle("FACEUP-Messenger ("+userName+")");
	        /*MessageBean loginMsg = new MessageBean(LOGIN, passwd, userName);
	        //System.out.println(loginMsg);
	        clientManager.sendMessage(loginMsg);
	        clientManager.receiveMessage(clientListListener, clientWindowListener);*/ 
	        
	        mi_sign_in.setEnabled(false);
	        mi_sign_out.setEnabled(true);
		 }else
	        JOptionPane.showMessageDialog(this,"Please enter your User Name and Password Correctly");
	}
	private void btn_signoutActionPerformed(ActionEvent evt) {
	        myPanel.remove(buddyList);
	        MessageBean logoutMsg = new MessageBean(DISCONNECT, "SERVER", userName);
	        clientManager.sendMessage(logoutMsg);
	        clientManager.flagoutput=true;
	        addLogInPanel();
	        setTitle("FACEUP");	//Added Later  kaj hochhe ki???
	        mi_sign_out.setEnabled(false);
	        mi_sign_in.setEnabled(true);
	        clientManager.disconnect(clientStatus);
	}
	 
	private void btn_NewUserActionPerformed(){
		//if(Desktop.isDesktopSupported()){
		try {
			ServAdd servadd=new ServAdd();
     	   	String serv_add=servadd.getAddress();
			Desktop.getDesktop().browse(new URI("http://"+serv_add+"/signup.html")); //or signup.jsp
			//Desktop.getDesktop().browse(new URI("http://mrdoob.com/projects/google-gravity/"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		
		}
	}
	void addBuddyList(){
		buddyList=new ClientListPanel();
	    myPanel.add(buddyList,BorderLayout.CENTER);
	    setVisible(true);

	    buddyList.list_online_clients.addMouseListener(new MouseAdapter(){
	    	public void mouseClicked(MouseEvent me){
	    		 if(me.getClickCount()==2){
	    			 String to=(String)buddyList.list_online_clients.getSelectedValue();

	    			 boolean isWindowOpen=false;	//Initially buddy window is not open
	    			 for(int i=0;i<chatboxFrameNo;i++){
	    				 if(chatboxFrames[i].to.equals(to)){
	    					 isWindowOpen=true;
	    					 break;
	    				 }
	    			 }
	    			 if(!isWindowOpen){
	    				 chatboxFrames[chatboxFrameNo]=new ChatBoxFrame(to,userName,clientManager);
	    				 chatboxFrames[chatboxFrameNo].setVisible(true);
	    				 chatboxFrameNo++;
	    			 }
	    		 }
	    	 }
	     });	// End of mouse event(mouse double click)	
	    }
	 
	 class myClientStatus implements ClientStatusListener{
		 public void loginStatus(String status){
			 lb_status.setText(status);
	     }
	 }

	 class myClientListListener implements ClientListListener{
		 public void addToList(String usersName){
			 if(!usersName.equalsIgnoreCase(userName)){
				 buddyList.list_model.addElement(usersName);
			 }
        }
		public void removeFromList(String userName){
			buddyList.list_model.removeElement(userName);	//Removes this username from client list (buddylist)
		}
	 }
	 
	 class MyClientWindowListener implements ClientWindowListener{
		 
		 public void openWindow(MessageBean message){
			 boolean isWindowOpen=false;
	         int openWindowNo=0;
	         
	         String header=message.getHeader();
	         String to=message.getReceiver();
	         String from=message.getSender();
	         for(int i=0;i<chatboxFrameNo;i++){
	        	 if(chatboxFrames[i].to.equals(from)){
	        		 isWindowOpen=true;
	                 openWindowNo=i;
	                 break;
	             }
	         }
	         if(isWindowOpen){	        	 
	        	 //TODO:add code for audio ack & video ack
	        	 //System.out.println("Inside LOGINFRAME "+header+77);
	        	 if(header.equals(GETIPS)){
	        		 //header , rec_ip(me), sen_ip(sender)
	        		 StringTokenizer ipTokens=new StringTokenizer(message.getData());
	        		 chatboxFrames[openWindowNo].myIP=ipTokens.nextToken();//message.getData();
	        		 chatboxFrames[openWindowNo].sen_ip=ipTokens.nextToken();//from;
	        		 //break;
	        	 }
	        	 //use switch for speed
	        	 //Audio request manipulation
	        	 else if(header.equals(WANTSTOTALK)){	//else ki lagbe ekane
	        		 //System.out.println("Inside LOGINFRAME "+WANTSTOTALK);
	        		 //String sen_ip=message.getData();	        		 
	        		 chatboxFrames[openWindowNo].showAudioOption(header,from+" Wants To Talk With You");
	        	 }else if(header.equals(REJECTTALK)){
	        		 chatboxFrames[openWindowNo].showAudioOption(header,from+" Doesn't Want To Talk With You");
	        	 }else if(header.equals(STOPTALK)){
	        		 //TODO:add code when stopped talking
	        		 chatboxFrames[openWindowNo].showAudioOption(header,from+" has Stopped Talking");
	        	 }else if(header.equals(ACCEPTTALK)){
	        		 //TODO:add code when accepted
	        		 chatboxFrames[openWindowNo].showAudioOption(header,from+" has Accepted Your Request");
	        	 }
	        	 //VIDEO request manipulations
	        	 else if(header.equals(WANTSTOWATCH)){	//else if hobe naki sudhu if
	        		 //System.out.println("Inside LOGINFRAME "+WANTSTOTALK);
	        		 //StringTokenizer ipTokens=new StringTokenizer(message.getData());
	        		 //String sen_ip=ipTokens.nextToken();
	        		 //String my_ip=ipTokens.nextToken();
	        		 chatboxFrames[openWindowNo].showVideoOption(header,from+" Wants Video Chat With You ");
	        	 }else if(header.equals(REJECTVIDEO)){
	        		 chatboxFrames[openWindowNo].showVideoOption(header,from+" Doesn't Want To Have Video Chat With You");
	        	 }else if(header.equals(STOPVIDEO)){
	        		 //TODO:add code when stopped talking
	        		 chatboxFrames[openWindowNo].showVideoOption(header,from+" has Stopped Video Chat");
	        	 }else if(header.equals(ACCEPTVIDEO)){
	        		 //TODO:add code when accepted
	        		 chatboxFrames[openWindowNo].showVideoOption(header,from+" has Accepted Your Request");
	        	 }
	        	 else
	        		 chatboxFrames[openWindowNo].ta_view_message.append(from+" : "+(String)message.getData()+"\n");
	         }
	         else{
	             chatboxFrames[chatboxFrameNo]=new ChatBoxFrame(from,userName,clientManager);
	             chatboxFrames[chatboxFrameNo].setVisible(true);
	             chatboxFrames[chatboxFrameNo].ta_view_message.append(from+" : "+(String)message.getData()+"\n");
	             chatboxFrameNo++;
	         }
		 }
		 
		 public void closeWindow(String getMessage){
			 myPanel.remove(buddyList);
	         addLogInPanel();
	         mi_sign_out.setEnabled(false);
	         mi_sign_in.setEnabled(true);
	         lb_status.setText(getMessage);
	         //setTitle("FACEUP");	//Added Later kajer noe
	         //clientManager.disconnect(clientStatus);	//Added Later
	     }
		 
		 public void fileStatus(String filesStatus){
			 lb_status.setText(filesStatus);
	     }
	 }
	 
	 private void btn_aboutFACEUPActionPerformed(ActionEvent evt) {
		 JOptionPane.showMessageDialog(this,"Made By ARITRA","ABOUT FACEUP",JOptionPane.INFORMATION_MESSAGE);
		 //JInternalFrame
		 aboutUS=new AboutFACEUP();
		 aboutUS.setVisible(true);
	 }
	
    private void prepareGUI(){
    	    	
    	setContentPane(new ImagePanel("res/6.jpg"));	//current panel bg
    	setIconImage(new ImageIcon(getClass().getResource("res/icon.png")).getImage());    	
    	//Image imageicon = new ImageIcon(getClass().getResource("res/icon.png")).getImage();
    	//setIconImage(imageicon);
    	
    	myPanel=new JPanel();    	
    	lb_status = new JLabel();
    	lb_status.setForeground(Color.RED);
    	lb_status.setFont(new Font("Tahoma", Font.BOLD, 15));    	
        jMenuBar1 = new JMenuBar();
        jMenu1 = new JMenu();
        mi_sign_in = new JMenuItem();
        mi_sign_out = new JMenuItem();
        //jMenu2 = new JMenu();        
    	
        setBackground(SystemColor.desktop);
        //getContentPane().setBackground(new Color(224, 255, 255));
		setMinimumSize(new Dimension(350, 400));
		setSize(new Dimension(350, 400));
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FACEUP");
                
        lb_status.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_status.setText("You are not connected to the FACEUP Server");

        jMenu1.setText("User");

        mi_sign_in.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        mi_sign_in.setText("Sign In");
        jMenu1.add(mi_sign_in);

        mi_sign_out.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        mi_sign_out.setText("Sign Out");
        mi_sign_out.setEnabled(false);
        mi_sign_out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_signoutActionPerformed(evt);
            }
        });
        jMenu1.add(mi_sign_out);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);
        
        btnHelp = new JButton("About FACEUP");
        btnHelp.setMinimumSize(new Dimension(53, 25));
        btnHelp.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnHelp.setBorderPainted(false);
        btnHelp.setFocusPainted(false);
        btnHelp.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnHelp.setHorizontalAlignment(SwingConstants.LEADING);
        btnHelp.setMargin(new Insets(0, 0, 0, 0));
        btnHelp.setOpaque(false);
        btnHelp.setPreferredSize(new Dimension(50, 23));
        btnHelp.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		btn_aboutFACEUPActionPerformed(evt);
        	}
		});
        jMenuBar1.add(btnHelp);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(lb_status, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        		.addComponent(myPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(myPanel, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
        			.addGap(46)
        			.addComponent(lb_status, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addGap(36))
        );
        getContentPane().setLayout(layout);        

        pack();
	}
}