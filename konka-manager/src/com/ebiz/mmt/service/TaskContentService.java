package com.ebiz.mmt.service;

import java.util.List;
import java.util.Map;

import com.ebiz.mmt.domain.TaskContent;

/**
 * 
 * @author zhou
 * 
 */
public interface TaskContentService {

	Long createTaskContent(TaskContent t);

	int modifyTaskContent(TaskContent t);

	int removeTaskContent(TaskContent t);

	TaskContent getTaskContent(TaskContent t);

	List<TaskContent> getTaskContentList(TaskContent t);

	Long getTaskContentCount(TaskContent t);

	List<TaskContent> getTaskContentPaginatedList(TaskContent t);

	Long getTaskContentYwyReportCount(TaskContent tc);

	List<TaskContent> getTaskContentYwyReportPaginatedList(TaskContent tc);

	Long getTaskContentCustReportCount(TaskContent tc);

	List<TaskContent> getTaskContentCustReportPaginatedList(TaskContent tc);

	Long getTaskReportByCustCount(TaskContent tc);

	List<Map<String, String>> getTaskReportByCustPaginatedList(TaskContent tc);
}