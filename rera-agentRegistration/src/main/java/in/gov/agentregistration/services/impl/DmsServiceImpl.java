package in.gov.agentregistration.services.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.ResourceAccessException;

import in.gov.agentregistration.model.DmsModel;
import in.gov.agentregistration.services.DmsService;
import in.gov.agentregistration.wfo.model.AgentWfoDocModel;
import in.gov.agentregistration.services.AgentRestTemplateServices;

@Service
public class DmsServiceImpl implements DmsService {
	private static final Logger logger = LogManager.getLogger(DmsServiceImpl.class);

	@Value("${URL_CREATE_VDMS}")
	String vdmsURL;

	@Override
	public DmsModel commitDoc(DmsModel model) {
		return AgentRestTemplateServices.saveDoc(model, vdmsURL);
	}

	@Override
	public void commitAgentWfodocs(AgentWfoDocModel agentDocs) {
		// TODO Auto-generated method stub
	
		DmsModel dmsModel = new DmsModel();

		if (!ObjectUtils.isEmpty(agentDocs)) {
			
           if (ObjectUtils.isEmpty(agentDocs.getItrReturnDocYear1UId()) && agentDocs.getItrReturnDocYear1Id() != null) {
				dmsModel.setFolderId(agentDocs.getAgentId().toString());
				dmsModel.setDocumentId(Long.parseLong(agentDocs.getItrReturnDocYear1Id().toString()));
				dmsModel.setDocumentType("AGENT-WFO-DOCS");
				DmsModel comitPandoc = AgentRestTemplateServices.saveDoc(dmsModel, vdmsURL);
				Optional.ofNullable(comitPandoc)
						.orElseThrow(() -> new ResourceAccessException("There are some issue in document commit."));
				agentDocs.setItrReturnDocYear1UId(comitPandoc.getUid());
				agentDocs.setItrReturnDocYear1Id(comitPandoc.getDocumentId());
		   }
        
           if (ObjectUtils.isEmpty(agentDocs.getItrReturnDocYear2UId()) && agentDocs.getItrReturnDocYear2Id() != null) {
				dmsModel.setFolderId(agentDocs.getAgentId().toString());
				dmsModel.setDocumentId(Long.parseLong(agentDocs.getItrReturnDocYear2Id().toString()));
				dmsModel.setDocumentType("AGENT-WFO-DOCS");
				DmsModel comitPandoc = AgentRestTemplateServices.saveDoc(dmsModel, vdmsURL);
				Optional.ofNullable(comitPandoc)
						.orElseThrow(() -> new ResourceAccessException("There are some issue in document commit."));
				agentDocs.setItrReturnDocYear2UId(comitPandoc.getUid());
				agentDocs.setItrReturnDocYear2Id(comitPandoc.getDocumentId());
		   }
        
           if (ObjectUtils.isEmpty(agentDocs.getItrReturnDocYear3UId()) && agentDocs.getItrReturnDocYear3Id() != null) {
				dmsModel.setFolderId(agentDocs.getAgentId().toString());
				dmsModel.setDocumentId(Long.parseLong(agentDocs.getItrReturnDocYear3Id().toString()));
				dmsModel.setDocumentType("AGENT-WFO-DOCS");
				DmsModel comitPandoc = AgentRestTemplateServices.saveDoc(dmsModel, vdmsURL);
				Optional.ofNullable(comitPandoc)
						.orElseThrow(() -> new ResourceAccessException("There are some issue in document commit."));
				agentDocs.setItrReturnDocYear3UId(comitPandoc.getUid());
				agentDocs.setItrReturnDocYear3Id(comitPandoc.getDocumentId());
		   }
           
		}
		
     }
}
