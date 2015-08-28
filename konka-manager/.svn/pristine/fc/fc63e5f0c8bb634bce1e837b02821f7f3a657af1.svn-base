package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JfScortsExchangeDao;
import com.ebiz.mmt.domain.JfScortsExchange;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-25 15:25:02
 */
@Service
public class JfScortsExchangeDaoSqlMapImpl extends EntityDaoSqlMapImpl<JfScortsExchange> implements JfScortsExchangeDao {

	/**
	 * @author Wu,ShangLong
	 * @version 2013-06-26
	 */
	@SuppressWarnings("unchecked")
	public List<JfScortsExchange> selectJfScortsExchangeAndGiftNameList(JfScortsExchange t) {
		return super.getSqlMapClientTemplate().queryForList("selectJfScortsExchangeAndGiftNameList", t);
	}

	/**
	 * 
	 * @author pan,gang
	 * @date 2013-8-2
	 */
	@SuppressWarnings("unchecked")
	public List<JfScortsExchange> selectJfScortsExchangeAndGiftNameForMemberCardList(JfScortsExchange t) {
		return super.getSqlMapClientTemplate().queryForList("selectJfScortsExchangeAndGiftNameForMemberCardList", t);
	}
}
