package audio;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.sound.sampled.*;
import compressdecompress.*;

public class AudioPlayer {
	
	boolean stopCapture = false;
	ByteArrayOutputStream byteArrayOutputStream;
	AudioFormat audioFormat;	
	AudioInputStream audioInputStream;
	SourceDataLine sourceDataLine;
	
	InputStream in;
	BufferedInputStream bufferedInputStream;
	//byte tempBuffer[] = new byte[10000];
	TargetDataLine tdl;
	
	public AudioPlayer(InputStream input){
		in=input;
	}
	/*public AudioPlayer(AudioChatManager audioChatManager){
		in=audioChatManager.input;
	}*/

	// This method creates and returns an
	// AudioFormat object for a given set
	// of format parameters. If these
	// parameters don't work well for
	// you, try some of the other
	// allowable parameter values, which
	// are shown in comments following
	// the declarations.
	private AudioFormat getAudioFormat() {
		float sampleRate = 8000.0F;
		/*// 8000,11025,16000,22050,44100
		int sampleSizeInBits = 16;
		// 8,16
		int channels = 1;
		// 1,2
		boolean signed = true;
		// true,false
		boolean bigEndian = false;
		// true,false
		return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);*/
		
		return new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
	    sampleRate,    // sampleRate
	    16,            // sampleSizeInBits
	    1,             // channels
	    2,             // frameSize
	    sampleRate,    // frameRate
	    false);        // bigEndian*/
	}// end getAudioFormat
	// ===================================//
	
	public void playAudio() {
		try {
			// Get everything set up for
			// playback.
			// Get the previously-saved data
			// into a byte array object.
			//byte audioData[] = byteArrayOutputStream.toByteArray();
			//bufferedInputStream=new BufferedInputStream(in);
			//byte audioData[] = ;
			// Get an input stream on the
			// byte array containing the data
			//InputStream byteArrayInputStream = new ByteArrayInputStream(audioData);
			AudioFormat audioFormat = getAudioFormat();
			/*audioInputStream = new AudioInputStream(byteArrayInputStream,
					audioFormat, audioData.length / audioFormat.getFrameSize());*/
			//int length=tempBuffer.length/audioFormat.getFrameSize();			
			//audioInputStream=new AudioInputStream(in, audioFormat, length);
			////tdl=new in;
			////audioInputStream=new AudioInputStream(tdl);
			/*int length =bufferedInputStream.available()/audioFormat.getFrameSize();
			audioInputStream=new AudioInputStream(bufferedInputStream, audioFormat, length);
			DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
			sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
			sourceDataLine.open(audioFormat);
			sourceDataLine.start();*/
			
			
			DataLine.Info dataLineInfo1 = new DataLine.Info(SourceDataLine.class, audioFormat);
			sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo1);
			sourceDataLine.open(audioFormat);
			sourceDataLine.start();

			// Create a thread to play back
			// the data and start it
			// running. It will run until
			// all the data has been played
			// back.
			Thread playThread = new Thread(new PlayThread());
			playThread.start();
		} catch (Exception e) {
			/*System.out.println(e);
			System.exit(0);*/
			e.printStackTrace();
		}// end catch
	}// end playAudio
	
	class PlayThread extends Thread {
		byte tempBuffer[] = new byte[10];
		CompressedBlockInputStream decompress;
		InputStream gzipin;
		public void run() {
			/*try {
				int cnt;
				// Keep looping until the input
				// read method returns -1 for
				// empty stream.
				while ((cnt = audioInputStream.read(tempBuffer, 0,tempBuffer.length)) != -1) {
					if (cnt > 0) {
						// Write data to the internal
						// buffer of the data line
						// where it will be delivered
						// to the speaker.
						sourceDataLine.write(tempBuffer, 0, cnt);
						System.out.println("In audio Player tempBuffer");//ADded Later
						//System.out.println(tempBuffer.toString());//ADded Later
					}// end if
				}// end while
				// Block and wait for internal
				// buffer of the data line to
				// empty.
				sourceDataLine.drain();
				sourceDataLine.close();
			} catch (Exception e) {
				/*System.out.println(e);
				System.exit(0);*/
				//e.printStackTrace();
			//}// end catchbyte tempBuffer[] = new byte[10000];
	
		try {
			decompress=new CompressedBlockInputStream(in);
			////while (in.read(tempBuffer) != -1) {
			while (decompress.read(tempBuffer) != -1) {
			/*gzipin=new GZIPInputStream(in);
			while(gzipin.read(tempBuffer)!=-1){*/
				sourceDataLine.write(tempBuffer, 0, 10);
			//BufferedReader inbuffreader = new BufferedReader(new InputStreamReader(new CompressedBlockInputStream(in)));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		}// end run
	}// end inner class PlayThread
	// ===================================//

}