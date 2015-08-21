package video;

import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;

/**
 * An example of how to stream a media file using RTP.
 * <p>
 * The client specifies an MRL of <code>rtp://@230.0.0.1:5555</code>
 */

public class VideoSender extends VlcjTest implements Runnable {

	String media; // args[0];
	String options;// = formatRtpStream("127.0.0.1", 5004);
	HeadlessMediaPlayer mediaPlayer;
	//String ip;
	//int senderPort;//=5004;
	volatile boolean terminate=false;

	// System.out.println("Streaming '" + media + "' to '" + options + "'");
	public VideoSender(String myIP,int myPort) {
		System.out.println("In Video sender myIP,myPort :"+myIP+myPort);
		MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory("--no-video-title-show");// -I rc dshow://
		mediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();
		if(myPort==5004)
			media="dshow://";//"E:\\Projects\\Online_Video_Chat\\Under_Development\\Version_x1\\Client\\Client\\1.mp4";//"dshow://";
		else
			media="dshow://";//"E:\\Projects\\Online_Video_Chat\\Under_Development\\Version_x1\\Client\\Client\\2.mpg";
		options = formatRtpStream(myIP, myPort);
		//System.out.println(options);
		////mediaPlayer.playMedia(media, options,
				// ":no-sout-rtp-sap",
				// ":no-sout-standard-sap",
				// ":sout-all",
				////":sout-keep"); 		// dshow:// :sout=#transcode{vcodec=mp2v,vb=800,scale=1,acodec=mpga,ab=128,channels=2,samplerate=44100}:rtp{dst=127.0.0.1,port=5004,mux=ts,ttl=1}
		// :sout-keep

		// Don't exit  changed by me
		/*try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
	}

	private String formatRtpStream(String serverAddress, int serverPort) {
		StringBuilder sb = new StringBuilder(150);
		// by me //
		sb.append(":sout=#transcode{vcodec=mp2v,vb=400,acodec=mp3,ab=64,channels=1,samplerate=8000}");
		// till here//
		// sb.append(":sout=#rtp{dst=");
		sb.append(":rtp{dst=");
		sb.append(serverAddress);
		sb.append(",port=");
		sb.append(serverPort);
		sb.append(",mux=ts}");
		return sb.toString();
	}

	public void run() {
		//try{
			mediaPlayer.playMedia(media, options,":sout-keep");
			/*synchronized(this) {
				while(terminate) {
					System.out.println("Inside termination of videosender ");
					wait();
					Thread.currentThread().join();
				}
			}
		}catch(InterruptedException iex){
			//This thread is interrupted 
		}*/
	}
	
	public void stopExec(){
		System.out.println("Inside termination of videosender ");
		mediaPlayer.stop();
		 //notify();
		//terminate=true;
	}
}