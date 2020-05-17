package in.gov.wf.util;

import java.io.InputStream;
import in.gov.wf.constant.ProcessConstant;
import in.gov.wf.xml.util.LoadConfiguration;

public class ProcessConfiguration {

	private static ProcessConfiguration configuration;

	static {
		try {

			InputStream in = ProcessConfiguration.class.getResourceAsStream("/pr_conf/processconf.xml");

			LoadConfiguration loader = new LoadConfiguration();

			loader.readConfiguration(in);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void buildNewConf() {
		configuration = new ProcessConfiguration();
	}

	public static ProcessConfiguration getConfObj() {
		return configuration;
	}

	public static void destroy() {
		configuration = null;
	}

	private String workspacePath;

	private MapUtility processMap;
	private MapUtility formsMap;
	private MapUtility formSecMap;
	private MapUtility servicesMap;
	private MapUtility rolesMap;
	private MapUtility teamsMap;
	private MapUtility stateMap;
	private MapUtility childStateMap;
	private MapUtility departmentMap;
	private MapUtility actionMap;
	private MapUtility outComeMap;
	private MapUtility fsmMap;

	private ProcessConfiguration() {
		this.stateMap = MapUtility.getInstance();
		this.actionMap = MapUtility.getInstance();
		this.outComeMap = MapUtility.getInstance();
		this.fsmMap = MapUtility.getInstance();
		this.childStateMap = MapUtility.getInstance();
		this.departmentMap = MapUtility.getInstance();
		this.processMap = MapUtility.getInstance();

		this.formsMap = MapUtility.getInstance();
		this.formSecMap = MapUtility.getInstance();
		this.servicesMap = MapUtility.getInstance();
		this.rolesMap = MapUtility.getInstance();
		this.teamsMap = MapUtility.getInstance();

		this.processMap.putValue(ProcessConstant.PR_DEPTS, this.departmentMap);
		this.processMap.putValue(ProcessConstant.PR_CH_STATES, this.childStateMap);
		this.processMap.putValue(ProcessConstant.PR_STATES, this.stateMap);
		this.processMap.putValue(ProcessConstant.PR_ACTIONS, this.actionMap);
		this.processMap.putValue(ProcessConstant.PR_FSM_LIB, this.fsmMap);
		this.processMap.putValue(ProcessConstant.PR_OCS, this.outComeMap);

		this.processMap.putValue(ProcessConstant.PR_FORMS, this.formsMap);
		this.processMap.putValue(ProcessConstant.PR_FORM_SECS, this.formSecMap);

		this.processMap.putValue(ProcessConstant.PR_SERVICES, this.servicesMap);
		this.processMap.putValue(ProcessConstant.PR_ROLES, this.rolesMap);
		this.processMap.putValue(ProcessConstant.PR_TEAMS, this.teamsMap);

	}

	public MapUtility getProcessConf() {
		return this.processMap;
	}

	public MapUtility getProcessForms() {
		return this.formsMap;
	}

	public MapUtility getProcessFormSecs() {
		return this.formSecMap;
	}

	public MapUtility getProcessRoles() {
		return this.rolesMap;
	}

	public MapUtility getProcessServices() {
		return this.servicesMap;
	}

	public MapUtility getProcessTeams() {
		return this.teamsMap;
	}

	public MapUtility getProcesStates() {
		return this.stateMap;
	}

	public MapUtility getProcesChildStates() {
		return this.childStateMap;
	}

	public MapUtility getProcesDepts() {
		return this.departmentMap;
	}

	public MapUtility getProcesActions() {
		return this.actionMap;
	}

	public MapUtility getProcesOutcomes() {
		return this.outComeMap;
	}

	public MapUtility getProcesFsmLib() {
		return this.fsmMap;
	}

	public String getWorkspacePath() {
		return workspacePath;
	}

	public void setWorkspacePath(String workspacePath) {
		this.workspacePath = workspacePath;
	}

	public MapRenderUtility getFormRender() {
		return new MapRenderUtility(this.formsMap);
	}

	public MapRenderUtility getFormSecRender() {
		return new MapRenderUtility(this.formSecMap);
	}

	public MapRenderUtility getServiceRender() {
		return new MapRenderUtility(this.servicesMap);
	}

	public MapRenderUtility getRoleRender() {
		return new MapRenderUtility(this.rolesMap);
	}

	public MapRenderUtility getTeamRender() {
		return new MapRenderUtility(this.teamsMap);
	}

	public MapRenderUtility getStateRender() {
		return new MapRenderUtility(this.stateMap);
	}

	public MapRenderUtility getChildStateRender() {
		return new MapRenderUtility(this.childStateMap);
	}

	public MapRenderUtility getDepartmentRender() {
		return new MapRenderUtility(this.departmentMap);
	}

	public MapRenderUtility getOutComeRender() {
		return new MapRenderUtility(this.outComeMap);
	}

	public MapRenderUtility getFSMRender() {
		return new MapRenderUtility(this.fsmMap);
	}

	public MapRenderUtility getActionsRender() {
		return new MapRenderUtility(this.actionMap);
	}

}
