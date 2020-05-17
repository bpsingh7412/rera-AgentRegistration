package in.gov.wf.xml.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import in.gov.wf.constant.ProcessConstant;

public class GenerateFormXML {

	private Map<String, Object> processMap;
	private Document xmlDoc;
	private Element rootEle;

	public GenerateFormXML(Map<String, Object> prsMap) {
		processMap = prsMap;
		xmlDoc = DocumentHelper.createDocument();
		rootEle = xmlDoc.addElement(ProcessConstant.PR_ROOT);
	}

	public void generateXML() {
	}

	private void writeXML() {
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(System.out, format);
			writer.write(xmlDoc);
		} catch (Exception ex) {

		}
	}

}
