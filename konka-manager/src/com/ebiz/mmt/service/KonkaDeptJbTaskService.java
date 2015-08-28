package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaDeptJbTask;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-17 11:44:15
 */
public interface KonkaDeptJbTaskService {

	Long createKonkaDeptJbTask(KonkaDeptJbTask t);

	int modifyKonkaDeptJbTask(KonkaDeptJbTask t);

	int removeKonkaDeptJbTask(KonkaDeptJbTask t);

	KonkaDeptJbTask getKonkaDeptJbTask(KonkaDeptJbTask t);

	List<KonkaDeptJbTask> getKonkaDeptJbTaskList(KonkaDeptJbTask t);

	Long getKonkaDeptJbTaskCount(KonkaDeptJbTask t);

	List<KonkaDeptJbTask> getKonkaDeptJbTaskPaginatedList(KonkaDeptJbTask t);

	String createKonkaDeptJbTaskByExcel(List<KonkaDeptJbTask> list);

	List<KonkaDeptJbTask> getKonkaDeptJbTaskForBackMoneyList(KonkaDeptJbTask t);

}