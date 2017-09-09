package login;
import java.util.*;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Servlet implementation class StudentLoginServlet
 */
public class StudentLoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	private String driver;
	private String url;
	private String usr;
	private String passwd;
	
	private Connection con =null;
	private PreparedStatement psmnt=null,adminpstmt=null,subjpstmt=null;
	private String search_student="select uname,fstname,lstname,email from student where email=? and passwd=?";
	private String search_admin="select sid,uname,fstname,lstname,email from  student where email=? and passwd=?";
	private String subject="select distinct(subject) from ques";

    /**
     * Default constructor. 
     */
    public StudentLoginServlet()
    {
        // TODO Auto-generated constructor stub
    }
    
    public void init()
    {
    	ServletContext context=getServletContext();
		driver=context.getInitParameter("driver");
		url=context.getInitParameter("url");
		usr=context.getInitParameter("usr");
		passwd=context.getInitParameter("passwd");
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,usr,passwd);
			psmnt=con.prepareStatement(search_student);
			adminpstmt=con.prepareStatement(search_admin);
			subjpstmt=con.prepareStatement(subject);
			
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			//MUST REMOVE THESE CONSOLE MESSAGES.
			System.out.println("init()--ERROR LOADING DRIVER");
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO: handle exception
			System.out.println("init()--ERROR IN SQL : \n"+e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("init()--ERROR DUE TO SOMETHING ELSE : \n");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		process(request,response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		String stremail=request.getParameter("email");
		String strpasswd=request.getParameter("passwd");
		
		String ademail=request.getParameter("loginid");
		String adpasswd=request.getParameter("password");
				
		RequestDispatcher rd_success=request.getRequestDispatcher("/loginsuccess.jsp");
		RequestDispatcher rd_admin=request.getRequestDispatcher("/adminloginsuccess.jsp");
		RequestDispatcher rd_error=request.getRequestDispatcher("/displayerror.jsp");
		RequestDispatcher rd_login_fail=request.getRequestDispatcher("/index.jsp");
		
		try 
		{
			psmnt.setString(1,stremail);
			psmnt.setString(2,strpasswd);
			
			adminpstmt.setString(1,ademail);
			adminpstmt.setString(2,adpasswd);
			
			ResultSet rs=psmnt.executeQuery();
			ResultSet rs1=adminpstmt.executeQuery();
			ResultSet rs2=subjpstmt.executeQuery();
			
			HttpSession session=request.getSession(true);
			
			if(rs.next())
			{
				
				rd_success.forward(request,response);
			}
			else if(rs1.next())
			{				
				String adminid=rs1.getString(1);
				String fname=rs1.getString(2);
				String lname=rs1.getString(3);
				String emailid=rs1.getString(4);
				ArrayList<SubjectList> list=new ArrayList<SubjectList>();
				AdminBean ab=new AdminBean(adminid,fname,lname,emailid);
				
				request.setAttribute("ADMINISTRATOR", ab);
				while(rs2.next())
				{
					String subj=rs2.getString(1);
					SubjectList sl=new SubjectList(subj);
					list.add(sl);
				}				
				request.setAttribute("SUBJECT",list);
				
				session.setAttribute("SESS_USER",adminid);
				session.setAttribute("SESS_LOGIN_TIME",new java.util.Date());
				session.setAttribute("SESS_VALID","true");
				
				rd_admin.forward(request, response);
				
			}
			else 
			{				
				String errmsg="INVALID COMBINATION OF USERNAME AND PASSWORD.";
				request.setAttribute("ERRORMSG",errmsg);
				request.setAttribute("ERRORMSG1",errmsg);
				rd_login_fail.forward(request,response);												
			}
		}
		catch (SQLException e) 
		{
			// TODO: handle exception
			String errmsg="ERROR IN SQL OPERATION : "+e.getMessage();
			request.setAttribute("ERRORMSG",errmsg);
			rd_error.forward(request,response);
		}
		catch (NullPointerException e) 
		{
			// TODO: handle exception
			String errmsg="INVALID COMBINATION OF USERNAME AND PASSWORD.";
			request.setAttribute("ERRORMSG",errmsg);
			request.setAttribute("ERRORMSG1",errmsg);
			rd_login_fail.forward(request,response);
			//response.sendRedirect("index.jsp?MSG=INVALID COMBINATION OF USERNAME AND PASSWORD.");
		}
		catch (Exception e) 
		{
			String errmsg="GENERAL ERROR OCCURED."+e.getMessage();
			request.setAttribute("ERRORMSG",errmsg);
			rd_error.forward(request,response);
			//response.sendRedirect("index.jsp?MSG=ERROR");
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
