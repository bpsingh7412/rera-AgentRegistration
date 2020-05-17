package in.gov.agentregistration.services.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import in.gov.agentregistration.configuration.AgentAckNumberGeneration;
import in.gov.agentregistration.constants.CommonConstants;
import in.gov.agentregistration.dao.AgentWorkPaymentDetailsDao;
import in.gov.agentregistration.dao.YearlyStatusDao;
import in.gov.agentregistration.model.AgentPaymentDetailModel;
import in.gov.agentregistration.model.AgentRegistrationModel;
import in.gov.agentregistration.model.AgentWorkPaymentDetailsModel;
import in.gov.agentregistration.model.DefaulterStatusModel;
import in.gov.agentregistration.model.PaymentTransactionModel;
import in.gov.agentregistration.model.YearlyStatusModel;
import in.gov.agentregistration.services.AgentRegistrationService;
import in.gov.agentregistration.services.AgentWorkPaymentDetailsService;
import in.gov.agentregistration.services.YearlyStatusService;

@Service
@Transactional
public class AgentWorkPaymentDetailsServiceImpl implements AgentWorkPaymentDetailsService {

	@Autowired
	AgentWorkPaymentDetailsDao agentWorkPaymentDetailsDao;

	@Autowired
	YearlyStatusDao yearlyStatusDao;

	@Autowired
	YearlyStatusService yearlyStatusService;

	@Autowired
	PaymentTransactionModel paymentTransactionModel;

	@Autowired
	AgentRegistrationService agentRegistrationService;

	@Override
	public List<AgentWorkPaymentDetailsModel> getAllAgentYearPaymentDetail() {
		// TODO Auto-generated method stub
		List<AgentWorkPaymentDetailsModel> l = new ArrayList<AgentWorkPaymentDetailsModel>();
		agentWorkPaymentDetailsDao.findAll().iterator().forEachRemaining(l::add);
		return l;
	}

	@Override
	public AgentWorkPaymentDetailsModel getAgentWorkYearlyPaymentDetailById(Long id) {
		// TODO Auto-generated method stub
		return agentWorkPaymentDetailsDao.findById(id).get();
	}

	@Override
	public AgentWorkPaymentDetailsModel saveAgentWorkPaymentDetail(AgentWorkPaymentDetailsModel entity) {
		// TODO Auto-generated method stub
		AgentWorkPaymentDetailsModel m = entity;
		System.out.println(Optional.ofNullable(entity.getPaymentId()).isPresent());
		if (yearlyStatusDao.findById(entity.getyStatusId()).isPresent()) {
			YearlyStatusModel model = yearlyStatusDao.findById(entity.getyStatusId()).get();
			if (Optional.ofNullable(model.getPayment()).isPresent()) {
				m = model.getPayment();
			} else {
				model.setPayment(m);
				m = yearlyStatusDao.save(model).getPayment();
			}

		} else {
			throw new ResourceAccessException("Project not exist");
		}
		return m;
	}

	@Override
	public AgentWorkPaymentDetailsModel updatePaymentDetail(AgentWorkPaymentDetailsModel entity) {
		// TODO Auto-generated method stub

		AgentWorkPaymentDetailsModel m = entity;
		YearlyStatusModel agentModel = yearlyStatusService.getYearlyStatusById(entity.getyStatusId());
		if (yearlyStatusDao.findById(entity.getyStatusId()).isPresent()) {
			AgentWorkPaymentDetailsModel oldPaymentModel = agentModel.getPayment();
			if ("SUCCESS".contentEquals(entity.getStatus())) {
				oldPaymentModel.setSbiReferanceNo(entity.getSbiReferanceNo());
				oldPaymentModel.setTokenNo(entity.getTokenNo());
				oldPaymentModel.setStatus(entity.getStatus());
				oldPaymentModel.setTransactionNo(entity.getTransactionNo());
				String ackno = AgentAckNumberGeneration.getAcknowledgeWork();
				agentModel.setWorkStatusAckNo(ackno);
				agentModel.setStatus("PENDING");
				agentModel = yearlyStatusDao.save(agentModel);
				// engine.createNewTransaction(updatedProject,"SUBMIT", request);
			} else if ("BOOKED".contentEquals(entity.getStatus()) || "PENDING".equals(entity.getStatus())) {
				oldPaymentModel.setStatus(entity.getStatus());
			} else {
				agentModel.setPayment(m);
			}
		} else {
			throw new ResourceAccessException("Project is not found");
		}
		return agentModel.getPayment();

	}

	@Override
	public AgentWorkPaymentDetailsModel getPaymentFee(Long id, String amt) {
		// TODO Auto-generated method stub

		YearlyStatusModel yStatus = yearlyStatusDao.findById(id).get();
		String token = AgentAckNumberGeneration.generateAgentWorkToken(yStatus);
		System.out.println(token);
		AgentWorkPaymentDetailsModel m = new AgentWorkPaymentDetailsModel();
		m.setyStatusId(id);
		m.setAmount(Double.valueOf(amt));
		if (yStatus.getDefaulerList() != null && yStatus.getDefaulerList().size() > 0) {
			Double damt = 0d;
			for (DefaulterStatusModel d : yStatus.getDefaulerList()) {
				damt = damt + d.getReOpenPenalityAmt();
			}
			m.setPenaltyAmount(damt);
		} else {
			m.setPenaltyAmount(0.0d);
		}
		m.setTotalProjectAmount(m.getAmount() + m.getPenaltyAmount());
		m.setTokenNo(token);
		m.setStatus(CommonConstants.ePAY_SENT);
		return m;

	}

	@Override
	public PaymentTransactionModel getTransactiondetails(AgentWorkPaymentDetailsModel tmodel) {
		// TODO Auto-generated method stub

		YearlyStatusModel pm = yearlyStatusDao.findById(tmodel.getyStatusId()).get();
		AgentRegistrationModel agent = agentRegistrationService.getAgentDetailsById(pm.getAgentId());

		try {
			paymentTransactionModel.setPaymentId(pm.getPayment().getPaymentId());
			paymentTransactionModel.setEmailId(agent.getEmailId());
			paymentTransactionModel.setMobileNo(agent.getAgentDetail() != null ? agent.getAgentDetail().getMobileNo() : "");
			paymentTransactionModel.setPaymentRemarks("");
			paymentTransactionModel.setPenaltyAmount(pm.getPayment().getPenaltyAmount());
			paymentTransactionModel.setProcessName(CommonConstants.AGENT_WORK_STATUS);
			paymentTransactionModel.setRedirectUrl("");
			paymentTransactionModel.setRegistraionType(agent.getAgentType());
			paymentTransactionModel.setSbiReferanceNo("");
			paymentTransactionModel.setStatus(pm.getPayment().getStatus());
			paymentTransactionModel.setTokenNo(pm.getPayment().getTokenNo());
			paymentTransactionModel.setAmount(pm.getPayment().getAmount());
			paymentTransactionModel.setTransactionNo(pm.getPayment().getTransactionNo());
			paymentTransactionModel.setFirstName(agent.getAgentFirstName());
			paymentTransactionModel.setMiddelName(agent.getAgentMName());
			paymentTransactionModel.setLastName(agent.getAgentLName());
			paymentTransactionModel.setAknowledgementNo(pm.getWorkStatusAckNo());
			paymentTransactionModel.setPaymentStatus(pm.getPayment().getStatus());
			paymentTransactionModel.setSubmissiOn(pm.getLastModifiedOn());
			paymentTransactionModel.setTotalAmount(pm.getPayment().getTotalProjectAmount());
			
		} catch (Exception e) {
			throw new ResourceAccessException("Error : paymentTransactionModel" + e.getMessage());
		}
		return paymentTransactionModel;
	}

}
