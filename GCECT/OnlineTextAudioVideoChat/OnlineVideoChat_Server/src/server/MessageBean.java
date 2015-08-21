package server;

import java.io.Serializable;
import java.util.StringTokenizer;

public class MessageBean implements Serializable{	
	//private static final long serialVersionUID = 3545052198990400125L;
	
	String header;
	String receiver;	//Password when login
	String sender;		//User Name when login
	String data;
	
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
		
	public MessageBean() {
		//super();
		/*this.header = null;
		this.receiver = null;
		this.sender = null;
		this.data = null;*/
		this.header = "";
		this.receiver ="";
		this.sender = "";
		this.data = "";
	}
	public MessageBean(String header, String receiver, String sender) {
		//super();
		this.header = header;
		this.receiver = receiver;
		this.sender = sender;
		this.data = "";
	}
	public MessageBean(String header, String receiver, String sender,String data) {
		//super();
		this.header = header;
		this.receiver = receiver;
		this.sender = sender;
		this.data = data;
	}
	public MessageBean(MessageBean message){
		//super();
		this.header = message.getHeader();
		this.receiver = message.getReceiver();
		this.sender = message.getSender();
		this.data = message.getData();
	}
	public MessageBean(String messageString){
		StringTokenizer stringToken=new StringTokenizer(messageString);
		this.header=stringToken.nextToken();
		this.receiver=stringToken.nextToken();
		this.sender=stringToken.nextToken();
		this.data=stringToken.nextToken();
	}
	
	public String putToMessage(){
		return this.header+" "+this.receiver+" "+this.sender+" "+this.data;
	}
	
	public MessageBean getFromMessage(String messageString){
		int cuttingLength;
		StringTokenizer stringToken=new StringTokenizer(messageString);
		this.header=stringToken.nextToken();
		this.receiver=stringToken.nextToken();
		this.sender=stringToken.nextToken();
		cuttingLength=(this.header.length()+1+this.receiver.length()+1+this.sender.length()+1);
		this.data=messageString.substring(cuttingLength);//this.data=stringToken.nextToken();
		return new MessageBean(this.header, this.receiver, this.sender, this.data);		
	}
	
	public String toString(){
		return ("header= "+this.header+" , receiver= "+this.receiver+" , sender= "+sender+" , data="+data);		
	}
}
