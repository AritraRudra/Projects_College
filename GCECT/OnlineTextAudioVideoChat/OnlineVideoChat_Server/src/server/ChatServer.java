package server;

/**
 * @author Aritra
 */

public class ChatServer{	
	public static void main(String[] args) {
		System.out.println("MultiUser Voice Chat Server 'FACEUP' is starting ...");
        /*try {
            if(args.length > 0)
                port = Integer.parseInt(args[0]);
        } catch(NumberFormatException nfe) {
            System.err.println("Usage: java ChatServer [port]");
            System.err.println("Where options include:");
            System.err.println("\t[port] -> the port on which to listen.");
            System.exit(0);
        }*/                         
            
		//Load the GUI for the server side
        //ServerMonitor monitor=new ServerMonitor(serverManager);
        ServerManager serverManager=new ServerManager();
        ServerGUI monitor=new ServerGUI(serverManager);
        monitor.setVisible(true);
            
            
            /*while(true) {
                socket = serverSocket.accept();
                System.out.println("Connection received from " + socket.getRemoteSocketAddress());
                ChatHandler handler = new ChatHandler(socket);
                handler.start();
            }
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }
        }*/
	}
}