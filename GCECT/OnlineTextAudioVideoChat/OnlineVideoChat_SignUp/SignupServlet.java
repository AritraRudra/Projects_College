

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUp
 */
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String driver;
	private String url;
	private String usr;
	private String passwd;
	
	private Connection con=null;
	private PreparedStatement psmnt_insert_user=null,psmnt_search_user=null,psmnt_get_uid=null;
	private String insert_user="insert into user(uname,fstname,lstname,email,passwd) values(?,?,?,?,?)";
	private String search_user="select uname,passwd from user where uname=? and passwd=?";		
	private String get_uid="select (uid) from user where uname=?";
    
    public SignupServlet() {}
    
    public void init(){
	   	ServletContext context=getServletContext();
	   	driver=context.getInitParameter("driver");
		url=context.getInitParameter("url");
		usr=context.getInitParameter("usr");
		passwd=context.getInitParameter("passwd");
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,usr,passwd);
			psmnt_insert_user=con.prepareStatement(insert_user);
			//psmnt_search_user=con.prepareStatement(search_user);	
			psmnt_get_uid=con.prepareStatement(get_uid);
			
			System.out.println("In main connection servlet");				
		}catch (ClassNotFoundException e) {
			System.out.println("init()--ERROR LOADING DRIVER");
			e.printStackTrace();
		}catch (SQLException e) {
			System.out.println("init()--ERROR IN SQL : \n"+e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println("init()--ERROR DUE TO SOMETHING ELSE : \n");
			e.printStackTrace();
		}
    }
    
    public void destroy() {
    	if(con!=null){
    		try{
    			con.close();
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    }
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}
    
    
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    			
		//retrieving form data
		String struname=request.getParameter("uname");
		String strfstname=request.getParameter("fstname");
		String strlstname=request.getParameter("lstname");
		String stremail=request.getParameter("email");
		String strpasswd=request.getParameter("passwd");
						
		RequestDispatcher rd_user=request.getRequestDispatcher("/signup.jsp");
		//RequestDispatcher rd_error=request.getRequestDispatcher("/displayerror.jsp");	
				
		try {	
			System.out.println("In signup servlet");
			psmnt_insert_user.setString(1,struname);
			psmnt_insert_user.setString(2,strfstname);
			psmnt_insert_user.setString(3,strlstname);
			psmnt_insert_user.setString(4,stremail);
			psmnt_insert_user.setString(5,strpasswd);
			
			psmnt_insert_user.executeUpdate();	
			System.out.println("Successful Signup");
			String OKmsg="SIGN UP IS SUCCESSFUL";
			request.setAttribute("MSG",OKmsg);
			rd_user.forward(request,response);			
		}catch (SQLException e) {			
			//String errmsg="SAME USERNAME EMAIL COMBINATION ALREADY EXISTS CHOOSE ANOTHER COMBINATION "+e.getMessage();
			//request.setAttribute("ERRORMSG",errmsg);
			//rd_error.forward(request,response);
			response.sendRedirect("signup.jsp?ERRORMSG=SAME USERNAME EMAIL COMBINATION ALREADY EXISTS CHOOSE ANOTHER COMBINATION ");
		}catch (Exception e) {
			String errmsg="GENERAL ERROR OCCURED. PLEASE TRY AGAIN"+e.getMessage();
			request.setAttribute("MSG",errmsg);
			rd_user.forward(request,response);
		}
	}

}
