package in.gov.wf.xml.util;

import java.util.List;
import java.util.Set;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import in.gov.wf.constant.ProcessConstant;
import in.gov.wf.util.MapUtility;
import in.gov.wf.util.ProcessConfiguration;

public class PrFSMUtility {

	private Element ele;
	private MapUtility fsmMap;

	public PrFSMUtility(MapUtility fsmMap, Element ele) {
		this.fsmMap = fsmMap;
		this.ele = ele;
	}

	public PrFSMUtility(Element ele) {
		this.ele = ele;
	}

	public MapUtility read(MapUtility fsmMap) {
		this.fsmMap = fsmMap;
		List<Element> mlist = this.ele.elements(ProcessConstant.PR_FSM);
		if (mlist != null) {
			MapUtility fsm = null;
			ProcessConfiguration conf = ProcessConfiguration.getConfObj();
			for (Element mn : mlist) {
				fsm = MapUtility.getInstance();
				fsm.putValue(ProcessConstant.PR_FSM_STATE,
						conf.getProcesStates().getValue(mn.element(ProcessConstant.PR_FSM_STATE).getText()));
				fsm.putValue(ProcessConstant.PR_FSM_OC,
						conf.getProcesOutcomes().getValue(mn.element(ProcessConstant.PR_FSM_OC).getText()));
				fsm.putValue(ProcessConstant.PR_FSM_NSTATE,
						conf.getProcesStates().getValue(mn.element(ProcessConstant.PR_FSM_NSTATE).getText()));

				this.fsmMap.putValue(mn.element(ProcessConstant.PR_FSM_STATE).getText() + "~"
						+ mn.element(ProcessConstant.PR_FSM_OC).getText(), fsm);
			}
		}
		return this.fsmMap;
	}

	public void generate() {
		if (this.fsmMap != null) {
			Set<String> keys = this.fsmMap.keySet();
			for (String key : keys) {
				Element e1 = this.ele.addElement(ProcessConstant.PR_FSM);
				MapUtility fsm = this.fsmMap.getMap(key);

				Element e2 = e1.addElement(ProcessConstant.PR_FSM_STATE);
				MapUtility map = (MapUtility) fsm.getValue(ProcessConstant.PR_FSM_STATE);
				e2.add(DocumentHelper.createCDATA(map.getStringValue(ProcessConstant.PR_STATE_CODE)));

				map = (MapUtility) fsm.getValue(ProcessConstant.PR_FSM_OC);
				e2 = e1.addElement(ProcessConstant.PR_FSM_OC);
				e2.add(DocumentHelper.createCDATA(map.getStringValue(ProcessConstant.PR_OC_CODE)));

				e2 = e1.addElement(ProcessConstant.PR_FSM_NSTATE);
				map = (MapUtility) fsm.getValue(ProcessConstant.PR_FSM_NSTATE);
				e2.add(DocumentHelper.createCDATA(map.getStringValue(ProcessConstant.PR_STATE_CODE)));

			}

		}

	}
}
