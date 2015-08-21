package client;

import java.io.*;
import java.util.Enumeration;
import java.util.jar.*;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;

public class UnpackExtract {
	JarFile jarFile;
	ZipFile zipFile;
	
	void unPack()throws IOException,JarException {
		jarFile = new JarFile("Client.jar");
		//ZipFile jarFile = new ZipFile("vlc-2.0.2.zip");
		////Enumeration en = jarFile.entries();
		Enumeration en = jarFile.entries();		//**Edited here**//
		String entry;	//=proc(en.nextElement());		
		while (en.hasMoreElements()) {
			entry=getName(en.nextElement());
			if(entry.startsWith("2.0.5.win64")){
				if(entry.indexOf("/")>0){
					String file=entry.substring(0,entry.lastIndexOf("/"));
					System.out.println(file);
					File local=new File(file);
					//getAbsPath=local.getAbsolutePath();
					//String absPath=local.getAbsolutePath();
					if(!local.exists())
						local.mkdirs();
				}
				if(entry.indexOf(".")>0){
					int n=entry.length();
					String fil1=entry.substring(entry.lastIndexOf("/")+1,n);
					System.out.println(fil1);
					extractEachFile(jarFile.getName(),entry,null);  
				}
			}
		}
		jarFile.close();	///****added later to manage resource leak
		
		/*File delete = new File("2.0.5.win64");		
		delete.deleteOnExit();*/
	}
	
	/*void  extract()throws IOException,ZipException {
		//jarFile = new JarFile("Client.jar");
		zipFile = new ZipFile("vlc_x64.zip");
		////Enumeration en = jarFile.entries();
		Enumeration en = zipFile.entries();		//**Edited here**/
		/*String entry;	//=proc(en.nextElement());		
		while (en.hasMoreElements()) {
			entry=getName(en.nextElement());
			//if(entry.startsWith("vlc-2.0.2")){
				if(entry.indexOf("/")>0){
					String file=entry.substring(0,entry.lastIndexOf("/"));
					System.out.println(file);
					File local=new File("vlc/"+file);
					//String absPath=local.getAbsolutePath();
					//System.out.println("Canonical Path: "+local.getCanonicalPath());
					//System.out.println("Abs Path: "+local.getAbsolutePath());
					if(!local.exists())
						local.mkdirs();
				}
				if(entry.indexOf(".")>0){
					int n=entry.length();
					String fil1=entry.substring(entry.lastIndexOf("/")+1,n);
					System.out.println(fil1);
					extractEachFile(zipFile.getName(),entry,"vlc/");  
				}
			//}
		}
		zipFile.close();	///**** added later 
	}*/

	public String getName(Object obj){
		ZipEntry entry = (ZipEntry)obj;
		String name = entry.getName();
		System.out.println("\nEntry Name: "+name);
		return(name);
	}

	public void extractEachFile(String jarName,String entryName,String parentDir)throws IOException,ZipException{
		JarFile jar = new JarFile(jarName);		//use the global jar
		System.out.println(jarName + " opened.");

		try {
			// Get the entry and its input stream.
			JarEntry entry = jar.getJarEntry(entryName);

			// If the entry is not null, extract it. Otherwise, print a  message.			
			if (entry != null) {
				// Get an input stream for the entry.
				InputStream entryStream = jar.getInputStream(entry);
				try {
					FileOutputStream file;
					if(parentDir!=null)
					// Create the output file (clobbering the file if it exists).
					 file = new FileOutputStream(parentDir+entry.getName());
					else
						file = new FileOutputStream(entry.getName());

					try {
						// Allocate a buffer for reading the entry data.

						byte[] buffer = new byte[1024];
						int bytesRead;

						// Read the entry data and write it to the output file.

						while ((bytesRead = entryStream.read(buffer)) != -1) {
							file.write(buffer, 0, bytesRead);
						}

						System.out.println(entry.getName() + " extracted.");
					}
					finally {
						file.close();
					}
				}
				finally {
					entryStream.close();
				}
			}
			else {
				System.out.println(entryName + " not found.");
			} // end if
		}
		finally {
			jar.close();
			System.out.println(jarName + " closed.");
		}
	}
	
	/*public void extractEachFile1(String jarName,String entryName)throws IOException,ZipException{
		JarFile jar = new JarFile(jarName);		//use the global jar
		System.out.println(jarName + " opened.");

		try {
			// Get the entry and its input stream.
			JarEntry entry = jar.getJarEntry(entryName);

			// If the entry is not null, extract it. Otherwise, print a  message.			
			if (entry != null) {
				// Get an input stream for the entry.
				InputStream entryStream = jar.getInputStream(entry);
				try {
					// Create the output file (clobbering the file if it exists).
					FileOutputStream file = new FileOutputStream("vlc/"+entry.getName());

					try {
						// Allocate a buffer for reading the entry data.

						byte[] buffer = new byte[1024];
						int bytesRead;

						// Read the entry data and write it to the output file.

						while ((bytesRead = entryStream.read(buffer)) != -1) {
							file.write(buffer, 0, bytesRead);
						}

						System.out.println(entry.getName() + " extracted.");
					}
					finally {
						file.close();
					}
				}
				finally {
					entryStream.close();
				}
			}
			else {
				System.out.println(entryName + " not found.");
			} // end if
		}
		finally {
			jar.close();
			System.out.println(jarName + " closed.");
		}
	}*/
}