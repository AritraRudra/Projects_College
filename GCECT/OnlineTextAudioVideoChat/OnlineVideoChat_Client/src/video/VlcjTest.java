package video;

import java.io.File;
import java.util.StringTokenizer;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import uk.co.caprica.vlcj.logger.Logger;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.x.LibXUtil;

import com.sun.jna.NativeLibrary;

public abstract class VlcjTest {
  //private static final String VLCJ_LOG_LEVEL = "INFO";

  /**
   * Change this to point to your own vlc installation, or comment out the code
   * if you want to use your system default installation.
   * <p>
   * This is a bit more explicit than using the -Djna.library.path= system 
   * property.
   */
  
	static File toGetPath=new File("vlc");	
	static String vlcPath=toGetPath.getAbsolutePath();///*+"/vlc"*/,path;
	
	/*static{
		System.out.println("VLC PATH : "+vlcPath);
		StringTokenizer token=new StringTokenizer(vlcPath, "/");
		while(token.hasMoreTokens())
			path=token.nextToken()+"\\";	
	//path+=path+"vlc";
	System.out.println("PASSED PATH : "+path);
	}*/
		
	private static final String NATIVE_LIBRARY_SEARCH_PATH =vlcPath;//"E:\\Projects\\Online_Video_Chat\\Under_Development\\Under_Development_7\\Client\\Client\\vlc";//"E:\\Projects\\Online_Video_Chat\\vlc-2.0.2";//null; // WOW CHANGED
	//private static final String NATIVE_LIBRARY_SEARCH_PATH = getResource("vlc");
  
  /**
   * Set to true to dump out native JNA memory structures.
   */
  private static final String DUMP_NATIVE_MEMORY = "false";
  
  /**
   * Static initialisation.
   */
  static {
    /*if(null == System.getProperty("vlcj.log")) {
      System.setProperty("vlcj.log", VLCJ_LOG_LEVEL);
    }*/
    
    // Safely try to initialise LibX11 to reduce the opportunity for native
    // crashes - this will silently throw an Error on Windows (and maybe MacOS)
    // that can safely be ignored
    LibXUtil.initialise();		/////////***** Not working!! *****////////    platform.jar to be imported from vlcj
    
    if(null != NATIVE_LIBRARY_SEARCH_PATH) {
      Logger.info("Explicitly adding JNA native library search path: '{}'", NATIVE_LIBRARY_SEARCH_PATH);
      NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), NATIVE_LIBRARY_SEARCH_PATH);
      //NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "F:\\AmarProject\\JARs_LIBs\\vlc-2.0.2"); //added later
    }
    
    System.setProperty("jna.dump_memory", DUMP_NATIVE_MEMORY);
  }

  /**
   * Set the standard look and feel.
   */
  
  ////Lagbe kii????
  protected static final void setLookAndFeel() {
    String lookAndFeelClassName = null;
    LookAndFeelInfo[] lookAndFeelInfos = UIManager.getInstalledLookAndFeels();
    for(LookAndFeelInfo lookAndFeel : lookAndFeelInfos) {
      if("Nimbus".equals(lookAndFeel.getName())) {
        lookAndFeelClassName = lookAndFeel.getClassName();
      }
    }
    if(lookAndFeelClassName == null) {
      lookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
    }
    try {
      UIManager.setLookAndFeel(lookAndFeelClassName);
    }
    catch(Exception e) {
      // Silently fail, it doesn't matter
    }
  }
}

