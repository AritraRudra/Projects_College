package client;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import static client.ClientConstants.*;

/**
 *
 * @author Aritra
 */

public class ClientManager{
	//Variables declarations
    ExecutorService clientExecutor;
    Socket clientSocket ;
    boolean isConnected=false;

    ObjectInputStream input;
    ObjectOutputStream output;
    MessageReceiver messageReceiver;
    
    public boolean flagoutput=true;
    String SERVER_ADD;

    public ClientManager(){
        clientExecutor=Executors.newCachedThreadPool();
    }

    public void connect(ClientStatusListener clientStatus){
        try{
           if(isConnected)
        	   return;
           else{
        	   ServAdd servadd=new ServAdd();
        	   SERVER_ADD=servadd.getAddress();
        	   System.out.println(SERVER_ADD);
        	   clientSocket=new Socket(SERVER_ADD,SERVER_PORT);
               clientStatus.loginStatus("You are connected to :"+SERVER_ADD);
               isConnected=true;
           }
       }catch (UnknownHostException ex){
    	   clientStatus.loginStatus("No Server found");
       }catch (IOException ex){
            clientStatus.loginStatus("No Server found");
       }
   }

   public void disconnect(ClientStatusListener clientStatus){
	   messageReceiver.stopListening();
       try{
    	   clientStatus.loginStatus("You are no longer connected to Server");
           clientSocket.close();
           isConnected=false; //ADDED LATER
       }catch (IOException ex){}
   }

   public void sendMessage(MessageBean message){
        clientExecutor.execute(new MessageSender(message));
   }

    /*public void sendFile(String fileName)
    {
        clientExecutor.execute(new FileSender(fileName));
    }*/

    
    class MessageSender implements Runnable{
        MessageBean message;
        public MessageSender(MessageBean getMessage){
        	if(flagoutput){		//Initially true at first run
        		try{
                    output = new ObjectOutputStream(clientSocket.getOutputStream());
                    output.flush();
                    flagoutput=false;
                }
                catch (IOException ex){}
            }
            message=getMessage;
            //System.out.println("user is sending   "+ message.getHeader()+" "+message.getReceiver()+" "+message.getSender());
        }
        public void run(){
            try{
            	//System.out.println("user is sending   "+ message.getHeader()+" "+message.getReceiver()+" "+message.getSender());//added later during arraylist testing
            	//System.out.println("user is sending   "+message.toString());
            	//ArrayList<MessageBean> writeMsg=new ArrayList<MessageBean>();
            	//writeMsg.add(0,message);
                //output.writeObject(writeMsg);
            	//MessageBean msg=new MessageBean(LOGIN,"TESTSERVER","TESTCLIENT","TESTDATA");
            	//System.out.println("user is sending   "+msg.toString());
            	//System.out.println("user is sending   "+message.putToMessage());
            	System.out.println("user is sending   "+message.toString());
            	output.writeObject(message.putToMessage());
            	//output.writeObject(message);
                output.flush();                
            }
            catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

    public void receiveMessage(ClientListListener getClientListListener ,ClientWindowListener getClientWindowListener){
        messageReceiver=new MessageReceiver(clientSocket,getClientListListener, getClientWindowListener,this);
        clientExecutor.execute(messageReceiver);
    }
}