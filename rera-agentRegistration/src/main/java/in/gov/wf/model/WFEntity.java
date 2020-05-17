package in.gov.wf.model;

import java.util.Calendar;

public interface WFEntity {

	String getStateCode();

	String getStateName();

	String getProcessName();

	void setProcessName(String processName);

	String getWfoId();

	Long getId();

	void setWfoId(String wfoId);

	void setStateCode(String stateCode);

	void setStateName(String stateName);

	String getFinState();

	void setFinState(String finSate);

	String getLegalState();

	String getTpTeam();

	String getTpSrTeam();

	String getFinTeam();

	String getLegalTeam();

	void setTpTeam(String team);

	void setTpSrTeam(String team);

	void setFinTeam(String team);

	void setLegalTeam(String team);

	void setLegalState(String legalState);

	String getTpState();

	void setTpState(String tpState);

	Calendar getCreatedOn();

	Calendar getLastUpdatedOn();

	void setLastUpdatedOn(Calendar cal);

	void setLastUpdatedBy(Long id);

	void setLastUpdatedByName(String name);

	void setCreatedOn(Calendar cal);

	void setCreatedBy(Long id);

	void setCreatedByName(String name);

}
