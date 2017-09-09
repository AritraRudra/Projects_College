package add_quest;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import taking_test.QuestionBean;

public class ConfirmPageServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private String driver;
	private String url;
	private String usr;
	private String passwd;	
	private Connection con =null;
	private PreparedStatement questpstmt=null;
	private String add_question="insert into ques(question,subject,level,opt1,opt2,opt3,opt4,corr_opt) values(?,?,?,?,?,?,?,?)";   
   
    public ConfirmPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init()
    {
    	System.out.println("In init");
    	ServletContext context=getServletContext();
		driver=context.getInitParameter("driver");
		url=context.getInitParameter("url");
		usr=context.getInitParameter("usr");
		passwd=context.getInitParameter("passwd");
		try
		{
			Class.forName(driver);
			con=DriverManager.getConnection(url,usr,passwd);
			questpstmt=con.prepareStatement(add_question);			
		}
		catch (ClassNotFoundException e) 
		{
			System.out.println("init()--ERROR LOADING DRIVER");
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			System.out.println("init()--ERROR IN SQL : \n"+e.getMessage());
			e.printStackTrace();
		}
		catch (Exception e) 
		{			
			System.out.println("init()--ERROR DUE TO SOMETHING ELSE : \n");
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		process(request, response);
	}

	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd_success=request.getRequestDispatcher("/add_new_question.jsp");
		RequestDispatcher rd_error=request.getRequestDispatcher("/displayerror.jsp");
		
		try{
			HttpSession session=request.getSession(true);
			
			QuestionBean qb=(QuestionBean)session.getAttribute("QUES");
			
			questpstmt.setString(1, qb.getQuestion());
			questpstmt.setString(2, qb.getSubject());
			questpstmt.setString(3, qb.getLevel());
			questpstmt.setString(4, qb.getOpt1());
			questpstmt.setString(5, qb.getOpt2());
			questpstmt.setString(6, qb.getOpt3());
			questpstmt.setString(7, qb.getOpt4());			
			questpstmt.setString(8, qb.getCorr_opt());
			
			questpstmt.executeUpdate();						
			rd_success.forward(request, response);
		}
		catch (SQLException e) 	{
			String errmsg="ERROR IN SQL OPERATION : "+e.getMessage();
			request.setAttribute("ERRORMSG",errmsg);
			rd_error.forward(request,response);
		}
		catch (Exception e)		{
			String errmsg="GENERAL ERROR OCCURED."+e.getMessage();
			request.setAttribute("ERRORMSG",errmsg);
			rd_error.forward(request,response);			
		}
	}
	

	public void destroy(){
		System.out.println("In destroy");
		if(con!=null){
			try{
				con.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
