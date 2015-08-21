package add_quest;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import taking_test.QuestionBean;

public class AddQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String driver;
	private String url;
	private String usr;
	private String passwd;	
	private Connection con =null;
			
    public AddQuestionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(){
    	ServletContext context=getServletContext();
		driver=context.getInitParameter("driver");
		url=context.getInitParameter("url");
		usr=context.getInitParameter("usr");
		passwd=context.getInitParameter("passwd");
		try	{
			Class.forName(driver);
			con=DriverManager.getConnection(url,usr,passwd);		
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
		
		RequestDispatcher rd_success=request.getRequestDispatcher("/confirm.jsp");		
		try	{
			HttpSession session=request.getSession(true);
		
			String question=request.getParameter("question");
			String level=request.getParameter("level");
			String option1=request.getParameter("opt1");
			String option2=request.getParameter("opt2");
			String option3=request.getParameter("opt3");
			String option4=request.getParameter("opt4");
			String correct_option=request.getParameter("corr_opt");
			String subject=(String) session.getAttribute("SESS_SUBJ");
			
			QuestionBean qb=new QuestionBean(question, subject, level, option1, option2, option3, option4, correct_option);
			
			session.setAttribute("QUES",qb);
			rd_success.forward(request, response);
		}
		catch (Exception e) 
		{
			String errmsg="GENERAL ERROR OCCURED."+e.getMessage();
			request.setAttribute("ERRORMSG",errmsg);
		}
	}

	public void destroy(){
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
