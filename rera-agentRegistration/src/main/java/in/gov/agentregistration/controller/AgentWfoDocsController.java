package in.gov.agentregistration.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.validator.internal.util.TypeResolutionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import in.gov.agentregistration.exception.ResourceNotFoundException;
import in.gov.agentregistration.model.ResponseModel;
import in.gov.agentregistration.security.AuthUser;
import in.gov.agentregistration.services.AgentWfoDocService;
import in.gov.agentregistration.services.DmsService;
import in.gov.agentregistration.wfo.model.AgentWfoDocModel;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/agent_reg/secure/agent/docs/wfo")
public class AgentWfoDocsController {

	private static final Logger logger = LogManager.getLogger(AgentAuthController.class);
	
	@Autowired
	AgentWfoDocService agentWfoDocService; 
	
	@Autowired
	private Environment env;
	
	@Autowired
    DmsService dms;
		
	
	@PostMapping("/save-agent-doc-wfo-det")
	public ResponseEntity<?> saveAgentDocsWforDetails(@Valid @RequestBody AgentWfoDocModel entity,HttpServletRequest req) throws ResourceNotFoundException
			 {
		    logger.info("AgentWfoDocController , saveAgentDocsWforDetails() <START>");
		    Optional.ofNullable(entity).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
			ResponseModel rs = new ResponseModel();    
			AuthUser user = AuthUser.getLoggedInUser(req);
			entity.setAgentId(user.getUserId());
		    dms.commitAgentWfodocs(entity);
		    AgentWfoDocModel agentDocs = agentWfoDocService.saveAgentEntity(entity);
			   
		   if (agentDocs != null) {
				rs.setAgenId(null);
				rs.setData(agentDocs);
				rs.setMessage("AGENT-DOCS-FOUND");
				rs.setStatus("200");
			} else {
				rs.setAgenId(null);
				rs.setMessage("AGENT-DOCS-NOT-FOUND");
				rs.setData(agentDocs);
				rs.setStatus("400");
			}
		   logger.info("AgentWfoDocController , saveAgentDocsWforDetails() <END>");
			return ResponseEntity.ok(rs);
	 }

	
	@GetMapping("/get-agent-doc-wfo-det/{agentId}")
	public ResponseEntity<?> getAgentDocsWforDetails(@Valid @PathVariable(name="agentId") Long agentId
			,HttpServletRequest req) throws ResourceNotFoundException
			 {
		    logger.info("AgentWfoDocController , getAgentDocsWforDetails() <START>");
		    Optional.ofNullable(agentId).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
			
		    ResponseModel rs = new ResponseModel();    
		    AgentWfoDocModel agentDocs = agentWfoDocService.findByAgentId(agentId);
		    
		   if (agentDocs != null) {
				rs.setAgenId(null);
				rs.setData(agentDocs);
				rs.setMessage("AGENT-DOCS-FOUND");
				rs.setStatus("200");
			} else {
				rs.setAgenId(null);
				rs.setMessage("AGENT-DOCS-NOT-FOUND");
				rs.setData(agentDocs);
				rs.setStatus("400");
			}
		   logger.info("AgentWfoDocController , saveAgentDocsWforDetails() <END>");
			return ResponseEntity.ok(rs);
	 }

	@GetMapping("/get-agent-doc-wfo-det-id/{id}")
	public ResponseEntity<?> getAgentDocsWforDetById(@Valid @PathVariable(name="id") Long id
			,HttpServletRequest req) throws ResourceNotFoundException
			 {
		    logger.info("AgentWfoDocController , getAgentDocsWforDetById() <START>");
		    Optional.ofNullable(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
			
		    ResponseModel rs = new ResponseModel();    
		    AgentWfoDocModel agentDocs = agentWfoDocService.findById(id);
		    
		   if (agentDocs != null) {
				rs.setAgenId(null);
				rs.setData(agentDocs);
				rs.setMessage("AGENT-DOCS-FOUND");
				rs.setStatus("200");
			} else {
				rs.setAgenId(null);
				rs.setMessage("AGENT-DOCS-NOT-FOUND");
				rs.setData(agentDocs);
				rs.setStatus("400");
			}
		   logger.info("AgentWfoDocController , getAgentDocsWforDetById() <END>");
			return ResponseEntity.ok(rs);
	 }


	@GetMapping("/get-agent-doc-wfo-det-loginId")
	public ResponseEntity<?> getAgentDocsWforDetByLogInId(
			HttpServletRequest req) throws ResourceNotFoundException
			 {
		    logger.info("AgentWfoDocController , getAgentDocsWforDetByLogInId() <START>");
		    
		    ResponseModel rs = new ResponseModel();    
		    AuthUser user = AuthUser.getLoggedInUser(req);
		    AgentWfoDocModel agentDocs = agentWfoDocService.findByAgentId(user.getUserId());
		    
		   if (agentDocs != null) {
				rs.setAgenId(null);
				rs.setData(agentDocs);
				rs.setMessage("AGENT-DOCS-FOUND");
				rs.setStatus("200");
			} else {
				rs.setAgenId(null);
				rs.setMessage("AGENT-DOCS-NOT-FOUND");
				rs.setData(agentDocs);
				rs.setStatus("400");
			}
		   logger.info("AgentWfoDocController , getAgentDocsWforDetByLogInId() <END>");
			return ResponseEntity.ok(rs);
	 }

	
}
