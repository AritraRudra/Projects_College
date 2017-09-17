package login;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import db.SQLUtils;
import enums.AdminEnums;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private final String className= LoginServlet.class.getName();
	private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);
	private static final long serialVersionUID = 1L;
	String INVALID_CREDENTIALS = "INVALID COMBINATION OF USERNAME AND PASSWORD.";

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("Entering {}.{}", className,"doPost()");
		process(request, response);
		logger.debug("Exiting {}.{}", className,"doPost()");
	}

	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("Entering {}.{}", className,"process()");
		String strEmail = request.getParameter("email");
		String strPasswd = request.getParameter("passwd");
		String strAdminid = request.getParameter("loginid");
		String strAdminPasswd = request.getParameter("password");

		RequestDispatcher rd_success = request.getRequestDispatcher("/loginsuccess.jsp");
		RequestDispatcher rd_admin = request.getRequestDispatcher("/adminloginsuccess.jsp");
		RequestDispatcher rd_error = request.getRequestDispatcher("/displayerror.jsp");
		RequestDispatcher rd_login_fail = request.getRequestDispatcher("/index.jsp");

		SQLUtils sqlUtils = new SQLUtils();
		try {
			HttpSession session = request.getSession(true);

			if (strAdminid != null && strAdminPasswd != null) {
				if (sqlUtils.isValidAdmin(strAdminid, strAdminPasswd)) {
					AdminBean admin = sqlUtils.getAdmin(strAdminid, strAdminPasswd);
					request.setAttribute(AdminEnums.USER_ADMIN.toString(), admin);
					List<String> list = sqlUtils.getAllSubjects();
					request.setAttribute(AdminEnums.SUBJECTS.toString(), list);
					session.setAttribute("SESS_USER", strAdminid);
					session.setAttribute("SESS_LOGIN_TIME", new java.util.Date());
					session.setAttribute("SESS_VALID", "true");
					rd_admin.forward(request, response);
				} else {
					request.setAttribute(AdminEnums.ERROR_ADMIN_MSG.toString(), INVALID_CREDENTIALS);
					// request.setAttribute(, INVALID_CREDENTIALS);
					rd_login_fail.forward(request, response);
				}
			} else if (strEmail != null && strPasswd != null) {
				if (sqlUtils.isValidStudent(strEmail, strPasswd)) {
					rd_success.forward(request, response);
				} else {
					// request.setAttribute(, INVALID_CREDENTIALS);
					request.setAttribute(AdminEnums.ERROR_STUDENT.toString(), INVALID_CREDENTIALS);
					rd_login_fail.forward(request, response);
				}
			}
		} catch (SQLException e) {
			String errmsg = "ERROR IN SQL OPERATION : " + e.getMessage();
			request.setAttribute(AdminEnums.ERROR_ADMIN_MSG.toString(), errmsg);
			logger.error(errmsg);
			rd_error.forward(request, response);
		} catch (NullPointerException e) {
			String errmsg = "INVALID COMBINATION OF USERNAME AND PASSWORD.";
			request.setAttribute(AdminEnums.ERROR_ADMIN_MSG.toString(), errmsg);
			request.setAttribute("ERRORMSG1", errmsg);
			logger.error(errmsg);
			rd_login_fail.forward(request, response);
			// response.sendRedirect("index.jsp?MSG=INVALID COMBINATION OF USERNAME AND PASSWORD.");
		} catch (Exception e) {
			String errmsg = "GENERAL ERROR OCCURED." + e.getMessage();
			request.setAttribute(AdminEnums.ERROR_ADMIN_MSG.toString(), errmsg);
			logger.error(errmsg);
			rd_error.forward(request, response);
			// response.sendRedirect("index.jsp?MSG=ERROR");
		}
		logger.debug("Exiting {}.{}", className,"process()");
	}

}
