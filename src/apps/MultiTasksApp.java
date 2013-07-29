package apps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

import parser.ConfigXMLParser;
import parser.JobXMLParser;
import thread.QueryThread;

public class MultiTasksApp {
	
	private static DateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static final int maxThread = 4;
	
	public static void getConfigured() {
		ConfigXMLParser.parseConfigXML("configW.xml");
	}
	
	public static void main(String[] args) {
		System.out.println("["+date.format(new Date())+"] configuring...");
		getConfigured();
		JobXMLParser.parseJobXML(ConfigXMLParser.input);
		ArrayList<QueryThread> workers = new ArrayList<QueryThread> ();
		for (int i = 0; i < maxThread; i++) workers.add(new QueryThread(i,ConfigXMLParser.output));
		for (QueryThread worker: workers)worker.getThread().start();
		try {
			for (QueryThread worker: workers) worker.getThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		System.out.println("["+date.format(new Date())+"] finishing...");
	}

}
