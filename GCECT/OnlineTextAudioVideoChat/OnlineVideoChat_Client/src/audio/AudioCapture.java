package audio;

import java.io.*;
import javax.sound.sampled.*;
import java.util.zip.*;
import compressdecompress.*;

//import audio.AudioChatManager.AudioSenderReceiver;

public class AudioCapture {
	// Audio variables
	boolean stopCapture = false;
	ByteArrayOutputStream byteArrayOutputStream;
	AudioFormat audioFormat;
	TargetDataLine targetDataLine;
	AudioInputStream audioInputStream;
	SourceDataLine sourceDataLine;
	
	OutputStream out;

	// Control variables
	
	
	public AudioCapture(OutputStream output){
		out=output;
	}

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
		// 8000,11025,16000,22050,44100
		/*int sampleSizeInBits = 16;
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
	    false);        // bigEndian
	}// end getAudioFormat
	// ===================================//

	// This method captures audio input
	// from a microphone and saves it in
	// a ByteArrayOutputStream object.
	public void captureAudio() {
		try {
			// Get everything set up for
			// capture
			audioFormat = getAudioFormat();
			DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
			targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
			targetDataLine.open(audioFormat);
			targetDataLine.start();

			// Create a thread to capture the
			// microphone data and start it
			// running. It will run until
			// the Stop button is clicked.
			Thread captureThread = new Thread(new CaptureThread());
			captureThread.start();
		} catch (Exception e) {
			/*System.out.println(e);
			System.exit(0);*/
			e.printStackTrace();
		}// end catch
	}// end captureAudio method

	// Inner class to capture data from
	// microphone
	class CaptureThread extends Thread {
		// An arbitrary-size temporary holding
		// buffer
		byte tempBuffer[] = new byte[10];//normally 10000
		
		public void run() {
			byteArrayOutputStream = new ByteArrayOutputStream();
			stopCapture = false;
			try {
				//////OutputStream gzipout=new GZIPOutputStream(out);
				CompressedBlockOutputStream compressed = new CompressedBlockOutputStream(out, tempBuffer.length);
				// Loop until stopCapture is set
				// by another thread that
				// services the Stop button.
				//System.out.println("In AudioCapture->CaptureThred->run");
				while (!stopCapture) {
					// Read data from the internal
					// buffer of the data line.
					int cnt = targetDataLine.read(tempBuffer, 0, tempBuffer.length);
					// Save data in output stream
					// object.
					////out.write(tempBuffer);
					//////gzipout.write(tempBuffer);	//jhajhajhajhaahhhh
					
					// Make a stream that compresses outgoing data,
			        // sending a compressed block for every 1K worth of
			        // uncompressed data written.
					//compressed.compressAndSend();
			        compressed.write(tempBuffer,0,tempBuffer.length);
			        //outstreamwriter =new OutputStreamWriter(compressed);
			        //outstreamwriter.
					/*if (cnt > 0) {						
						byteArrayOutputStream.write(tempBuffer, 0, cnt);						
						//System.out.println("in audiocapture tempBuffer");// ADded Later
					}// end if*/
				}// end whiles
				//byteArrayOutputStream.close();
			
				/*byteArrayOutputStream = new ByteArrayOutputStream();
				stopCapture = false;
				try {
					while (!stopCapture) {	
						int cnt = targetDataLine.read(tempBuffer, 0, tempBuffer.length);
						out.write(tempBuffer);
						if (cnt > 0) {
							byteArrayOutputStream.write(tempBuffer, 0, cnt);
						}
					}
				byteArrayOutputStream.close();*/
				
			} catch (Exception e) {
				/*System.out.println(e);
				System.exit(0);*/
				e.printStackTrace();
			}// end catch
		}// end run
	}// end inner class CaptureThread

}