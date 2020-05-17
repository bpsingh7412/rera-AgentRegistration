package in.gov.wf.xml.util;

import java.io.File;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import in.gov.wf.constant.ProcessConstant;
import in.gov.wf.util.ProcessConfiguration;

public class LoadConfiguration {

	private ProcessConfiguration conf;
	private Element rootEle;

	public LoadConfiguration() {

		ProcessConfiguration.buildNewConf();
		conf = ProcessConfiguration.getConfObj();

	}

	private void loadConfFile(InputStream in) throws Exception {

		SAXReader reader = new SAXReader();
		Document xmldoc = reader.read(in);
		this.rootEle = xmldoc.getRootElement();
	}

//	public void readConfiguration(File cfile) throws Exception{
	// loadConfFile(cfile);
	public void readConfiguration(InputStream in) throws Exception {
		loadConfFile(in);
		conf.getProcessConf().putValue(ProcessConstant.PR_NAME, rootEle.element(ProcessConstant.PR_NAME).getText());
		conf.getProcessConf().putValue(ProcessConstant.PR_DESC, rootEle.element(ProcessConstant.PR_DESC).getText());

		Element chele = this.rootEle.element(ProcessConstant.PR_SERVICES);
		readServices(chele);

		chele = this.rootEle.element(ProcessConstant.PR_ROLES);
		readRoles(chele);

		chele = this.rootEle.element(ProcessConstant.PR_DEPTS);
		readDepts(chele);
		chele = this.rootEle.element(ProcessConstant.PR_OCS);
		readOutComes(chele);

		chele = this.rootEle.element(ProcessConstant.PR_CH_STATES);
		readChildStates(chele);

		chele = this.rootEle.element(ProcessConstant.PR_STATES);
		readStates(chele);

		chele = this.rootEle.element(ProcessConstant.PR_TEAMS);
		readTeams(chele);

		chele = this.rootEle.element(ProcessConstant.PR_ACTIONS);
		readActions(chele);

		chele = this.rootEle.element(ProcessConstant.PR_FSM_LIB);
		readFmsLib(chele);

		chele = this.rootEle.element(ProcessConstant.PR_FORM_SECS);
		readFormSecs(chele);

		chele = this.rootEle.element(ProcessConstant.PR_FORMS);
		readForms(chele);

	}

	private void readServices(Element ele) {
		PrServicesUtiliy util = new PrServicesUtiliy(ele);
		util.read(conf.getProcessServices());
	}

	private void readChildStates(Element ele) {
		PrChildStateUtility util = new PrChildStateUtility(ele);
		util.read(conf.getProcesChildStates());
	}

	private void readStates(Element ele) {
		PrStateUtility util = new PrStateUtility(ele);
		util.read(conf.getProcesStates());
	}

	private void readOutComes(Element ele) {
		PrOCUtility util = new PrOCUtility(ele);
		util.read(conf.getProcesOutcomes());
	}

	private void readFmsLib(Element ele) {
		PrFSMUtility util = new PrFSMUtility(ele);
		util.read(conf.getProcesFsmLib());
	}

	private void readActions(Element ele) {
		PrActionUtility util = new PrActionUtility(ele);
		util.read(conf.getProcesActions());
	}

	private void readTeams(Element ele) {
		PrTeamUtility util = new PrTeamUtility(ele);
		util.read(conf.getProcessTeams());
	}

	private void readRoles(Element ele) {
		PrRoleUtility util = new PrRoleUtility(ele);
		util.read(conf.getProcessRoles());
	}

	private void readDepts(Element ele) {
		PrDepartmentUtility util = new PrDepartmentUtility(ele);
		util.read(conf.getProcesDepts());
	}

	private void readFormSecs(Element ele) {
		PrFormSecUtility util = new PrFormSecUtility(ele);
		util.read(conf.getProcessFormSecs());
	}

	private void readForms(Element ele) {
		PrFormUtiliy util = new PrFormUtiliy(ele, conf.getProcessServices());
		util.read(conf.getProcessForms());
	}
}
