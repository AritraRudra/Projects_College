package client;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.jar.JarException;
import java.util.zip.ZipException;
import net.sf.sevenzipjbinding.SevenZipException;

/**
 * @author Aritra
 */

public class ChatClient {
	public static void main(String a[]){	
		ClientManager clientManager=new ClientManager();
        LoginFrame loginFrame=new LoginFrame(clientManager);
        loginFrame.setVisible(true);
        
        
        ///// *****		To check if the files have already been extracted		***** /////
        File exists=new File("vlc");
        if(!exists.exists()){
	        String absPath=exists.getAbsolutePath()+"\\";	/////##### may lead to error on unix as PATH String is " / "
	        
	        //to get the abs path in this string  ## if 7zip is used
	        //String absPath=new File("vlc").getAbsolutePath()+"\\";
	        
	        ////**** for unpacking and extracting	****////
	        UnpackExtract extract = new UnpackExtract();
	        try {
				extract.unPack();
				//extract.extract();  //if 7zip is used
			} catch (JarException jarExp) {
				jarExp.printStackTrace();
			}catch (ZipException zipExp) {
				zipExp.printStackTrace();
			}catch (IOException ioExp) {
				ioExp.printStackTrace();
			}
	        
	        /*System.out.println("GOT PATH : "+absPath);
			StringTokenizer tokens=new StringTokenizer(absPath, "/");
			while(tokens.hasMoreTokens())
				absPath=tokens.nextToken()+"\\";
			//path+=path+"vlc";
			System.out.println("PASSED PATH : "+absPath);
			
			
	        /////*****	Extract using 7zipJBinder  *****/////
	        
	        //String file="vlc-2.0.5-win64";
			//String extractPath="E:\\Projects\\Online_Video_Chat\\By_Others\\temp\\Unpacking_JARs\\7zip_LZMA\\";
			SevenZipJBindingExtractor lzmaExt=new SevenZipJBindingExtractor();
			try {
				lzmaExt.extract(absPath);//(file, extractPath);
			} catch (SevenZipException e) {			
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			File delete = new File("2.0.5.win64");
			delete.delete();
        }
        
	}
}