package audio;

import java.io.*;
import java.net.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class AudioChatManager {
	//Variables declarations
    ExecutorService clientExecutor,serverExecutor,executor;
    ServerSocket server;
    Socket clientSocket,server_clientSocket ;
    private boolean clientORserver;
    public boolean isConnected=false,flagoutput=true;
    InputStream input;//public??
    OutputStream output;

    //AudioCapture audioCapture;
    AudioSenderReceiver audioSenderReceiver;
    
    
    /*public AudioChatManager() {
    }*/
    
    //client    
    public void startListening(boolean likeClient){
    	this.clientORserver=likeClient;
    	System.out.println("I am becoming a client : "+this.clientORserver);
	    /*if(this.clientORserver)
	    	clientExecutor=Executors.newCachedThreadPool();
	    else
	    	serverExecutor=Executors.newCachedThreadPool();*/
	    //executor=Executors.newCachedThreadPool();
    }

    public void connect(String remoteAddress){
        try{
           /*if(isConnected)
        	   return;
           else{*/
        	System.out.println("In connnect");
        	   Thread.sleep(1000);
        	   int remotePort=9875;
        	   //System.out.println("Sender IP in AudioChatManager: "+remoteAddress);
        	   clientSocket=new Socket(remoteAddress.substring(1),remotePort);
               //clientStatus.loginStatus("You are connected to :"+SERVER_ADDRESS);
        	   //clientExecutor.execute(new AudioSenderReceiver());
        	   //executor.execute(new AudioSenderReceiver());
        	   audioSenderReceiver=new AudioSenderReceiver();
        	   //audioSenderReceiver.run();
               isConnected=true;
           //}
       }catch (UnknownHostException uhex){
    	   uhex.printStackTrace();
    	   //clientStatus.loginStatus("No Server found");
       }catch (IOException iox){
    	   iox.printStackTrace();
            //clientStatus.loginStatus("No Server found");
       }catch (Exception intex) {
    	   // Auto-generated catch block
    	   intex.printStackTrace();
		}
   }

   public void disconnect(){//private
	   //messageReceiver.stopListening();
	   
       try{
    	   System.out.println("In disconnect()");
    	   //clientStatus.loginStatus("You are no longer connected to Server");
    	   //this.clientExecutor.shutdown();//Added later to stop the voice chat
    	   //this.executor.shutdown();
    	   input.close();
    	   output.close();
    	   clientSocket.close();
    	   isConnected=false; //ADDED LATER           
       }catch (IOException ex){
    	   ex.printStackTrace();
       }catch (NullPointerException nullex) {
		//  handle exception lagbe na??
    	   nullex.printStackTrace();
       }/*finally{
    	   try {
    		   input.close();
        	   output.close();
        	   clientSocket.close();
		} catch (IOException iox) {
			iox.printStackTrace();
		}
       }*/
   }
   
   /*public void start(){
       clientExecutor.execute(new AudioSenderReceiver());
   }*/
   
   
   //Server like 
   public void startServer(){
       try{
    	   System.out.println("In startServer()");
           server=new ServerSocket(9875);
           //server_clientSocket=server.accept();
           clientSocket=server.accept();
           //serverExecutor.execute(new AudioSenderReceiver());
           //executor.execute(new AudioSenderReceiver());
           audioSenderReceiver=new AudioSenderReceiver();
           //audioSenderReceiver.run();
           isConnected=true;
       }
       catch(SocketException sktex){
           sktex.printStackTrace();
           System.out.println("Exception at AudioChatManager ->startserver -> Socket Exception");
           //break;	//Why this break?? 	maybe to close the server window , Infinite LOOP
       }
       catch(IOException ioe){
    	   ioe.printStackTrace();
           //statusListener.status("IOException occured When Server start");
       }
   }

   public void stopServer(){//private
       try{
    	   System.out.println("In stopServer()");
    	   //this.serverExecutor.shutdown();//Added later to stop the voice chat
    	   //this.executor.shutdown();
    	   this.server.close();
    	   this.isConnected=false;    	   
           //statusListener.status("Server is stopped");
       }
       catch(SocketException ex){
           ex.printStackTrace();
           //statusListener.status("SocketException Occured When Server is going to stoped");
       }
       catch (IOException ioe){
           ioe.printStackTrace();
           //statusListener.status("IOException Occured When Server is going to stoped");
       }catch (NullPointerException nullex) {
		// handle exception  lagbe na??
    	   nullex.printStackTrace();
       }/*finally{
    	   try {
			server.close();
		} catch (IOException iox) {
			iox.printStackTrace();
		}
       }*/
   }
   
   public void stopListening(){
	   try{
	   System.out.println("In stopListener");
	   System.out.println("I am client : "+this.clientORserver);
	   if(this.clientORserver)
		   this.disconnect();
	   else
		   this.stopServer();
	   this.flagoutput=true;
   }catch(NullPointerException nullex){
	   nullex.printStackTrace();
	   disconnect();
   }catch (Exception e) {
	// handle exception
	   e.printStackTrace();
	   disconnect();
   }
   }
   
   class AudioSenderReceiver {//implements Runnable{
       //MessageBean message;
       public AudioSenderReceiver(){
       	if(flagoutput){		//Initially true at first run
       		try{
                   input = clientSocket.getInputStream();
                   output = clientSocket.getOutputStream();
                   output.flush();
                   flagoutput=false;                   
               }
               catch (IOException iox){
            	   iox.printStackTrace();
               }
           }
           //message=getMessage;
           //System.out.println("user is sending   "+ message.getHeader()+" "+message.getReceiver()+" "+message.getSender());
       /*}
       public void run(){
           try{
        	   //if(clientORserver){
        		   AudioCapture audioCapture=new AudioCapture(output);
        		   //audioCapture=new AudioCapture(output);
        		   //System.out.println("In AudioChtManager -> AudioSenderReceiver->run->client");
        		   audioCapture.captureAudio();
        	   //}else{
        		   AudioPlayer audioPlayer=new AudioPlayer(input);
        		   //System.out.println("In AudioChtManager -> AudioSenderReceiver->run->server");
        		   audioPlayer.playAudio();
        	   //}
           }catch(Exception e){
        	   e.printStackTrace();
           }*/
       		AudioCapture audioCapture=new AudioCapture(output);
       		audioCapture.captureAudio();
       		AudioPlayer audioPlayer=new AudioPlayer(input);
		   audioPlayer.playAudio();
       }
   }
}