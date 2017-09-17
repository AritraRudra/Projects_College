package add_subject;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import enums.SessionEnums;
import enums.WebPagesEnumConstants;


/**
 * Servlet implementation class AddSubjectServlet
 */
public class AddSubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(AddSubjectServlet.class);

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String subject = request.getParameter("new_subject");

		RequestDispatcher rd_success = request.getRequestDispatcher("/"+WebPagesEnumConstants.ADD_NEW_QUESTION_PAGE.toString());
		RequestDispatcher rd_error = request.getRequestDispatcher("/"+WebPagesEnumConstants.ERROR_PAGE);

		try {
			HttpSession session = request.getSession(true);
			session.setAttribute(SessionEnums.SESSION_SUBJECT.toString(), subject);

			rd_success.forward(request, response);
		} catch (Exception e) {
			String errmsg = "GENERAL ERROR OCCURED." + e.getMessage();
			request.setAttribute("ERRORMSG", errmsg);
			rd_error.forward(request, response);
			// response.sendRedirect("index.jsp?MSG=ERROR");
		}
	}

}
