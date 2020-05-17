package in.gov.wf.xml.util;

import java.util.List;
import java.util.Set;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import in.gov.wf.constant.ProcessConstant;
import in.gov.wf.util.MapUtility;

public class PrActionUtility {

	private Element ele;
	private MapUtility actMap;

	public PrActionUtility(MapUtility actMap, Element ele) {
		this.actMap = actMap;
		this.ele = ele;
	}

	public PrActionUtility(Element ele) {
		this.ele = ele;
	}

	public MapUtility read(MapUtility actMap) {
		this.actMap = actMap;
		List<Element> mlist = this.ele.elements(ProcessConstant.PR_ACTION);
		if (mlist != null) {
			MapUtility act = null;
			for (Element mn : mlist) {
				act = MapUtility.getInstance();
				act.putValue(ProcessConstant.PR_ACTION_CODE, mn.element(ProcessConstant.PR_ACTION_CODE).getText());
				act.putValue(ProcessConstant.PR_ACTION_NAME, mn.element(ProcessConstant.PR_ACTION_NAME).getText());

				this.actMap.putValue(act.getStringValue(ProcessConstant.PR_ACTION_CODE), act);
			}
		}
		return this.actMap;
	}

	public void generate() {
		if (this.actMap != null) {
			Set<String> keys = this.actMap.keySet();
			for (String key : keys) {
				Element e1 = this.ele.addElement(ProcessConstant.PR_ACTION);
				MapUtility act = this.actMap.getMap(key);

				Element e2 = e1.addElement(ProcessConstant.PR_ACTION_CODE);
				e2.add(DocumentHelper.createCDATA(act.getStringValue(ProcessConstant.PR_ACTION_CODE)));

				e2 = e1.addElement(ProcessConstant.PR_ACTION_NAME);
				e2.add(DocumentHelper.createCDATA(act.getStringValue(ProcessConstant.PR_ACTION_NAME)));

			}

		}

	}
}
