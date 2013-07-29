package test;

import validator.XMLValidator;

public class TestValidator {
	public static void main(String[] args) {
			boolean config = XMLValidator.validateConfigXMLValidator("C:\\Users\\rx710r\\workspace\\QueryDataExtractor\\schema\\config.xsd" , "C:\\Users\\rx710r\\workspace\\QueryDataExtractor\\src\\config.xml");
			if (config) System.out.println("The Configuration is Validated");
			else System.out.println("The Configuration is NOT Validated!!!");
			boolean job = XMLValidator.validateConfigXMLValidator("C:\\Users\\rx710r\\workspace\\QueryDataExtractor\\schema\\job.xsd" , "C:\\Users\\rx710r\\workspace\\QueryDataExtractor\\input\\sample.xml");
			if (job) System.out.println("The Job is Validated");
			else System.out.println("The Job is NOT Validated!!!");
	}
}
