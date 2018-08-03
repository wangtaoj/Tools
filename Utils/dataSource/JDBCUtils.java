package csuft.com.estore.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class JDBCUtils {
	
	private static BasicDataSource  dataSource = new BasicDataSource();
	static{
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql:///estore");
		dataSource.setUsername("angel");
		dataSource.setPassword("angel");
	}
	
	public static DataSource getDataSource(){
		return dataSource;
	}
	
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
}
