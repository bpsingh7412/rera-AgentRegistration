package in.gov.wf.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.gov.agentregistration.model.AgentRegistrationModel;
import in.gov.wf.dao.ActivityLogDao;
import in.gov.wf.model.FormRequest;
import in.gov.wf.model.FormResponse;
import in.gov.wf.model.ResponseDto;
import in.gov.wf.model.WFEntity;
import in.gov.wf.security.AuthUser;
import in.gov.wf.service.ActivitySignalServices;
import in.gov.wf.service.WFService;
import in.gov.wf.util.MapUtility;
import in.gov.wf.util.ProcessConfiguration;
import in.gov.wf.util.WfFormUtill;
import in.gov.wf.util.WorkflowEngine;

@CrossOrigin("*")
@RestController
public class WFController {

	@Autowired
	WFService wfService;

	@RequestMapping(value = "/project_alt/secure/projectaltform/{wid}", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<FormResponse> viewTrx(@PathVariable("wid") String wfoId, HttpServletRequest req,
			HttpServletRequest resp) {
		System.out.println("WFOID " + wfoId);
		try {
			WFEntity entity = wfService.getWfEntityById(wfoId);
			AuthUser user = AuthUser.getLoggedInUser(req);
			WfFormUtill util = new WfFormUtill();
			FormResponse fresp = util.getFormAttr(entity, user);
			AgentRegistrationModel project = (AgentRegistrationModel) entity;
			// entity=project.populateResponse();
			fresp.setEntity(project);
			return ResponseEntity.ok(fresp);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.ok(new FormResponse());
		}
	}

	@Autowired
	WorkflowEngine engine;

	/******************************************************************************************
	 * wfform submit
	 *****************************************************************************************/
	@RequestMapping(value = "/project_alt/secure/wfformsubmit", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<FormResponse> submitTrx(@RequestBody FormRequest submit, HttpServletRequest req,
			HttpServletRequest resp) {
		WFEntity entity = wfService.getWfEntityById(submit.getWfoId());

		try {

			engine.runWorkflow(submit, req);
			return ResponseEntity.ok(FormResponse.ok("Suucess"));
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.ok(FormResponse.error(ex.getMessage()));
		}

	}

	@RequestMapping(value = "/project_alt/projectformtest", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<Object> viewTrxTest(HttpServletRequest req, HttpServletRequest resp) {
		ProcessConfiguration conf = ProcessConfiguration.getConfObj();
		MapUtility map = conf.getProcesStates();
		return ResponseEntity.ok(map.getMap());
	}

	@RequestMapping(value = "/project_alt/secure/activitylog/{wfoid}", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<ResponseDto> activitylog(@PathVariable("wfoid") String wfoId) {
		return ResponseEntity.ok(ResponseDto.ok(logDao.findAllByWfoId(wfoId)));
	}

	@RequestMapping(value = "/project_alt/secure/signal/{wfoid}", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<ResponseDto> activitySignal(@PathVariable("wfoid") String wfoId) {
		return ResponseEntity.ok(ResponseDto.ok(signalService.getSignalData(wfoId)));
	}

	@Autowired
	private ActivityLogDao logDao;

	@Autowired
	private ActivitySignalServices signalService;
}
