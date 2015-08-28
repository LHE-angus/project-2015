package com.ebiz.mmt.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.TaskContentDao;
import com.ebiz.mmt.domain.TaskContent;
import com.ebiz.mmt.service.TaskContentService;

/**
 * 
 * @author zhou
 * 
 */
@Service
public class TaskContentServiceImpl implements TaskContentService {

	@Resource
	private TaskContentDao taskContentDao;

	@Override
	public Long createTaskContent(TaskContent t) {
		return this.taskContentDao.insertEntity(t);
	}

	@Override
	public TaskContent getTaskContent(TaskContent t) {
		return this.taskContentDao.selectEntity(t);
	}

	@Override
	public Long getTaskContentCount(TaskContent t) {
		return this.taskContentDao.selectEntityCount(t);
	}

	@Override
	public List<TaskContent> getTaskContentList(TaskContent t) {
		return this.taskContentDao.selectEntityList(t);
	}

	@Override
	public int modifyTaskContent(TaskContent t) {
		return this.taskContentDao.updateEntity(t);
	}

	@Override
	public int removeTaskContent(TaskContent t) {
		return this.taskContentDao.deleteEntity(t);
	}

	@Override
	public List<TaskContent> getTaskContentPaginatedList(TaskContent t) {
		return this.taskContentDao.selectEntityPaginatedList(t);
	}

	@Override
	public Long getTaskContentYwyReportCount(TaskContent tc) {
		
		return this.taskContentDao.selectTaskContentYwyReportCount(tc);
	}

	@Override
	public List<TaskContent> getTaskContentYwyReportPaginatedList(TaskContent tc) {
		
		return this.taskContentDao.selectTaskContentYwyReportPaginatedList(tc);
	}

	@Override
	public Long getTaskContentCustReportCount(TaskContent tc) {
		
		return this.taskContentDao.selectTaskContentCustReportCount(tc);
	}

	@Override
	public List<TaskContent> getTaskContentCustReportPaginatedList(
			TaskContent tc) {
		
		return this.taskContentDao.selectTaskContentCustReportPaginatedList(tc);
	}

	@Override
	public Long getTaskReportByCustCount(TaskContent tc) {
		return this.taskContentDao.selectTaskReportByCustCount(tc);
	}

	@Override
	public List<Map<String, String>> getTaskReportByCustPaginatedList(
			TaskContent tc) {
		return this.taskContentDao.selectTaskReportByCustPaginatedList(tc);
	}

}
