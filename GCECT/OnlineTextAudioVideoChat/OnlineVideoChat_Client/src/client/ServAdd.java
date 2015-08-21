package client;

import java.io.InputStream;
import java.io.FileInputStream;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class ServAdd {
	
	String address="127.0.0.1";  	//by default loopback	
	SAXParserFactory factory = SAXParserFactory.newInstance();
	public String getAddress(){
	try {
	    InputStream xmlInput  = new FileInputStream("servadd");
		SAXParser saxParser = factory.newSAXParser();
	    
	    DefaultHandler handler   = new SaxHandler();
	    saxParser.parse(xmlInput, handler);
	    //address=handler.;
	}catch(Exception exp){}
	//System.out.println(address);
	return address;
	}


class SaxHandler extends DefaultHandler {
	
    public void startDocument() throws SAXException {
    }

    public void endDocument() throws SAXException {
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
    }

    public void characters(char ch[], int start, int length) throws SAXException {
    	address=new String(ch, start,length);
    	//System.out.println(address);
    }

    /*public void ignorableWhitespace(char ch[], int start, int length) throws SAXException {
    }*/
}
}

/*	
	//Create a "parser factory" for creating SAX parsers
    SAXParserFactory spfac = SAXParserFactory.newInstance();

    //Now use the parser factory to create a SAXParser object
    SAXParser sp = spfac.newSAXParser();

    //Create an instance of this class; it defines all the handler methods
    ServAdd handler = new ServAdd();

    //Finally, tell the parser to parse the input and notify the handler
    sp.parse("bank.xml", handler);
   
    handler.readList();
    
    /*
     * When the parser encounters plain text (not XML elements),
     * it calls(this method, which accumulates them in a string buffer
     */
/*    public void characters(char[] buffer, int start, int length) {
           temp = new String(buffer, start, length);
    }
   

    /*
     * Every time the parser encounters the beginning of a new element,
     * it calls this method, which resets the string buffer
     */ 
    /*public void startElement(String uri, String localName,
                  String qName, Attributes attributes) throws SAXException {
           temp = "";
           if (qName.equalsIgnoreCase("Account")) {
                  acct = new Account();
                  acct.setType(attributes.getValue("type"));

           }
    }

    /*
     * When the parser encounters the end of an element, it calls this method
     */
/*    public void endElement(String uri, String localName, String qName)
                  throws SAXException {

           if (qName.equalsIgnoreCase("Account")) {
                  // add it to the list
                  accList.add(acct);

           } else if (qName.equalsIgnoreCase("Name")) {
                  acct.setName(temp);
           } else if (qName.equalsIgnoreCase("Id")) {
                  acct.setId(Integer.parseInt(temp));
           } else if (qName.equalsIgnoreCase("Amt")) {
                  acct.setAmt(Integer.parseInt(temp));
           }

    }

    private void readList() {
           System.out.println("No of  the accounts in bank '" + accList.size()  + "'.");
           Iterator<Account> it = accList.iterator();
           while (it.hasNext()) {
                  System.out.println(it.next().toString());
           }
    }

*/
	
	
	
	/***{
	 try {
		 
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
		 
			DefaultHandler handler = new DefaultHandler() {
		 
			boolean bfname = false;
			boolean blname = false;
			boolean bnname = false;
			boolean bsalary = false;
		 
			public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
				System.out.println("Start Element :" + qName);
		 
				if (qName.equalsIgnoreCase("FIRSTNAME")) {
					bfname = true;
				}
		 
				if (qName.equalsIgnoreCase("LASTNAME")) {
					blname = true;
				}
		 
				if (qName.equalsIgnoreCase("NICKNAME")) {
					bnname = true;
				}
		 
				if (qName.equalsIgnoreCase("SALARY")) {
					bsalary = true;
				}
			}
		 
			public void endElement(String uri, String localName,String qName) throws SAXException {		 
				System.out.println("End Element :" + qName);
			}
		 
			public void characters(char ch[], int start, int length) throws SAXException {
		 
				if (bfname) {
					System.out.println("First Name : " + new String(ch, start, length));
					bfname = false;
				}
		 
				if (blname) {
					System.out.println("Last Name : " + new String(ch, start, length));
					blname = false;
				}
		 
				if (bnname) {
					System.out.println("Nick Name : " + new String(ch, start, length));
					bnname = false;
				}
		 
				if (bsalary) {
					System.out.println("Salary : " + new String(ch, start, length));
					bsalary = false;
				}
		 
			}
		 
		     };
		 
		       saxParser.parse("c:\\file.xml", handler);
		 
		     } catch (Exception e) {
		       e.printStackTrace();
		     }
	}	 	
}****/

/*
<company>
<staff>
	<firstname>yong</firstname>
	<lastname>mook kim</lastname>
	<nickname>mkyong</nickname>
	<salary>100000</salary>
</staff>
<staff>
	<firstname>low</firstname>
	<lastname>yin fong</lastname>
	<nickname>fong fong</nickname>
	<salary>200000</salary>
</staff>
</company>
*/