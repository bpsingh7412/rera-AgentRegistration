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
import in.gov.agentregistration.exception.ResourceNotFoundException;
import in.gov.agentregistration.model.AgentPaymentDetailModel;
import in.gov.agentregistration.model.AgentRegistrationModel;
import in.gov.agentregistration.model.PaymentTransactionModel;
import in.gov.agentregistration.model.ResponseModel;
import in.gov.agentregistration.services.AgentPaymentDetailService;
import in.gov.agentregistration.services.AgentRegistrationService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/agent_reg/payment")
public class AgentPaymentRestController {
	private static final Logger logger = LogManager.getLogger(AgentPaymentRestController.class);

	@Autowired
	private Environment env;

	@Autowired
	AgentRegistrationService services;

	@Autowired
	AgentRegistrationDao agentRegistrationDao;

	@Autowired
	AgentPaymentDetailService agentPaymentDetailService;

	/**************************************************************************
	 * getAllBCRPaymentDetails
	 **************************************************************************/

	@GetMapping("/all")
	public ResponseEntity<?> getAllBcrPaymentDetails() {
		List<AgentPaymentDetailModel> list = new ArrayList<AgentPaymentDetailModel>();
		list = agentPaymentDetailService.getAllAgentPaymentDetail();
		if (list.size() == 0)
			throw new ResourceAccessException(env.getProperty("NOT_FOUND"));
		return ResponseEntity.ok(list);
	}

	/**************************************************************************
	 * saveAgentPaymentDetails
	 **************************************************************************/
	@PostMapping("/save-agent-payment-details")
	public ResponseEntity<?> saveAgentPaymentDetails(@RequestBody AgentPaymentDetailModel entity)
			throws ResourceNotFoundException {
		logger.debug("saveAgentPaymentDetails");
		String flag = "";
		Optional.ofNullable(entity).orElseThrow(() -> new ResourceAccessException(env.getProperty("DATA_INVALID")));

		AgentPaymentDetailModel pm = agentPaymentDetailService.saveAgentPaymentDetail(entity);
		Optional<AgentPaymentDetailModel> op = Optional.ofNullable(pm);
		flag = op.isPresent() ? env.getProperty("SUCCESS") : env.getProperty("FAILED");
		ResponseModel rs = new ResponseModel();
		rs.setMessage(flag);
		if (flag.equals(env.getProperty("SUCCESS"))) {
			rs.setStatus("SUCCESS");
			rs.setId(pm.getPaymentId());
			rs.setAgenId(pm.getAgentId());
		} else {
			rs.setStatus("FAILED");
		}
		return ResponseEntity.ok(rs);
	}

	/**************************************************************************
	 * saveAgentPaymentDetails
	 **************************************************************************/
	@PostMapping("/modify")
	public ResponseEntity<?> modifyAgentPaymentDetails(@RequestBody AgentPaymentDetailModel entity,
			HttpServletRequest req) throws ResourceNotFoundException {
		logger.debug("modifyAgentPaymentDetails");
		String flag = "";
		Optional.ofNullable(entity).orElseThrow(() -> new ResourceAccessException(env.getProperty("DATA_INVALID")));

		AgentPaymentDetailModel pm = agentPaymentDetailService.updatePaymentDetail(entity);
		Optional<AgentPaymentDetailModel> op = Optional.ofNullable(pm);
		flag = op.isPresent() ? env.getProperty("SUCCESS") : env.getProperty("FAILED");

		PaymentTransactionModel t = agentPaymentDetailService.getTransactiondetails(pm);
		ResponseModel rs = new ResponseModel();
		if (flag.equals(env.getProperty("SUCCESS"))) {
			rs.setStatus("200");
			AgentRegistrationModel agentModel = agentRegistrationDao.findById(entity.getAgentId()).get();
			agentModel.setStatus(CommonConstants.PENDING_WITH_AUTHORITY);
			agentRegistrationDao.save(agentModel);
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
	public ResponseEntity<?> getAgentPaymentDetailsById(@PathVariable(value = "id") Long id) {
		logger.debug("modifyAgentPaymentDetails");
		AgentPaymentDetailModel m = agentPaymentDetailService.getAgentPaymentDetailById(id);
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
		AgentPaymentDetailModel m = agentPaymentDetailService.getPaymentFee(id, env.getProperty("INDVISUAL_FEE"),
				env.getProperty("FIRM_COMP_FEE"));
		AgentPaymentDetailModel savedEntity = agentPaymentDetailService.saveAgentPaymentDetail(m);
		PaymentTransactionModel t = agentPaymentDetailService.getTransactiondetails(savedEntity);
		ResponseModel rs = new ResponseModel();
		rs.setStatus("200");
		rs.setData(t);
		return ResponseEntity.ok(rs);
	}

}
