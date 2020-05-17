package in.gov.wf.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.gov.agentregistration.dao.AgentRegistrationDao;
import in.gov.agentregistration.dao.OfficeNoteDao;
import in.gov.agentregistration.model.AgentRegistrationModel;
import in.gov.agentregistration.model.OfficeNoteModel;
import in.gov.wf.dao.ActivityLogDao;
import in.gov.wf.model.ActivityLog;

@Service
public class ActivitySignalServices {

	@Autowired
	private ActivityLogDao logDao;

	@Autowired
	private AgentRegistrationDao projectDao;

	@Autowired
	private OfficeNoteDao noteDao;

	public static int POKED = 4;
	public static int OK = 1;
	public static int REJECT = 3;
	public static int ENQ = 2;
	public static int NONE = 0;

	public Map<String, Object> getSignalData(String wfoid) {
		Map<String, Object> data = new HashMap<String, Object>();
		List<Object> tp = new ArrayList<Object>();
		List<Object> fin = new ArrayList<Object>();
		List<Object> lg = new ArrayList<Object>();
		List<Object> mgmt = new ArrayList<Object>();
		data.put("tp", tp);
		data.put("fin", fin);
		data.put("lg", lg);
		data.put("mgmt", mgmt);

		AgentRegistrationModel project = this.projectDao.findByWfoId(wfoid);
		List<OfficeNoteModel> note = this.noteDao.getNoteByProjectId(project.getAgentId());
		List<ActivityLog> lst = this.logDao.findAllByWfoId(wfoid);

		// tp.add(POKED); tp.add(ENQ);tp.add(ENQ);tp.add(OK);
		// lg.add(POKED); lg.add(ENQ);lg.add(OK);
		// fin.add(POKED); fin.add(OK);fin.add(ENQ);
		// mgmt.add(NONE); mgmt.add(NONE);mgmt.add(NONE);
		setTpSignal(tp, project, note, lst);
		setFinSignal(fin, project, note, lst);
		setLgSignal(lg, project, note, lst);
		setMgmtSignal(mgmt, project, note, lst);
		return data;
	}

	private void setTpSignal(List<Object> tp, AgentRegistrationModel project, List<OfficeNoteModel> note,
			List<ActivityLog> lst) {
		tp.add(checkNote(note, "TP") ? POKED : NONE);
		tp.add(getActivityStatus(lst, "LEVELONE", "TP", "OFFC"));
		tp.add(getActivityStatus(lst, "LEVELONE", "TP", "TPSR"));
		tp.add(getActivityStatus(lst, "LEVELONE", "TP", "NEW"));
	}

	private void setFinSignal(List<Object> tp, AgentRegistrationModel project, List<OfficeNoteModel> note,
			List<ActivityLog> lst) {
		tp.add(checkNote(note, "FIN") ? POKED : NONE);
		tp.add(getActivityStatus(lst, "LEVELONE", "FIN", "OFFC"));
		tp.add(getActivityStatus(lst, "LEVELONE", "FIN", "NEW"));
	}

	private void setLgSignal(List<Object> tp, AgentRegistrationModel project, List<OfficeNoteModel> note,
			List<ActivityLog> lst) {
		tp.add(checkNote(note, "LEGAL") ? POKED : NONE);
		tp.add(getActivityStatus(lst, "LEVELONE", "LEGAL", "OFFC"));
		tp.add(getActivityStatus(lst, "LEVELONE", "LEGAL", "NEW"));
	}

	private void setMgmtSignal(List<Object> tp, AgentRegistrationModel project, List<OfficeNoteModel> note,
			List<ActivityLog> lst) {
		tp.add(getActivityStatus(lst, "CHAIRPERSON"));
		tp.add(getActivityStatus(lst, "MEMBERONE"));
		tp.add(getActivityStatus(lst, "SECRETRY"));
	}

	private boolean checkNote(List<OfficeNoteModel> note, String dept) {
		boolean flf = false;
		if (note != null && !note.isEmpty()) {
			for (OfficeNoteModel nn : note) {
				if (nn.getToDepartment().equals(dept)) {

					flf = true;
					break;
				}
			}
		}
		return flf;
	}

	private int getActivityStatus(List<ActivityLog> lst, String state, String dept, String chState) {
		int xyz = NONE;
		if (lst != null && !lst.isEmpty()) {
			for (ActivityLog log : lst) {
				if (log.getStateCode().equals(state) && log.getDeptName().equals(dept)
						&& log.getChStateCode().equals(chState)) {
					xyz = parseReason(log.getReason());
					break;
				}
			}
		}
		return xyz;
	}

	private int getActivityStatus(List<ActivityLog> lst, String state) {
		int xyz = NONE;
		if (lst != null && !lst.isEmpty()) {
			for (ActivityLog log : lst) {
				if (log.getStateCode().equals(state)) {
					xyz = parseReason(log.getReason());
					break;
				}
			}
		}
		return xyz;
	}

	private int parseReason(String reason) {
		if (reason == null)
			reason = "Approv";
		if (reason.toLowerCase().contains("approv")) {
			return OK;
		} else if (reason.toLowerCase().contains("reject")) {
			return REJECT;
		} else if (reason.toLowerCase().contains("enquiry")) {
			return ENQ;
		} else {
			return OK;
		}
	}
}
