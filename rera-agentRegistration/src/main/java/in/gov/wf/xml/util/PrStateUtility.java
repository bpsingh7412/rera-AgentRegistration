package in.gov.wf.xml.util;

import java.util.List;
import java.util.Set;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import in.gov.wf.constant.ProcessConstant;
import in.gov.wf.util.MapRenderUtility;
import in.gov.wf.util.MapUtility;
import in.gov.wf.util.ProcessConfiguration;

public class PrStateUtility {

	private Element ele;
	private MapUtility stateMap;

	public PrStateUtility(MapUtility stateMap, Element ele) {
		this.stateMap = stateMap;
		this.ele = ele;
	}

	public PrStateUtility(Element ele) {
		this.ele = ele;
	}

	public MapUtility read(MapUtility stateMap) {
		this.stateMap = stateMap;
		List<Element> mlist = this.ele.elements(ProcessConstant.PR_STATE);
		if (mlist != null) {
			MapUtility state = null;
			for (Element mn : mlist) {
				state = MapUtility.getInstance();

				state.putValue(ProcessConstant.PR_STATE_CODE, mn.element(ProcessConstant.PR_STATE_CODE).getText());
				state.putValue(ProcessConstant.PR_STATE_NAME, mn.element(ProcessConstant.PR_STATE_NAME).getText());
				state.putValue(ProcessConstant.PR_STATE_TYPE, mn.element(ProcessConstant.PR_STATE_TYPE).getText());
				if (mn.element(ProcessConstant.PR_STATE_TYPE).getText().equals(ProcessConstant.PR_STATE_TYPE_M)) {

					state.putValue(ProcessConstant.PR_CH_STATES, MapUtility.getInstance());
					state.putValue(ProcessConstant.PR_ST_FSM_LIB, MapUtility.getInstance());
					state.putValue(ProcessConstant.PR_ST_FSM_OC, MapUtility.getInstance());
					state.putValue(ProcessConstant.PR_ST_ENDSTATES, MapUtility.getInstance());
					state.putValue(ProcessConstant.PR_ST_CALS_OCS, MapUtility.getInstance());

					mappedChilds(state, mn);
				}
				System.out.println("In state utility >> " + state.getStringValue(ProcessConstant.PR_STATE_CODE)
						+ " >><< " + state);
				this.stateMap.putValue(state.getStringValue(ProcessConstant.PR_STATE_CODE), state);
			}
		}
		return this.stateMap;
	}

	private void mappedChilds(MapUtility state, Element mn) {
		ProcessConfiguration conf = ProcessConfiguration.getConfObj();
		if (mn.element(ProcessConstant.PR_CH_DFSTATE).getText() != null) {
			state.putValue(ProcessConstant.PR_CH_DFSTATE,
					conf.getProcesChildStates().getValue(mn.element(ProcessConstant.PR_CH_DFSTATE).getText()));
		}
		Element chs = mn.element(ProcessConstant.PR_CH_STATES);

		if (chs != null) {
			List<Element> mlist = chs.elements(ProcessConstant.PR_CH_STATE);
			if (mlist != null) {

				for (Element ech : mlist) {

					state.getMap(ProcessConstant.PR_CH_STATES).putValue(ech.getText(),
							conf.getProcesChildStates().getValue(ech.getText()));
				}
			}
		}

		chs = mn.element(ProcessConstant.PR_ST_ENDSTATES);

		if (chs != null) {
			List<Element> mlist = chs.elements(ProcessConstant.PR_CH_STATE);
			if (mlist != null) {

				for (Element ech : mlist) {

					state.getMap(ProcessConstant.PR_ST_ENDSTATES).putValue(ech.getText(),
							conf.getProcesChildStates().getValue(ech.getText()));
				}
			}
		}

		chs = mn.element(ProcessConstant.PR_ST_FSM_LIB);

		if (chs != null) {
			List<Element> mlist = chs.elements(ProcessConstant.PR_ST_FSM);
			if (mlist != null) {

				for (Element ech : mlist) {
					MapUtility map = MapUtility.getInstance();
					map.putValue(ProcessConstant.PR_ST_FSM_CHSTATE,
							conf.getProcesChildStates().getValue(ech.elementText(ProcessConstant.PR_ST_FSM_CHSTATE)));

					map.putValue(ProcessConstant.PR_ST_FSM_N_CHSTATE,
							conf.getProcesChildStates().getValue(ech.elementText(ProcessConstant.PR_ST_FSM_N_CHSTATE)));

					map.putValue(ProcessConstant.PR_ST_FSM_OC,
							conf.getProcesOutcomes().getValue(ech.elementText(ProcessConstant.PR_ST_FSM_OC)));

					map.putValue(ProcessConstant.PR_ST_FSM_DEPT,
							conf.getProcesDepts().getValue(ech.elementText(ProcessConstant.PR_ST_FSM_DEPT)));
					map.putValue(ProcessConstant.PR_ROLE,
							conf.getProcessRoles().getValue(ech.elementText(ProcessConstant.PR_ROLE)));
					map.putValue("KEY",
							ech.elementText(ProcessConstant.PR_ROLE) + "~"
									+ ech.elementText(ProcessConstant.PR_ST_FSM_DEPT) + "~"
									+ ech.elementText(ProcessConstant.PR_ST_FSM_CHSTATE) + "~"
									+ ech.elementText(ProcessConstant.PR_ST_FSM_OC));

					state.getMap(ProcessConstant.PR_ST_FSM_LIB).putValue(map.getStringValue("KEY"), map);
				}
			}
		}

		chs = mn.element(ProcessConstant.PR_ST_CALS_OCS);

		if (chs != null) {
			List<Element> mlist = chs.elements(ProcessConstant.PR_ST_CALS_OC);
			if (mlist != null) {

				for (Element ech : mlist) {
					MapUtility map = MapUtility.getInstance();
					map.putValue(ProcessConstant.PR_ST_FSM_CHSTATE,
							conf.getProcesChildStates().getValue(ech.elementText(ProcessConstant.PR_ST_FSM_CHSTATE)));

					map.putValue(ProcessConstant.PR_ST_FSM_CAL_TYPE,
							ech.elementText(ProcessConstant.PR_ST_FSM_CAL_TYPE));

					map.putValue(ProcessConstant.PR_ST_FSM_OC,
							conf.getProcesOutcomes().getValue(ech.elementText(ProcessConstant.PR_ST_FSM_OC)));

					map.putValue("KEY", ech.elementText(ProcessConstant.PR_ST_FSM_CHSTATE) + "~"
							+ ech.elementText(ProcessConstant.PR_ST_FSM_CAL_TYPE));

					state.getMap(ProcessConstant.PR_ST_CALS_OCS).putValue(map.getStringValue("KEY"), map);
				}
			}
		}

	}

	public void generate() {
		if (this.stateMap != null) {
			Set<String> keys = this.stateMap.keySet();
			for (String key : keys) {
				Element e1 = this.ele.addElement(ProcessConstant.PR_STATE);
				MapUtility state = this.stateMap.getMap(key);

				Element e2 = e1.addElement(ProcessConstant.PR_STATE_CODE);
				e2.add(DocumentHelper.createCDATA(state.getStringValue(ProcessConstant.PR_STATE_CODE)));

				e2 = e1.addElement(ProcessConstant.PR_STATE_NAME);
				e2.add(DocumentHelper.createCDATA(state.getStringValue(ProcessConstant.PR_STATE_NAME)));
				e2 = e1.addElement(ProcessConstant.PR_STATE_TYPE);
				e2.add(DocumentHelper.createCDATA(state.getStringValue(ProcessConstant.PR_STATE_TYPE)));
				if (state.getStringValue(ProcessConstant.PR_STATE_TYPE).equals(ProcessConstant.PR_STATE_TYPE_M)) {
					generateChilds(state, e1);
				}
			}

		}

	}

	private void generateChilds(MapUtility state, Element e1) {
		Element e2 = e1.addElement(ProcessConstant.PR_CH_DFSTATE);
		if (state.getMap(ProcessConstant.PR_CH_DFSTATE) != null)
			e2.add(DocumentHelper.createCDATA(
					state.getMap(ProcessConstant.PR_CH_DFSTATE).getStringValue(ProcessConstant.PR_CH_STATE_CODE)));
		e2 = e1.addElement(ProcessConstant.PR_CH_STATES);
		MapRenderUtility rend = new MapRenderUtility(state.getMap(ProcessConstant.PR_CH_STATES));
		while (rend.isNext()) {
			Element e3 = e2.addElement(ProcessConstant.PR_CH_STATE);

			e3.add(DocumentHelper.createCDATA(rend.getValue(ProcessConstant.PR_CH_STATE_CODE)));

		}
		e2 = e1.addElement(ProcessConstant.PR_ST_ENDSTATES);
		rend = new MapRenderUtility(state.getMap(ProcessConstant.PR_ST_ENDSTATES));
		while (rend.isNext()) {
			Element e3 = e2.addElement(ProcessConstant.PR_CH_STATE);
			e3.add(DocumentHelper.createCDATA(rend.getValue(ProcessConstant.PR_CH_STATE_CODE)));

		}

		e2 = e1.addElement(ProcessConstant.PR_ST_FSM_LIB);
		rend = new MapRenderUtility(state.getMap(ProcessConstant.PR_ST_FSM_LIB));
		while (rend.isNext()) {
			Element e3 = e2.addElement(ProcessConstant.PR_ST_FSM);
			Element e4 = e3.addElement(ProcessConstant.PR_ST_FSM_CHSTATE);
			MapUtility mp = rend.getMap(ProcessConstant.PR_ST_FSM_CHSTATE);
			e4.add(DocumentHelper.createCDATA(mp.getStringValue(ProcessConstant.PR_CH_STATE_CODE)));
			e4 = e3.addElement(ProcessConstant.PR_ST_FSM_N_CHSTATE);
			mp = rend.getMap(ProcessConstant.PR_ST_FSM_N_CHSTATE);
			e4.add(DocumentHelper.createCDATA(mp.getStringValue(ProcessConstant.PR_CH_STATE_CODE)));
			e4 = e3.addElement(ProcessConstant.PR_ST_FSM_OC);
			mp = rend.getMap(ProcessConstant.PR_ST_FSM_OC);
			e4.add(DocumentHelper.createCDATA(mp.getStringValue(ProcessConstant.PR_OC_CODE)));
			e4 = e3.addElement(ProcessConstant.PR_ST_FSM_DEPT);
			mp = rend.getMap(ProcessConstant.PR_ST_FSM_DEPT);
			e4.add(DocumentHelper.createCDATA(mp.getStringValue(ProcessConstant.PR_DEPT_CODE)));

			e4 = e3.addElement(ProcessConstant.PR_ROLE);
			mp = rend.getMap(ProcessConstant.PR_ROLE);
			e4.add(DocumentHelper.createCDATA(mp.getStringValue(ProcessConstant.PR_ROLE_CODE)));

		}

		e2 = e1.addElement(ProcessConstant.PR_ST_CALS_OCS);
		rend = new MapRenderUtility(state.getMap(ProcessConstant.PR_ST_CALS_OCS));
		while (rend.isNext()) {
			Element e3 = e2.addElement(ProcessConstant.PR_ST_CALS_OC);
			Element e4 = e3.addElement(ProcessConstant.PR_ST_FSM_CHSTATE);
			MapUtility mp = rend.getMap(ProcessConstant.PR_ST_FSM_CHSTATE);
			e4.add(DocumentHelper.createCDATA(mp.getStringValue(ProcessConstant.PR_CH_STATE_CODE)));
			e4 = e3.addElement(ProcessConstant.PR_ST_FSM_CAL_TYPE);

			e4.add(DocumentHelper.createCDATA(rend.getValue(ProcessConstant.PR_ST_FSM_CAL_TYPE)));

			e4 = e3.addElement(ProcessConstant.PR_ST_FSM_OC);
			mp = rend.getMap(ProcessConstant.PR_ST_FSM_OC);
			e4.add(DocumentHelper.createCDATA(mp.getStringValue(ProcessConstant.PR_OC_CODE)));

		}

	}
}
