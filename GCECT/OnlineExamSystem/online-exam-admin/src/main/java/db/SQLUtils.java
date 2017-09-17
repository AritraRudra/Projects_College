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
	private static final Logger logger = LoggerFactory.getLogger(SQLUtils.class);
	private Connection connection;

	private Connection con = null;
	// private PreparedStatement psmnt=null,adminpstmt=null,subjpstmt=null;
	private String search_student = "select uname,fstname,lstname,email from student where email=? and passwd=?";
	private String search_admin = "select sid, uname, fstname, lstname, email from student where email=? and passwd=?";
	private String subject = "select distinct(subject) from ques";

	public boolean isValidAdmin(String strAdminid, String strAdminPasswd) throws SQLException {
		if (getAdmin(strAdminid, strAdminPasswd) != null)
			return true;
		else
			return false;
	}

	public AdminBean getAdmin(String strAdminid, String strAdminPasswd) throws SQLException {
		try {
			connection = ConnectionMagager.getConnection();
			PreparedStatement pstmnt = connection.prepareStatement(search_admin);
			pstmnt.setString(1, strAdminid);
			pstmnt.setString(2, strAdminPasswd);
			ResultSet rs = pstmnt.executeQuery();
			if (rs.next()) {
				String adminid = rs.getString(1);
				String fname = rs.getString(2);
				String lname = rs.getString(3);
				String emailid = rs.getString(4);
				AdminBean admin = new AdminBean(adminid, fname, lname, emailid);
				return admin;
			}
			return null;
		} finally {
			closeConnection();
		}
	}

	public List<String> getAllSubjects() throws SQLException {
		try {
			connection = ConnectionMagager.getConnection();
			PreparedStatement pstmnt = connection.prepareStatement(subject);
			ResultSet rs = pstmnt.executeQuery();
			List<String> subjectList = new ArrayList<String>();
			while (rs.next()) {
				subjectList.add(rs.getString(1));
			}
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
