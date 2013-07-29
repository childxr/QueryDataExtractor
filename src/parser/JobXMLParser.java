package parser;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import pojo.Task;

public class JobXMLParser {
	public static ArrayList<Task> job = new ArrayList<Task> ();
	public static void parseJobXML(String xmlPath) {
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
		    			Task task = new Task();
		    			for (Node nd = node.getFirstChild(); nd != null; nd = nd.getNextSibling()) {
		    				if (nd.getNodeName().equals("titleline")) {
			    				task.setTitleLine(nd.getFirstChild().getNodeValue());
			    			} else if(nd.getNodeName().equals("fileout")) {
			    				task.setOutputFile(nd.getFirstChild().getNodeValue());
			    			} else if(nd.getNodeName().equals("query")) {
			    				task.setQuery(nd.getFirstChild().getNodeValue());
			    			}
		    			}
		    			job.add(task);
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
