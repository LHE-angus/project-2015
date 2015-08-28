package com.ebiz.mmt.dao;

import java.util.List;
import java.util.Map;

import com.ebiz.mmt.domain.TaskContent;
import com.ebiz.ssi.dao.EntityDao;

/**
 * 
 * @author zhou
 * 
 */
public interface TaskContentDao extends EntityDao<TaskContent> {

	Long selectTaskContentYwyReportCount(TaskContent tc);

	List<TaskContent> selectTaskContentYwyReportPaginatedList(TaskContent tc);

	Long selectTaskContentCustReportCount(TaskContent tc);

	List<TaskContent> selectTaskContentCustReportPaginatedList(TaskContent tc);
	
	Long selectTaskReportByCustCount(TaskContent tc);

	List<Map<String, String>> selectTaskReportByCustPaginatedList(TaskContent tc);

}
