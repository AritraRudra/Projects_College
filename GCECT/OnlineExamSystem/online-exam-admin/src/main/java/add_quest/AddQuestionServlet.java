package add_quest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.SQLUtils;
import enums.QuestionEnums;
import enums.WebPagesEnumConstants;

/**
 * Servlet implementation class AddQuestionServlet
 */
public class AddQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String className = AddQuestionServlet.class.getName();
	private static final Logger logger = LoggerFactory.getLogger(AddQuestionServlet.class);
	private String driver;
	private String url;
	private String usr;
	private String passwd;
	private Connection con = null;
	private PreparedStatement questpstmt = null;

	// private String add_question="insert into
	// ques(question,subject,level,opt1,opt2,opt3,opt4,corr_opt)
	// values(?,?,?,?,?,?,?,?)";

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("Entering {}.process()", className);
		HttpSession session = request.getSession(true);

		String question = request.getParameter(QuestionEnums.QUESTION.toString());
		// String subject=(String)session.getAttribute("SESS_SUBJ");
		String level = request.getParameter(QuestionEnums.LEVEL.toString());
		String option1 = request.getParameter(QuestionEnums.OPTION_1.toString());
		String option2 = request.getParameter(QuestionEnums.OPTION_2.toString());
		String option3 = request.getParameter(QuestionEnums.OPTION_3.toString());
		String option4 = request.getParameter(QuestionEnums.OPTION_4.toString());
		String correct_option = request.getParameter(QuestionEnums.CORRECT_OPTION.toString());

		RequestDispatcher rd_success = request.getRequestDispatcher("/confirm.jsp");
		RequestDispatcher rd_error = request.getRequestDispatcher("/"+WebPagesEnumConstants.ERROR_PAGE.toString());

		try {
			SQLUtils sqlUtils = new SQLUtils();
			boolean isAddedSuccessfully = sqlUtils.addQuestion(question, option1, option2, option3, option4, level);
			
			session.setAttribute("SESS_QUEST", question);
			session.setAttribute("SESS_LEVEL", level);
			session.setAttribute("SESS_OPT1", option1);
			session.setAttribute("SESS_OPT2", option2);
			session.setAttribute("SESS_OPT3", option3);
			session.setAttribute("SESS_OPT4", option4);
			session.setAttribute("SESS_CORRECT", correct_option);

			rd_success.forward(request, response);
		}
		/*
		 * catch (SQLException e) { // TODO: handle exception String
		 * errmsg="ERROR IN SQL OPERATION : "+e.getMessage();
		 * request.setAttribute("ERRORMSG",errmsg);
		 * rd_error.forward(request,response); }
		 */
		catch (Exception e) {
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
