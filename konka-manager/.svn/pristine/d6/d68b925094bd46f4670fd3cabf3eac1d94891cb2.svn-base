package com.ebiz.mmt.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.YwtTaskDao;
import com.ebiz.mmt.domain.YwtTask;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-20 10:01:47
 */
@Service
public class YwtTaskDaoSqlMapImpl extends EntityDaoSqlMapImpl<YwtTask> implements YwtTaskDao {

	@Override
	public Long selectYwtTaskLBCount(YwtTask v) {
		 return (Long) super.getSqlMapClientTemplate().queryForObject("selectYwtTaskLBCount", v);
	}

	@Override
	public List<YwtTask> selectxiaji(YwtTask v) {
		return super.getSqlMapClientTemplate().queryForList("selectxiaji", v);
	}
	
	@Override
	public List<YwtTask> selectYwtTaskLBPaginatedList(YwtTask v) {
		return super.getSqlMapClientTemplate().queryForList("selectYwtTaskLBPaginatedList", v);
	}

	@Override
	public List<Map<String, String>> selectChooseList(YwtTask v) {
		return super.getSqlMapClientTemplate().queryForList("selectChooseList", v);
	}

	@Override
	public List<Map<String, String>> selectNoChooseList(YwtTask v) {
		return super.getSqlMapClientTemplate().queryForList("selectNoChooseList", v);
	}

}
