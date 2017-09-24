package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionMagager {
	private static final String className = ConnectionMagager.class.getName();
	private static final Logger logger = LoggerFactory.getLogger(ConnectionMagager.class.getName());

	//@Resource(name = "java:jboss/jdbc/online_exam")
	private static DataSource dataSource;

	// one way to do, otherwise directly inject as resourse
	private static void loadFromConnectionPool_way1() {
		try {
			// Get DataSource from context
			Context initContext = new InitialContext();
			//Context envContext = (Context) initContext.lookup("java:/comp/env");
			//Context envContext = (Context) initContext.lookup("java:jboss/jdbc/online_exam");
			/*
			NamingEnumeration<NameClassPair> namedEnums = initContext.list("java:jboss/jdbc/online_exam");
			while(namedEnums.hasMore()){
				logger.info(" {} ", namedEnums.next().getName());	
			}
			*/			
			//dataSource = (DataSource) envContext.lookup("datasource/online_exam");
			dataSource = (DataSource) initContext.lookup("java:jboss/jdbc/online_exam");
		} catch (NamingException e) {
			// e.printStackTrace();
			logger.error("NamingException occurred.", e);
			throw new ExceptionInInitializerError(e); // https://stackoverflow.com/a/1916124/1679643
		}
	}

	// TODO
	public static Connection getConnection() throws SQLException {
		loadFromConnectionPool_way1();
		return dataSource.getConnection();
	}

	@Deprecated
	public static Connection getConnectionUsingHardCodedCredentialsTemporarily() throws SQLException {
		//final String driver = "com.mysql.jdbc.Driver";
		final String url = "jdbc:mysql://localhost:3306/online_exam";
		final String user = "admin";
		final String password = "MyGroot";
		Connection connection = null;
		try {
			//Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);
			return connection;
		/*
		}catch (ClassNotFoundException cnfEx) {
			logger.error("Exception occurred.", cnfEx);
		*/
		}catch (SQLException sqlEx) {
			logger.error("Exception occurred.", sqlEx);
			throw sqlEx;
		}
	}

}
