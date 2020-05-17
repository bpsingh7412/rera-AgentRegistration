package in.gov.agentregistration.services;

import in.gov.agentregistration.model.DmsModel;
import in.gov.agentregistration.wfo.model.AgentWfoDocModel;

public interface DmsService {
	public DmsModel commitDoc(DmsModel model);
    public void commitAgentWfodocs(AgentWfoDocModel agentDocs);
 
}
