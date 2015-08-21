package signup;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String driver;
	private String url;
	private String usr;
	private String passwd;
	
	private Connection con =null;
	private PreparedStatement psmnt=null;
	private String insert_student="insert into student (uname,fstname,lstname,email,passwd) values(?,?,?,?,?)";

    public void init(){
    	ServletContext context=getServletContext();
    	driver=context.getInitParameter("driver");		
		url=context.getInitParameter("url");
		usr=context.getInitParameter("usr");
		passwd=context.getInitParameter("passwd");
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,usr,passwd);
			psmnt=con.prepareStatement(insert_student);	
		} catch (ClassNotFoundException e) {
			//for debugging
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
       
   public SignUpServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request,response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//retrieving form data
		String struname=request.getParameter("uname");
		String strfstname=request.getParameter("fstname");
		String strlstname=request.getParameter("lstname");
		String stremail=request.getParameter("email");
		String strpasswd=request.getParameter("passwd");
						
		RequestDispatcher rd_success=request.getRequestDispatcher("/success.jsp");
		RequestDispatcher rd_error=request.getRequestDispatcher("/displayerror.jsp");
				
		try {					
			psmnt.setString(1,struname);
			psmnt.setString(2,strfstname);
			psmnt.setString(3,strlstname);
			psmnt.setString(4,stremail);
			psmnt.setString(5,strpasswd);
			psmnt.executeUpdate();
			
			HttpSession session=request.getSession(true);				
			session.setAttribute("SESS_USER",struname);
			session.setAttribute("SESS_LOGIN_TIME",new java.util.Date());
			session.setAttribute("SESS_VALID",true);
			
			rd_success.forward(request,response);
			
		}catch (SQLException e) {
			
			String errmsg="PLEASE SUPPLY ALL THE FIELDS : "+e.getMessage();
			request.setAttribute("ERRORMSG",errmsg);
			rd_error.forward(request,response);
		}catch (Exception e) {
			String errmsg="GENERAL ERROR OCCURED."+e.getMessage();
			request.setAttribute("ERRORMSG",errmsg);
			rd_error.forward(request,response);			
		}
	}
	
	public void destroy(){
		if(con!=null){
			try{
				con.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
