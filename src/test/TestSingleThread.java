package test;

import qbo.DBEasyConnection;
import java.sql.ResultSet;
import extractor.DataExtractor;
import parser.ConfigXMLParser;

public class TestSingleThread {
	
	public static void main(String args[]) {
		System.out.println("Testing Connection!");
		DBEasyConnection dbcon = new DBEasyConnection();
		String query = "SELECT OWNER, TABLE_NAME FROM ALL_TAB_COLUMNS WHERE TABLE_NAME like 'DU%'";
		ConfigXMLParser.parseConfigXML("C:\\Users\\rx710r\\workspace\\QueryDataExtractor\\src\\apps\\config.xml");
		String fileout = ConfigXMLParser.output + "rs.csv";
		//String query = "select table_name from all_tables where table_name = 'DUAL'";
		/*
		String query = "SELECT EUTRANCELLFDD,TO_CHAR(DATETIME, 'YYYYMMDD HH24:MI:SS'),PMRADIORECINTFPWRPUCCH,PMRADIORECINTERFERENCEPWR  "
				+ "FROM  ERICSSON_EUTRAN.NB_EUCELLFDD "
				+ "WHERE  TO_CHAR(DATETIME, 'YYYYMMDD HH24:MI:SS') >= '20130709 00:00:00'  AND"
				+ "  TO_CHAR(DATETIME, 'YYYYMMDD HH24:MI:SS') <  '20130710 00:00:00' AND  "
				+ "ROWNUM < 9  "
				+ "ORDER BY  EUTRANCELLFDD, DATETIME";*/
		ResultSet result = dbcon.runQuery(query);
		try {
			if (result != null) {
				DataExtractor.loadData(result, "OWNER, TABLE_NAME ", fileout);
				System.out.println("Success!!");
			}
		} catch (Exception e) {
			System.out.println("connect error?!");
			e.printStackTrace();
		} finally {
			dbcon.close();
		}
		System.out.println("end");
	}
}
