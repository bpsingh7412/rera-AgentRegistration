package in.gov.wf.util;

import java.util.HashMap;
import java.util.Map;

import in.gov.wf.constant.ProcessConstant;
import in.gov.wf.model.FormResponse;
import in.gov.wf.model.WFEntity;
import in.gov.wf.security.AuthUser;

public class WfFormUtill {

	private WFEntity entity;
	private AuthUser user;
	private String chState;

	public FormResponse getFormAttr(WFEntity entity, AuthUser user) {
		this.entity = entity;
		this.user = user;
		getChState();
		FormResponse resp = new FormResponse();
		ProcessUtill futil = new ProcessUtill();
		MapUtility form = futil.getForm(entity.getStateCode());
		System.out.println("Forms >>> " + form);
		MapUtility acts = form.getMap(ProcessConstant.PR_FORM_ACTIONS);
		MapUtility secs = form.getMap(ProcessConstant.PR_FORM_SECS);
		MapUtility servs = form.getMap(ProcessConstant.PR_FORM_SERVICES);
		executeServices(servs);
		resp.setWfoId(entity.getWfoId());
		resp.setEntity(entity);

		resp.setSections(getFormSecs(secs));
		resp.setActions(getFormActs(acts));
		resp.setStatus(200);
		return resp;
	}

	private Map<String, Boolean> getFormSecs(MapUtility map) {
		Map<String, Boolean> secs = new HashMap<String, Boolean>();
		MapRenderUtility rend = new MapRenderUtility(map);
		while (rend.isNext()) {
			MapUtility sec = rend.next();
			secs.put(sec.getStringValue(ProcessConstant.PR_FORM_SEC), (sec.getStringValue(ProcessConstant.PR_ROLE)
					.equals(this.user.getUserType())
					|| sec.getStringValue(ProcessConstant.PR_ROLE).equals(ProcessConstant.PR_DEFUALT_VALUE))
					&& (sec.getStringValue(ProcessConstant.PR_CH_STATE).equals(this.chState)
							|| sec.getStringValue(ProcessConstant.PR_CH_STATE).equals(ProcessConstant.PR_DEFUALT_VALUE))
					&& (sec.getStringValue(ProcessConstant.PR_DEPT).equals(this.user.getDepartment())
							|| sec.getStringValue(ProcessConstant.PR_DEPT).equals(ProcessConstant.PR_DEFUALT_VALUE)));
		}
		return secs;
	}

	private Map<String, Map<String, Object>> getFormActs(MapUtility map) {
		Map<String, Map<String, Object>> secs = new HashMap<String, Map<String, Object>>();
		MapRenderUtility rend = new MapRenderUtility(map);
		while (rend.isNext()) {
			MapUtility sec = rend.next();

			// act.putValue(ProcessConstant.PR_OUTCOME,ocname);

			if (sec.getStringValue(ProcessConstant.PR_FORM_ACTION_ROLE).equals(this.user.getUserType())
					&& (sec.getStringValue(ProcessConstant.PR_FORM_ACTION_DEPT).equals(this.user.getDepartment()))) {
				System.out.println(sec.getStringValue(ProcessConstant.PR_FORM_ACTION_NAME) + " >> "
						+ this.user.getUserType() + "  >> " + sec.getStringValue(ProcessConstant.PR_FORM_ACTION_ROLE));
				System.out.println(sec.getStringValue(ProcessConstant.PR_FORM_ACTION_NAME) + " >> " + this.chState
						+ "  >> " + sec.getStringValue(ProcessConstant.PR_FORM_ACTION_CH_STATE));
				System.out.println(
						sec.getStringValue(ProcessConstant.PR_FORM_ACTION_NAME) + " >> " + this.user.getDepartment()
								+ "  >> " + sec.getStringValue(ProcessConstant.PR_FORM_ACTION_DEPT));

				Map<String, Object> act = new HashMap<String, Object>();
				act.put("auth", (sec.getStringValue(ProcessConstant.PR_FORM_ACTION_CH_STATE).equals(this.chState)));
				act.put("outcome", sec.getStringValue(ProcessConstant.PR_OUTCOME));
				secs.put(sec.getStringValue(ProcessConstant.PR_FORM_ACTION_NAME), act);
			}
		}
		return secs;
	}

	private void executeServices(MapUtility servs) {

	}

	private void getChState() {
		if (!this.user.getDepartment().equals(ProcessConstant.PR_DEFUALT_VALUE)) {
			if (this.user.getDepartment().equals("TP")) {
				this.chState = this.entity.getTpState();
			} else if (this.user.getDepartment().equals("FIN")) {
				this.chState = this.entity.getFinState();
			} else if (this.user.getDepartment().equals("LEGAL")) {
				this.chState = this.entity.getLegalState();
			} else {
				this.chState = ProcessConstant.PR_DEFUALT_VALUE;
			}
		} else {
			this.chState = ProcessConstant.PR_DEFUALT_VALUE;
		}

	}
}
