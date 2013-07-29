package qbo;

import java.sql.*;
import qbo.DBConnectionPool;

public class DBQuery {
	private static DBConnectionPool pool = DBConnectionPool.getInstance();
	
	public ResultSet runQuery(String sql) {
		ResultSet rs = null;
		Connection connection = null;
		try {
			System.out.println("execute Query..."+Thread.currentThread().getId());
			connection = pool.getConnection();
			Statement stat = connection.createStatement();
			rs = stat.executeQuery(sql);
			System.out.println("execution end..."+Thread.currentThread().getId());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.putConnection(connection);
		}
		return rs;
	}
	
	public void updateQuery(String sql) {
		Connection connection = null;
		try {
			connection = pool.getConnection();
			Statement stat = connection.createStatement();
			stat.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.putConnection(connection);
		}
	}
}
