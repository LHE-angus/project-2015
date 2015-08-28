package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.YwtTask;
import com.ebiz.mmt.domain.YwtTaskReceive;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-20 10:01:47
 */
public interface YwtTaskReceiveDao extends EntityDao<YwtTaskReceive> {
	
	 Long selectYwtTaskReceiveLBCount(YwtTaskReceive v);
		
	 List<YwtTaskReceive> selectYwtTaskReceiveLBPaginatedList(YwtTaskReceive v);
     
	 public List<YwtTaskReceive> selectCurTaskSub(YwtTaskReceive v);

	Long updateYwtTaskReceive(YwtTaskReceive v);

	List<YwtTaskReceive> selectYwtTaskReceiveTaskName(YwtTaskReceive v);
	
	List<YwtTaskReceive> selectSubTaskList(YwtTaskReceive v);

}
