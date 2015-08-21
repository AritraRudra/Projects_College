package server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import static server.ServerConstants.*;

public class ServerManager implements MessageListener{
    ExecutorService serverExeCutor;
    ServerSocket server;
    Socket clientSocket;
    Clients[] client;
    int clientNumber=0;
    static String[] clientTracker;
    String users="";

    public ServerManager(){
        client=new Clients[CLIENT_NUMBER];
        clientTracker=new String [CLIENT_NUMBER];
        serverExeCutor=Executors.newCachedThreadPool();
    }

    public void startServer(ServerStatusListener statusListener,ClientListener clientListener){
        try{
            statusListener.status("Server is Listening on port : "+SERVER_PORT);
            server=new ServerSocket(SERVER_PORT,BACKLOG);
            serverExeCutor.execute(new ConnectionController(statusListener,clientListener));
        }
        catch(IOException ioe){
            statusListener.status("IOException occured When Server start");
        }
    }

    public void stopServer(ServerStatusListener statusListener){
        try{
        	server.close();
            statusListener.status("Server is stoped");
        }
        catch(SocketException ex){
            //ex.printStackTrace();
            statusListener.status("SocketException Occured When Server is going to stoped");
        }
        catch (IOException ioe){
            //ioe.printStackTrace();
            statusListener.status("IOException Occured When Server is going to stoped");
        }
    }

    public void controllConnection(ServerStatusListener statusListener,ClientListener clientListener){
        while(clientNumber<CLIENT_NUMBER){
        	try{
                clientSocket= server.accept();
                client[clientNumber]=new Clients(clientListener,clientSocket,this,clientNumber);
                serverExeCutor.execute(client[clientNumber]);
                clientNumber++;
                System.out.println(clientNumber);
            }
            catch(SocketException ex){
                ex.printStackTrace();
                System.out.println("Exception at Server Manager ->controllConnection -> Socket Exception");
                break;	//Why this break?? 	maybe to close the server window , Infinite LOOP
            }
            catch (IOException ioe){
                ioe.printStackTrace();
                statusListener.status("Some Problem Occured When connection received");
                System.out.println("Exception at Server Manager->controllConnection -> IOException");
                break; // Why this break?? 	maybe to close the server window,not infinite loop            
    		} catch (Exception e) {		//Not required
				// TODO Auto-generated catch block
				e.printStackTrace();
			}            
        }
    }

       // for sending particular Client
    public void sendInfo(MessageBean message){        
        //String header=message.getHeader();
        String to=message.getReceiver();

        for(int i=0;i<clientNumber;i++){
            if(clientTracker[i].equalsIgnoreCase(to)){
                try{
                	//System.out.println("Server is sending   "+ message.putToMessage());
                	/*ArrayList<MessageBean> writeMsg=new ArrayList<MessageBean>();
                	writeMsg.add(0,message);*/
                	client[i].output.writeObject(message.putToMessage());
                    //client[i].output.writeObject(message);
                    client[i].output.flush();
                }
                catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        }
    }

   /* public void sendFile(String sendTo,int file){
        for(int i=0;i<clientNumber;i++){
            if(clientTracker[i].equalsIgnoreCase(sendTo)){
                try{
                    client[i].output.writeInt(file);
                    client[i].output.flush();
                }
                catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        }
    }*/
    
    
    // send all;

    public void sendNameToAll(MessageBean message){
        for(int i=0;i<clientNumber;i++){
            try{
                System.out.println("Server is sending   "+ message.putToMessage());
                //ArrayList<MessageBean> writeMsg=new ArrayList<MessageBean>();
            	client[i].output.writeObject(message.putToMessage());
                //client[i].output.writeObject(message);
                client[i].output.flush();
            }
            catch (IOException ex){
            	System.out.println("IOException in ServerManager");
            	ex.printStackTrace();
            }
        }
    }

    class ConnectionController implements Runnable{
        ServerStatusListener statusListener;
        ClientListener clientListener;

        ConnectionController(ServerStatusListener getStatusListener,ClientListener getClientListener){
            statusListener=getStatusListener;
            clientListener=getClientListener;
        }

        public void run(){
            controllConnection(statusListener,clientListener);
        }
    }
}