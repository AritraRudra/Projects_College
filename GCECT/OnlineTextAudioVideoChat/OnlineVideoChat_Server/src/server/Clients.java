package server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import static server.ServerConstants.*;

public class Clients implements Runnable{
	Socket client;	
    ObjectInputStream input;
    ObjectOutputStream output;
    boolean keepListening;
    ServerManager serverManager;
    int clientNumber;
    ClientListener clientListener;

    public Clients(ClientListener getClientListener,Socket getClient,ServerManager getServerManager,int getClientNumber){
        client=getClient;
        clientListener=getClientListener;
        try{
            serverManager=getServerManager;
            clientNumber=getClientNumber;            
            input = new ObjectInputStream(client.getInputStream());
            output = new ObjectOutputStream(client.getOutputStream());
        }catch (IOException iox){
            iox.printStackTrace();
        }
        keepListening=true;
    }

    public void run(){
    	MessageBean message=new MessageBean();
    	String name="";
        boolean sameName=false;
        while(keepListening){
            try{
            	String readMsg=(String)input.readObject();
            	System.out.println("Server is receiving   "+ readMsg);
            	message.getFromMessage(readMsg);
                //System.out.println("Server is receiving   "+ message.toString());
                //System.out.println("Server is receiving   "+ message.getHeader()+" "+message.getReceiver());                
                String header=message.getHeader();
                name=message.getSender();//login name
                
                if(header.equals(LOGIN)){
                	//TODO: code for login password check and grant
                	
                	String passwd=message.getReceiver();//password
                	//System.out.println("Name "+name+"Pass "+passwd);
                	DBOps dbOps=new DBOps();
                	if(dbOps.DBOps_Search(name, passwd)){
                	//if((name.equals("a")&& passwd.equals("a"))|| (name.equals("b")) && passwd.equals("b")){
                		//System.out.println("Valid Uname & Pass");
                		MessageBean msgToAll=new MessageBean(LOGIN,name,"SERVER");//Receiver is username here but send to all 
                		serverManager.sendNameToAll(msgToAll);
                		ServerManager.clientTracker[clientNumber]=name;
                		//System.out.println("Got here 1");

                		for(int i=0;i<serverManager.clientNumber;i++){ //Create & send  users list                			
                			String userName=ServerManager.clientTracker[i];
                			if(!userName.equalsIgnoreCase("")){                				
                				MessageBean loginMsg=new MessageBean(LOGIN, userName, "SERVER");
                				/*ArrayList<MessageBean> writeLoginMsg=new ArrayList<MessageBean>();
                				writeLoginMsg.add(loginMsg);
                				output.writeObject(writeLoginMsg);*/	//client ip client kei pathacchi !!
                				output.writeObject(loginMsg.putToMessage());
                				//System.out.println("Got here 4");
                				System.out.print("$$"+client.getInetAddress());
                				output.flush();
                				//System.out.println("Got here 5");
                			}
                		}
                		//System.out.println("Got here 6");
                		clientListener.signIn(name);
                		clientListener.clientStatus(name+": is signIn , IPaddress :"+client.getInetAddress()+" , portNumber :"+client.getPort());
                		String ip ="";
                		ip += client.getInetAddress();
                		clientListener.mapped(name,ip);                	
                	}else{
                		MessageBean invalidMessage=new MessageBean(INVALIDUSER, name, "SERVER");
                		/*ArrayList<MessageBean> writeInvalidMsg=new ArrayList<MessageBean>();
                		writeInvalidMsg.add(message);
                    	output.writeObject(writeInvalidMsg);*/
                		output.writeObject(invalidMessage.putToMessage());
                		//client.close();//ADDED LATER
                		//serverManager.sendNameToAll("Denied"); //All will get DENIED
                	}
                }
                else if(header.equals(DISCONNECT)){
                    clientListener.signOut(name);
                    MessageBean logoutMsg=new MessageBean(DISCONNECT, name, "SERVER");
                    serverManager.sendNameToAll(logoutMsg);
                    ServerManager.clientTracker[clientNumber]="";
                    keepListening=false;
                    //client.close();	//ADDED LATER
                }
                
                //AUDIO Controls
                else if(header.equals(WANTSTOTALK)){//lagbe ki ??
                	String rec_name = message.getReceiver();
                	String sen_name=message.getSender();
                    String rec_ip = ServerGUI.hm.get(rec_name);                    
                    String sen_ip = ServerGUI.hm.get(sen_name);
                    MessageBean sendIPS=new MessageBean(SENDIPS, rec_name, sen_name,rec_ip+" "+sen_ip);
                    serverManager.sendInfo(sendIPS);
                    //String message1 = rec_name+" "+WANTSTOTALK+" "+rec_ip;	//a reqst b => b WANTSTOTALK b's_IP
                    MessageBean wantsToTalkMsg = new MessageBean(WANTSTOTALK, rec_name, sen_name);	
                    //System.out.println(message1);
                    serverManager.sendInfo(wantsToTalkMsg);
                }
                
                else if(header.equals(ACCEPTTALK)){		//lagbe ki??
                	/*String rec_name = message.getReceiver();
                	String sen_name=message.getSender();
                	String ip;
                	MessageBean talkingMsg = new MessageBean(WANTSTOTALK, rec_name, sen_name, message.getData());*/	
                    //System.out.println(talkingMsg);*/
                    serverManager.sendInfo(message);
                }
                
                else if(header.equals(REJECTTALK)){	//lagbe ki??
                	/*String rec_name = message.getReceiver();
                	String sen_name=message.getSender();
                	String message1 = REJECTTALK+" "+rec_name+" "+sen_name;	
                    System.out.println(message1);*/
                    serverManager.sendInfo(message);
                }
                
                else if(header.equals(STOPTALK)){
                	/*String rec_name = message.getReceiver();
                	String sen_name=message.getSender();
                	String message1 = STOPTALK+" "+rec_name+" "+sen_name;	
                    System.out.println(message1);*/
                    serverManager.sendInfo(message);
                }
                
                //VIDEO Controls
                else if(header.equals(WANTSTOWATCH)){//lagbe ki ??
                	String rec_name = message.getReceiver();
                	String sen_name=message.getSender();
                    String rec_ip = ServerGUI.hm.get(rec_name);
                    String sen_ip = ServerGUI.hm.get(sen_name);
                    //send ips to whom being requested
                    MessageBean sendIPtoRecvr=new MessageBean(SENDIPS, rec_name, sen_name,rec_ip+" "+sen_ip);
                    serverManager.sendInfo(sendIPtoRecvr);
                    //send ips to requester
                    MessageBean sendIPtosendr=new MessageBean(SENDIPS, sen_name,rec_name,sen_ip+" "+rec_ip);
                    serverManager.sendInfo(sendIPtosendr);
                    //String message1 = rec_name+" "+WANTSTOTALK+" "+rec_ip;	//a reqst b => b WANTSTOTALK b's_IP
                    MessageBean wantsToSeeMsg = new MessageBean(WANTSTOWATCH, rec_name, sen_name);	
                    //System.out.println(message1);
                    serverManager.sendInfo(wantsToSeeMsg);
                }
                
                else if(header.equals(ACCEPTVIDEO)){		//lagbe ki??
                	/*String rec_name = message.getReceiver();
                	String sen_name=message.getSender();
                    String rec_ip = ServerGUI.hm.get(rec_name);
                    String sen_ip = ServerGUI.hm.get(sen_name);
                    MessageBean sendIPS=new MessageBean(SENDIPS, rec_name, sen_name,rec_ip+" "+sen_ip);
                    serverManager.sendInfo(sendIPS);
                	MessageBean watchingMsg = new MessageBean(ACCEPTVIDEO, rec_name, sen_name);
                	serverManager.sendInfo(watchingMsg);*/
                	serverManager.sendInfo(message);
                }
                
                /*else if(header.equalsIgnoreCase("video"))
                {
                        String name1 = tokens.nextToken();
                        String rec_ip = ServerGUI.hm.get(name1);
                        
                        String sen_ip = ServerGUI.hm.get(name);
                        String message1 = name+" video "+rec_ip;
                        System.out.println("server ip back"+message1);
                        serverManager.sendInfo(message1);
                        
                    String messa2 = name1+" video1 "+sen_ip;
                        serverManager.sendInfo(messa2);
                        
                                
                }*/
      
                else{
                    serverManager.sendInfo(message);
                }                
            }
            catch (IOException ex){
            	System.out.println("IOException Occured in Clients->1");
                clientListener.signOut(name);
                MessageBean disconnectMsg=new MessageBean(DISCONNECT, name, "SERVER", null);
                serverManager.sendNameToAll(disconnectMsg); ///exp
                ServerManager.clientTracker[clientNumber]="";
            	System.out.println("IOException Occured in Clients->2");
            	ex.printStackTrace();
                break;
            }
            catch (ClassNotFoundException ex){}
        }
    }
}