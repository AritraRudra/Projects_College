package login;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class AdminControlServlet
 */
public class AdminControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(AdminControlServlet.class);

	private String driver;
	private String url;
	private String usr;
	private String passwd;
	private Connection con = null;
	private PreparedStatement pstmt = null;

	public void init() {
		ServletContext context = getServletContext();
		driver = context.getInitParameter("driver");
		url = context.getInitParameter("url");
		usr = context.getInitParameter("usr");
		passwd = context.getInitParameter("passwd");
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, usr, passwd);
		} catch (ClassNotFoundException e) {
			System.out.println("init()--ERROR LOADING DRIVER");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("init()--ERROR IN SQL : \n" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("init()--ERROR DUE TO SOMETHING ELSE : \n");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String subj = request.getParameter("subject");

		RequestDispatcher rd_add_quest = request.getRequestDispatcher("/add_new_question.jsp");
		RequestDispatcher rd_add_subj = request.getRequestDispatcher("/addsubject.jsp");
		RequestDispatcher rd_error = request.getRequestDispatcher("/displayerror.jsp");

		try {
			HttpSession session = request.getSession(true);

			if (subj.equals("null")) {
				rd_add_subj.forward(request, response);
			} else {
				session.setAttribute("SESS_SUBJ", subj);
				rd_add_quest.forward(request, response);
			}
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
