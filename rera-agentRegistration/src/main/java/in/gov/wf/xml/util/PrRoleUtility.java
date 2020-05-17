package in.gov.wf.xml.util;

import java.util.List;
import java.util.Set;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import in.gov.wf.constant.ProcessConstant;
import in.gov.wf.util.MapUtility;

public class PrRoleUtility {
	private Element ele;
	private MapUtility roleMap;

	public PrRoleUtility(MapUtility roleMap, Element ele) {
		this.roleMap = roleMap;
		this.ele = ele;
	}

	public PrRoleUtility(Element ele) {

		this.ele = ele;
	}

	public MapUtility read(MapUtility roleMap) {
		this.roleMap = roleMap;
		List<Element> rlist = this.ele.elements(ProcessConstant.PR_ROLE);
		if (rlist != null) {
			MapUtility role = null;
			for (Element mn : rlist) {
				role = MapUtility.getInstance();
				role.putValue(ProcessConstant.PR_ROLE_CODE, mn.element(ProcessConstant.PR_ROLE_CODE).getText());
				role.putValue(ProcessConstant.PR_ROLE_NAME, mn.element(ProcessConstant.PR_ROLE_NAME).getText());
				role.putValue(ProcessConstant.PR_ROLE_DESC, mn.element(ProcessConstant.PR_ROLE_DESC).getText());

				this.roleMap.putValue(role.getStringValue(ProcessConstant.PR_ROLE_CODE), role);
			}
		}
		return this.roleMap;
	}

	public void generate() {
		if (this.roleMap != null) {
			Set<String> keys = this.roleMap.keySet();
			for (String key : keys) {
				Element e1 = this.ele.addElement(ProcessConstant.PR_ROLE);
				MapUtility role = this.roleMap.getMap(key);

				Element e2 = e1.addElement(ProcessConstant.PR_ROLE_CODE);
				e2.add(DocumentHelper.createCDATA(role.getStringValue(ProcessConstant.PR_ROLE_CODE)));

				e2 = e1.addElement(ProcessConstant.PR_ROLE_NAME);
				e2.add(DocumentHelper.createCDATA(role.getStringValue(ProcessConstant.PR_ROLE_NAME)));

				e2 = e1.addElement(ProcessConstant.PR_ROLE_DESC);
				e2.add(DocumentHelper.createCDATA(role.getStringValue(ProcessConstant.PR_ROLE_DESC)));

			}

		}

	}
}
