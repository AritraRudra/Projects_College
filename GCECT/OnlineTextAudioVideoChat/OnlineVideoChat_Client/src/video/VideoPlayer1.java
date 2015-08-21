package video;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;


/**
 * An example of "playing" the screen/desktop.
 * <p>
 * See {@link ScreenRecorder} if you want to encode and save the video.
 * <p>
 * Additional media options that can be set are:
 *
 * <pre>
 *   :screen-top=
 *   :screen-left=
 *   :screen-width=
 *   :screen-height=
 * </pre>
 *
 * See <a href="http://wiki.videolan.org/Documentation:Modules/screen">Screen Module</a>.
 */
public class VideoPlayer1 extends VlcjTest {

    //1st try variables
    private final JFrame frame;
    private final JPanel contentPane;
    private final Canvas canvas;
    private final MediaPlayerFactory factory;
    private final EmbeddedMediaPlayer mediaPlayer;
    private final CanvasVideoSurface videoSurface;
    String remoteIP;
    int remoteVideoPort;//=5004;	// same port ?? for both transmission & receiving??
    boolean terminate=false;

    /*static{
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VideoPlayer().start();
            }
        });
    }*/

    /*Runnable startPlaying= new Runnable() {
		public void run() {
    		new VideoPlayer().start();
        }
    }
    SwingUtilities.invokeLater(startPlaying);
    
    /*Runnable doHelloWorld = new Runnable() {
     public void run() {
         System.out.println("Hello World on " + Thread.currentThread());
     }
 };

 SwingUtilities.invokeLater(doHelloWorld);
 System.out.println("This might well be displayed before the other message.");
*/
    public VideoPlayer1(String remoteIP,int remotePort,String to) {
    	this.remoteIP=remoteIP;
    	this.remoteVideoPort=remotePort;
        canvas = new Canvas();
        canvas.setBackground(SystemColor.desktop);
        canvas.setSize(800, 600);
        

        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.desktop);
        contentPane.setLayout(new BorderLayout());
        contentPane.add(canvas, BorderLayout.CENTER);

        frame = new JFrame(to);//TODO:get the sender name here 
        frame.setIconImage(new ImageIcon(getClass().getResource("../client/res/camera.png")).getImage());
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();

        factory = new MediaPlayerFactory("--no-video-title-show");//lagbe ki ???check minimaltestplayer 
        mediaPlayer = factory.newEmbeddedMediaPlayer();

        videoSurface = factory.newVideoSurface(canvas);

        mediaPlayer.setVideoSurface(videoSurface);
    	
    	/*/2nd try
    	final EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
    	String mrl=formatStream("rtp", remoteIP, remoteVideoPort);
    	System.out.println("remoteIP in vid player"+remoteIP);

        JFrame f = new JFrame("Test Player");
        //f.setIconImage(new ImageIcon(MinimalTestPlayer.class.getResource("/icons/vlcj-logo.png")).getImage());
        f.setSize(800, 600);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mediaPlayerComponent.release();
            }
        });
        f.setContentPane(mediaPlayerComponent);
        f.setVisible(true);
        Thread.currentThread().start();

        mediaPlayerComponent.getMediaPlayer().playMedia("rtp://127.0.0.1:5004");

        try {
			Thread.currentThread().join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}*/
        //1st try : 
        Thread playThread = new Thread(new PlayThread());
		playThread.start();
    }

    
    class PlayThread extends Thread{
    	//boolean terminate=false;
	    public void run() {
	        //1st try: 
	    	frame.setVisible(true);
	        
	        String mrl = formatStream("rtp", remoteIP, remoteVideoPort);//"rtp://"+remoteIP+":"+port;//TODO: get ip and port before this
	
	        String[] options = {
	            //":screen-fps=30",
	            //":screen-caching=100"
	        };
	        try{
	        	mediaPlayer.playMedia(mrl, options);
	        	sleep(100);
	        	synchronized(this) {
	        		while(terminate) {
	        			System.out.println("Inside termination of videoplyer1 ");
	        			//sleep(1000);
	        			wait();
	        			//join();
	        		}
	        	}
	        }catch(InterruptedException iex){
	        	//This thread is interrupted 
	        }
	    }
    
    	public void stopExec(){
    		terminate=true;
    	}
	    
    	private String formatStream(String protocol,String ip,int port){
    		StringBuilder sb=new StringBuilder(50);
    		sb.append(protocol);
    		sb.append("://");
    		sb.append(ip);
    		sb.append(":");
    		sb.append(remoteVideoPort);
    		return sb.toString();
    	}
    }
    
    void stopRunning(){
    	terminate=true;
    }
}