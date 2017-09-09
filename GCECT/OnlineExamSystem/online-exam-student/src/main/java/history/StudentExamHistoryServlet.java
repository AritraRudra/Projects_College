package history;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import history.ResultHistoryBean;

public class StudentExamHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String driver;
	private String url;
	private String usr;
	private String passwd;
	
	private Connection con =null;
	private PreparedStatement psmnt=null,psmnt_get_sid=null;
	private String stu_test_history="select examid,subject,level,marks,exam_date from exam where sid=?";
	private String get_sid="select (sid) from student where uname=?";

    public void init(){
    	ServletContext context=getServletContext();
    	driver=context.getInitParameter("driver");
		url=context.getInitParameter("url");
		usr=context.getInitParameter("usr");
		passwd=context.getInitParameter("passwd");
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,usr,passwd);
			psmnt=con.prepareStatement(stu_test_history);	
			psmnt_get_sid=con.prepareStatement(get_sid);
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
        
    public StudentExamHistoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	//retrieving form data
	RequestDispatcher rd_success=request.getRequestDispatcher("/resulthistory.jsp");
	RequestDispatcher rd_error=request.getRequestDispatcher("/displayerror.jsp");

	try {
		
		HttpSession session=request.getSession(true);		
		String uname=(String)session.getAttribute("SESS_USER");
		psmnt_get_sid.setString(1,uname);
		ResultSet rs_sid=psmnt_get_sid.executeQuery();
		if(rs_sid.next()){
			int sid=rs_sid.getInt(1);
			psmnt.setInt(1,sid);
			ResultSet rs=psmnt.executeQuery();
			ArrayList<ResultHistoryBean> resultlist=new ArrayList<ResultHistoryBean>();
			while(rs.next()){				
				//Pass  through object
				int examid=rs.getInt(1);
				String subject=rs.getString(2);
				String level=rs.getString(3);
				int marks=rs.getInt(4);
				Date exam_date=rs.getDate(5);
				ResultHistoryBean result=new ResultHistoryBean(examid,sid,subject,level,marks,exam_date);
				resultlist.add(result);	
			}
			request.setAttribute("RESULT_LIST",resultlist);			
			rd_success.forward(request,response);
		}
	}catch (NullPointerException e) {		
		response.sendRedirect("index.jsp");
	}catch (SQLException e) {
		String errmsg="PLEASE LOGIN AGAIN : "+e.getMessage();
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
