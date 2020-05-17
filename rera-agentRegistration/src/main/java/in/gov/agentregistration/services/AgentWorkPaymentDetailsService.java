package in.gov.agentregistration.services;

import java.util.List;

import in.gov.agentregistration.model.AgentWorkPaymentDetailsModel;
import in.gov.agentregistration.model.PaymentTransactionModel;

public interface AgentWorkPaymentDetailsService {
	List<AgentWorkPaymentDetailsModel> getAllAgentYearPaymentDetail();

	AgentWorkPaymentDetailsModel getAgentWorkYearlyPaymentDetailById(Long id);

	AgentWorkPaymentDetailsModel saveAgentWorkPaymentDetail(AgentWorkPaymentDetailsModel entity);

	AgentWorkPaymentDetailsModel updatePaymentDetail(AgentWorkPaymentDetailsModel entity);

	AgentWorkPaymentDetailsModel getPaymentFee(Long id, String property);

	PaymentTransactionModel getTransactiondetails(AgentWorkPaymentDetailsModel savedEntity);

}
