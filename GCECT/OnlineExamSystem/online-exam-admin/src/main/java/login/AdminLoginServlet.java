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
public class AdminLoginServlet extends HttpServlet {
	private final String className= AdminLoginServlet.class.getName();
	private static final Logger logger = LoggerFactory.getLogger(AdminLoginServlet.class);
	private static final long serialVersionUID = 1L;
	private static final String INVALID_CREDENTIALS = "INVALID COMBINATION OF USERNAME AND PASSWORD.";

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("Entering {}.{}", className,"doPost()");
		process(request, response);
		logger.debug("Exiting {}.{}", className,"doPost()");
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("Entering {}.{}", className,"process()");
		logger.debug("Request parameters : {}", request.getParameterNames());
		String strAdminEmailId = request.getParameter(AdminEnums.EMAIL.toString());
		String strAdminPasswd = request.getParameter(AdminEnums.PASSWORD.toString());
		
		logger.info("Param1 {}, param2 {}", strAdminEmailId, strAdminPasswd);

		RequestDispatcher rd_admin = request.getRequestDispatcher("/adminloginsuccess.jsp");
		RequestDispatcher rd_error = request.getRequestDispatcher("/displayerror.jsp");
		RequestDispatcher rd_login_fail = request.getRequestDispatcher("/index.jsp");

		final SQLUtils sqlUtils = new SQLUtils();
		try {
			HttpSession session = request.getSession(true);

			if (strAdminEmailId != null && strAdminPasswd != null) {
				if (sqlUtils.isValidAdmin(strAdminEmailId, strAdminPasswd)) {
					AdminBean admin = sqlUtils.getAdmin(strAdminEmailId, strAdminPasswd);
					request.setAttribute(AdminEnums.USER_ADMIN.toString(), admin);
					List<String> list = sqlUtils.getAllSubjects();
					request.setAttribute(AdminEnums.SUBJECTS.toString(), list);
					session.setAttribute(AdminEnums.SESSION_USER.toString(), strAdminEmailId);
					session.setAttribute(AdminEnums.SESSION_START_TIME.toString(), new java.util.Date());
					session.setAttribute(AdminEnums.IS_SESSION_VALID.toString(), true);
					rd_admin.forward(request, response);
				} else {
					request.setAttribute(AdminEnums.ERROR_ADMIN_MSG.toString(), INVALID_CREDENTIALS);
					// request.setAttribute(, INVALID_CREDENTIALS);
					rd_login_fail.forward(request, response);
				}
			}
		} catch (SQLException e) {
			String errmsg = "ERROR IN SQL OPERATION : " + e.getMessage();
			request.setAttribute(AdminEnums.ERROR_MSG.toString(), errmsg);
			logger.error(errmsg);
			rd_error.forward(request, response);
		} catch (NullPointerException npEx) {
			String errmsg = "INVALID COMBINATION OF USERNAME AND PASSWORD.";
			request.setAttribute(AdminEnums.ERROR_ADMIN_MSG.toString(), errmsg);
			request.setAttribute("ERRORMSG1", errmsg);
			logger.error("NullPointerException Occurred.", npEx);
			rd_login_fail.forward(request, response);
			// response.sendRedirect("index.jsp?MSG=INVALID COMBINATION OF USERNAME AND PASSWORD.");
		} catch (Exception e) {
			String errmsg = "GENERAL ERROR OCCURED." + e.getMessage();
			request.setAttribute(AdminEnums.ERROR_MSG.toString(), errmsg);
			logger.error("Exception Occurred.", e);
			rd_error.forward(request, response);
			// response.sendRedirect("index.jsp?MSG=ERROR");
		}
		logger.debug("Exiting {}.{}", className,"process()");
	}

}
