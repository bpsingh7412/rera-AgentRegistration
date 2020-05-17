package in.gov.agentregistration.services;

import java.util.List;

import in.gov.agentregistration.exception.ResourceNotFoundException;
import in.gov.agentregistration.model.AgentPaymentDetailModel;
import in.gov.agentregistration.model.PaymentTransactionModel;

public interface AgentPaymentDetailService {

	public AgentPaymentDetailModel saveAgentPaymentDetail(AgentPaymentDetailModel entity);

	public AgentPaymentDetailModel getAgentPaymentDetailById(Long id);

	public List<AgentPaymentDetailModel> getAllAgentPaymentDetail();

	public AgentPaymentDetailModel getPaymentFee(Long id, String indFee, String comfee);

	public PaymentTransactionModel getTransactiondetails(AgentPaymentDetailModel tmodel)
			throws ResourceNotFoundException;

	public AgentPaymentDetailModel updatePaymentDetail(AgentPaymentDetailModel entity);
}
