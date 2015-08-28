package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JxcEntpSellDao;
import com.ebiz.mmt.domain.JxcEntpSell;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-02-10 11:05:59
 */
@Service
public class JxcEntpSellDaoSqlMapImpl extends EntityDaoSqlMapImpl<JxcEntpSell> implements JxcEntpSellDao {

	/**
	 * @author Wu,ShangLong
	 * @version 2011-02-10
	 */
	@SuppressWarnings("unchecked")
	public List<JxcEntpSell> selectJxcEntpSellListForPart(JxcEntpSell t) {
		return super.getSqlMapClientTemplate().queryForList("selectJxcEntpSellListForPart", t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011-02-10
	 */
	public Long selectJxcEntpSellforDistinctCount(JxcEntpSell t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectJxcEntpSellforDistinctCount", t);
	}
}
