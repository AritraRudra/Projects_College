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
 * Servlet implementation class ConfirmPageServlet
 */
public class ConfirmPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String driver;
	private String url;
	private String usr;
	private String passwd;
	private Connection con = null;
	private PreparedStatement questpstmt = null;
	private String add_question = "insert into ques(question,subject,level,opt1,opt2,opt3,opt4,corr_opt) values(?,?,?,?,?,?,?,?)";

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		String question = (String) session.getAttribute("SESS_QUEST");
		String subject = (String) session.getAttribute("SESS_SUBJ");
		String level = (String) session.getAttribute("SESS_LEVEL");
		String option1 = (String) session.getAttribute("SESS_OPT1");
		String option2 = (String) session.getAttribute("SESS_OPT2");
		String option3 = (String) session.getAttribute("SESS_OPT3");
		String option4 = (String) session.getAttribute("SESS_OPT4");
		String correct_option = (String) session.getAttribute("SESS_CORRECT");

		RequestDispatcher rd_success = request.getRequestDispatcher("/add_new_question.jsp");
		RequestDispatcher rd_error = request.getRequestDispatcher("/displayerror.jsp");

		try {
			questpstmt.setString(1, question);
			questpstmt.setString(2, subject);
			questpstmt.setString(3, level);
			questpstmt.setString(4, option1);
			questpstmt.setString(5, option2);
			questpstmt.setString(6, option3);
			questpstmt.setString(7, option4);
			questpstmt.setString(8, correct_option);

			questpstmt.executeUpdate();

			/*
			 * session.setAttribute("SESS_QUEST",question);
			 * session.setAttribute("SESS_LEVEL",level);
			 * session.setAttribute("SESS_OPT1",option1);
			 * session.setAttribute("SESS_OPT2",option2);
			 * session.setAttribute("SESS_OPT3",option3);
			 * session.setAttribute("SESS_OPT4",option4);
			 * session.setAttribute("SESS_CORRECT",correct_option);
			 */

			rd_success.forward(request, response);
		} catch (SQLException e) {
			// TODO: handle exception
			String errmsg = "ERROR IN SQL OPERATION : " + e.getMessage();
			request.setAttribute("ERRORMSG", errmsg);
			rd_error.forward(request, response);
		} catch (Exception e) {
			String errmsg = "GENERAL ERROR OCCURED." + e.getMessage();
			request.setAttribute("ERRORMSG", errmsg);
			rd_error.forward(request, response);
			// response.sendRedirect("index.jsp?MSG=ERROR");
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
