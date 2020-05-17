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
import in.gov.agentregistration.dao.AgentPaymentDetailDao;
import in.gov.agentregistration.dao.AgentRegistrationDao;
import in.gov.agentregistration.exception.ResourceNotFoundException;
import in.gov.agentregistration.model.AgentPaymentDetailModel;
import in.gov.agentregistration.model.AgentRegistrationModel;
import in.gov.agentregistration.model.PaymentTransactionModel;
import in.gov.agentregistration.services.AgentPaymentDetailService;

@Transactional
@Service
public class AgentPaymentDetailServiceImpl implements AgentPaymentDetailService {

	@Autowired
	AgentPaymentDetailDao agentPaymentDetailDao;

	@Autowired
	AgentRegistrationDao agentRegistrationDao;

	@Autowired
	PaymentTransactionModel paymentTransactionModel;

	@Override
	public AgentPaymentDetailModel saveAgentPaymentDetail(AgentPaymentDetailModel entity) {
		// TODO Auto-generated method stub
		AgentPaymentDetailModel m = entity;
		System.out.println(Optional.ofNullable(entity.getPaymentId()).isPresent());
		if (agentRegistrationDao.findById(entity.getAgentId()).isPresent()) {
			AgentRegistrationModel model = agentRegistrationDao.findById(entity.getAgentId()).get();
			if (Optional.ofNullable(model.getPaymentDetailsModel()).isPresent()) {
				m = model.getPaymentDetailsModel();
			} else {
				model.setPaymentDetailsModel(m);
				m = agentRegistrationDao.save(model).getPaymentDetailsModel();
			}

		} else {
			throw new ResourceAccessException("Project not exist");
		}
		return m;
	}

	@Override
	public AgentPaymentDetailModel getAgentPaymentDetailById(Long id) {
		// TODO Auto-generated method stub
		return agentPaymentDetailDao.findById(id).get();
	}

	@Override
	public List<AgentPaymentDetailModel> getAllAgentPaymentDetail() {
		// TODO Auto-generated method stub
		List<AgentPaymentDetailModel> l = new ArrayList<AgentPaymentDetailModel>();
		agentPaymentDetailDao.findAll().iterator().forEachRemaining(l::add);
		return l;
	}

	@Override
	public AgentPaymentDetailModel getPaymentFee(Long id, String indFee, String comFee) {
		// TODO Auto-generated method stub

		AgentRegistrationModel agent = agentRegistrationDao.findById(id).get();
		String token = AgentAckNumberGeneration.generateTokenAgentRegRenewal(agent);
		AgentPaymentDetailModel m = new AgentPaymentDetailModel();
		m.setAgentId(id);
		if (agent.getAgentType().equals(CommonConstants.INDVISUAL_REG)) {
			m.setAmount(Double.parseDouble(indFee));
			m.setTotalProjectAmount(Double.parseDouble(indFee));
		} else {
			m.setAmount(Double.parseDouble(comFee));
			m.setTotalProjectAmount(Double.parseDouble(comFee));
		}
		m.setPenaltyAmount(0.0d);
		m.setTotalProjectAmount(4d);
		m.setTokenNo(token);
		m.setStatus(CommonConstants.ePAY_SENT);
		return m;

	}

	@Override
	public PaymentTransactionModel getTransactiondetails(AgentPaymentDetailModel tmodel)
			throws ResourceNotFoundException {
		// TODO Auto-generated method stub

		AgentRegistrationModel pm = agentRegistrationDao.findById(tmodel.getAgentId()).get();

		try {
			paymentTransactionModel.setPaymentId(pm.getPaymentDetailsModel().getPaymentId());
			paymentTransactionModel.setEmailId(pm.getEmailId());
			paymentTransactionModel.setMobileNo(pm.getAgentDetail().getMobileNo());
			paymentTransactionModel.setPaymentRemarks("");
			paymentTransactionModel.setPenaltyAmount(pm.getPaymentDetailsModel().getPenaltyAmount());
			paymentTransactionModel.setProcessName(CommonConstants.AGENT_REG);
			paymentTransactionModel.setRedirectUrl("");
			paymentTransactionModel.setRegistraionType(pm.getAgentType());
			paymentTransactionModel.setSbiReferanceNo("");
			paymentTransactionModel.setStatus(pm.getPaymentDetailsModel().getStatus());
			paymentTransactionModel.setTokenNo(pm.getPaymentDetailsModel().getTokenNo());
			paymentTransactionModel.setAmount(pm.getPaymentDetailsModel().getAmount());
			paymentTransactionModel.setTransactionNo(pm.getPaymentDetailsModel().getTransactionNo());
			paymentTransactionModel.setFirstName(pm.getAgentFirstName());
			paymentTransactionModel.setMiddelName(pm.getAgentMName());
			paymentTransactionModel.setLastName(pm.getAgentLName());
			paymentTransactionModel.setAknowledgementNo(pm.getAgentAckNo());
			paymentTransactionModel.setPaymentStatus(pm.getPaymentDetailsModel().getStatus());
			paymentTransactionModel.setSubmissiOn(pm.getApprovedOn());
		} catch (Exception e) {
			throw new ResourceAccessException("Error : paymentTransactionModel" + e.getMessage());
		}
		return paymentTransactionModel;
	}

	@Override
	public AgentPaymentDetailModel updatePaymentDetail(AgentPaymentDetailModel entity) {
		AgentPaymentDetailModel m = entity;
		AgentRegistrationModel agentModel = agentRegistrationDao.findById(entity.getAgentId()).get();
		if (agentRegistrationDao.findById(entity.getAgentId()).isPresent()) {
			AgentPaymentDetailModel oldPaymentModel = agentModel.getPaymentDetailsModel();
			if ("SUCCESS".contentEquals(entity.getStatus())) {
				oldPaymentModel.setSbiReferanceNo(entity.getSbiReferanceNo());
				oldPaymentModel.setTokenNo(entity.getTokenNo());
				oldPaymentModel.setStatus(entity.getStatus());
				oldPaymentModel.setTransactionNo(entity.getTransactionNo());
				String ackno = AgentAckNumberGeneration.getApplicationNo(agentModel);
				agentModel.setAgentAckNo(ackno);
				agentModel.setSubmissionDate(Calendar.getInstance());
				agentModel.setStatus("PENDING");
				agentModel = agentRegistrationDao.save(agentModel);
				// engine.createNewTransaction(updatedProject,"SUBMIT", request);
			} else if ("BOOKED".contentEquals(entity.getStatus()) || "PENDING".equals(entity.getStatus())) {
				oldPaymentModel.setStatus(entity.getStatus());
			} else {
				agentModel.setPaymentDetailsModel(m);
			}
		} else {
			throw new ResourceAccessException("Project is not found");
		}
		return agentModel.getPaymentDetailsModel();
	}

}
