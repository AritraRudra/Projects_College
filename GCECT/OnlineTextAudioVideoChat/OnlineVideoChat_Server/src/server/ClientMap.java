package server;

import java.util.HashMap;

public class ClientMap {
	
	//Adou ki lagbe	?? works like buddies bean
	static HashMap<String,String> hm = new HashMap<String,String>();
	   
    void mapped(String nam,String ip){
      if(hm.get(nam) == null){      
        hm.put(nam,ip);
        System.out.println(nam+" "+ip);
        System.out.print("In ClientMap");
      } 
    }
}