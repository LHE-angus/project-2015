package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JSubSellRecDao;
import com.ebiz.mmt.domain.JSubSellRec;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-17 11:19:45
 */
@Service
public class JSubSellRecDaoSqlMapImpl extends EntityDaoSqlMapImpl<JSubSellRec> implements JSubSellRecDao {

	/**
	 * @author Xing,XiuDong
	 * @version 2013-09-25
	 */
	@SuppressWarnings("unchecked")
	public List<JSubSellRec> selectJSubSellRecPaginatedListOfDetails(JSubSellRec t) {
		return super.getSqlMapClientTemplate().queryForList("selectJSubSellRecPaginatedListOfDetails", t);
	}

	/**
	 * @author Xing,XiuDong
	 * @version 2013-09-25
	 */
	public Long selectJSubSellRecCountOfDetails(JSubSellRec t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectJSubSellRecCountOfDetails", t);
	}

	@Override
	public Long selectJSubSellRecCountForFourWeek(JSubSellRec t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectJSubSellRecCountForFourWeek", t);
	}
}
