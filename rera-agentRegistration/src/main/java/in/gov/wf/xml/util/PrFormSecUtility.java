package in.gov.wf.xml.util;

import java.util.List;
import java.util.Set;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import in.gov.wf.constant.ProcessConstant;
import in.gov.wf.util.MapUtility;

public class PrFormSecUtility {

	private Element ele;
	private MapUtility secMap;

	public PrFormSecUtility(MapUtility secMap, Element ele) {
		this.secMap = secMap;
		this.ele = ele;
	}

	public PrFormSecUtility(Element ele) {
		this.ele = ele;
	}

	public MapUtility read(MapUtility secMap) {
		this.secMap = secMap;
		if (this.ele != null) {
			List<Element> mlist = this.ele.elements(ProcessConstant.PR_FORM_SEC);
			if (mlist != null) {
				MapUtility sec = null;
				for (Element mn : mlist) {
					sec = MapUtility.getInstance();
					sec.putValue(ProcessConstant.PR_SEC_CODE, mn.element(ProcessConstant.PR_SEC_CODE).getText());
					sec.putValue(ProcessConstant.PR_SEC_NAME, mn.element(ProcessConstant.PR_SEC_NAME).getText());

					this.secMap.putValue(sec.getStringValue(ProcessConstant.PR_SEC_CODE), sec);
				}
			}
		}
		return this.secMap;
	}

	public void generate() {
		if (this.secMap != null) {
			Set<String> keys = this.secMap.keySet();
			for (String key : keys) {
				Element e1 = this.ele.addElement(ProcessConstant.PR_FORM_SEC);
				MapUtility sec = this.secMap.getMap(key);

				Element e2 = e1.addElement(ProcessConstant.PR_SEC_CODE);
				e2.add(DocumentHelper.createCDATA(sec.getStringValue(ProcessConstant.PR_SEC_CODE)));

				e2 = e1.addElement(ProcessConstant.PR_SEC_NAME);
				e2.add(DocumentHelper.createCDATA(sec.getStringValue(ProcessConstant.PR_SEC_NAME)));

			}

		}

	}
}
