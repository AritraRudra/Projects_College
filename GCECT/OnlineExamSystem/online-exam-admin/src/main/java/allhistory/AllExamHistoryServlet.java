package allhistory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.SQLUtils;
import enums.WebPagesEnumConstants;

/**
 * Servlet implementation class AllExamHistoryServlet
 */
public class AllExamHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(AllExamHistoryServlet.class);
	private String sdetails = "select examid,sid,subject,level,marks,exam_date from exam";
	private String detail = "select uname from student_1 inner join exam on student_1.sid=exam.sid";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd_success = request.getRequestDispatcher("/studentDetails.jsp");
		RequestDispatcher rd_error = request.getRequestDispatcher("/"+WebPagesEnumConstants.ERROR_PAGE.toString());

		try {
			SQLUtils sqlUtils = new SQLUtils();
			List<ResultBean> historyList = sqlUtils.getAllExamHistory();
			
			request.setAttribute("RESULT", historyList);

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

}
