package in.gov.agentregistration.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import in.gov.agentregistration.configuration.Util;
import in.gov.agentregistration.constants.CommonConstants;
import in.gov.agentregistration.exception.ResourceBadRequestException;
import in.gov.agentregistration.exception.ResourceNotFoundException;
import in.gov.agentregistration.model.AgentAssosiateModel;
import in.gov.agentregistration.model.AgentAuthorizedSignatoryModel;
import in.gov.agentregistration.model.AgentDetailModel;
import in.gov.agentregistration.model.AgentRegistrationModel;
import in.gov.agentregistration.model.DmsModel;
import in.gov.agentregistration.model.EnquiryTrxModel;
import in.gov.agentregistration.model.ResponseModel;
import in.gov.agentregistration.model.UserAccountModel;
import in.gov.agentregistration.services.AgentRegistrationService;
import in.gov.agentregistration.services.AgentRestTemplateServices;
import in.gov.agentregistration.services.DmsService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/agent_reg/agent")

public class AgentRegistrationRestController {
	private static final Logger logger = LogManager.getLogger(AgentRegistrationRestController.class);
	@Autowired
	AgentRegistrationService services;
	@Autowired
	private Environment env;

	@Autowired
	DmsService dmsService;
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	DmsModel dmsModel;

	@Autowired
	HttpSession session;

	@Autowired
	UserAccountModel userAccountDetailsModel;
	
	@GetMapping("/get-agent-by-emaild-ph/{emailId}/{mob}")
	public ResponseEntity<?> getAgentDetailById(
			  @PathVariable(value = "emailId") String emailId,
			  @PathVariable(value = "mob") String mob
			) throws ResourceNotFoundException {
		   ResponseModel rs = new ResponseModel();
		   
		   List<AgentRegistrationModel>  agentList = services.findByEmailIdAndStatus(emailId,CommonConstants.ENQUIRY);
		   agentList = agentList.stream().filter(e-> e.getAgentDetail().getMobileNo().equals(mob))
				       .collect(Collectors.toList());
		
		   if (agentList != null && agentList.size()>0) {
			rs.setAgenId(null);
			rs.setData(agentList);
			rs.setMessage("AGENT-FOUND");
			rs.setStatus("200");
		} else {
			rs.setAgenId(null);
			rs.setMessage("AGENT-NOT-FOUND");
			rs.setData(agentList);
			rs.setStatus("400");
		}
		return ResponseEntity.ok(rs);

	}


	/****************************************************************************************************
	 * getAllPromoterRegistration
	 ****************************************************************************************************/

	@GetMapping("/all-agent")
	public ResponseEntity<?> getAllPromoterRegistrationDetails() throws ResourceNotFoundException {
		List<AgentRegistrationModel> l = new ArrayList<AgentRegistrationModel>();
		l = services.getAllAgentRegistration();
		if (l.size() == 0)
			throw new ResourceAccessException("NOT FOUND");
		return ResponseEntity.ok(l);
	}

	/****************************************************************************************************
	 * savePromoterRegistrationDetail
	 * 
	 * @throws ResourceBadRequestException
	 ****************************************************************************************************/

	/*
	 * @GetMapping("/save-agent-detail/test") public ResponseEntity<?>
	 * savePromoterRegistrationDetails() { AgentRegistrationModel entity = new
	 * AgentRegistrationModel(); entity.setAssosiateList(new
	 * ArrayList<AgentAssosiateModel>()); entity.setAuthorizedSignatoryList(new
	 * ArrayList<AgentAuthorizedSignatoryModel>());
	 * entity.getAssosiateList().add(new AgentAssosiateModel());
	 * entity.getAssosiateList().add(new AgentAssosiateModel());
	 * entity.getAuthorizedSignatoryList().add(new AgentAuthorizedSignatoryModel());
	 * entity.getAuthorizedSignatoryList().add(new AgentAuthorizedSignatoryModel());
	 * return ResponseEntity.ok(entity); }
	 */

	@PostMapping("/save-agent-detail")
	public ResponseEntity<?> savePromoterRegistrationDetails(@RequestBody AgentRegistrationModel entity)
			throws ResourceNotFoundException {
		AgentRegistrationModel m = null;
		// logger.debug("savePromoterRegistrationDetail");
		
		System.out.println("****************save-agent-detail*****************");
		System.out.println("Assosiate:" + entity.getAssosiateList().size());
		System.out.println("Authorized:" + entity.getAuthorizedSignatoryList().size());
		if (services.isAgentRegistrationExist(entity)) {
			throw new ResourceAccessException(CommonConstants.AGENT_EXISTS);
		}
		entity.setStatus(CommonConstants.PENDING);
		if (null != entity.getAgentId()) {
			AgentRegistrationModel oldModel = services.getAgentDetailsById(entity.getAgentId());
			entity.setEnqiryList(oldModel.getEnqiryList());
		}
		AgentRegistrationModel pm = services.saveAgentRegistrationDetails(entity);
		try {
			m = commitDocument(pm);
			m = services.saveAgentRegistrationDetails(m);

		} catch (Exception e) {
			System.out.println(e);
			logger.debug(e);
			// throw new ResourceAccessException("Registered but status is not updated");
		}

		ResponseModel rs = new ResponseModel();
		rs.setStatus("200");
		rs.setMessage("User Registration is pending for authority approval");
		rs.setData(m);
		return ResponseEntity.ok(rs);
	}

	/****************************************************************************************************
	 * getAgentDetailById
	 ****************************************************************************************************/

	@GetMapping("/agent{id}")
	public ResponseEntity<?> getAgentDetailById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		AgentRegistrationModel l = services.getAgentDetailsById(id);
		Optional.ofNullable(l).orElseThrow(() -> new ResourceAccessException("Not Found"));
		return ResponseEntity.ok(l);
	}

	@GetMapping("/company{cin}/type{type}")
	public ResponseEntity<?> getPromoterDetailByCinAndType(@PathVariable(value = "cin") String cin,
			@PathVariable(value = "type") String type) throws ResourceNotFoundException {
		AgentRegistrationModel l = services.getAgentDetailsByCinAndType(cin, type);
		Optional.ofNullable(l).orElseThrow(() -> new ResourceAccessException("Not Found"));
		return ResponseEntity.ok(l);
	}

	/*
	 * @RequestMapping(value = "/template/user") public String getProductList() {
	 * HttpHeaders headers = new HttpHeaders();
	 * headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	 * ResponseEntity<String> response =
	 * restTemplate.exchange(URL_EMPLOYEES,HttpMethod.POST, entity, String.class);
	 * 
	 * return restTemplate.exchange("http://localhost:8080/products",
	 * HttpMethod.GET, entity, String.class).getBody(); }
	 */

	// @ResponseBody
	@GetMapping(value = "/captcha", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] captchaPhoto() throws ResourceNotFoundException {
		logger.debug("captchaPhoto");
		Util util = new Util();
		byte[] b = util.getCaptchaImage();
		session.setAttribute("captchaStr", util.getCaptchaString());
		return b;
	}

	private AgentRegistrationModel commitDocument(AgentRegistrationModel m) throws ResourceBadRequestException {
		DmsModel dmsModel = new DmsModel();
		if (m.getAgentDetail() != null && Optional.ofNullable(m.getAgentDetail().getPanDocId()).isPresent()
				&& m.getAgentDetail().getPanDocUId() == null) {
			dmsModel.setFolderId(m.getAgentId().toString());
			dmsModel.setDocumentId(m.getAgentDetail().getPanDocId());
			dmsModel.setDocumentType("AGENT_PAN_DOC");
			DmsModel comitPandoc = dmsService.commitDoc(dmsModel);
            Optional.ofNullable(comitPandoc)
					.orElseThrow(() -> new ResourceAccessException("There are some issue in document commit."));
			m.getAgentDetail().setPanDocUId(comitPandoc.getUid());
			m.getAgentDetail().setPanDocId(comitPandoc.getDocumentId());
		}

		if (m.getAgentDetail() != null
				&& Optional.ofNullable(m.getAgentDetail().getEntities_developerGroupCertificate()).isPresent()
				&& m.getAgentDetail().getEntities_developerGroupCertificateUID() == null) {
			dmsModel.setFolderId(m.getAgentId().toString());
			dmsModel.setDocumentId(m.getAgentDetail().getEntities_developerGroupCertificate());
			dmsModel.setDocumentType("AGENT_PAN_CERTI");
			DmsModel comitPandocGrpCer = dmsService.commitDoc(dmsModel);

			logger.debug("Pandoc:" + comitPandocGrpCer.getFileName());

			Optional.ofNullable(comitPandocGrpCer)
					.orElseThrow(() -> new ResourceAccessException("There are some issue in document commit."));
			m.getAgentDetail().setEntities_developerGroupCertificateUID(comitPandocGrpCer.getUid());
			m.getAgentDetail().setEntities_developerGroupCertificate(comitPandocGrpCer.getDocumentId());
		}
		if (m.getAgentDetail() != null
				&& Optional.ofNullable(m.getAgentDetail().getCompanyRegCertificateDocId()).isPresent()
				&& m.getAgentDetail().getCompanyRegCertificateDocUId() == null) {
			dmsModel.setFolderId(m.getAgentId().toString());
			dmsModel.setDocumentId(m.getAgentDetail().getCompanyRegCertificateDocId());
			dmsModel.setDocumentType("AGENT_PAN_DOC");
			DmsModel comitPandocr = dmsService.commitDoc(dmsModel);

			logger.debug("Pandoc:" + comitPandocr.getFileName());

			Optional.ofNullable(comitPandocr)
					.orElseThrow(() -> new ResourceAccessException("There are some issue in document commit."));
			m.getAgentDetail().setCompanyRegCertificateDocUId(comitPandocr.getUid());
			m.getAgentDetail().setCompanyRegCertificateDocId(comitPandocr.getDocumentId());
		}
		if (m.getAgentDetail() != null && Optional.ofNullable(m.getAgentDetail().getAuthsignPANColorPDFId()).isPresent()
				&& m.getAgentDetail().getAuthsignPANColorPDFUId() == null) {
			dmsModel.setFolderId(m.getAgentId().toString());
			dmsModel.setDocumentId(m.getAgentDetail().getAuthsignPANColorPDFId());
			dmsModel.setDocumentType("AGENT_PAN_DOC");
			DmsModel comitPandocrp = dmsService.commitDoc(dmsModel);

			logger.debug("Pandoc:" + comitPandocrp.getFileName());

			Optional.ofNullable(comitPandocrp)
					.orElseThrow(() -> new ResourceAccessException("There are some issue in document commit."));
			m.getAgentDetail().setAuthsignPANColorPDFUId(comitPandocrp.getUid());
			m.getAgentDetail().setAuthsignPANColorPDFId(comitPandocrp.getDocumentId());
		}
		if (m.getAgentDetail() != null && Optional.ofNullable(m.getAgentDetail().getAuthsignCerificateId()).isPresent()
				&& m.getAgentDetail().getAuthsignCerificateUId() == null) {
			dmsModel.setFolderId(m.getAgentId().toString());
			dmsModel.setDocumentId(m.getAgentDetail().getAuthsignCerificateId());
			dmsModel.setDocumentType("AGENT_PAN_DOC");
			DmsModel comitPandocauth = dmsService.commitDoc(dmsModel);

			logger.debug("Pandoc:" + comitPandocauth.getFileName());

			Optional.ofNullable(comitPandocauth)
					.orElseThrow(() -> new ResourceAccessException("There are some issue in document commit."));
			m.getAgentDetail().setAuthsignCerificateUId(comitPandocauth.getUid());
			m.getAgentDetail().setAuthsignCerificateId(comitPandocauth.getDocumentId());
		}
		if (m.getAgentDetail() != null
				&& Optional.ofNullable(m.getAgentDetail().getRegistrationCertificateId()).isPresent()
				&& m.getAgentDetail().getRegistrationCertificateUId() == null) {

			dmsModel.setFolderId(m.getAgentId().toString());
			dmsModel.setDocumentId(m.getAgentDetail().getRegistrationCertificateId());
			dmsModel.setDocumentType("AGENT_PAN_DOC");
			DmsModel comitregcert = dmsService.commitDoc(dmsModel);

			logger.debug("Pandoc:" + comitregcert.getFileName());

			Optional.ofNullable(comitregcert)
					.orElseThrow(() -> new ResourceAccessException("There are some issue in document commit."));
			m.getAgentDetail().setRegistrationCertificateId(comitregcert.getDocumentId());
			m.getAgentDetail().setRegistrationCertificateUId(comitregcert.getUid());
		}
		if (m.getAgentDetail() != null && Optional.ofNullable(m.getAgentDetail().getIndividualPhotoDocId()).isPresent()
				&& m.getAgentDetail().getIndividualPhotoDocUId() == null) {

			dmsModel.setFolderId(m.getAgentId().toString());
			dmsModel.setDocumentId(m.getAgentDetail().getIndividualPhotoDocId());
			dmsModel.setDocumentType("AGENT_REG_DOC");
			DmsModel comitCertificatedoc = dmsService.commitDoc(dmsModel);

			logger.debug("comitCertificatedoc:" + comitCertificatedoc.getFileName());
			Optional.ofNullable(comitCertificatedoc)
					.orElseThrow(() -> new ResourceAccessException("There are some issue in document commit."));
			m.getAgentDetail().setIndividualPhotoDocUId(comitCertificatedoc.getUid());
			m.getAgentDetail().setIndividualPhotoDocId(comitCertificatedoc.getDocumentId());
		}

		if (m.getAgentDetail() != null && Optional.ofNullable(m.getAgentDetail().getAuthsignPANColorPDId()).isPresent()
				&& m.getAgentDetail().getAuthsignPANColorPDFUId() == null) {
			dmsModel.setFolderId(m.getAgentId().toString());
			dmsModel.setDocumentId(m.getAgentDetail().getAuthsignPANColorPDId());
			dmsModel.setDocumentType("AGENT_AUTHORITY_PAN_DOC");
			DmsModel comitPandoc = dmsService.commitDoc(dmsModel);
			logger.debug("Pandoc:" + comitPandoc.getFileName());
			Optional.ofNullable(comitPandoc)
					.orElseThrow(() -> new ResourceAccessException("There are some issue in document commit."));
			m.getAgentDetail().setAuthsignPANColorPDId(comitPandoc.getDocumentId());
			m.getAgentDetail().setAuthsignPanColorPDUId(comitPandoc.getUid());
		}

		if (m.getAgentDetail() != null && Optional.ofNullable(m.getAgentDetail().getAuthsignPhotId()).isPresent()
				&& m.getAgentDetail().getAuthsignPhotoUId() == null) {
			dmsModel.setFolderId(m.getAgentId().toString());
			dmsModel.setDocumentId(m.getAgentDetail().getAuthsignPhotId());
			dmsModel.setDocumentType("AGENT_AUTHORITY_PAN_DOC");
			DmsModel comitPandoct = dmsService.commitDoc(dmsModel);

			logger.debug("Pandoc:" + comitPandoct.getFileName());
			Optional.ofNullable(comitPandoct)
					.orElseThrow(() -> new ResourceAccessException("There are some issue in document commit."));
			m.getAgentDetail().setAuthsignPhotId(comitPandoct.getDocumentId());
			m.getAgentDetail().setAuthsignPhotUId(comitPandoct.getUid());
		}

		if (m.getAgentDetail() != null && Optional.ofNullable(m.getAgentDetail().getAuthsignAdharDoId()).isPresent()
				&& m.getAgentDetail().getAuthsignAdharDoUId() == null) {
			dmsModel.setFolderId(m.getAgentId().toString());
			dmsModel.setDocumentId(m.getAgentDetail().getAuthsignAdharDoId());
			dmsModel.setDocumentType("AGENT_AUTHORITY_PAN_DOC");
			DmsModel comitPandocAd = dmsService.commitDoc(dmsModel);

			logger.debug("Pandoc:" + comitPandocAd.getFileName());
			Optional.ofNullable(comitPandocAd)
					.orElseThrow(() -> new ResourceAccessException("There are some issue in document commit."));
			m.getAgentDetail().setAuthsignAdharDoId(comitPandocAd.getDocumentId());
			m.getAgentDetail().setAuthsignAdharDoUId(comitPandocAd.getUid());
		}

		if (m.getAgentDetail() != null && Optional.ofNullable(m.getAgentDetail().getAuthsignCerificatId()).isPresent()
				&& m.getAgentDetail().getAuthsignCerificatUId() == null) {
			dmsModel.setFolderId(m.getAgentId().toString());
			dmsModel.setDocumentId(m.getAgentDetail().getAuthsignCerificatId());
			dmsModel.setDocumentType("AGENT_AUTHORITY_PAN_DOC");
			DmsModel comitCertificatedoc = dmsService.commitDoc(dmsModel);
			logger.debug("comitCertificatedoc:" + comitCertificatedoc.getFileName());
			Optional.ofNullable(comitCertificatedoc)
					.orElseThrow(() -> new ResourceAccessException("There are some issue in document commit."));
			m.getAgentDetail().setAuthsignCerificatId(comitCertificatedoc.getDocumentId());
			m.getAgentDetail().setAuthsignCerificatUId(comitCertificatedoc.getUid());
		}

		List<AgentAssosiateModel> cm = m.getAssosiateList();
		if (cm != null && cm.size() > 0) {
			for (AgentAssosiateModel ms : cm) {
				if (Optional.ofNullable(ms.getAssocaitePANColorPDFId()).isPresent()
						&& ms.getAssocaitePANColorPDFUId() == null) {
                    dmsModel.setFolderId(ms.getId().toString());
					dmsModel.setDocumentId(ms.getAssocaitePANColorPDFId());
					dmsModel.setDocumentType("AGENT_ASSOCAITE_PAN_DOC");

					DmsModel comitCertificatedocpan = dmsService.commitDoc(dmsModel);
					logger.debug("comitCertificatedoc:" + comitCertificatedocpan.getFileName());
					Optional.ofNullable(comitCertificatedocpan)
							.orElseThrow(() -> new ResourceAccessException("There are some issue in document commit."));
					ms.setAssocaitePANColorPDFId(comitCertificatedocpan.getDocumentId());
					ms.setAssocaitePANColorPDFUId(comitCertificatedocpan.getUid());
				}
				if (Optional.ofNullable(ms.getAssocaiteADHARId()).isPresent() && ms.getAssocaiteADHARPDFUId() == null) {
					dmsModel.setFolderId(ms.getId().toString());
					dmsModel.setDocumentId(ms.getAssocaiteADHARId());
					dmsModel.setDocumentType("AGENT_AUTHORITY_PAN_DOC");
					DmsModel comitCertificatedocaad = dmsService.commitDoc(dmsModel);
					logger.debug("comitCertificatedoc:" + comitCertificatedocaad.getFileName());
					Optional.ofNullable(comitCertificatedocaad)
							.orElseThrow(() -> new ResourceAccessException("There are some issue in document commit."));
					ms.setAssocaiteADHARId(comitCertificatedocaad.getDocumentId());
					ms.setAssocaiteADHARPDFUId(comitCertificatedocaad.getUid());
				}
				if (Optional.ofNullable(ms.getAssocaitePhtoId()).isPresent() && ms.getAssocaitePhotoUId() == null) {
					dmsModel.setFolderId(ms.getId().toString());
					dmsModel.setDocumentId(ms.getAssocaitePhtoId());
					dmsModel.setDocumentType("AGENT_AUTHORITY_PAN_DOC");
					DmsModel comitCertificatedocpho = dmsService.commitDoc(dmsModel);
					logger.debug("comitCertificatedoc:" + comitCertificatedocpho.getFileName());
					Optional.ofNullable(comitCertificatedocpho)
							.orElseThrow(() -> new ResourceAccessException("There are some issue in document commit."));
					ms.setAssocaitePhtoId(comitCertificatedocpho.getDocumentId());
					ms.setAssocaitePhotoUId(comitCertificatedocpho.getUid());
				}
				ms.setAgentId(m.getAgentId());
			}
		}

		List<AgentAuthorizedSignatoryModel> pcm = m.getAuthorizedSignatoryList();

		if (pcm != null && pcm.size() > 0) {

			for (AgentAuthorizedSignatoryModel pm : pcm) {
				if (Optional.ofNullable(pm.getAuthsignPANColorPDId()).isPresent()
						&& pm.getAuthsignPanColorPDUId() == null) {
					dmsModel.setFolderId(pm.getId().toString());
					dmsModel.setDocumentId(pm.getAuthsignPANColorPDId());
					dmsModel.setDocumentType("AGENT_AUTHORITY_PAN_DOC");
					DmsModel comitCertificatedocauthpan = dmsService.commitDoc(dmsModel);
					logger.debug("comitCertificatedoc:" + comitCertificatedocauthpan.getFileName());
					Optional.ofNullable(comitCertificatedocauthpan)
							.orElseThrow(() -> new ResourceAccessException("There are some issue in document commit."));
					pm.setAuthsignPANColorPDId(comitCertificatedocauthpan.getDocumentId());
					pm.setAuthsignPanColorPDUId(comitCertificatedocauthpan.getUid());
				}

				if (Optional.ofNullable(pm.getAuthsignAdharDoId()).isPresent() && pm.getAuthsignAdharDoUId() == null) {
					dmsModel.setFolderId(pm.getId().toString());
					dmsModel.setDocumentId(pm.getAuthsignAdharDoId());
					dmsModel.setDocumentType("AGENT_AUTHORITY_PAN_DOC");
					DmsModel comitCertificatedocauthaad = dmsService.commitDoc(dmsModel);
					logger.debug("comitCertificatedoc:" + comitCertificatedocauthaad.getFileName());
					Optional.ofNullable(comitCertificatedocauthaad)
							.orElseThrow(() -> new ResourceAccessException("There are some issue in document commit."));
					pm.setAuthsignAdharDoId(comitCertificatedocauthaad.getDocumentId());
					pm.setAuthsignAdharDoUId(comitCertificatedocauthaad.getUid());
				}
				if (Optional.ofNullable(pm.getAuthsignCerificatId()).isPresent()
						&& pm.getAuthsignCerificatUId() == null) {
					dmsModel.setFolderId(pm.getId().toString());
					dmsModel.setDocumentId(pm.getAuthsignCerificatId());
					dmsModel.setDocumentType("AGENT_AUTHORITY_PAN_DOC");
					DmsModel comitCertificatedocauthsign = dmsService.commitDoc(dmsModel);
					logger.debug("comitCertificatedoc:" + comitCertificatedocauthsign.getFileName());
					Optional.ofNullable(comitCertificatedocauthsign)
							.orElseThrow(() -> new ResourceAccessException("There are some issue in document commit."));
					pm.setAuthsignCerificatId(comitCertificatedocauthsign.getDocumentId());
					pm.setAuthsignCerificatUId(comitCertificatedocauthsign.getUid());
				}
				if (Optional.ofNullable(pm.getAuthsignPhotId()).isPresent() && pm.getAuthsignPhotUId() == null) {
					dmsModel.setFolderId(pm.getId().toString());
					dmsModel.setDocumentId(pm.getAuthsignPhotId());
					dmsModel.setDocumentType("AGENT_AUTHORITY_PAN_DOC");
					DmsModel comitCertificatedocauthsignpho = dmsService.commitDoc(dmsModel);
					logger.debug("comitCertificatedoc:" + comitCertificatedocauthsignpho.getFileName());
					Optional.ofNullable(comitCertificatedocauthsignpho)
							.orElseThrow(() -> new ResourceAccessException("There are some issue in document commit."));
					pm.setAuthsignPhotId(comitCertificatedocauthsignpho.getDocumentId());
					pm.setAuthsignPhotUId(comitCertificatedocauthsignpho.getUid());
				}
				pm.setAgentId(m.getAgentId());
			}
		}
		m.setAuthorizedSignatoryList(pcm);
		m.setAssosiateList(cm);
		AgentRegistrationModel k = services.saveAgentRegistrationDetails(m);

		Optional.ofNullable(k).orElseThrow(() -> new ResourceAccessException("Document commit, But not updated"));
		return k;
	}

	@GetMapping("/agent-associate-details/{panNo}")
	public ResponseEntity<?> getPromoterDetailByPanNo(@PathVariable(value = "panNo") String panNo)
			throws ResourceNotFoundException {
		AgentDetailModel l = services.getAgentDetailsByPanNo(panNo);
		Optional.ofNullable(l).orElseThrow(() -> new ResourceAccessException(env.getProperty("PRM.EMPTY")));
		return ResponseEntity.ok(l);
	}

	/*******************************************************************************
	 * updatePromoterModelDetails
	 *******************************************************************************/
	@PostMapping("/update-agent-details")
	public ResponseEntity<AgentRegistrationModel> updateUser(@RequestBody AgentRegistrationModel entity)
			throws ResourceNotFoundException {
		Optional.ofNullable(entity).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
		return ResponseEntity.ok(services.updateAgentRegistration(entity));
	}

	@PostMapping("/update-agent-status/{id}/{status}")
	public ResponseEntity<?> updateAgentStaus(@PathVariable(value = "id") Long id,
			@PathVariable(value = "status") String status) throws ResourceNotFoundException {
		ResponseModel rs = new ResponseModel();
		Optional.ofNullable(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
		Optional.ofNullable(status).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
		AgentRegistrationModel agent = services.getAgentDetailsById(id);

		if (status.equals(CommonConstants.APPROVED)) {
			UserAccountModel user = new UserAccountModel();
			user.setId(agent.getAgentId());
			user.setEmail(agent.getEmailId());
			user.setUserCategory(agent.getAgentType());
			user.setMobile(agent.getAgentDetail().getMobileNo());
			user.setUserName(agent.getAgentFirstName() + "" + agent.getAgentMName() + "" + agent.getAgentLName());
			user.setUserStatus(status);
			AgentRestTemplateServices.createAccount(user, env.getProperty("URL_CREATE_USER_ACC"));
			agent.setStatus(CommonConstants.REGISTERED);
		} else {
			agent.setStatus(status);

		}
		agent = services.saveAgentRegistrationDetails(agent);
		if (agent != null) {
			rs.setAgenId(null);
			rs.setData(agent);
			rs.setMessage("AGENT-UPDATED");
			rs.setStatus("200");
		} else {
			rs.setAgenId(null);
			rs.setMessage("AGENT-NOT-UPDATED");
			rs.setData(agent);
			rs.setStatus("400");
		}
		return ResponseEntity.ok(rs);

	}

	@GetMapping("/get-agent-payment")
	public ResponseEntity<?> getAgentPayment() throws ResourceNotFoundException {
		ResponseModel rs = new ResponseModel();
		List<AgentRegistrationModel> agentList = services.findByStatus(CommonConstants.PENDING_WITH_AUTHORITY);

		if (agentList != null && agentList.size() > 0) {
			rs.setAgenId(null);
			rs.setData(agentList);
			rs.setStatus("200");
		} else {
			rs.setAgenId(null);
			rs.setData(agentList);
			rs.setStatus("400");
		}
		return ResponseEntity.ok(rs);
	}

}
