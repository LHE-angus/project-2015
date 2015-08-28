package com.ebiz.mmt.service;

import java.util.List;
import java.util.Map;

import com.ebiz.mmt.domain.YwtTask;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-20 10:01:47
 */
public interface YwtTaskService {

	Long createYwtTask(YwtTask t);

	int modifyYwtTask(YwtTask t);

	int removeYwtTask(YwtTask t);

	YwtTask getYwtTask(YwtTask t);

	List<YwtTask> getYwtTaskList(YwtTask t);

	Long getYwtTaskCount(YwtTask t);

	List<YwtTask> getYwtTaskPaginatedList(YwtTask t);

	Long getYwtTaskLBCount(YwtTask v);
		
	List<YwtTask> getYwtTaskLBPaginatedList(YwtTask v);
	
	int createYwtTask_YwtTaskReceive(YwtTask t);
	
	int modifyYwtTask_YwtTaskReceive(YwtTask t);
	
	List<Map<String, String>> getNoChooseList(YwtTask v);
	 
	List<Map<String, String>> getChooseList(YwtTask v);

	List<YwtTask> selectxiaji(YwtTask v);
}