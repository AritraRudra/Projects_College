package server;

public interface ServerConstants {
	public static final int SERVER_PORT=5789;
    public static final int BACKLOG=100;
    public static final int CLIENT_NUMBER=100;
    public static final int MULTICAST_SENDING_PORT=5555;
    public static final int MULTICAST_LISTENING_PORT=5554;
    public static final String MULTICAST_ADDRESS="239.0.0.1";
    public static final String LOGIN="LOGIN";//Use chars not printable by keyboard
    public static final String INVALIDUSER="INVALID";
    public static final String SENDIPS="BOTH_IPS";
    public static final String TEXTCHAT="TEXTCHAT";
    public static final String WANTSTOTALK="TALKWAITING";
    public static final String ACCEPTTALK="TALKING";
    public static final String REJECTTALK="NOTTALKING";
    public static final String STOPTALK="STOPTALKING";
    public static final String WANTSTOWATCH="VIDEOWAITING";
    public static final String ACCEPTVIDEO="WATCHING";
    public static final String REJECTVIDEO="NOTWATCHING";
    public static final String STOPVIDEO="STOPVIDEO";
    public static final String DISCONNECT="DISCONNECT";
    public static final String MESSAGE_SEPARATOR=" >> ";	
}