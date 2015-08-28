package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.YwtTaskReceiveDao;
import com.ebiz.mmt.domain.YwtTask;
import com.ebiz.mmt.domain.YwtTaskReceive;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-20 10:01:47
 */
@Service
public class YwtTaskReceiveDaoSqlMapImpl extends EntityDaoSqlMapImpl<YwtTaskReceive> implements YwtTaskReceiveDao {

	@Override
	public Long selectYwtTaskReceiveLBCount(YwtTaskReceive v) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectYwtTaskReceiveLBCount", v);
	}

	@Override
	public List<YwtTaskReceive> selectYwtTaskReceiveLBPaginatedList(YwtTaskReceive v) {
		return super.getSqlMapClientTemplate().queryForList("selectYwtTaskReceiveLBPaginatedList", v);
	}

	@Override					
	public List<YwtTaskReceive> selectCurTaskSub(YwtTaskReceive v) {
		
		return super.getSqlMapClientTemplate().queryForList("selectCurTaskSub", v);
	}
	@Override
	public Long updateYwtTaskReceive(YwtTaskReceive v) {
		
		return (long) super.getSqlMapClientTemplate().update("updateYwtTaskReceive", v);
	}

	@Override
	public List<YwtTaskReceive> selectYwtTaskReceiveTaskName(YwtTaskReceive v) {
		
		return super.getSqlMapClientTemplate().queryForList("selectYwtTaskReceiveTaskName", v);
	}
	
	@Override					
	public List<YwtTaskReceive> selectSubTaskList(YwtTaskReceive v) {
		
		return super.getSqlMapClientTemplate().queryForList("selectSubTaskList", v);
	}

}
