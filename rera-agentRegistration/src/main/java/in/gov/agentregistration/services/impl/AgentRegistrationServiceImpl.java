package in.gov.agentregistration.services.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import in.gov.agentregistration.constants.CommonConstants;
import in.gov.agentregistration.dao.AgentDetailDao;
import in.gov.agentregistration.dao.AgentRegistrationDao;
import in.gov.agentregistration.exception.ResourceNotFoundException;
import in.gov.agentregistration.model.AgentDetailModel;
import in.gov.agentregistration.model.AgentPaymentDetailModel;
import in.gov.agentregistration.model.AgentRegistrationModel;
import in.gov.agentregistration.services.AgentRegistrationService;

@Service
public class AgentRegistrationServiceImpl implements AgentRegistrationService {

	@Autowired
	AgentRegistrationDao dao;

	@Autowired
	AgentDetailDao detaildao;

	@Transactional
	@Override
	public AgentRegistrationModel saveAgentRegistrationDetails(AgentRegistrationModel entity) {
		return dao.save(entity);
	}

	@Override
	public List<AgentRegistrationModel> getAllAgentRegistration() {
		List<AgentRegistrationModel> l = new ArrayList<AgentRegistrationModel>();
		dao.findAll().iterator().forEachRemaining(l::add);
		return l;
	}

	@Override
	public boolean isAgentRegistrationExist(AgentRegistrationModel entity) {
		boolean flag = false;
		AgentRegistrationModel m = dao.findByEmailId(entity.getEmailId());
		if (null != m && CommonConstants.REGISTERED.equalsIgnoreCase(m.getStatus())) {
			flag = true;
		}
		return flag;
	}

	@Override
	public AgentRegistrationModel getAgentDetailsById(Long id) {
		return dao.findById(id).get();
	}

	/*
	 * @Override public AgentRegistrationModel getAgentDetailsByCinAndType(String
	 * cin,String type) { AgentRegistrationModel m =
	 * dao.getAgentDetailsByCinAndType(cin, type); return m; }
	 */

	@Override
	public AgentRegistrationModel getAgentDetailsById(String emailId) {
		return dao.findByEmailId(emailId);
	}

	@Override
	public AgentDetailModel getAgentDetailsByPanNo(String panNo) {

		return detaildao.findByPanNo(panNo);

	}

	@Override
	public AgentRegistrationModel updateAgentRegistration(AgentRegistrationModel Model) {
		return dao.save(Model);
	}

	@Override
	public AgentRegistrationModel getAgentDetailsByEmailId(String emailId) {
		return dao.findByEmailId(emailId);
	}

	@Override
	public AgentRegistrationModel getAgentDetailsByCinAndType(String cin, String type) {
		// AgentRegistrationModel m = dao.getAgentDetailsByCinAndType(cin, type); return
		// m;
		return null;
	}

	@Override
	public List<AgentRegistrationModel> findByStatus(String pendingWithAuthority) {
		// TODO Auto-generated method stub
		return dao.findByStatus(pendingWithAuthority);
	}

	@Override
	public List<AgentRegistrationModel> findByCompanyRegistrationNumber(String no) {
		// TODO Auto-generated method stub
		return dao.findByCompanyRegistrationNumber(no);
	}

	@Override
	public List<AgentRegistrationModel> findByAgentAckNo(String no) {
		// TODO Auto-generated method stub
		return dao.findByAgentAckNo(no);
	}

	@Override
	public List<AgentRegistrationModel> findByEmailIdAndStatus(String emailId, String agentEnquiry) {
		// TODO Auto-generated method stub
		return dao.findByEmailIdAndStatus(emailId,agentEnquiry);
	}

}
