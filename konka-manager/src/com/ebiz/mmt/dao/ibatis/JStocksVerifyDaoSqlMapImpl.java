package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JStocksVerifyDao;
import com.ebiz.mmt.domain.JStocksVerify;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-08 17:03:35
 */
@Service
public class JStocksVerifyDaoSqlMapImpl extends EntityDaoSqlMapImpl<JStocksVerify> implements JStocksVerifyDao {

	/**
	 * @author Ren,zhong
	 * @date 2013-06-15
	 */
	public JStocksVerify selectJStocksVerifyForLastedVerify(JStocksVerify t) {
		return (JStocksVerify) super.getSqlMapClientTemplate().queryForObject("selectJStocksVerifyForLastedVerify", t);
	}

	@Override
	public Long selectJStocksVerifyForInventoryCount(JStocksVerify t) {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectJStocksVerifyForInventoryCount", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JStocksVerify> selectJStocksVerifyForInventoryList(JStocksVerify t) {
		return this.getSqlMapClientTemplate().queryForList("selectJStocksVerifyForInventoryList", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JStocksVerify> selectJStocksVerifyForInventoryPaginatedList(JStocksVerify t) {
		return this.getSqlMapClientTemplate().queryForList("selectJStocksVerifyForInventoryPaginatedList", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JStocksVerify> selectJStocksVerifyForInventoryFormList(JStocksVerify t) {
		return this.getSqlMapClientTemplate().queryForList("selectJStocksVerifyForInventoryFormList", t);
	}

	@Override
	public int AutoRunUpdateStatementForInsertJStocksVerify() {
		try {
			this.getSqlMapClientTemplate().update("AutoRunUpdateStatementForInsertJStocksVerify");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public List<JStocksVerify> selectJStocksVerifyWithStoreAndGoodsNamePaginatedList(JStocksVerify t) {
		return this.getSqlMapClientTemplate().queryForList("selectJStocksVerifyWithStoreAndGoodsNamePaginatedList", t);
	}

	@Override
	public long selectJStocksVerifyForManagerCount(JStocksVerify entity) {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectJStocksVerifyForManagerCount", entity);
	}

	@Override
	public List<JStocksVerify> selectJStocksVerifyForManagerPaginatedList(JStocksVerify entity) {
		return this.getSqlMapClientTemplate().queryForList("selectJStocksVerifyForManagerPaginatedList",entity);
	}
}
