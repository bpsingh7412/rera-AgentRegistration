package in.gov.agentregistration.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.gov.agentregistration.dao.AgentRegistrationDao;
import in.gov.agentregistration.dao.YearlyStatusDao;
import in.gov.agentregistration.model.AgentRegistrationModel;
import in.gov.agentregistration.model.YearlyStatusDto;
import in.gov.agentregistration.model.YearlyStatusModel;
import in.gov.agentregistration.services.YearlyStatusService;

@Service
@Transactional
public class YearlyStatusServiceImpl implements YearlyStatusService {

	@Autowired
	YearlyStatusDao yearlyStatusDao;
	
	@Autowired
	AgentRegistrationDao agDao;

	@Override
	public YearlyStatusModel save(YearlyStatusModel yStatus) {
		// TODO Auto-generated method stub
		return yearlyStatusDao.save(yStatus);
	}

	@Override
	public List<YearlyStatusModel> findByAgentId(Long agentId) {
		// TODO Auto-generated method stub
		return yearlyStatusDao.findByAgentId(agentId);
	}

	@Override
	public YearlyStatusModel getYearlyStatusById(Long yId) {
		// TODO Auto-generated method stub
		return yearlyStatusDao.findById(yId).get();
	}

	@Override
	public List<YearlyStatusDto> getYearlyStatusById(YearlyStatusDto dto) {
		List<YearlyStatusDto> dtoList = new ArrayList<>();
		List<YearlyStatusModel> list = this.yearlyStatusDao.getAllAgentFilter(dto.getYearlyStatus());
		for(YearlyStatusModel model:list)
		{
			YearlyStatusDto dt = new  YearlyStatusDto();
			AgentRegistrationModel reg = new AgentRegistrationModel();
			if(null!=model.getAgentId())
			reg = agDao.findById(model.getAgentId()).get();
			dt.setAgentId(model.getAgentId());
			dt.setAgentName(reg.getAgentName());
			dt.setAgentRegNo(reg.getCompanyRegistrationNumber());
			dt.setRegType(reg.getAgentType());
			dt.setStartDate(model.getStartDate());
			dt.setEndDate(model.getEndDate());
			dt.setYearlyStatus(model.getStatus());
			dt.setYearName(model.getDisplayName());
			if(model.getStatus().equals("SUCCESS"))
			{
				dt.setSubmissionDate(model.getPayment().getCreatedOn());
			}
			dt.setDefaulterId(model.getDefaulterFlag());
			dtoList.add(dt);
		}
		return dtoList;
	}

}
