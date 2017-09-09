package subject_select;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import subject_class.SubjectBean;


public class SubjectSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String driver;
	private String url;
	private String usr;
	private String passwd;
	
	private Connection con =null;
	private PreparedStatement psmnt=null;
	private String diff_sub="select distinct(subject) as DiffSubjects from ques;";
	
    public SubjectSelectServlet() {
        super();
       
    }

    public void init(){
    	ServletContext context=getServletContext();
    	
    	driver=context.getInitParameter("driver");
		url=context.getInitParameter("url");
		usr=context.getInitParameter("usr");
		passwd=context.getInitParameter("passwd");
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,usr,passwd);
			psmnt=con.prepareStatement(diff_sub);
			
		} catch (ClassNotFoundException e) {
			//For debugging purpose
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
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd_sub_list=request.getRequestDispatcher("/taketest.jsp");
				
		try {
			ResultSet rs=psmnt.executeQuery();
			ArrayList<SubjectBean> sublist=new ArrayList<SubjectBean>();
			while(rs.next()){				
				//Pass  through object
				String subject=rs.getString(1);
				SubjectBean sub_b=new SubjectBean(subject);
				sublist.add(sub_b);
			}			
			HttpSession session=request.getSession(true);	
			String uname=(String)session.getAttribute("SESS_USER");			
			session.setAttribute("SESS_VALID",true);
			
			session.setAttribute("SET_QUESTION",true);				
			request.setAttribute("SUBJECT_LIST",sublist);
			rd_sub_list.forward(request,response);
		}catch (SQLException e) {			
			String errmsg="ERROR IN SQL OPERATION : "+e.getMessage();
			request.setAttribute("ERRORMSG",errmsg);			
			response.sendRedirect("success.jsp");
		}catch (Exception e) {
			String errmsg="GENERAL ERROR OCCURED."+e.getMessage();
			request.setAttribute("ERRORMSG",errmsg);			
			response.sendRedirect("success.jsp");
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
