package taking_test;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

import javax.servlet.*;
import javax.servlet.http.*;

public class TakeTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String driver;
	private String url;
	private String usr;
	private String passwd;
	
	private Connection con =null;
	private PreparedStatement psmnt=null,psmnt_marks=null,psmnt_sid=null;
	private String get_ques="select qid,question,opt1,opt2,opt3,opt4,corr_opt from ques where subject=? and level=?";
	private String set_marks="insert into exam (sid,subject,level,marks,exam_date) values (?,?,?,?,?)";
	private String get_sid="select sid from student where uname=?";

    public void init(){
    	ServletContext context=getServletContext();
    	driver=context.getInitParameter("driver");
		url=context.getInitParameter("url");
		usr=context.getInitParameter("usr");
		passwd=context.getInitParameter("passwd");
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,usr,passwd);
			psmnt=con.prepareStatement(get_ques);	
			psmnt_marks=con.prepareStatement(set_marks);
			psmnt_sid=con.prepareStatement(get_sid);
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
    
    public TakeTestServlet() {
        super();
       
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd_success=request.getRequestDispatcher("/testpage.jsp");
		RequestDispatcher rd_error=request.getRequestDispatcher("/displayerror.jsp");
		RequestDispatcher rd_complete=request.getRequestDispatcher("/resulthistory.jsp");
		int easy=10,hard=20;
		
		try {
						
			HttpSession session=request.getSession(true);	
			String uname=(String)session.getAttribute("SESS_USER");
			session.setAttribute("SESS_VALID",true);
									
			if((Boolean)session.getAttribute("SET_QUESTION")){				

				//retrieving form data
				String strsub=request.getParameter("subject");
				String strlevel=request.getParameter("level");
				if(strsub==null){
					response.sendRedirect("SubjectSelectServlet");
				}
				if(strlevel==null){
					response.sendRedirect("SubjectSelectServlet");
				}
				psmnt.setString(1,strsub);
				psmnt.setString(2,strlevel);			
				ResultSet rs=psmnt.executeQuery();
				ArrayList<QuestionBean> questionlist=new ArrayList<QuestionBean>();
				ArrayList<QuestionBean> questionset=new ArrayList<QuestionBean>();
				
				while(rs.next()){
					int qid=rs.getInt(1);
					String ques=rs.getString(2);
					String opt1=rs.getString(3);
					String opt2=rs.getString(4);
					String opt3=rs.getString(5);
					String opt4=rs.getString(6);
					String corr_opt=rs.getString(7);				
					QuestionBean question=new QuestionBean(qid,ques,strsub,strlevel,opt1,opt2,opt3,opt4,corr_opt);
					questionlist.add(question);				
				}			
				Collections.shuffle(questionlist);
				Iterator<QuestionBean> itr=questionlist.iterator();
				int i=0;
				if(strlevel.equalsIgnoreCase("EASY")){
					while (i<easy) {;
						QuestionBean question=itr.next();
						questionset.add(question);
						session.setAttribute("LEVEL","EASY");
						i++;
					}				
				}else if(strlevel.equalsIgnoreCase("HARD")){
					while (i<hard) {
						QuestionBean question=itr.next();
						questionset.add(question);
						session.setAttribute("LEVEL","HARD");
						i++;
					}System.out.println("out of hard while ");			
				}else {
					//redirect to subject select		
					response.sendRedirect("taketest.jsp");				
				}
				int count=0;
				QuestionBean each_question=questionset.get(count);
				request.setAttribute("THIS_QUESTION",each_question);
				session.setAttribute("SUBJECT",strsub);
				session.setAttribute("QUESTION_SET",questionset);
				session.setAttribute("MARKS",0);
				session.setAttribute("SET_QUESTION",false);
				session.setAttribute("COUNT",count);
				
			}
			else{
					String strans=request.getParameter("answer");									
					//Retriving Data from testpage by session
					String corr_ans=(String)session.getAttribute("CORR_ANS");	
					int marks=(Integer)session.getAttribute("MARKS");
					int count=(Integer)session.getAttribute("COUNT");
					ArrayList<QuestionBean> questionset=(ArrayList<QuestionBean>)session.getAttribute("QUESTION_SET");	
					String sub=(String)session.getAttribute("SUBJECT");
					String level=(String)session.getAttribute("LEVEL");
					
					if(strans!=null){
						if (strans.equals(corr_ans)) {
							marks++;
						}
					}
					//After Completion of test					
					if(count==easy && level.equalsIgnoreCase("EASY")){
						
						psmnt_sid.setString(1,uname);						
						ResultSet rs_sid=psmnt_sid.executeQuery();
						rs_sid.next();
						int sid=rs_sid.getInt(1);
						
						java.util.Date currdate=new java.util.Date();
						java.sql.Date today=new java.sql.Date(currdate.getTime());
					      																	
						psmnt_marks.setInt(1,sid);
						psmnt_marks.setString(2,sub);
						psmnt_marks.setString(3,level);
						psmnt_marks.setInt(4,marks);
						psmnt_marks.setDate(5,today);
						psmnt_marks.executeUpdate();
						//Set exam_ques Here  added on 11:26 PM 8/2/2012
						
						session.removeAttribute("LEVEL");
						session.removeAttribute("MARKS");
						session.removeAttribute("CORR_ANS");
						session.removeAttribute("COUNT");
						session.removeAttribute("QUESTION_SET");
						session.removeAttribute("SET_QUESTION");						
						session.setAttribute("CURR_RESULT",marks);				
						rd_complete.forward(request,response);						
					}
					if(count==hard && level.equalsIgnoreCase("HARD")){
						psmnt_sid.setString(1,uname);						
						ResultSet rs_sid=psmnt_sid.executeQuery();
						rs_sid.next();
						int sid=rs_sid.getInt(1);
						
						java.util.Date currdate=new java.util.Date();
						java.sql.Date today=new java.sql.Date(currdate.getTime());
					      																	
						psmnt_marks.setInt(1,sid);
						psmnt_marks.setString(2,sub);
						psmnt_marks.setString(3,level);
						psmnt_marks.setInt(4,marks);
						psmnt_marks.setDate(5,today);
						psmnt_marks.executeUpdate();
						
						session.removeAttribute("LEVEL");
						session.removeAttribute("MARKS");
						session.removeAttribute("CORR_ANS");
						session.removeAttribute("COUNT");
						session.removeAttribute("QUESTION_SET");
						session.removeAttribute("SET_QUESTION");						
						session.setAttribute("CURR_RESULT",marks);				
						rd_complete.forward(request,response);						
					}
										
					QuestionBean each_question=questionset.get(count);
					request.setAttribute("THIS_QUESTION",each_question);
					session.setAttribute("QUESTION_SET",questionset);
					session.setAttribute("MARKS",marks);															
				}					
				rd_success.forward(request,response);		
		}catch (SQLException e) {			
			String errmsg="PLEASE SUPPLY ALL THE FIELDS : "+e.getMessage();
			request.setAttribute("ERRORMSG",errmsg);
			rd_error.forward(request,response);
		}catch (Exception e) {
			String errmsg="GENERAL ERROR OCCURED."+e.getMessage();
			request.setAttribute("ERRORMSG",errmsg);			
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
