package in.gov.agentregistration.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.gov.agentregistration.model.OfficeNoteModel;

public interface OfficeNoteDao extends JpaRepository<OfficeNoteModel, Long> {

	@Query(value = "Select o from OfficeNoteModel o where o.projectId=?1 and ( o.fromDepartment=?2 or o.toDepartment=?3 )")
	public List<OfficeNoteModel> getNoteForOfficer(Long projectId, String frmdept, String todept);

	@Query(value = "Select o from OfficeNoteModel o where o.projectId=?1  ")
	public List<OfficeNoteModel> getNoteByProjectId(Long projectId);
}
