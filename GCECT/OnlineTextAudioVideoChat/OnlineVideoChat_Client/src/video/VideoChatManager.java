package video;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VideoChatManager {
	VideoSender videoSender;
	//VideoPlayer videoPlayer;
	VideoPlayer1 videoPlayer1;
	ExecutorService videoSendingExecutor,videoRecvngExecutor;
	public void videoSender(String myIP,int myPort){
		System.out.println("In Video Chat manager->video sender ,myIP, myPort :"+myIP+myPort);
		videoSendingExecutor=Executors.newCachedThreadPool();
		//videoSendingExecutor.execute(new VideoSender(myIP.substring(1),myPort));
		//VideoSender videoSender=new VideoSender(myIP.substring(1));
		videoSender=new VideoSender(myIP.substring(1),myPort);
		videoSender.run();
	}
	
	/*public void videoReceiver(String remoteIP,String to){
		System.out.println("In Video Chat manager->video receiver ,remote IP ,to :"+remoteIP+to);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		VideoPlayer videoPlayer=new VideoPlayer(remoteIP.substring(1),to);
		videoPlayer.start();
		//videoPlayer.playVideo();
	}*/
	
	public void videoReceiver(String remoteIP,int remotePort,String to){//block hoe jachhe by previous sender
		System.out.println("In Video Chat manager->video receiver ,remote IP, remotePort,to :"+remoteIP+remotePort+to);
		videoPlayer1=new VideoPlayer1(remoteIP.substring(1),remotePort,to);
		//videoRecvngExecutor=Executors.newCachedThreadPool();
		//videoRecvngExecutor.execute(new VideoPlayer1(remoteIP.substring(1), remotePort, to));
	}
	
	public void stopVideoChat(){
		try{
			System.out.println("In Video Chat manager->stopvideoChat()");
		videoSender.stopExec();
		videoPlayer1.stopRunning();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		//videoSendingExecutor.shutdown();
		//videoRecvngExecutor.shutdown();
		
		// wait for threads to finish		//ekane hobe naki stopVideoChat()  er baire hobe
		/*try {
		System.out.println("Waiting for threads to finish.");
		/*ob1.t.join();
		ob2.t.join();
		} catch (InterruptedException e) {
		
		}*/
	}
	
	
}