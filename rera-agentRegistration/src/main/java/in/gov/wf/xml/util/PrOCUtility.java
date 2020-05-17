package in.gov.wf.xml.util;

import java.util.List;
import java.util.Set;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import in.gov.wf.constant.ProcessConstant;
import in.gov.wf.util.MapUtility;

public class PrOCUtility {

	private Element ele;
	private MapUtility ocMap;

	public PrOCUtility(MapUtility ocMap, Element ele) {
		this.ocMap = ocMap;
		this.ele = ele;
	}

	public PrOCUtility(Element ele) {
		this.ele = ele;
	}

	public MapUtility read(MapUtility ocMap) {
		this.ocMap = ocMap;
		List<Element> mlist = this.ele.elements(ProcessConstant.PR_OUTCOME);
		if (mlist != null) {
			MapUtility oc = null;
			for (Element mn : mlist) {
				oc = MapUtility.getInstance();
				oc.putValue(ProcessConstant.PR_OC_CODE, mn.element(ProcessConstant.PR_OC_CODE).getText());
				oc.putValue(ProcessConstant.PR_OC_NAME, mn.element(ProcessConstant.PR_OC_NAME).getText());

				this.ocMap.putValue(oc.getStringValue(ProcessConstant.PR_OC_CODE), oc);
			}
		}
		return this.ocMap;
	}

	public void generate() {
		if (this.ocMap != null) {
			Set<String> keys = this.ocMap.keySet();
			for (String key : keys) {
				Element e1 = this.ele.addElement(ProcessConstant.PR_OUTCOME);
				MapUtility oc = this.ocMap.getMap(key);

				Element e2 = e1.addElement(ProcessConstant.PR_OC_CODE);
				e2.add(DocumentHelper.createCDATA(oc.getStringValue(ProcessConstant.PR_OC_CODE)));

				e2 = e1.addElement(ProcessConstant.PR_OC_NAME);
				e2.add(DocumentHelper.createCDATA(oc.getStringValue(ProcessConstant.PR_OC_NAME)));

			}

		}

	}
}
