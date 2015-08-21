package server;

import java.sql.*;

public class DBOps {
	private String driver;
	private String url;
	private String usr;
	private String passwd;
	
	private Connection con = null;
	protected PreparedStatement psmnt_insert_user=null,psmnt_search_user=null,psmnt_get_uid=null;
	private String insert_user="insert into user(uname,fstname,lstname,email,passwd) values(?,?,?,?,?)";
	private String search_user="select uname,passwd from user where uname=? and passwd=?";		
	private String get_uid="select (uid) from user where uname=?";
	
	private void openConnection(){
		
	   	/*driver=context.getInitParameter("driver");
		url=context.getInitParameter("url");
		usr=context.getInitParameter("usr");
		passwd=context.getInitParameter("passwd");*/
		
		driver="com.mysql.jdbc.Driver";
		url="jdbc:mysql://localhost:3306/vdoconf";
		usr="root";
		passwd="root";
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,usr,passwd);
			psmnt_search_user=con.prepareStatement(search_user);
			psmnt_insert_user=con.prepareStatement(insert_user);
			psmnt_get_uid=con.prepareStatement(get_uid);
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR LOADING DRIVER");
			e.printStackTrace();
		}catch (SQLException e) {
			System.out.println("ERROR IN SQL : \n"+e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println("ERROR DUE TO SOMETHING ELSE : \n");
			e.printStackTrace();
		}
	}	
	
	private void closeConnection(){
		if(con!=null){	
			try{
				con.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean DBOps_Search(String userName,String passWord) {
		
		boolean gotit=false;
		openConnection();
		try {			
			psmnt_search_user.setString(1,userName);		
			psmnt_search_user.setString(2,passWord);		
			ResultSet rs_user=psmnt_search_user.executeQuery();			
			if(rs_user.next())
				gotit= true;						
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		closeConnection();
		return gotit;
	}
}