package client;

/*import javax.media.*;   
import javax.media.rtp.*;   
import javax.media.rtp.event.*;   
import javax.media.rtp.rtcp.*;   
import javax.media.protocol.*;   
import javax.media.protocol.DataSource;   
import javax.media.format.AudioFormat;   
import javax.media.format.VideoFormat;   
import javax.media.Format;   
import javax.media.format.FormatChangeEvent;   
import javax.media.control.BufferControl;   */

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

import static client.ClientConstants.*;

/**
 *
 * @author Aritra
 */
public class MessageReceiver implements Runnable{
    ObjectInputStream input;
    boolean keepListening=true;
    ClientListListener clientListListener;
    ClientWindowListener clientWindowListener;
    ClientManager clientManager;
    Socket clientSocket;
    ExecutorService clientExecutor;


    MessageReceiver(Socket getClientSocket,ClientListListener getClientListListener,
    		ClientWindowListener getClientWindowListener,ClientManager getClientManager)
    {
        clientExecutor=Executors.newCachedThreadPool();
        clientManager=getClientManager;
        clientSocket=getClientSocket;
        try{
            input = new ObjectInputStream(getClientSocket.getInputStream());
        }
        catch (IOException ex){}
        clientListListener=getClientListListener;
        clientWindowListener=getClientWindowListener;
    }
    
    public void run(){
        MessageBean message=new MessageBean();
        String name="",ips = "";
        while(keepListening){
            try{
            	/*ArrayList<MessageBean> readMsg=new ArrayList<MessageBean>();
                readMsg = (ArrayList<MessageBean>)input.readObject();
                message=readMsg.get(0);*/
            	String readMsg=(String)input.readObject();
            	System.out.println("User is receiving   "+ readMsg);
            	message.getFromMessage(readMsg);
                //System.out.println("User is receiving   "+ message.toString());
                String header=message.getHeader();                
                name=message.getReceiver();
                //System.out.println("user is receiving "+header+" "+name);
                
                if(header.equals(LOGIN)){
                    clientListListener.addToList(name); 
                }
                
                else if(header.equals(DISCONNECT)){
                    clientListListener.removeFromList(name);
                }
                
                /*else if(header.equalsIgnoreCase("server")){// ??
                    clientWindowListener.closeWindow(message);
                }*/
                
                else if(header.equals(WANTSTOTALK)){	//header sendername recname
                	/*String rec_name = name;
                	String sen_name=message.getSender();                    
                	MessageBean wantsToTalkMsg = new MessageBean(WANTSTOTALK, rec_name, sen_name);		
                    //System.out.println("In Message Receiver "+message1);
                    clientWindowListener.openWindow(wantsToTalkMsg);
                    //serverManager.sendInfo(message1);*/
                    clientWindowListener.openWindow(message);
                    /*String messa2 = rec_name+" "+ACCEPTTALK+" "+sen_ip;
                    serverManager.sendInfo(messa2);*/
                }
                
                else if(header.equals(ACCEPTTALK)){		//header sendername recname
                	/*String rec_name = name;
                	String sen_name=tokens.nextToken();
                	String message1 = ACCEPTTALK+" "+rec_name+" "+sen_name;	
                    //System.out.println("In Message Receiver "+message1);*/
                    clientWindowListener.openWindow(message);
                    //serverManager.sendInfo(message1);
                }
                
                else if(header.equals(REJECTTALK)){		//header recname sendername
                	/*String rec_name = name;
                	String sen_name=tokens.nextToken();
                	String message1 = REJECTTALK+" "+rec_name+" "+sen_name;	
                    //System.out.println(message1);*/
                    clientWindowListener.openWindow(message);
                    //serverManager.sendInfo(message1);
                }
                
                else if(header.equals(STOPTALK)){		//header recname sendername
                	/*String rec_name = name;
                	String sen_name=tokens.nextToken();
                	String message1 = STOPTALK+" "+rec_name+" "+sen_name;	
                    //System.out.println(message1);*/
                    clientWindowListener.openWindow(message);
                }
                else if(header.equals(WANTSTOWATCH)){
                	clientWindowListener.openWindow(message);
                }
                // Video 
                /*else if(name.equalsIgnoreCase("video"))
                {
                   // clientWindowListener.fileStatus("One File is Receiving");
                  //  String address=tokens.nextToken();
                   // String fileName=tokens.nextToken();
                //    clientExecutor.execute(new FileReceiver(address));
                ips = tokens.nextToken();
                    
                           String st="",pt="";
                           st += ips;
                           System.out.println("#########"+st);

                                
                           String str[] = new String[2];
                           str[0]  =  st + "/20002";
                           str[1]  =  st + "/20004";
                           AVReceive2 avReceive= new AVReceive2(str);   
                           //VideoTransmit vt = new VideoTransmit(new MediaLocator("vfw://0"),st,"20006");
                           for(int o=1;o<st.length();o++)pt += st.charAt(o);
                           AVTransmit2 vt = new AVTransmit2(new MediaLocator("vfw://0"),pt,"20006",null);
                           AVTransmit2 at = new AVTransmit2(new MediaLocator("javasound://8000"),pt,"20008",null);
                           at.start();  
                            String result = vt.start();
                            
                         
                                if (!avReceive.initialize())    
                                 {   
                                     System.err.println("Failed to initialize the sessions.");   
                                     System.exit(-1);   
                                 }  
                                        
                                 try    
                                 {   
                                     while (!avReceive.isDone())   
                                         Thread.sleep(60000);   
                                 }    
                                 catch (Exception ex)    
                                 {}  
                                        
        
                        
                                if (result != null) {
                                    System.out.println("Error : " + result);
                                    System.exit(0);
                                }
                        
                                System.out.println("Start transmission for 60 seconds...");
                                 
                                     
                                // Transmit for 60 seconds and then close the processor
                                // This is a safeguard when using a capture data source
                                // so that the capture device will be properly released
                                // before quitting.
                                // The right thing to do would be to have a GUI with a
                                // "Stop" button that would call stop on VideoTransmit
                                try {
                                    Thread.currentThread().sleep(60000);
                                } catch (InterruptedException ie) {
                                }
                        
                                // Stop the transmission
                                vt.stop();
                           // avReceive.stop();
                                            
                }		// Upto Here
                /*else if(name.equalsIgnoreCase("video1"))
                {
                
               ips = tokens.nextToken();
                    
                           String st="",pt="";
                           st += ips;
                           System.out.println("*******"+st);
                           for(int o=1;o<st.length();o++)pt += st.charAt(o);
                           //VideoTransmit vt = new VideoTransmit(new MediaLocator("vfw://0"),st,"20002");
                           //VideoTransmit at = new VideoTransmit(new MediaLocator("javasound://8000"),st,"20004");
                           
                           AVTransmit2 vt = new AVTransmit2(new MediaLocator("vfw://0"),pt,"20002",null);
                           
                           AVTransmit2 at = new AVTransmit2(new MediaLocator("javasound://8000"),pt,"20004",null);
               at.start();
               String result = vt.start(); 
                if (result != null) {
                    System.out.println("Error : " + result);
                    System.exit(0);
                   }
    
    
                   System.out.println("Start transmission for 60 seconds..."); 
                                        
                           String str[] = new String[2];
                           str[0]  =  st + "/20006";
                           str[1]  =  st + "/20008";
                           AVReceive2 avReceive= new AVReceive2(str);
                
                         if (!avReceive.initialize())    
                                 {   
                                     System.err.println("Failed to initialize the sessions.");   
                                     System.exit(-1);   
                                 }  
                                        
                                 try    
                                 {   
                                     while (!avReceive.isDone())   
                                         Thread.sleep(60000);   
                                 }    
                                 catch (Exception ex)    
                                 {}  
                                        
                                 try {
                          Thread.currentThread().sleep(600000);
                         } catch (InterruptedException ie)
                         {
                         }
      
                     // Stop the transmission
                        vt.stop();      
                                        
                        
                }*/ 
                
                /*else if(name.equalsIgnoreCase("Allowed"))		//Lagbe ??
                {
                    //clientWindowListener.openWindow(message);
                }*/
                else if(header.equals(INVALIDUSER)){
                    //TODO: Code for access denial
                	clientWindowListener.closeWindow("Invalid USERNAME/PASSWORD");
                	//clientManager.disconnect(clientStatus);
                	System.out.println("Access Denied ");
                }
                
                else{
                    clientWindowListener.openWindow(message);
                }
            }catch (IOException ex){
                clientListListener.removeFromList(name);
            }catch (ClassNotFoundException ex){}
            catch (Exception e) {	//Catch Block added later
            	e.printStackTrace();
			}
        }
    }

    void stopListening(){
        keepListening=false;
    }
}