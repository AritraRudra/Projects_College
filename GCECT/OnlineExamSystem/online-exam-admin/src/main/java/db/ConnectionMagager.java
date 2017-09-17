package db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionMagager {
	
	@Resource(name = "jdbc/onlineexam")
	private static DataSource dataSource;

	// one way to do, otherwise directly inject as resourse
	private void loadFromConnectionPool_way1() {
		try {
			// Get DataSource from context
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/testdb");
		} catch (NamingException e) {
			//e.printStackTrace();
			throw new ExceptionInInitializerError(e); 	//https://stackoverflow.com/a/1916124/1679643
		}
	}
	
	public static Connection getConnection() throws SQLException {  
        return dataSource.getConnection();             
    }

}
