package client;

/**
 *
 * @author Aritra
 */
public interface ClientConstants{
    //public static final String SERVER_ADDRESS="127.0.0.1";
    public static final int SERVER_PORT=5789;
    public static final int VOICE_PORT=9875;
    public static final int VIDEO_PORTA=5004;
    public static final int VIDEO_PORTB=5005;
    public static final int CLIENT_NUMBER=100;
    public static final String WINCAPTUREDEV="dshow://";//in  similar way vlcjTest e dileo hoe 
    public static final String MESSAGE_SEPARATOR=" >> ";
    public static final String LOGIN="LOGIN";//Use chars not printable by keyboard
    public static final String INVALIDUSER="INVALID";
    public static final String GETIPS="BOTH_IPS";
    public static final String TEXTCHAT="TEXTCHAT";
    //public static final String REMOTEHOSTACCEPTED="YES";
    public static final String WANTSTOTALK="TALKWAITING";
    public static final String ACCEPTTALK="TALKING";
    public static final String REJECTTALK="NOTTALKING";
    public static final String STOPTALK="STOPTALKING";
    public static final String WANTSTOWATCH="VIDEOWAITING";
    public static final String ACCEPTVIDEO="WATCHING";
    public static final String REJECTVIDEO="NOTWATCHING";
    public static final String STOPVIDEO="STOPVIDEO";
    public static final String DISCONNECT="DISCONNECT";
}
