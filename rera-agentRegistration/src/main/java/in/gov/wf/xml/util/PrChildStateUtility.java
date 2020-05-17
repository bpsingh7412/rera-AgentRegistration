package in.gov.wf.xml.util;

import java.util.List;
import java.util.Set;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import in.gov.wf.constant.ProcessConstant;
import in.gov.wf.util.MapUtility;

public class PrChildStateUtility {

	private Element ele;
	private MapUtility chStateMap;

	public PrChildStateUtility(MapUtility stateMap, Element ele) {
		this.chStateMap = stateMap;
		this.ele = ele;
	}

	public PrChildStateUtility(Element ele) {
		this.ele = ele;
	}

	public MapUtility read(MapUtility stateMap) {
		this.chStateMap = stateMap;
		List<Element> mlist = this.ele.elements(ProcessConstant.PR_CH_STATE);
		if (mlist != null) {
			MapUtility state = null;
			for (Element mn : mlist) {
				state = MapUtility.getInstance();
				state.putValue(ProcessConstant.PR_CH_STATE_CODE,
						mn.element(ProcessConstant.PR_CH_STATE_CODE).getText());
				state.putValue(ProcessConstant.PR_CH_STATE_NAME,
						mn.element(ProcessConstant.PR_CH_STATE_NAME).getText());

				this.chStateMap.putValue(state.getStringValue(ProcessConstant.PR_CH_STATE_CODE), state);
			}
		}
		return this.chStateMap;
	}

	public void generate() {
		if (this.chStateMap != null) {
			Set<String> keys = this.chStateMap.keySet();
			for (String key : keys) {
				Element e1 = this.ele.addElement(ProcessConstant.PR_CH_STATE);
				MapUtility state = this.chStateMap.getMap(key);

				Element e2 = e1.addElement(ProcessConstant.PR_CH_STATE_CODE);
				e2.add(DocumentHelper.createCDATA(state.getStringValue(ProcessConstant.PR_CH_STATE_CODE)));

				e2 = e1.addElement(ProcessConstant.PR_CH_STATE_NAME);
				e2.add(DocumentHelper.createCDATA(state.getStringValue(ProcessConstant.PR_CH_STATE_NAME)));

			}

		}

	}
}
