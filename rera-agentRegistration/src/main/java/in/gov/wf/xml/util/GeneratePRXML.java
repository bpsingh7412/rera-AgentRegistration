package in.gov.wf.xml.util;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import in.gov.wf.constant.ProcessConstant;
import in.gov.wf.util.MapUtility;

public class GeneratePRXML {

	private MapUtility processMap;
	private Document xmlDoc;
	private Element rootEle;

	public GeneratePRXML(MapUtility prsMap) {
		processMap = prsMap;
		xmlDoc = DocumentHelper.createDocument();
		rootEle = xmlDoc.addElement(ProcessConstant.PR_ROOT);
	}

	public void generateXML(String folderPath) {
		// CDATA
		Element ele = rootEle.addElement(ProcessConstant.PR_NAME);
		ele.add(DocumentHelper.createCDATA(processMap.getStringValue(ProcessConstant.PR_NAME).toString()));

		ele = rootEle.addElement(ProcessConstant.PR_DESC);
		ele.add(DocumentHelper.createCDATA(processMap.getStringValue(ProcessConstant.PR_DESC).toString()));

		MapUtility slist = processMap.getMap(ProcessConstant.PR_SERVICES);
		ele = rootEle.addElement(ProcessConstant.PR_SERVICES);
		if (slist != null)
			generateServiceXML(ele, slist);

		slist = processMap.getMap(ProcessConstant.PR_CH_STATES);
		ele = rootEle.addElement(ProcessConstant.PR_CH_STATES);
		if (slist != null)
			generateChildStateXML(ele, slist);

		slist = processMap.getMap(ProcessConstant.PR_STATES);
		ele = rootEle.addElement(ProcessConstant.PR_STATES);
		if (slist != null)
			generateStateXML(ele, slist);

		slist = processMap.getMap(ProcessConstant.PR_ACTIONS);
		ele = rootEle.addElement(ProcessConstant.PR_ACTIONS);
		if (slist != null)
			generateActXML(ele, slist);

		slist = processMap.getMap(ProcessConstant.PR_OCS);
		ele = rootEle.addElement(ProcessConstant.PR_OCS);
		if (slist != null)
			generateOCXML(ele, slist);

		slist = processMap.getMap(ProcessConstant.PR_FSM_LIB);
		ele = rootEle.addElement(ProcessConstant.PR_FSM_LIB);
		if (slist != null)
			generateFsmXML(ele, slist);

		slist = processMap.getMap(ProcessConstant.PR_TEAMS);
		ele = rootEle.addElement(ProcessConstant.PR_TEAMS);
		if (slist != null)
			generateTeamXML(ele, slist);

		slist = processMap.getMap(ProcessConstant.PR_ROLES);
		ele = rootEle.addElement(ProcessConstant.PR_ROLES);
		if (slist != null)
			generateRoleXML(ele, slist);

		slist = processMap.getMap(ProcessConstant.PR_DEPTS);
		ele = rootEle.addElement(ProcessConstant.PR_DEPTS);
		if (slist != null)
			generateDeptXML(ele, slist);

		slist = processMap.getMap(ProcessConstant.PR_FORM_SECS);
		ele = rootEle.addElement(ProcessConstant.PR_FORM_SECS);
		if (slist != null)
			generateFormSecXML(ele, slist);

		slist = processMap.getMap(ProcessConstant.PR_FORMS);
		ele = rootEle.addElement(ProcessConstant.PR_FORMS);
		if (slist != null)
			generateFormXML(ele, slist);

		writeXML(folderPath);
	}

	private void writeXML(String folderPath) {
		try {
			FileOutputStream out = new FileOutputStream(folderPath + "/" + ProcessConstant.CONF_FOLDER + "/"
					+ ProcessConstant.CONF_FILE_NAME + "." + ProcessConstant.PR_FILE_EXT);
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(xmlDoc);
			out.close();
		} catch (Exception ex) {

		}
	}

	public void generateServiceXML(Element ele, MapUtility slist) {
		if (slist != null) {
			PrServicesUtiliy serv = new PrServicesUtiliy(slist, ele);
			serv.generate();
		}
	}

	public void generateTeamXML(Element ele, MapUtility slist) {
		if (slist != null) {
			PrTeamUtility mn = new PrTeamUtility(slist, ele);
			mn.generate();
		}
	}

	public void generateRoleXML(Element ele, MapUtility slist) {
		if (slist != null) {
			PrRoleUtility role = new PrRoleUtility(slist, ele);
			role.generate();

		}
	}

	public void generateDeptXML(Element ele, MapUtility slist) {
		if (slist != null) {
			PrDepartmentUtility role = new PrDepartmentUtility(slist, ele);
			role.generate();

		}
	}

	public void generateFormSecXML(Element ele, MapUtility slist) {
		if (slist != null) {
			PrFormSecUtility frm = new PrFormSecUtility(slist, ele);
			frm.generate();
		}
	}

	public void generateFormXML(Element ele, MapUtility slist) {
		if (slist != null) {
			PrFormUtiliy frm = new PrFormUtiliy(slist, ele);
			frm.generate();
		}
	}

	public void generateStateXML(Element ele, MapUtility plist) {

		if (plist != null) {
			PrStateUtility view = new PrStateUtility(plist, ele);
			view.generate();
		}
	}

	public void generateChildStateXML(Element ele, MapUtility plist) {

		if (plist != null) {
			PrChildStateUtility view = new PrChildStateUtility(plist, ele);
			view.generate();
		}
	}

	public void generateOCXML(Element ele, MapUtility plist) {

		if (plist != null) {
			PrOCUtility view = new PrOCUtility(plist, ele);
			view.generate();
		}
	}

	public void generateFsmXML(Element ele, MapUtility plist) {

		if (plist != null) {
			PrFSMUtility view = new PrFSMUtility(plist, ele);
			view.generate();
		}
	}

	public void generateActXML(Element ele, MapUtility plist) {

		if (plist != null) {
			PrActionUtility view = new PrActionUtility(plist, ele);
			view.generate();
		}
	}
}
