package login;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import subject_class.*;
import student_class.StudentBean;

public class StudentLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String driver;
	private String url;
	private String usr;
	private String passwd;
	
	private Connection con =null;
	private PreparedStatement psmnt=null,psmnt_sub;
	private String subject="select distinct(subject) from ques";
	private String search_student="select uname,email,passwd from student where email=? and passwd=?";

    
    public StudentLoginServlet() {
       
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
			psmnt=con.prepareStatement(search_student);
			psmnt_sub=con.prepareStatement(subject);
			
		} catch (ClassNotFoundException e) {
			//For Debugging purpose
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
	
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
						
		RequestDispatcher rd_success=request.getRequestDispatcher("/success.jsp");
		RequestDispatcher rd_admin=request.getRequestDispatcher("/adminloginsuccess.jsp");
		RequestDispatcher rd_error=request.getRequestDispatcher("/displayerror.jsp");		
		RequestDispatcher rd_login_fail=request.getRequestDispatcher("/index.jsp");
				
		try {
			String stremail=request.getParameter("email");
			String strpasswd=request.getParameter("passwd");
			
			String admemail=request.getParameter("admemail");
			String admpasswd=request.getParameter("admpasswd");
						
			psmnt.setString(1,stremail);
			psmnt.setString(2,strpasswd);
						
			String admin_uname="Administrator01";
			String admin_fname="John";
			String admin_lname="Merchant";
			String adminemail="admin@exam";
			String adminpasswd="admin";
			
			ResultSet rs=psmnt.executeQuery();			
			
			if(rs.next()){				
				String uname=rs.getString(1);
				String email=rs.getString(2);
				String passwd=rs.getString(3);				
				if((email.equals(stremail)) && (passwd.equals(strpasswd)) ){									
					HttpSession session=request.getSession(true);					
					session.setAttribute("SESS_USER",uname);
					session.setAttribute("SESS_LOGIN_TIME",new java.util.Date());					
					rd_success.forward(request,response);
				}else{
					String errmsg="USERNAME AND PASSWORD ARE CASE SENSITIVE.";					
					request.setAttribute("ERRORMSG",errmsg);
					rd_login_fail.forward(request,response);					
				}
			}else if(admemail!=null && admpasswd!=null){
				if(admemail.equals(adminemail) && admpasswd.equals(adminpasswd)){					
					HttpSession session=request.getSession(true);						
					ResultSet rs_sub=psmnt_sub.executeQuery();
					ArrayList<SubjectBean> subjlist=new ArrayList<SubjectBean>();				
					while(rs_sub.next()){					
							String subj=rs_sub.getString(1);
							SubjectBean sl=new SubjectBean(subj);
							subjlist.add(sl);
					}
					
					session.setAttribute("SESS_USER",admin_uname);
					session.setAttribute("SUBJECTADM",subjlist);					
					StudentBean adminfo=new StudentBean(admin_uname, admin_fname, admin_lname,adminemail,adminpasswd);
					session.setAttribute("ADMINFO",adminfo);
					session.setAttribute("SESS_LOGIN_TIME_ADM",new java.util.Date());
					rd_admin.forward(request,response);
				}else {
					String errmsgadm="INVALID COMBINATION OF USERNAME AND PASSWORD.";
					request.setAttribute("ERRORMSGADM",errmsgadm);
					rd_login_fail.forward(request,response);
				}
			}else {
				String errmsg="INVALID COMBINATION OF USERNAME AND PASSWORD.";
				request.setAttribute("ERRORMSG",errmsg);
				rd_login_fail.forward(request,response);				
		}
		}catch (SQLException e) {
			String errmsg="ERROR IN SQL OPERATION : "+e.getMessage();
			request.setAttribute("ERRORMSG",errmsg);
			rd_error.forward(request,response);
		}catch (NullPointerException e) {			
			String errmsg="INVALID COMBINATION OF USERNAME AND PASSWORD.";
			request.setAttribute("ERRORMSG",errmsg);
			rd_login_fail.forward(request,response);			
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
