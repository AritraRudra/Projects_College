package video;

import javax.swing.JFrame;
//import javax.swing.SwingUtilities;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;

public class VideoPlayer extends VlcjTest implements Runnable {
	
	private JFrame frame;
    private EmbeddedMediaPlayerComponent mediaPlayerComponent;
    String mrl,remoteIP;
    int remoteVideoPort=5004;

    /*void begin(){
    	/*SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            new VideoPlayer().start(mrl);
        }
    });
    }*/
    	
    
    public VideoPlayer(String IP,String to){
	    mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
	    this.remoteIP=IP;
	    this.mrl=formatStream("rtp", remoteIP, remoteVideoPort);
	    System.out.println("In video player : "+remoteIP+mrl);
	    frame = new JFrame(to);
	    frame.setLocation(50, 50);
	    frame.setSize(800,600);
	    frame.setContentPane(mediaPlayerComponent);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	    //start();
	    /*try {
			join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
    }

    public void run() {
    	this.mediaPlayerComponent.getMediaPlayer().playMedia(this.mrl);
    }
    
    private String formatStream(String protocol,String ip,int port){
		StringBuilder sb=new StringBuilder(50);
		sb.append(protocol);
		sb.append("://@");
		sb.append(ip);
		sb.append(":");
		sb.append(remoteVideoPort);
		return sb.toString();
	}
}
