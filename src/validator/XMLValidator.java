package validator;

import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import org.xml.sax.SAXException;

public class XMLValidator {
	
	public static boolean validateConfigXMLValidator(String schemaPath, String xmlPath)  {
		Source schemaFile = null;
        Source xmlFile = null;
        try {
                schemaFile = new StreamSource(new File(schemaPath));
                xmlFile = new StreamSource(new File(xmlPath));
                SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                Schema schema = schemaFactory.newSchema(schemaFile);
                Validator validator = schema.newValidator();
                validator.validate(xmlFile);
        } catch(SAXException e) {
                System.out.println(xmlFile.getSystemId() + " is NOT valid");
                System.out.println("Reason: " + e.getLocalizedMessage());
                return false;
        } catch(IOException e) {
                e.printStackTrace();
                return false;
        }

        return true;
	}

}
