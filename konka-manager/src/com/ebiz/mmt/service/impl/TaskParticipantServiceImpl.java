package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.TaskParticipantDao;
import com.ebiz.mmt.domain.TaskParticipant;
import com.ebiz.mmt.service.TaskParticipantService;


@Service
public class TaskParticipantServiceImpl implements TaskParticipantService {

	@Resource
	private TaskParticipantDao taskParticipantDao;
	

	public Long createTaskParticipant(TaskParticipant t) {
		return this.taskParticipantDao.insertEntity(t);
	}

	public TaskParticipant getTaskParticipant(TaskParticipant t) {
		return this.taskParticipantDao.selectEntity(t);
	}

	public Long getTaskParticipantCount(TaskParticipant t) {
		return this.taskParticipantDao.selectEntityCount(t);
	}

	public List<TaskParticipant> getTaskParticipantList(TaskParticipant t) {
		return this.taskParticipantDao.selectEntityList(t);
	}

	public int modifyTaskParticipant(TaskParticipant t) {
		return this.taskParticipantDao.updateEntity(t);
	}

	public int removeTaskParticipant(TaskParticipant t) {
		return this.taskParticipantDao.deleteEntity(t);
	}

	public List<TaskParticipant> getTaskParticipantPaginatedList(TaskParticipant t) {
		return this.taskParticipantDao.selectEntityPaginatedList(t);
	}

}
