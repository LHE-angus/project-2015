package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.PePositionDao;
import com.ebiz.mmt.domain.PePosition;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-12 11:39:08
 */
@Service
public class PePositionDaoSqlMapImpl extends EntityDaoSqlMapImpl<PePosition> implements PePositionDao {

	/**
	 * @author Li,Yuan
	 * @version 2011-05-12
	 */
	public Long selectPePositionWithNameCount(PePosition t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectPePositionWithNameCount", t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2011-05-12
	 */
	@SuppressWarnings("unchecked")
	public List<PePosition> selectPePositionWithNamePaginatedList(PePosition t) {
		return super.getSqlMapClientTemplate().queryForList("selectPePositionWithNamePaginatedList", t);
	}

}
