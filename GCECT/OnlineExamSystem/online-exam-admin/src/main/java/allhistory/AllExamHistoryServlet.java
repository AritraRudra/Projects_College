package allhistory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AllExamHistoryServlet
 */
public class AllExamHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String un;
	private String driver;
	private String url;
	private String usr;
	private String passwd;
	private Connection con = null;
	private PreparedStatement pstmt = null, dpstmt = null;
	private String sdetails = "select examid,sid,subject,level,marks,exam_date from exam";
	private String detail = "select uname from student_1 inner join exam on student_1.sid=exam.sid";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AllExamHistoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		ServletContext context = getServletContext();
		driver = context.getInitParameter("driver");
		url = context.getInitParameter("url");
		usr = context.getInitParameter("usr");
		passwd = context.getInitParameter("passwd");
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, usr, passwd);
			pstmt = con.prepareStatement(sdetails);
			dpstmt = con.prepareStatement(detail);

		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			// MUST REMOVE THESE CONSOLE MESSAGES.
			System.out.println("init()--ERROR LOADING DRIVER");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("init()--ERROR IN SQL : \n" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("init()--ERROR DUE TO SOMETHING ELSE : \n");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd_success = request.getRequestDispatcher("/studentDetails.jsp");
		RequestDispatcher rd_error = request.getRequestDispatcher("/displayerror.jsp");

		try {
			ResultSet rs = pstmt.executeQuery();
			ResultSet rs2 = dpstmt.executeQuery();
			ArrayList<ResultBean> list = new ArrayList<ResultBean>();
			while (rs2.next()) {
				String uname = rs2.getString(1);
				un = uname;
			}
			while (rs.next()) {
				int examid = rs.getInt(1);
				int sid = rs.getInt(2);
				String subj = rs.getString(3);
				String level = rs.getString(4);
				int marks = rs.getInt(5);
				Date dt = rs.getDate(6);
				ResultBean rb = new ResultBean(examid, sid, un, subj, level, marks, dt);
				list.add(rb);
			}
			request.setAttribute("RESULT", list);

			rd_success.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			// out.println("<h3>error occured in sql
			// operation:"+e.getMessage()+"</h3>");
			String errmsg = "ERROR in SQL: " + e.getMessage();
			request.setAttribute("ERRORING", errmsg);
			rd_error.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void destroy() {
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
