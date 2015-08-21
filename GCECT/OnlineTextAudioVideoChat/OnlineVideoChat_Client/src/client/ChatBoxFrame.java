package client;

import java.awt.*;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import audio.AudioChatManager;
import video.VideoChatManager;

import static client.ClientConstants.*;

/**
 *  @author Aritra
 *
 */

public class ChatBoxFrame extends JFrame{
	//private static final long serialVersionUID = 6576920212920030511L;
	//For JFRAME
	private JPanel jPanel1, jPanel2;//,jPanel3;
	private JScrollPane jScrollPane1, jScrollPane2;
	public JTextArea ta_view_message;
	private JTextPane tp_write_message;
	private JButton btn_send, btn_audio, btn_video;
	//private	JTextField tf_file;	//lagbe na
		
	ClientManager clientManager;
	String from,to;
	String sen_ip,myIP;
	//ObjectInputStream input;//lagbe ki??	
	
	//Voice chat var
	private boolean voiceClientOrServer;//=true; 
	AudioChatManager audioChatManager=new AudioChatManager();
	
	//Video Chat Variable
	VideoChatManager videoChatManager=new VideoChatManager();// undefined default constructor	
	
	public ChatBoxFrame(String getto,String getfrom,ClientManager getClientManager){
		setPreferredSize(new Dimension(600, 550));
		getContentPane().setBackground(SystemColor.menu);	
	    to=getto;
	    from=getfrom;
	    prepareGUI();
	    setTitle(to);
	    clientManager=getClientManager;
	}
	
	/*public ChatBoxFrame(String getto,String getfrom){
		from=getfrom;
	    to=getto;
	    prepareGUI();
	    setTitle(to);	
	}*/
	
	private void prepareGUI() {		
		jPanel1 = new ImagePanel("res/20.jpg");
		setIconImage(new ImageIcon(getClass().getResource("res/icon.png")).getImage());
    	
		jPanel1.setOpaque(false);
		jPanel1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jPanel1.setPreferredSize(new Dimension(0, 0));
        jScrollPane1 = new JScrollPane();
        jScrollPane1.setOpaque(false);
        jScrollPane1.setPreferredSize(new Dimension(0, 0));
        jScrollPane1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        ta_view_message = new JTextArea() {
        	
            //Image grayImage = GrayFilter.createDisabledImage(image);
            {setOpaque(false);}

            public void paintComponent (Graphics g){
            	
            	//ImageIcon imageicon = new ImageIcon("res/22.jpg");
        		ImageIcon imageicon = new ImageIcon(getClass().getResource("res/20.jpg"));
            	Image image = imageicon.getImage();
            	
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                //g.drawImage(grayImage, 0, 0, (int)getSize().getWidth(), (int)getSize().getHeight(), this);
                super.paintComponent(g);
            }
        };        
        ta_view_message.setOpaque(false);
        ta_view_message.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));        
        ta_view_message.setFont(new Font("Verdana", Font.PLAIN, 15));   
        ta_view_message.setForeground(Color.ORANGE);
        
        jPanel2 = new JPanel();
        jScrollPane2 = new JScrollPane();
        tp_write_message = new JTextPane();
        tp_write_message.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btn_send = new JButton();
        btn_send.setFont(new Font("Tahoma", Font.BOLD, 14));
        //jPanel3 = new ImagePanel("14.jpg");
        btn_audio=new JButton();
        btn_video = new JButton();        
        //tf_file = new JTextField();
        
        setBackground(SystemColor.desktop);	
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(new Dimension(600, 550));
		setMinimumSize(new Dimension(550, 500));
		ImagePanel imagePanel = new ImagePanel("res/20.jpg");
		imagePanel.setPreferredSize(new Dimension(580, 520));
		setContentPane(imagePanel);

        ta_view_message.setBackground(new Color(153, 204, 255));
        ta_view_message.setColumns(20);
        ta_view_message.setEditable(false);
        ta_view_message.setRows(5);
        ta_view_message.setBorder(null);
        jScrollPane1.setViewportView(ta_view_message);
        
        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tp_write_message.setBorder(null);
        jScrollPane2.setViewportView(tp_write_message);

        btn_send.setText("Send");
        btn_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2Layout.setHorizontalGroup(
        	jPanel2Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(jPanel2Layout.createSequentialGroup()
        			.addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
        			//.addPreferredGap(ComponentPlacement.RELATED)  // Gap btn textpane and send button
        			.addComponent(btn_send, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
        	jPanel2Layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(btn_send, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
        		.addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
        );
        jPanel2.setLayout(jPanel2Layout);

        
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
        			//.addContainerGap()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(jScrollPane1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
        				.addComponent(jPanel2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE))
        			//.addContainerGap()
        				)
        );
        
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
        			//.addContainerGap()
        			.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
        			//.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
        			//.addContainerGap()
        			)
        );
        jPanel1.setLayout(jPanel1Layout);       

        btn_audio.setText("Talk");
        btn_audio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_audioActionPerformed(evt);
            }
        });
        
        btn_video.setText("Video Chat");
        btn_video.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_videoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(100)
        			.addComponent(btn_audio, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        			.addGap(184)
        			.addComponent(btn_video, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        			.addGap(100))
        		.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(28)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btn_video, 40, 40, 40)
        				.addComponent(btn_audio, 40, 40, 40))
        			.addGap(18)
        			.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
        			.addContainerGap())
        );
        
        getContentPane().setLayout(layout);

        pack();
    }
	
	private void btn_sendActionPerformed(java.awt.event.ActionEvent evt) {        
        sendMessage();
    }
	
	private void btn_audioActionPerformed(java.awt.event.ActionEvent evt) {
		//TODO: add code for audio
		String audio_btn_status=btn_audio.getText();
		if(audio_btn_status.equalsIgnoreCase("Talk")){
			MessageBean message=new MessageBean(WANTSTOTALK, to, from);//add client or server
			clientManager.sendMessage(message);
		}else if(audio_btn_status.equalsIgnoreCase("Stop")){
			MessageBean message=new MessageBean(STOPTALK, to, from);
			btn_audio.setText("Talk");
			clientManager.sendMessage(message);
			showAudioOption(STOPTALK, "You have Stopped Talking");
		}
	}		
	
	private void btn_videoActionPerformed(java.awt.event.ActionEvent evt) {
		//TODO: add code for video
		String video_btn_status=btn_video.getText();
		if(video_btn_status.equalsIgnoreCase("Video Chat")){
			MessageBean message=new MessageBean(WANTSTOWATCH, to, from);//add client or server
			clientManager.sendMessage(message);
		}else if(video_btn_status.equalsIgnoreCase("Stop")){
			MessageBean message=new MessageBean(STOPVIDEO, to, from);
			btn_video.setText("Video Chat");
			clientManager.sendMessage(message);
			showVideoOption(STOPVIDEO, "You have Stopped Video Chat");
		}
	}
	
	void sendMessage(){
        MessageBean message=new MessageBean(TEXTCHAT,to,from,tp_write_message.getText());
        clientManager.sendMessage(message);
        tp_write_message.setText(null);
        ta_view_message.append(from+" : "+message.getData()+"\n");
    }
	
	void showAudioOption(String header,String message){
		//setVisible(true);
		//System.out.println("Inside ChatBoxFrame->showOption() "+message);
		//boolean clientORserver=true;//Check??
		//AudioChatManager audioChatManager=new AudioChatManager();// undefined default constructor
		if(header.equals(WANTSTOTALK)){
			int choice=JOptionPane.showConfirmDialog(getContentPane(), message, "PLEASE SELECT YOUR CHOICE",		
				JOptionPane.YES_NO_OPTION ,JOptionPane.QUESTION_MESSAGE);
			MessageBean talkChoice;
			if(choice==JOptionPane.YES_OPTION){//User accepts to talk
				/*String remoteAddress="";
				int loop=0;*/
				talkChoice=new MessageBean(ACCEPTTALK,to,from);				
				clientManager.sendMessage(talkChoice);
				btn_audio.setText("Stop");
				/*StringTokenizer tokens=new StringTokenizer(message);
				while(loop++ <3)
					remoteAddress=tokens.nextToken();
				//System.out.println("In chatboxframe "+remoteAddress);*/				
				
				//TODO: add code for audio chat
				this.voiceClientOrServer=true;
				audioChatManager.startListening(this.voiceClientOrServer);
				//System.out.println("Sender IP in chatboxframe: "+sen_ip+to+from);
				audioChatManager.connect(sen_ip);//remoteAddress);
				//audioChatManager.start();
			}else{
				talkChoice=new MessageBean(REJECTTALK,to,from);
				clientManager.sendMessage(talkChoice);
			}
		}else if(header.equals(ACCEPTTALK)){			
			//TODO: code for serversocket, place them at the beginning of this block for better performance
			//swap i.e., if req. for talk then also tart to listen skt conn
			this.voiceClientOrServer=false;
			audioChatManager.startListening(this.voiceClientOrServer);
			audioChatManager.startServer();
			
			//show
			this.btn_audio.setText("Stop");
			JOptionPane.showMessageDialog(this, message);
		}else if(header.equals(REJECTTALK)){			
			JOptionPane.showMessageDialog(this, message);
		}else if(header.equals(STOPTALK)){
			//TODO: Code for stop
			//if(audioChatManager.isConnected){
				//audioChatManager.flagoutput=true;
				//audioChatManager.stopListening();
			//}
			if(this.voiceClientOrServer)
				audioChatManager.disconnect();
			else
				audioChatManager.stopServer();
			this.btn_audio.setText("Talk");
			JOptionPane.showMessageDialog(this, message);
		}
		//setVisible(true);
	}
	
	void showVideoOption(String header,String message){
		//VideoChatManager videoChatManager=new VideoChatManager();// undefined default constructor
		if(header.equals(WANTSTOWATCH)){
			int choice=JOptionPane.showConfirmDialog(getContentPane(), message, "PLEASE SELECT YOUR CHOICE",		
				JOptionPane.YES_NO_OPTION ,JOptionPane.QUESTION_MESSAGE);
			MessageBean videoChoice;
			if(choice==JOptionPane.YES_OPTION){//User accepts video req.
				/*String remoteAddress=null,myIP=null;
				int loop=0;*/
				videoChoice=new MessageBean(ACCEPTVIDEO,to,from);				
				clientManager.sendMessage(videoChoice);
				btn_video.setText("Stop");
				/*StringTokenizer tokens=new StringTokenizer(message);
				while(loop++ <3)
					remoteAddress=tokens.nextToken();
				while(loop++ <9)
					myIP=tokens.nextToken();
				//System.out.println("In chatboxframe "+remoteAddress);				
				*/
				//TODO: add code for audio chat
				//this.voiceClientOrServer=true;
				this.videoChatManager.videoSender(myIP,VIDEO_PORTA);
				System.out.println("After videosender in chatboxframe I'm accepting");
				this.videoChatManager.videoReceiver(sen_ip,VIDEO_PORTB,to);//block hoe jachhe by previous sender
				System.out.println("After videoreceiver in chatboxframe I'm accepting");
				//audioChatManager.start();
			}else{
				videoChoice=new MessageBean(REJECTVIDEO,to,from);
				clientManager.sendMessage(videoChoice);
			}
		}else if(header.equals(ACCEPTVIDEO)){			
			//TODO: code for serversocket, place them at the beginning of this block for better performance
			//swap i.e., if req. for talk then also tart to listen skt conn
			//this.voiceClientOrServer=false;
			//audioChatManager.startListening(this.voiceClientOrServer);
			//audioChatManager.startServer();
			
			/*String remoteAddress=null,myIP=null;
			int loop=0;
			StringTokenizer tokens=new StringTokenizer(message);
			while(loop++ <3)
				remoteAddress=tokens.nextToken();
			while(loop++ <9)
				myIP=tokens.nextToken();
			*/
			
			this.btn_video.setText("Stop");
			JOptionPane.showMessageDialog(this, message);
			
			this.videoChatManager.videoSender(myIP,VIDEO_PORTB);
			System.out.println("After videosender in chatboxframe first else-if");
			this.videoChatManager.videoReceiver(sen_ip,VIDEO_PORTA,to);//block hoe jachhe by previous sender
			System.out.println("After videoreceiver in chatboxframe first else-if");
			
			//show 		upore tulte hobe ekon unceachable
			/*this.btn_video.setText("Stop");
			JOptionPane.showMessageDialog(this, message);*/
		}else if(header.equals(REJECTVIDEO)){			
			JOptionPane.showMessageDialog(this, message);
		}else if(header.equals(STOPVIDEO)){
			//TODO: Code for stop video 
			
			/*if(this.voiceClientOrServer)
				audioChatManager.disconnect();
			else
			
				audioChatManager.stopServer();*/
			this.btn_video.setText("Video Chat");
			JOptionPane.showMessageDialog(this, message);// ei duto line pore rakha e valo
			System.out.println("Going to stop Video Chat in chatbox frame");
			this.videoChatManager.stopVideoChat();
			System.out.println("After video chat stop() in chatbox frame");
			/*this.btn_video.setText("Video Chat");
			JOptionPane.showMessageDialog(this, message);*/
		}
	}
}