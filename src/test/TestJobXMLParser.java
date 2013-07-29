package test;

import parser.*;
import pojo.Task;

public class TestJobXMLParser {
	public static void main(String[] args) {
		ConfigXMLParser.parseConfigXML("C:\\Users\\rx710r\\workspace\\QueryDataExtractor\\src\\apps\\config.xml");
		JobXMLParser.parseJobXML(ConfigXMLParser.input + "multi.xml");
		for (Task task : JobXMLParser.job) {
			System.out.println(task.getTitleLine());
			System.out.println(task.getOutputFile());
			System.out.println(task.getQuery());
		}
	}
}
