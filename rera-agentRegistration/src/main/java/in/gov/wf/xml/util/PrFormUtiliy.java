package in.gov.wf.xml.util;

import java.util.List;
import java.util.Set;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import in.gov.wf.constant.ProcessConstant;
import in.gov.wf.util.MapUtility;
import in.gov.wf.util.ProcessConfiguration;

public class PrFormUtiliy {
	private Element ele;
	private MapUtility formMap;
	private MapUtility serviceMap;

	public PrFormUtiliy(MapUtility formMap, Element ele) {
		this.formMap = formMap;
		this.ele = ele;
	}

	public PrFormUtiliy(Element ele, MapUtility serviceMap) {

		this.ele = ele;
		this.serviceMap = serviceMap;
	}

	public MapUtility read(MapUtility formMap) {
		this.formMap = formMap;
		List<Element> flist = this.ele.elements(ProcessConstant.PR_FORM);
		ProcessConfiguration cnf = ProcessConfiguration.getConfObj();
		if (flist != null) {
			MapUtility form = null;
			for (Element mn : flist) {

				form = MapUtility.getInstance();
				form.putValue(ProcessConstant.PR_FORM_NAME, mn.element(ProcessConstant.PR_FORM_NAME).getText());
				form.putValue(ProcessConstant.PR_FORM_DESC, mn.element(ProcessConstant.PR_FORM_DESC).getText());
				System.out.println("State in Forms >>> "
						+ cnf.getProcesStates().getValue(mn.element(ProcessConstant.PR_STATE).getText()));
				form.putValue(ProcessConstant.PR_STATE,
						cnf.getProcesStates().getValue(mn.element(ProcessConstant.PR_STATE).getText()));

				form.putValue(ProcessConstant.PR_FORM_ACTIONS, MapUtility.getInstance());

				form.putValue(ProcessConstant.PR_FORM_SECS, MapUtility.getInstance());
				form.putValue(ProcessConstant.PR_FORM_SUBMITS, MapUtility.getInstance());
				List<Element> servs = mn.element(ProcessConstant.PR_FORM_SERVICES)
						.elements(ProcessConstant.PR_FORM_SERVICE);
				MapUtility srmap = MapUtility.getInstance();
				form.putValue(ProcessConstant.PR_FORM_SERVICES, srmap);
				if (servs != null) {
					for (Element serv : servs) {
						srmap.putValue(serv.getText(), this.serviceMap.getMap(serv.getText()));
					}
				}

				List<Element> list = mn.element(ProcessConstant.PR_FORM_ACTIONS)
						.elements(ProcessConstant.PR_FORM_ACTION);
				readActions(list, form.getMap(ProcessConstant.PR_FORM_ACTIONS));
				if (mn.element(ProcessConstant.PR_FORM_SECS) != null) {
					list = mn.element(ProcessConstant.PR_FORM_SECS).elements(ProcessConstant.PR_FORM_SEC);
					readFormSecs(list, form.getMap(ProcessConstant.PR_FORM_SECS));
				}
				if (mn.element(ProcessConstant.PR_FORM_SUBMITS) != null) {
					list = mn.element(ProcessConstant.PR_FORM_SUBMITS).elements(ProcessConstant.PR_FORM_SUBMIT);
					readFormSubmits(list, form.getMap(ProcessConstant.PR_FORM_SUBMITS));
				}

				this.formMap.putValue(form.getStringValue(ProcessConstant.PR_FORM_NAME), form);
			}
		}
		return this.formMap;
	}

	public void generate() {
		if (this.formMap != null) {
			Set<String> keys = this.formMap.keySet();
			for (String key : keys) {
				Element e1 = this.ele.addElement(ProcessConstant.PR_FORM);
				MapUtility frm = this.formMap.getMap(key);

				Element e2 = e1.addElement(ProcessConstant.PR_FORM_NAME);
				e2.add(DocumentHelper.createCDATA(frm.getStringValue(ProcessConstant.PR_FORM_NAME)));

				e2 = e1.addElement(ProcessConstant.PR_FORM_DESC);
				e2.add(DocumentHelper.createCDATA(frm.getStringValue(ProcessConstant.PR_FORM_DESC)));
				MapUtility st = (MapUtility) frm.getValue(ProcessConstant.PR_STATE);
				e2 = e1.addElement(ProcessConstant.PR_STATE);
				e2.add(DocumentHelper.createCDATA(st.getStringValue(ProcessConstant.PR_STATE_CODE)));

				e2 = e1.addElement(ProcessConstant.PR_FORM_SERVICES);
				MapUtility map = frm.getMap(ProcessConstant.PR_FORM_SERVICES);
				if (map != null) {
					Set<String> srvs = map.keySet();
					for (String sr : srvs) {
						e2.addElement(ProcessConstant.PR_FORM_SERVICE).add(DocumentHelper.createCDATA(sr));
					}
				}

				map = frm.getMap(ProcessConstant.PR_FORM_ACTIONS);
				e2 = e1.addElement(ProcessConstant.PR_FORM_ACTIONS);
				generateFormActinXML(e2, map);

				map = frm.getMap(ProcessConstant.PR_FORM_SECS);
				e2 = e1.addElement(ProcessConstant.PR_FORM_SECS);
				generateFormSecsXML(e2, map);

				map = frm.getMap(ProcessConstant.PR_FORM_SUBMITS);
				e2 = e1.addElement(ProcessConstant.PR_FORM_SUBMITS);
				generateFormSubmitXML(e2, map);

			}

		}
	}

	private void readFormSubmits(List<Element> list, MapUtility cmap) {
		if (list != null) {
			MapUtility act = null;
			for (Element ch : list) {
				act = MapUtility.getInstance();
				act.putValue(ProcessConstant.PR_OUTCOME, ch.element(ProcessConstant.PR_OUTCOME).getText());

				act.putValue(ProcessConstant.PR_ROLE, ch.element(ProcessConstant.PR_ROLE).getText());
				act.putValue(ProcessConstant.PR_CH_STATE, ch.element(ProcessConstant.PR_CH_STATE).getText());
				act.putValue(ProcessConstant.PR_DEPT, ch.element(ProcessConstant.PR_DEPT).getText());
				act.putValue(ProcessConstant.PR_SERVICE, ch.element(ProcessConstant.PR_SERVICE).getText());
				cmap.putValue(ch.element(ProcessConstant.PR_OUTCOME).getText() + "~"
						+ ch.element(ProcessConstant.PR_ROLE).getText() + "~"
						+ ch.element(ProcessConstant.PR_CH_STATE).getText() + "~"
						+ ch.element(ProcessConstant.PR_DEPT).getText(), act);
			}

		}
	}

	private void generateFormSubmitXML(Element ele, MapUtility map) {
		if (map != null) {
			Set<String> keys = map.keySet();
			for (String key : keys) {
				MapUtility act = map.getMap(key);
				Element e1 = ele.addElement(ProcessConstant.PR_FORM_SUBMIT);

				Element e2 = e1.addElement(ProcessConstant.PR_SERVICE);
				e2.add(DocumentHelper.createCDATA(act.getStringValue(ProcessConstant.PR_SERVICE)));
				e2 = e1.addElement(ProcessConstant.PR_ROLE);
				e2.add(DocumentHelper.createCDATA(act.getStringValue(ProcessConstant.PR_ROLE)));
				e2 = e1.addElement(ProcessConstant.PR_CH_STATE);
				e2.add(DocumentHelper.createCDATA(act.getStringValue(ProcessConstant.PR_CH_STATE)));
				e2 = e1.addElement(ProcessConstant.PR_DEPT);
				e2.add(DocumentHelper.createCDATA(act.getStringValue(ProcessConstant.PR_DEPT)));
				e2 = e1.addElement(ProcessConstant.PR_OUTCOME);
				e2.add(DocumentHelper.createCDATA(act.getStringValue(ProcessConstant.PR_OUTCOME)));

			}
		}
	}

	private void readFormSecs(List<Element> list, MapUtility cmap) {
		if (list != null) {
			MapUtility act = null;
			for (Element ch : list) {
				act = MapUtility.getInstance();
				act.putValue(ProcessConstant.PR_FORM_SEC, ch.element(ProcessConstant.PR_SEC_CODE).getText());

				act.putValue(ProcessConstant.PR_ROLE, ch.element(ProcessConstant.PR_ROLE).getText());
				act.putValue(ProcessConstant.PR_CH_STATE, ch.element(ProcessConstant.PR_CH_STATE).getText());
				act.putValue(ProcessConstant.PR_DEPT, ch.element(ProcessConstant.PR_DEPT).getText());
				cmap.putValue(ch.element(ProcessConstant.PR_SEC_CODE).getText() + "~"
						+ ch.element(ProcessConstant.PR_ROLE).getText() + "~"
						+ ch.element(ProcessConstant.PR_CH_STATE).getText() + "~"
						+ ch.element(ProcessConstant.PR_DEPT).getText(), act);
			}

		}
	}

	private void generateFormSecsXML(Element ele, MapUtility map) {
		if (map != null) {
			Set<String> keys = map.keySet();
			for (String key : keys) {
				MapUtility act = map.getMap(key);
				Element e1 = ele.addElement(ProcessConstant.PR_FORM_SEC);

				Element e2 = e1.addElement(ProcessConstant.PR_SEC_CODE);
				e2.add(DocumentHelper.createCDATA(act.getStringValue(ProcessConstant.PR_FORM_SEC)));
				e2 = e1.addElement(ProcessConstant.PR_ROLE);
				e2.add(DocumentHelper.createCDATA(act.getStringValue(ProcessConstant.PR_ROLE)));
				e2 = e1.addElement(ProcessConstant.PR_CH_STATE);
				e2.add(DocumentHelper.createCDATA(act.getStringValue(ProcessConstant.PR_CH_STATE)));
				e2 = e1.addElement(ProcessConstant.PR_DEPT);
				e2.add(DocumentHelper.createCDATA(act.getStringValue(ProcessConstant.PR_DEPT)));

			}
		}
	}

	private void readActions(List<Element> list, MapUtility cmap) {
		if (list != null) {
			MapUtility act = null;
			for (Element ch : list) {
				act = MapUtility.getInstance();
				act.putValue(ProcessConstant.PR_FORM_ACTION_NAME,
						ch.element(ProcessConstant.PR_FORM_ACTION_NAME).getText());

				act.putValue(ProcessConstant.PR_FORM_ACTION_ROLE,
						ch.element(ProcessConstant.PR_FORM_ACTION_ROLE).getText());
				act.putValue(ProcessConstant.PR_OUTCOME, ch.element(ProcessConstant.PR_OUTCOME).getText());
				act.putValue(ProcessConstant.PR_FORM_ACTION_CH_STATE,
						ch.element(ProcessConstant.PR_FORM_ACTION_CH_STATE).getText());
				act.putValue(ProcessConstant.PR_FORM_ACTION_DEPT,
						ch.element(ProcessConstant.PR_FORM_ACTION_DEPT).getText());
				cmap.putValue(ch.element(ProcessConstant.PR_FORM_ACTION_NAME).getText() + "~"
						+ ch.element(ProcessConstant.PR_FORM_ACTION_ROLE).getText() + "~"
						+ ch.element(ProcessConstant.PR_FORM_ACTION_CH_STATE).getText() + "~"
						+ ch.element(ProcessConstant.PR_FORM_ACTION_DEPT).getText(), act);
			}

		}
	}

	private void generateFormActinXML(Element ele, MapUtility map) {
		if (map != null) {
			Set<String> keys = map.keySet();
			for (String key : keys) {
				MapUtility act = map.getMap(key);
				Element e1 = ele.addElement(ProcessConstant.PR_FORM_ACTION);

				Element e2 = e1.addElement(ProcessConstant.PR_FORM_ACTION_NAME);
				e2.add(DocumentHelper.createCDATA(act.getStringValue(ProcessConstant.PR_FORM_ACTION_NAME)));
				e2 = e1.addElement(ProcessConstant.PR_FORM_ACTION_ROLE);
				e2.add(DocumentHelper.createCDATA(act.getStringValue(ProcessConstant.PR_FORM_ACTION_ROLE)));
				e2 = e1.addElement(ProcessConstant.PR_OUTCOME);
				e2.add(DocumentHelper.createCDATA(act.getStringValue(ProcessConstant.PR_OUTCOME)));
				e2 = e1.addElement(ProcessConstant.PR_FORM_ACTION_CH_STATE);
				e2.add(DocumentHelper.createCDATA(act.getStringValue(ProcessConstant.PR_FORM_ACTION_CH_STATE)));
				e2 = e1.addElement(ProcessConstant.PR_FORM_ACTION_DEPT);
				e2.add(DocumentHelper.createCDATA(act.getStringValue(ProcessConstant.PR_FORM_ACTION_DEPT)));

			}
		}
	}

}
