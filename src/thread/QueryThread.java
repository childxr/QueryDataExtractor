package thread;

import qbo.DBQuery;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import parser.JobXMLParser;
import extractor.DataExtractor;

public class QueryThread implements Runnable {
	private Thread thread;
	private int workerId;
	private ResultSet rs;
	
	private String sql;
	private String fileout;
	private String titleline;
	
	private static int nThread = 0;
	public static String workPath = null;
	
	public QueryThread (String threadName, String sql) {
		this.sql = sql;
		thread = new Thread(this, threadName);
		thread.start();
		nThread++;
	}
	
	public QueryThread(int workerId, String path) {
		this.workerId = workerId;
		thread = new Thread(this);
		if (workPath == null) workPath = path;
		nThread++;
	}
	
	public void run() {
		try {
			DateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			System.out.println("["+date.format(new Date())+"] workder "+workerId+": preparing connection ..."+Thread.currentThread().getId());
			DBQuery query = new DBQuery();
			for (int i = workerId; i < JobXMLParser.job.size(); i+=nThread) {
				System.out.println("["+date.format(new Date())+"] workder "+workerId+": working on task "+i+" ..."+Thread.currentThread().getId());
				sql = JobXMLParser.job.get(i).getQuery();
				fileout = workPath+JobXMLParser.job.get(i).getOutputFile();
				titleline = JobXMLParser.job.get(i).getTitleLine();
				System.out.println("["+date.format(new Date())+"] workder "+workerId+" output=>"+fileout+" ..."+Thread.currentThread().getId());
				System.out.println("["+date.format(new Date())+"] workder "+workerId+" query =>"+sql+" ..."+Thread.currentThread().getId());
				rs = query.runQuery(sql);
				System.out.println("["+date.format(new Date())+"] workder "+workerId+": loading data "+i+" ..."+Thread.currentThread().getId());
				if (rs != null) DataExtractor.loadData(rs, titleline, fileout);
				System.out.println("["+date.format(new Date())+"] workder "+workerId+": task finished "+i+" ..."+Thread.currentThread().getId());
			}
			System.out.println("["+date.format(new Date())+"] workder "+workerId+": complete work ..."+Thread.currentThread().getId());
		} catch (Exception e) {
			e.printStackTrace();
		} 

	}
	
	public ResultSet getResultSet() {
		return this.rs;
	}
	
	public Thread getThread() {
		return this.thread;
	}
	
	public String getSql() {
		return this.sql;
	}
	
	public String getFileout() {
		return this.fileout;
	}
	
	public String getTitleline() {
		return this.titleline;
	}
	
	public static int getNThread() {
		return nThread;
	}
	
	public int getWorkerId() {
		return this.workerId;
	}
 }
