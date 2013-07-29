package parser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ConfigXMLParser {
	public static String input = "";
	public static String output = "";
	public static String schema = "";
	public static void parseConfigXML(String xmlPath) {
		DocumentBuilderFactory documentFacotry = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder documentBuilder=documentFacotry.newDocumentBuilder();
			File f = new File(xmlPath);
			Document document=documentBuilder.parse(f);
		    Element root=document.getDocumentElement();
		    NodeList config = root.getChildNodes();
		    if (config != null) {
		    	for (int i = 0; i < config.getLength(); i++) {
		    		Node node = config.item(i);
		    		if (node.getNodeType() == Node.ELEMENT_NODE) {
		    			if (node.getNodeName().equals("input")) {input = node.getFirstChild().getNodeValue();}
		    			else if (node.getNodeName().equals("output")) {output = node.getFirstChild().getNodeValue();}
		    			else if (node.getNodeName().equals("schema")) {schema = node.getFirstChild().getNodeValue();}
		    		}
		    	}
		    }
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
}
