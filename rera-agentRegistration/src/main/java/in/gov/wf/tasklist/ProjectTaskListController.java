package in.gov.wf.tasklist;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.gov.wf.model.TaskListResponse;
import in.gov.wf.security.AuthUser;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/agent_reg/secure/")
public class ProjectTaskListController {

	@Autowired
	ProjectTaskListService service;

	@RequestMapping(path = "agent-tasklist", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<TaskListResponse> getProjectForTaskList(HttpServletRequest req) {
		AuthUser user = AuthUser.getLoggedInUser(req);

		try {
			List<AgentSummaryModel> plist = this.service.getProjectList(user);
			return ResponseEntity.ok(TaskListResponse.ok(plist));
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.ok(TaskListResponse.error(ex.getMessage()));
		}
	}

}
