package test;

import parser.ConfigXMLParser;

public class TestConfigXMLParser {
	public static void main(String[] args) {
		ConfigXMLParser.parseConfigXML("C:\\Users\\rx710r\\workspace\\QueryDataExtractor\\src\\apps\\config.xml");
		System.out.println(ConfigXMLParser.input);
		System.out.println(ConfigXMLParser.output);
		System.out.println(ConfigXMLParser.schema);
	}
}
