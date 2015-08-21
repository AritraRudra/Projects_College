package login;

import history.ResultHistoryBean;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

public class AdminControlServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	private String driver;
	private String url;
	private String usr;
	private String passwd;	
	private Connection con =null;
	private PreparedStatement psmnt_exam=null;	
	private String stu_test_history="select examid,sid,subject,level,marks,exam_date from exam";
	
    public AdminControlServlet() {
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
			psmnt_exam=con.prepareStatement(stu_test_history);	
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
		RequestDispatcher rd_add_quest=request.getRequestDispatcher("/add_new_question.jsp");
		RequestDispatcher rd_add_subj=request.getRequestDispatcher("/addsubject.jsp");
		RequestDispatcher rd_exam=request.getRequestDispatcher("/studentDetails.jsp");
		RequestDispatcher rd_error=request.getRequestDispatcher("/displayerror.jsp");
		
		try
		{
			HttpSession session=request.getSession(true);
			if((session.getAttribute("SESS_SUBJ"))!=null){
				session.removeAttribute("SESS_SUBJ");
			}
			
			String addques=request.getParameter("addques");
			String addsubj=request.getParameter("addsubject");
			String subj=request.getParameter("subject");
			String examdetails=request.getParameter("examdetails");
			
			if(addsubj!=null){				
				rd_add_subj.forward(request, response);
			}else if(subj!=null && addques!=null){
				session.setAttribute("SESS_SUBJ",subj);
				rd_add_quest.forward(request, response);				
			}else if(examdetails!=null){
				ResultSet rs=psmnt_exam.executeQuery();
				ArrayList<ResultHistoryBean> resultlist=new ArrayList<ResultHistoryBean>();
				while(rs.next()){					
					int examid=rs.getInt(1);
					int sid=rs.getInt(2);
					String subject=rs.getString(3);
					String level=rs.getString(4);
					int marks=rs.getInt(5);
					Date exam_date=rs.getDate(6);
					ResultHistoryBean result=new ResultHistoryBean(examid,sid,subject,level,marks,exam_date);
					resultlist.add(result);	
				}
				request.setAttribute("ALL_RESULTS",resultlist);
				rd_exam.forward(request, response);
			}else{
				System.out.println("Inside else block");
				response.sendRedirect("adminloginsuccess.jsp");			
			}
		}
		catch (Exception e) 
		{
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
