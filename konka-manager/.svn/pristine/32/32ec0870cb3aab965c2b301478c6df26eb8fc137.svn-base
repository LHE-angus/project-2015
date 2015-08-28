package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JsStocksDao;
import com.ebiz.mmt.domain.JsStocks;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2014-01-09 15:57:14
 */
@Service
public class JsStocksDaoSqlMapImpl extends EntityDaoSqlMapImpl<JsStocks> implements JsStocksDao {
	/**
	 * @author Liu,ZhiXiang
	 * @version 2014-01-10
	 */
	@Override
	public Long selectJsStockForSumStocks(JsStocks t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectJsStockForSumStocks");
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2014-01-14
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<JsStocks> selectJsStockForTotal(JsStocks t) {
		return super.getSqlMapClientTemplate().queryForList("selectJsStockForTotal", t);
	}
	
	/**
	 * @author Liu,ZhiXiang
	 * @version 2014-01-17
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<JsStocks> selectJsStocksListForFX(JsStocks t) {
		return super.getSqlMapClientTemplate().queryForList("selectJsStocksListForFX", t);
	}
}
