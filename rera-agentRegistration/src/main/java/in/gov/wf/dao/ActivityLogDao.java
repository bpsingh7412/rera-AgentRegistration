package in.gov.wf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.gov.wf.model.ActivityLog;

@Repository
public interface ActivityLogDao extends JpaRepository<ActivityLog, Long> {

	List<ActivityLog> findAllByWfoId(String wfoId);

}
