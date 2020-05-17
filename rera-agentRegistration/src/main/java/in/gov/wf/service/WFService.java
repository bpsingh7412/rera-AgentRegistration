package in.gov.wf.service;

import in.gov.wf.model.WFEntity;

public interface WFService {

	void updateWfEntity(WFEntity entity);

	WFEntity getWfEntityById(String wfoid);

	String getTeamNameForAssign();
}
