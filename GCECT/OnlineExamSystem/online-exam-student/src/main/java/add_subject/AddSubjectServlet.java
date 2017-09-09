package add_subject;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class AddSubjectServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private String driver;
	private String url;
	private String usr;
	private String passwd;
	
	private Connection con =null;
	
    public AddSubjectServlet() {
        super();     
    }
    
    public void init()
    {
    	System.out.println("In init");
    	ServletContext context=getServletContext();
		driver=context.getInitParameter("driver");
		url=context.getInitParameter("url");
		usr=context.getInitParameter("usr");
		passwd=context.getInitParameter("passwd");
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,usr,passwd);
			
		} catch (ClassNotFoundException e) {			
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{		
		process(request, response);
	}
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 	{
		RequestDispatcher rd_success=request.getRequestDispatcher("/add_new_question.jsp");
		RequestDispatcher rd_error=request.getRequestDispatcher("/displayerror.jsp");
		
		try{
			HttpSession session=request.getSession(true);
			String subject=request.getParameter("new_subject");
			
			if(subject!=null){
				session.setAttribute("SESS_SUBJ",subject);				
				rd_success.forward(request, response);
			}else{
				response.sendRedirect("addsubject.jsp");
			}
		}
		catch (Exception e) 
		{
			String errmsg="GENERAL ERROR OCCURED."+e.getMessage();
			request.setAttribute("ERRORMSG",errmsg);
			rd_error.forward(request,response);
		}
	}

	public void destroy()
	{
		System.out.println("In destroy.");
		if(con!=null){
			try{
				con.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
