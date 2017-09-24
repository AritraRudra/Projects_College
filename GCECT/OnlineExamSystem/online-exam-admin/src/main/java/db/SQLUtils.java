package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import allhistory.ResultBean;
import login.AdminBean;

public class SQLUtils {
	private static final String className = SQLUtils.class.getName();
	private static final Logger logger = LoggerFactory.getLogger(SQLUtils.class.getName());
	private Connection connection;

	// private PreparedStatement psmnt=null,adminpstmt=null,subjpstmt=null;
	private String search_student = "select uname,fstname,lstname,email from ONLINE_EXAM_USERS where email=? and passwd=?";
	private String search_admin = "select USER_ID, FIRST_NAME, LAST_NAME from ONLINE_EXAM_USERS where EMAIL=? and PASSWORD=?";
	private String subject = "select distinct(SUBJECT) from ONLINE_EXAM_QUESTIONS";

	public boolean isValidAdmin(String strEmailId, String strAdminPasswd) throws SQLException {
		logger.debug("Entering {}.isValidAdmin()", className);
		if (getAdmin(strEmailId, strAdminPasswd) != null)
			return true;
		else
			return false;
	}

	public AdminBean getAdmin(String emailId, String password) throws SQLException {
		logger.debug("Entering {}.getAdmin()", className);
		try {
			connection = ConnectionMagager.getConnection();
			//connection = ConnectionMagager.getConnectionUsingHardCodedCredentialsTemporarily();
			PreparedStatement pstmnt = connection.prepareStatement(search_admin);
			pstmnt.setString(1, emailId);
			pstmnt.setString(2, password);
			logger.debug("Starting to execute query {}", pstmnt.toString());
			ResultSet rs = pstmnt.executeQuery();
			logger.debug("Successfully executed query {}", pstmnt.toString());
			if (rs.next()) {
				logger.debug("Inside resultset");
				int userId = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				AdminBean admin = new AdminBean(userId, firstName, lastName, emailId);
				closeResultSet(rs);
				closePreparedStatement(pstmnt);
				return admin;
			}
			return null;
		}catch( SQLException sqlEx){
			logger.error("Exception occurred.", sqlEx);
			throw sqlEx;
		} finally {
			closeConnection();
			logger.debug("Exiting {}.getAdmin()", className);
		}
	}


	public List<String> getAllSubjects() throws SQLException {
		try {
			connection = ConnectionMagager.getConnection();
			//connection = ConnectionMagager.getConnectionUsingHardCodedCredentialsTemporarily();
			PreparedStatement pstmnt = connection.prepareStatement(subject);
			ResultSet rs = pstmnt.executeQuery();
			List<String> subjectList = new ArrayList<String>();
			while (rs.next()) {
				subjectList.add(rs.getString(1));
			}
			closeResultSet(rs);
			closePreparedStatement(pstmnt);
			return subjectList;
		} finally {
			closeConnection();
		}
	}

	public boolean isValidStudent(String strEmail, String strPasswd) throws SQLException {
		try {
			connection = ConnectionMagager.getConnection();
			PreparedStatement pstmnt = connection.prepareStatement(search_admin);
			pstmnt.setString(1, strEmail);
			pstmnt.setString(2, strPasswd);
			ResultSet rs = pstmnt.executeQuery();
			if (rs.next()) {
				return true;
			} else
				return false;
		}catch(SQLException sqlEx){
			logger.error(sqlEx.getMessage(), sqlEx);
			throw sqlEx;
		} finally {
			closeConnection();
		}
	}
	
	public boolean addQuestion(String question, String option1, String option2, String option3, String option4,
			String level) throws SQLException {
		try {
			connection = ConnectionMagager.getConnection();
			PreparedStatement pstmnt = connection.prepareStatement(search_admin);
			//pstmnt.setString(1, strEmail);
			//pstmnt.setString(2, strPasswd);
			ResultSet rs = pstmnt.executeQuery();
			if (rs.next()) {
				return true;
			} else
				return false;
		} finally {
			closeConnection();
		}
	}

	public List<ResultBean> getAllExamHistory() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	private void closePreparedStatement(PreparedStatement pstmnt) {
		if (pstmnt != null) {
			try {
				pstmnt.close();
			} catch (SQLException sqlEx) {
				logger.error(sqlEx.getMessage(), sqlEx);
			}
		}
	}

	private void closeResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException sqlEx) {
				logger.error(sqlEx.getMessage(), sqlEx);
			}
		}

	}

	private void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
