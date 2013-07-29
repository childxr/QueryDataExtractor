package apps;

import java.sql.ResultSet;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import extractor.DataExtractor;
import parser.ConfigXMLParser;
import parser.JobXMLParser;
import qbo.DBEasyConnection;
import pojo.Task;

public class SingleTaskApp {
	
	private static String out;
	private static DateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public static void getConfigured() {
		ConfigXMLParser.parseConfigXML("src\\apps\\config.xml");
		out = ConfigXMLParser.output;
	}

	public static void main(String[] args) {
		System.out.println("["+date.format(new Date())+"] configuring...");
		getConfigured();
		JobXMLParser.parseJobXML(ConfigXMLParser.input);
		Task task = JobXMLParser.job.get(0);
		String fileout = out + task.getOutputFile();
		String query = task.getQuery();
		String titleLine = task.getTitleLine();
		System.out.println("["+date.format(new Date())+"] input =>"+ConfigXMLParser.input);
		System.out.println("["+date.format(new Date())+"] output=>"+fileout);
		System.out.println("["+date.format(new Date())+"] query =>"+query);
		
		System.out.println("["+date.format(new Date())+"] connecting DB...");
		DBEasyConnection dbcon = new DBEasyConnection();
		
		System.out.println("["+date.format(new Date())+"] running SQL...");
		ResultSet result = dbcon.runQuery(query);
		
		try {
			if (result != null) {
				System.out.println("["+date.format(new Date())+"] loading DT...");
				DataExtractor.loadData(result, titleLine, fileout);
			}
		} catch (Exception e) {
			System.out.println("connect error?!");
			e.printStackTrace();
		} finally {
			System.out.println("["+date.format(new Date())+"] closing connection...");
			dbcon.close();
		}
		System.out.println("["+date.format(new Date())+"] returning...");
	}

}
