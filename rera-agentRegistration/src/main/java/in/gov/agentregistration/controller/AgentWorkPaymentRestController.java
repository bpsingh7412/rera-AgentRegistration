package in.gov.agentregistration.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import in.gov.agentregistration.constants.CommonConstants;
import in.gov.agentregistration.dao.AgentRegistrationDao;
import in.gov.agentregistration.dao.YearlyStatusDao;
import in.gov.agentregistration.exception.ResourceNotFoundException;
import in.gov.agentregistration.model.AgentPaymentDetailModel;
import in.gov.agentregistration.model.AgentRegistrationModel;
import in.gov.agentregistration.model.AgentWorkPaymentDetailsModel;
import in.gov.agentregistration.model.PaymentTransactionModel;
import in.gov.agentregistration.model.ResponseModel;
import in.gov.agentregistration.model.YearlyStatusModel;
import in.gov.agentregistration.services.AgentPaymentDetailService;
import in.gov.agentregistration.services.AgentRegistrationService;
import in.gov.agentregistration.services.AgentWorkPaymentDetailsService;
import in.gov.agentregistration.services.YearlyStatusService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/agent_reg/secure/work/payment")
public class AgentWorkPaymentRestController {
	private static final Logger logger = LogManager.getLogger(AgentWorkPaymentRestController.class);

	@Autowired
	private Environment env;

	@Autowired
	YearlyStatusService services;

	@Autowired
	YearlyStatusDao yearlyStatusDao;

	@Autowired
	AgentWorkPaymentDetailsService agentWorkPaymentDetailsService;

	/**************************************************************************
	 * getAllBCRPaymentDetails
	 **************************************************************************/

	@GetMapping("/all")
	public ResponseEntity<?> getAllPaymentDetails() {
		List<AgentWorkPaymentDetailsModel> list = new ArrayList<AgentWorkPaymentDetailsModel>();
		list = agentWorkPaymentDetailsService.getAllAgentYearPaymentDetail();
		if (list.size() == 0)
			throw new ResourceAccessException(env.getProperty("NOT_FOUND"));
		return ResponseEntity.ok(list);
	}

	/**************************************************************************
	 * saveAgentPaymentDetails
	 **************************************************************************/
	@PostMapping("/save-agent-work-payment-details")
	public ResponseEntity<?> saveAgentWorkPaymentDetails(@RequestBody AgentWorkPaymentDetailsModel entity)
			throws ResourceNotFoundException {
		logger.debug("saveAgentWorkPaymentDetails");
		String flag = "";
		Optional.ofNullable(entity).orElseThrow(() -> new ResourceAccessException(env.getProperty("DATA_INVALID")));

		AgentWorkPaymentDetailsModel pm = agentWorkPaymentDetailsService.saveAgentWorkPaymentDetail(entity);
		Optional<AgentWorkPaymentDetailsModel> op = Optional.ofNullable(pm);
		flag = op.isPresent() ? env.getProperty("SUCCESS") : env.getProperty("FAILED");
		ResponseModel rs = new ResponseModel();
		rs.setMessage(flag);
		if (flag.equals(env.getProperty("SUCCESS"))) {
			rs.setStatus("SUCCESS");
			rs.setId(pm.getPaymentId());
			rs.setAgenId(pm.getyStatusId());
		} else {
			rs.setStatus("FAILED");
		}
		return ResponseEntity.ok(rs);
	}

	/**************************************************************************
	 * saveAgentPaymentDetails
	 **************************************************************************/
	
	@PostMapping("/modify")
	public ResponseEntity<?> modifyAgentPaymentDetails(@RequestBody AgentWorkPaymentDetailsModel entity,
			HttpServletRequest req) throws ResourceNotFoundException {
		logger.debug("modifyAgentPaymentDetails");
		String flag = "";
		Optional.ofNullable(entity).orElseThrow(() -> new ResourceAccessException(env.getProperty("DATA_INVALID")));

		AgentWorkPaymentDetailsModel pm = agentWorkPaymentDetailsService.updatePaymentDetail(entity);
		Optional<AgentWorkPaymentDetailsModel> op = Optional.ofNullable(pm);
		flag = op.isPresent() ? env.getProperty("SUCCESS") : env.getProperty("FAILED");

		PaymentTransactionModel t = agentWorkPaymentDetailsService.getTransactiondetails(pm);
		ResponseModel rs = new ResponseModel();
		if (flag.equals(env.getProperty("SUCCESS"))) {
			rs.setStatus("200");
			YearlyStatusModel agentModel = yearlyStatusDao.findById(entity.getyStatusId()).get();
			agentModel.setStatus(entity.getStatus());
			yearlyStatusDao.save(agentModel);
			if ("SUCCESS".contentEquals(pm.getStatus())) {

				rs.setMessage("Your payment has been successfully paid and your acknowledge number is "
						+ t.getAknowledgementNo());

			} else if ("BOOKED".contentEquals(pm.getStatus()) || "PENDING".contentEquals(pm.getStatus())) {
				rs.setMessage("Your payment is under settlement.");

			} else {
				rs.setStatus("200");
				rs.setMessage("Your payment is failed. Please try again for payment.");
			}
			rs.setData(t);
		} else {
			rs.setStatus("some thing went wrong. Please contact to administrator.");
		}
		return ResponseEntity.ok(rs);
	}

	/**************************************************************************
	 * AgentPaymentDetailsById
	 **************************************************************************/
	@GetMapping("/{id}")
	public ResponseEntity<?> getAgentWorkYearlyPaymentDetailsById(@PathVariable(value = "id") Long id) {
		logger.debug("modifyAgentPaymentDetails");
		AgentWorkPaymentDetailsModel m = agentWorkPaymentDetailsService.getAgentWorkYearlyPaymentDetailById(id);
		Optional.ofNullable(m).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		return ResponseEntity.ok(m);
	}

	/**************************************************************************
	 * AgentPaymentDetails
	 * 
	 * @throws ResourceNotFoundException
	 **************************************************************************/

	@GetMapping("/details/agent/{id}")
	public ResponseEntity<?> agentPaymentDetails(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		logger.debug("agentPaymentDetails");
		AgentWorkPaymentDetailsModel m = agentWorkPaymentDetailsService.getPaymentFee(id,
				env.getProperty("YEARLY_WORK_FEE"));
		AgentWorkPaymentDetailsModel savedEntity = agentWorkPaymentDetailsService.saveAgentWorkPaymentDetail(m);
		PaymentTransactionModel t = agentWorkPaymentDetailsService.getTransactiondetails(savedEntity);
		ResponseModel rs = new ResponseModel();
		rs.setStatus("200");
		rs.setData(t);
		return ResponseEntity.ok(rs);
	}

}
