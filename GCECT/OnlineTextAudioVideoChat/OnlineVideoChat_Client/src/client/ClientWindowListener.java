package client;

/**
 *
 * @author Aritra
 */

public interface ClientWindowListener{	
    public void openWindow(MessageBean message);
    public void closeWindow(String message);
    public void fileStatus(String filesStatus);
}