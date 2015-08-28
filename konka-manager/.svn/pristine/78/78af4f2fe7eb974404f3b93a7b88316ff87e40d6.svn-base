package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JStocksStackDao;
import com.ebiz.mmt.domain.JStocksStack;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-18 09:43:03
 */
@Service
public class JStocksStackDaoSqlMapImpl extends EntityDaoSqlMapImpl<JStocksStack> implements JStocksStackDao {
	/**
	 * 
	 * @author Wu,ShangLong
	 * @version 2013-6-18
	 */
	public void updateJStocksPopStack(JStocksStack t) {
		super.getSqlMapClientTemplate().update("updateJStocksPopStack", t);
	}

	/**
	 * @author Ren,zhong
	 * @date 2013-06-20
	 */
	@SuppressWarnings("unchecked")
	public List<JStocksStack> selectJStocksStackForSskcResultList(JStocksStack t) {
		return super.getSqlMapClientTemplate().queryForList("selectJStocksStackForSskcResultList", t);
	}
}
