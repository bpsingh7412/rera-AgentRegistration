package in.gov.agentregistration.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.gov.agentregistration.dao.YearlyStatusDao;
import in.gov.agentregistration.model.YearlyStatusModel;
import in.gov.agentregistration.services.YearlyStatusService;

@Service
@Transactional
public class YearlyStatusServiceImpl implements YearlyStatusService {

	@Autowired
	YearlyStatusDao yearlyStatusDao;

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

}
