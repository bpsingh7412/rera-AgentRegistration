package in.gov.wf.xml.util;

import java.util.List;
import java.util.Set;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import in.gov.wf.constant.ProcessConstant;
import in.gov.wf.util.MapUtility;

public class PrDepartmentUtility {
	private Element ele;
	private MapUtility deptMap;

	public PrDepartmentUtility(MapUtility deptMap, Element ele) {
		this.deptMap = deptMap;
		this.ele = ele;
	}

	public PrDepartmentUtility(Element ele) {

		this.ele = ele;
	}

	public MapUtility read(MapUtility deptMap) {
		this.deptMap = deptMap;
		List<Element> rlist = this.ele.elements(ProcessConstant.PR_DEPT);
		if (rlist != null) {
			MapUtility role = null;
			for (Element mn : rlist) {
				role = MapUtility.getInstance();
				role.putValue(ProcessConstant.PR_DEPT_CODE, mn.element(ProcessConstant.PR_DEPT_CODE).getText());
				role.putValue(ProcessConstant.PR_DEPT_NAME, mn.element(ProcessConstant.PR_DEPT_NAME).getText());

				this.deptMap.putValue(role.getStringValue(ProcessConstant.PR_DEPT_CODE), role);
			}
		}
		return this.deptMap;
	}

	public void generate() {
		if (this.deptMap != null) {
			Set<String> keys = this.deptMap.keySet();
			for (String key : keys) {
				Element e1 = this.ele.addElement(ProcessConstant.PR_DEPT);
				MapUtility role = this.deptMap.getMap(key);

				Element e2 = e1.addElement(ProcessConstant.PR_DEPT_CODE);
				e2.add(DocumentHelper.createCDATA(role.getStringValue(ProcessConstant.PR_DEPT_CODE)));

				e2 = e1.addElement(ProcessConstant.PR_DEPT_NAME);
				e2.add(DocumentHelper.createCDATA(role.getStringValue(ProcessConstant.PR_DEPT_NAME)));

			}

		}

	}
}
