package add_quest;

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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddQuestionServlet
 */
public class AddQuestionServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private String driver;
	private String url;
	private String usr;
	private String passwd;	
	private Connection con =null;
	private PreparedStatement questpstmt=null;
	//private String add_question="insert into ques(question,subject,level,opt1,opt2,opt3,opt4,corr_opt) values(?,?,?,?,?,?,?,?)";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddQuestionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init()
    {
    	ServletContext context=getServletContext();
		driver=context.getInitParameter("driver");
		url=context.getInitParameter("url");
		usr=context.getInitParameter("usr");
		passwd=context.getInitParameter("passwd");
		try
		{
			Class.forName(driver);
			con=DriverManager.getConnection(url,usr,passwd);
			//questpstmt=con.prepareStatement(add_question);			
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
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		process(request, response);
	}
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
				
		HttpSession session=request.getSession(true);
		
		String question=request.getParameter("question");
		//String subject=(String)session.getAttribute("SESS_SUBJ");
		String level=request.getParameter("level");
		String option1=request.getParameter("opt1");
		String option2=request.getParameter("opt2");
		String option3=request.getParameter("opt3");
		String option4=request.getParameter("opt4");
		String correct_option=request.getParameter("corr_opt");
		
		
		RequestDispatcher rd_success=request.getRequestDispatcher("/confirm.jsp");
		RequestDispatcher rd_error=request.getRequestDispatcher("/displayerror.jsp");
		
		try
		{
			/*questpstmt.setString(1, question);
			questpstmt.setString(2, subject);
			questpstmt.setString(3, level);
			questpstmt.setString(4, option1);
			questpstmt.setString(5, option2);
			questpstmt.setString(6, option3);
			questpstmt.setString(7, option4);			
			questpstmt.setString(8, correct_option);
			questpstmt.executeUpdate();	*/		
			
			session.setAttribute("SESS_QUEST",question);
			session.setAttribute("SESS_LEVEL",level);
			session.setAttribute("SESS_OPT1",option1);
			session.setAttribute("SESS_OPT2",option2);
			session.setAttribute("SESS_OPT3",option3);
			session.setAttribute("SESS_OPT4",option4);
			session.setAttribute("SESS_CORRECT",correct_option);
			
			rd_success.forward(request, response);
		}
	/*	catch (SQLException e) 
		{
			// TODO: handle exception
			String errmsg="ERROR IN SQL OPERATION : "+e.getMessage();
			request.setAttribute("ERRORMSG",errmsg);
			rd_error.forward(request,response);
		}*/
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
