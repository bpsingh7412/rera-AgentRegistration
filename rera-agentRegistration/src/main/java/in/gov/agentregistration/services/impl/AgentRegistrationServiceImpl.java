package in.gov.agentregistration.services.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.gov.agentregistration.constants.CommonConstants;
import in.gov.agentregistration.dao.AgentDetailDao;
import in.gov.agentregistration.dao.AgentRegistrationDao;
import in.gov.agentregistration.model.AgentDetailModel;
import in.gov.agentregistration.model.AgentRegistrationModel;
import in.gov.agentregistration.model.ResponseModel;
import in.gov.agentregistration.model.YearlyStatusDto;
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
	
	@Override
	public  Map<String, String> getFyYearList(Calendar incdt,String[] cut){
		Map<String, String> map=new HashMap<String, String>();
		Calendar curr=Calendar.getInstance();
		curr.set(Calendar.MONTH,Calendar.OCTOBER);
		Calendar pdt=Calendar.getInstance();
		if(incdt!=null)
		pdt.setTime(incdt.getTime());
		else{
		pdt.set(Calendar.YEAR,2001);
		}
		int date=Integer.parseInt(cut[0]);
		int month=Integer.parseInt(cut[1])-1;
		pdt.add(Calendar.YEAR, 1);
		if(curr.get(Calendar.MONTH)<month ||
		(curr.get(Calendar.MONTH)==month && curr.get(Calendar.DATE)<date)){
		curr.add(Calendar.YEAR,-1);
		}
		if(pdt.get(Calendar.MONTH)>2){
		pdt.add(Calendar.YEAR, 1);
		}
		if(pdt.get(Calendar.YEAR)<=curr.get(Calendar.YEAR)){
		map.put("FY3",getParseFY(curr.get(Calendar.YEAR)));
		}else{
		map.put("FY3","NA");  
		}
		curr.add(Calendar.YEAR,-1);
		if(pdt.get(Calendar.YEAR)<=curr.get(Calendar.YEAR)){
		map.put("FY2",getParseFY(curr.get(Calendar.YEAR)));
		}else{
		map.put("FY2","NA");  
		}
		curr.add(Calendar.YEAR,-1);
		if(pdt.get(Calendar.YEAR)<=curr.get(Calendar.YEAR)){
		map.put("FY1",getParseFY(curr.get(Calendar.YEAR)));
		}else{
		map.put("FY1","NA");  
		}
		return map;
		}
	
	private static String getParseFY(int year)
    {
      StringBuffer sb=new StringBuffer();
      int lst=year%100;
      year=year-1;
      sb.append(year).append("-").append(lst);
      return sb.toString();
}

	@Override
	public ResponseModel findAgentListbyFilter(YearlyStatusDto dto) {
		ResponseModel resp=null;
		List<YearlyStatusDto> flterData = new ArrayList<>();
		
	    if(null==dto.getAgentRegNo()||"".equals(dto.getAgentRegNo()) )	
	    {
	    	dto.setAgentRegNo("%");
	    }
	    if(null==dto.getAgentName()||"".equals(dto.getAgentName()) )	
	    {
	    	dto.setAgentName("%");
	    }
	    if(null==dto.getRegType()||"".equals(dto.getRegType()) )	
	    {
	    	dto.setRegType("%");
	    }
	    if(null==dto.getRegType()||"".equals(dto.getRegType()) )	
	    {
	    	dto.setRegType("%");
	    }
		Page<AgentRegistrationModel> page= 	null;
		PageRequest pag=PageRequest.of(dto.getStartWith(),dto.getDataSize());
		
		return null;
	
	}
		
}
