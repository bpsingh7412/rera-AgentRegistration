package in.gov.agentregistration.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import in.gov.agentregistration.constants.CommonConstants;
import in.gov.agentregistration.model.AgentSaveWorkStatusYTrxModel;
import in.gov.agentregistration.model.AgentWorkStatusModel;
import in.gov.agentregistration.model.DefaulterStatusModel;
import in.gov.agentregistration.model.ResponseModel;
import in.gov.agentregistration.model.YearlyStatusDto;
import in.gov.agentregistration.model.YearlyStatusModel;
import in.gov.agentregistration.security.AuthUser;
import in.gov.agentregistration.services.AgentWorkStatusService;
import in.gov.agentregistration.services.YearlyStatusService;
import in.gov.wf.util.DateUtil;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/agent_reg/secure/agent/work")

public class AgentRegWorkStatusController {
	private static final Logger logger = LogManager.getLogger(AgentRegWorkStatusController.class);

	@Autowired
	YearlyStatusService yearlyStatusService;

	@Autowired
	AgentWorkStatusService agentWorkStatusService;

	@Autowired
	private Environment env;

	@GetMapping("/auto-gen-y")
	public ResponseEntity<?> autoGenerateYear(HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		AuthUser user = AuthUser.getLoggedInUser(req);
		Calendar cal = Calendar.getInstance();
		YearlyStatusModel yStatus = new YearlyStatusModel();
		yStatus.setStartDate(DateUtil.getStartDateOfFinYear(cal).getTime());
		yStatus.setEndDate(DateUtil.getEndDateOfFinYear(cal).getTime());
		cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		cal.add(Calendar.YEAR, 1);
		cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 5);
		cal.add(Calendar.DATE, -1);
		yStatus.setActualEndDate(cal.getTime());
		yStatus.setDisplayName("Y1");
		yStatus.setIsActive(1);
		yStatus.setCreatedBy(user.getUserId());
		yStatus.setStatus(CommonConstants.ACTIVE);
		yStatus.setAgentId(user.getProfileId());
		yStatus = yearlyStatusService.save(yStatus);
		YearlyStatusModel yStatus2 = new YearlyStatusModel();
		if (yStatus != null) {
			cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, 1);
			yStatus2.setStartDate(DateUtil.getStartDateOfFinYear(cal).getTime());
			yStatus2.setEndDate(DateUtil.getEndDateOfFinYear(cal).getTime());
			cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			cal.add(Calendar.YEAR, 2);
			cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, 5);
			cal.add(Calendar.DATE, -1);
			yStatus2.setActualEndDate(cal.getTime());
			yStatus2.setDisplayName("Y2");
			yStatus2.setIsActive(0);
			yStatus2.setCreatedBy(user.getUserId());
			yStatus2.setStatus(CommonConstants.IN_ACTIVE);
			yStatus2.setAgentId(user.getProfileId());

			yStatus2 = yearlyStatusService.save(yStatus2);
		}

		YearlyStatusModel yStatus3 = new YearlyStatusModel();
		if (yStatus2 != null) {
			cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, 2);
			yStatus3.setStartDate(DateUtil.getStartDateOfFinYear(cal).getTime());
			yStatus3.setEndDate(DateUtil.getEndDateOfFinYear(cal).getTime());
			cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, 3);
			cal.add(Calendar.DATE, -1);
			cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, 5);
			cal.add(Calendar.DATE, -1);
			yStatus3.setActualEndDate(cal.getTime());
			yStatus3.setDisplayName("Y3");
			yStatus3.setIsActive(0);
			yStatus3.setCreatedBy(user.getUserId());
			yStatus3.setStatus(CommonConstants.IN_ACTIVE);
			yStatus3.setAgentId(user.getProfileId());

			yStatus3 = yearlyStatusService.save(yStatus3);
		}

		YearlyStatusModel yStatus4 = new YearlyStatusModel();

		if (yStatus3 != null) {
			cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, 3);
			yStatus4.setStartDate(DateUtil.getStartDateOfFinYear(cal).getTime());
			yStatus4.setEndDate(DateUtil.getEndDateOfFinYear(cal).getTime());
			cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, 4);
			cal.add(Calendar.DATE, -1);
			cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, 5);
			cal.add(Calendar.DATE, -1);
			yStatus4.setActualEndDate(cal.getTime());
			yStatus4.setDisplayName("Y4");
			yStatus4.setIsActive(0);
			yStatus4.setCreatedBy(user.getUserId());
			yStatus4.setStatus(CommonConstants.IN_ACTIVE);
			yStatus4.setAgentId(user.getProfileId());
			yStatus4 = yearlyStatusService.save(yStatus4);
		}

		YearlyStatusModel yStatus5 = new YearlyStatusModel();
		if (yStatus4 != null) {
			cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, 4);
			yStatus5.setStartDate(DateUtil.getStartDateOfFinYear(cal).getTime());
			yStatus5.setEndDate(DateUtil.getEndDateOfFinYear(cal).getTime());
			cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			cal.add(Calendar.YEAR, 5);
			cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, 5);
			cal.add(Calendar.DATE, -1);
			yStatus5.setActualEndDate(cal.getTime());
			yStatus5.setDisplayName("Y5");
			yStatus5.setIsActive(0);
			yStatus5.setCreatedBy(user.getUserId());
			yStatus5.setStatus(CommonConstants.IN_ACTIVE);
			yStatus5.setAgentId(user.getProfileId());
			yStatus5 = yearlyStatusService.save(yStatus5);
		}
		List<YearlyStatusModel> yStatusList = yearlyStatusService.findByAgentId(user.getProfileId());

		return ResponseEntity.ok().body(yStatusList);
	}

	
	
	@GetMapping("/get-auto-gent-y")
	public ResponseEntity<?> getAutoGenerateYearByAgentId(HttpServletRequest req) {
        ResponseModel rs = new ResponseModel();
        AuthUser user = AuthUser.getLoggedInUser(req);
		List<YearlyStatusModel> yStatusList = yearlyStatusService.findByAgentId(user.getProfileId());
		if (yStatusList != null && yStatusList.size() > 0) {
			rs.setAgenId(null);
			rs.setData(yStatusList);
			rs.setMessage("GET AUTO GENT YEARS");
			rs.setStatus("200");
		} else {
			rs.setAgenId(null);
			rs.setMessage("DATA NOT FOUND");
			rs.setData(yStatusList);
			rs.setStatus("400");
		}
		return ResponseEntity.ok(rs);
	}

	@GetMapping("/get-auto-gent-y-id/{agentId}")
	public ResponseEntity<?> getAutoGenerateYearByAgentId2(@PathVariable(value = "agentId") Long agentId) {
        ResponseModel rs = new ResponseModel();
		List<YearlyStatusModel> yStatusList = yearlyStatusService.findByAgentId(agentId);
		if (yStatusList != null && yStatusList.size() > 0) {
			rs.setAgenId(null);
			rs.setData(yStatusList);
			rs.setMessage("GET AUTO GENT YEARS");
			rs.setStatus("200");
		} else {
			rs.setAgenId(null);
			rs.setMessage("DATA NOT FOUND");
			rs.setData(yStatusList);
			rs.setStatus("400");
		}
		return ResponseEntity.ok(rs);
	}
	
	@GetMapping("/get-work-y-byid/{yId}")
	public ResponseEntity<?> getWorkYId(@PathVariable(value = "yId") Long yId) {

		ResponseModel rs = new ResponseModel();
		YearlyStatusModel yStatus = yearlyStatusService.getYearlyStatusById(yId);

		if (yStatus != null) {
			rs.setAgenId(null);
			rs.setData(yStatus);
			rs.setMessage("GET  AGENT YEARS");
			rs.setStatus("200");
		} else {
			rs.setAgenId(null);
			rs.setMessage("DATA NOT FOUND");
			rs.setData(yStatus);
			rs.setStatus("400");
		}
		return ResponseEntity.ok(rs);
	}

	@PostMapping("/save-update-list-work-status-y")
	public ResponseEntity<?> saveUpdateWorkStatusY(@RequestBody AgentSaveWorkStatusYTrxModel yWorkStatus,HttpServletRequest req) {
		
		ResponseModel rs = new ResponseModel();
		AuthUser user = AuthUser.getLoggedInUser(req);
		YearlyStatusModel yStatus = yearlyStatusService.getYearlyStatusById(yWorkStatus.getYearId());
		yStatus.setAgentWorkList(yWorkStatus.getAgentWorkStatusList());
		yStatus.setRemarks(yWorkStatus.getRemark());
		yStatus.setCreatedBy(user.getProfileId());
		yStatus.setLastModifiedBy(user.getProfileId());
		yStatus = yearlyStatusService.save(yStatus);
		if (yStatus != null) {
			rs.setAgenId(null);
			rs.setData(yStatus);
			rs.setMessage("SAVED AGENT WORK STATUS YEARLY");
			rs.setStatus("200");
		} else {
			rs.setAgenId(null);
			rs.setMessage("DATA NOT FOUND");
			rs.setData(yStatus);
			rs.setStatus("400");
		}
		return ResponseEntity.ok(rs);
	}
	//  will upate single defaulter status
	@PostMapping("/reopen-one-agent")
	public ResponseEntity<?> saveUpdateAgentDefaY(@RequestBody DefaulterStatusModel agentDefa,
			 HttpServletRequest req) {
		ResponseModel rs = new ResponseModel();
		AuthUser user = AuthUser.getLoggedInUser(req);
        YearlyStatusModel yStatus = yearlyStatusService.getYearlyStatusById(agentDefa.getyStatus());
        agentDefa.setReOpenBy(user.getUserId());
        agentDefa.setyStatus(1l);
		yStatus.getDefaulerList().add(agentDefa);
		yStatus = yearlyStatusService.save(yStatus);
        if (yStatus != null) {
			rs.setAgenId(agentDefa.getyStatus());
			rs.setData(yStatus);
			rs.setMessage("SAVED AGENT DEFAULTER");
			rs.setStatus("200");
		} else {
			rs.setAgenId(null);
			rs.setMessage("DATA NOT FOUND");
			rs.setData(yStatus);
			rs.setStatus("404");
		}
		return ResponseEntity.ok(rs);
	}

//  will upate multiple defaulter status
	@PostMapping("/reopen-multi-agent")
	public ResponseEntity<?> saveUpdateMultiAgentDefaY(@RequestBody List<DefaulterStatusModel> list,
			 HttpServletRequest req) {
		ResponseModel rs = new ResponseModel();
		AuthUser user = AuthUser.getLoggedInUser(req);
		for(DefaulterStatusModel model:list) {
        YearlyStatusModel yStatus = yearlyStatusService.getYearlyStatusById(model.getyStatus());
        model.setReOpenBy(user.getUserId());
        model.setyStatus(1l);
		yStatus.getDefaulerList().add(model);
		yStatus = yearlyStatusService.save(yStatus);
		}
			rs.setAgenId(null);
			rs.setData("");
			rs.setMessage("SAVED AGENT DEFAULTER ");
			rs.setStatus("200");
		return ResponseEntity.ok(rs);
	}

	@GetMapping("/get-list-work-status-by-y/(yId)")
	public ResponseEntity<?> getWorkStatusByYearlyId(@PathVariable(value = "yId") Long yId) {
		ResponseModel rs = new ResponseModel();
		List<AgentWorkStatusModel> agentWorkStausList = agentWorkStatusService.findByYearlyId(yId);
		if (agentWorkStausList != null && agentWorkStausList.size() > 0) {
			rs.setAgenId(null);
			rs.setData(agentWorkStausList);
			rs.setMessage("GET AGENT WORK STATUS YEARLY");
			rs.setStatus("200");
		} else {
			rs.setAgenId(null);
			rs.setMessage("DATA NOT FOUND");
			rs.setData(agentWorkStausList);
			rs.setStatus("400");
		}
		return ResponseEntity.ok(rs);
	}
	
	// get agent work Status dtl by prj id
	@GetMapping("/get-agent-work-status-by-prjid/{projectId}")
	public ResponseEntity<?> getWorkStatusByYearlyByPrjId(@PathVariable(value = "projectId") Long projectId) {
		ResponseModel rs = new ResponseModel();
		List<AgentWorkStatusModel> agentWorkStausList = agentWorkStatusService.findByProjectId(projectId);
		if (agentWorkStausList != null && agentWorkStausList.size() > 0) {
			rs.setAgenId(null);
			rs.setData(agentWorkStausList);
			rs.setMessage("GET AGENT WORK STATUS YEARLY");
			rs.setStatus("200");
		} else {
			rs.setAgenId(null);
			rs.setMessage("DATA NOT FOUND");
			rs.setData(agentWorkStausList);
			rs.setStatus("400");
		}
		return ResponseEntity.ok(rs);
	}
	
	// update promoter status Status by prj id
		@PutMapping("/update-prm-status/{workStatusId}/{status}")
		public ResponseEntity<?> updatePromoterStatus(@PathVariable(value = "workStatusId") Long workStatusId,
				@PathVariable(value = "status") String status) {
			ResponseModel rs = new ResponseModel();
			AgentWorkStatusModel model = agentWorkStatusService.findByWorkStatusId(workStatusId);
			model.setPromoterStatus(status);
			model = agentWorkStatusService.save(model);
			if (model != null) {
				rs.setAgenId(null);
				rs.setData(model);
				rs.setMessage("Promoter status upated successfully");
				rs.setStatus("200");
			} else {
				rs.setAgenId(null);
				rs.setMessage("DATA NOT FOUND");
				rs.setData("");
				rs.setStatus("404");
			}
			return ResponseEntity.ok(rs);
		}
	
	@PostMapping("/get-all-agent-list")
	public ResponseEntity<?> getAgentYearlyStatusList(@RequestBody YearlyStatusDto dto,
			 HttpServletRequest req) {
		ResponseModel rs = new ResponseModel();
		List<YearlyStatusDto> list = yearlyStatusService.getYearlyStatusById(dto);
     
        if (list != null) {
			rs.setAgenId(null);
			rs.setData(list);
			rs.setMessage("Data found");
			rs.setStatus("200");
		} else {
			rs.setAgenId(null);
			rs.setMessage("DATA NOT FOUND");
			rs.setData(list);
			rs.setStatus("404");
		}
		return ResponseEntity.ok(rs);
	}

}
