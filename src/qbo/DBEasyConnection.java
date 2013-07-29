package qbo;

import java.sql.*;

public class DBEasyConnection {
	private Connection conn;
    private Statement stat;
    private ResultSet rs;
   
    public DBEasyConnection(){}
    
    // Connect to the database
    public Connection getConn(){
    	try{
    		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
    		String url = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = opm01p1-vip.wnsnet.attws.com)(PORT = 1701))(ADDRESS = (PROTOCOL = TCP)(HOST = opm01p2-vip.wnsnet.attws.com)(PORT = 1701))(ADDRESS = (PROTOCOL = TCP)(HOST = opm01p3-vip.wnsnet.attws.com)(PORT = 1701))(ADDRESS = (PROTOCOL = TCP)(HOST = opm01p4-vip.wnsnet.attws.com)(PORT = 1701))(ADDRESS = (PROTOCOL = TCP)(HOST = opm01p5-vip.wnsnet.attws.com)(PORT = 1701))(ADDRESS = (PROTOCOL = TCP)(HOST = opm01p6-vip.wnsnet.attws.com)(PORT = 1701)) (ADDRESS = (PROTOCOL = TCP)(HOST = opm01p7-vip.wnsnet.attws.com)(PORT = 1701)) (ADDRESS = (PROTOCOL = TCP)(HOST = opm01p8-vip.wnsnet.attws.com)(PORT = 1701)) (LOAD_BALANCE = ON)(FAILOVER = ON)(CONNECT_DATA =(SERVER = DEDICATED) (SERVICE_NAME = OPTIMA_REPORT_PWRUSR)))";
            String userName = "";
            String passWord = "";
            conn=DriverManager.getConnection(url, userName, passWord);
            }
    	catch(Exception ex){
    		ex.printStackTrace();
    		}
            return conn;
    }
    
    // Execute SQL
    public ResultSet runQuery(String sql){
    	try{
    		conn=getConn();
    		stat=conn.createStatement();
    		rs=stat.executeQuery(sql);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return rs;
    }
    
    // Update SQL
    public void updateQuery(String sql){
    	try{
    		conn=getConn();
    		stat=conn.createStatement();
    		stat.executeUpdate(sql);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    // Close the connection
    public void close(){
    	try{
    		if(rs!=null)rs.close();
    		if(stat!=null)stat.close();
    		if(conn!=null)conn.close();
    	}
    	catch(Exception ex){
    			ex.printStackTrace();
    	}
    }
}
