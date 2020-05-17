package in.gov.wf.util;

import in.gov.wf.constant.ProcessConstant;

public class ProcessUtill {

	static ProcessConfiguration conf = ProcessConfiguration.getConfObj();

	public String getProcessName() {
		return conf.getProcessConf().getStringValue(ProcessConstant.PR_NAME);
	}

	public String getNextState(String stateCode, String outcome) {
		MapUtility map = conf.getProcesFsmLib();
		if (map.containsKey(stateCode + "~" + outcome)) {
			return map.getStringValue(stateCode + "~" + outcome);
		} else {
			return stateCode;
		}
	}

	public String getNextChildState(String stateCode, String chStateCode, String outcome, String deptCode) {
		MapUtility state = conf.getProcesStates().getMap(stateCode);
		MapUtility fsm = state.getMap(ProcessConstant.PR_ST_FSM_LIB);

		return "";
	}

	public boolean isNestedState(String stateCode) {
		return conf.getProcesStates().getMap(stateCode).getStringValue(ProcessConstant.PR_STATE_TYPE)
				.equals(ProcessConstant.PR_STATE_TYPE_M);

	}

	public MapUtility getSubmitForm(String formName, String stateCode) {
		MapUtility form = getForm(formName, stateCode);
		return form.getMap(ProcessConstant.PR_FORM_SUBMITS);
	}

	public MapUtility getSubmitForm(String stateCode) {
		MapUtility form = getForm(stateCode);
		return form.getMap(ProcessConstant.PR_FORM_SUBMITS);
	}

	public MapUtility getFormSections(String formName, String stateCode) {
		MapUtility form = getForm(formName, stateCode);
		return form.getMap(ProcessConstant.PR_FORM_SECS);
	}

	public MapUtility getFormSections(String stateCode) {
		MapUtility form = getForm(stateCode);
		return form.getMap(ProcessConstant.PR_FORM_SECS);
	}

	public MapUtility getFormActions(String formName, String stateCode) {
		MapUtility form = getForm(formName, stateCode);
		return form.getMap(ProcessConstant.PR_FORM_ACTIONS);
	}

	public MapUtility getFormActions(String stateCode) {
		MapUtility form = getForm(stateCode);
		return form.getMap(ProcessConstant.PR_FORM_ACTIONS);
	}

	public MapUtility getFormServices(String formName, String stateCode) {
		MapUtility form = getForm(formName, stateCode);
		return form.getMap(ProcessConstant.PR_FORM_SERVICES);
	}

	public MapUtility getFormServices(String stateCode) {
		MapUtility form = getForm(stateCode);
		return form.getMap(ProcessConstant.PR_FORM_SERVICES);
	}

	public MapUtility getForm(String formName, String stateCode) {
		return conf.getProcessForms().getMap(formName + "~" + stateCode);
	}

	public MapUtility getForm(String stateCode) {
		MapRenderUtility ren = conf.getFormRender();
		MapUtility map = null;
		while (ren.isNext()) {
			map = ren.next();

			if (map.getMap(ProcessConstant.PR_STATE).getStringValue(ProcessConstant.PR_STATE_CODE).equals(stateCode)) {
				break;
			} else {
				map = null;
			}
		}
		return map;
	}
}
