package com.ebiz.mmt.dao;

import java.util.List;
import java.util.Map;

import com.ebiz.mmt.domain.YwtTask;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-20 10:01:47
 */
public interface YwtTaskDao extends EntityDao<YwtTask> {
	
	 Long selectYwtTaskLBCount(YwtTask v);
		
	 List<YwtTask> selectYwtTaskLBPaginatedList(YwtTask v);
	 
	 List<Map<String, String>> selectNoChooseList(YwtTask v);
	 
	 List<Map<String, String>> selectChooseList(YwtTask v);

	List<YwtTask> selectxiaji(YwtTask v);
}
