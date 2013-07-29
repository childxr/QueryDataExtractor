package qbo;

import java.sql.*;
import java.util.*;

import oracle.jdbc.driver.OracleDriver;

@SuppressWarnings("unused")
public class DBConnectionPool {
	
	private static DBConnectionPool pool;
	private LinkedList<Connection> connections = null;
	public static final int MAX_CONNECTION = 4;
	
	private DBConnectionPool() {//here must be empty
	}
	
	public static synchronized DBConnectionPool getInstance() {
		System.out.println("get Instance..."+Thread.currentThread().getId());
		if (pool == null) {
			pool = new DBConnectionPool();
			pool.initialize();
		}
		return pool;
	}
	
	public synchronized void initialize() {
		System.out.println("Initializing connection pool..."+Thread.currentThread().getId());
		if (connections == null) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				String url = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = opm01p1-vip.wnsnet.attws.com)(PORT = 1701))(ADDRESS = (PROTOCOL = TCP)(HOST = opm01p2-vip.wnsnet.attws.com)(PORT = 1701))(ADDRESS = (PROTOCOL = TCP)(HOST = opm01p3-vip.wnsnet.attws.com)(PORT = 1701))(ADDRESS = (PROTOCOL = TCP)(HOST = opm01p4-vip.wnsnet.attws.com)(PORT = 1701))(ADDRESS = (PROTOCOL = TCP)(HOST = opm01p5-vip.wnsnet.attws.com)(PORT = 1701))(ADDRESS = (PROTOCOL = TCP)(HOST = opm01p6-vip.wnsnet.attws.com)(PORT = 1701)) (ADDRESS = (PROTOCOL = TCP)(HOST = opm01p7-vip.wnsnet.attws.com)(PORT = 1701)) (ADDRESS = (PROTOCOL = TCP)(HOST = opm01p8-vip.wnsnet.attws.com)(PORT = 1701)) (LOAD_BALANCE = ON)(FAILOVER = ON)(CONNECT_DATA =(SERVER = DEDICATED) (SERVICE_NAME = OPTIMA_REPORT_PWRUSR)))";
	            String userName = "";
	            String passWord = "";
	            connections = new LinkedList<Connection> ();
				for (int count = 0; count < MAX_CONNECTION; count++) {
					Connection connection = DriverManager.getConnection(url, userName, passWord);
					connections.add(connection);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized Connection getConnection() {
		System.out.println("get connection..."+Thread.currentThread().getId());
		Connection connection = null;
		try {
			if (connections.size() == 0) wait(); 
			connection = connections.remove();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public synchronized void putConnection(Connection connection) {
		System.out.println("return connection..."+Thread.currentThread().getId());
		if (connection != null) {
			connections.add(connection);
			notifyAll();
		}
	}
	
	public synchronized void clearConnectionPool() {
		if (connections != null) for (Connection connection : connections) connections.remove(connection);
	}
	
}
