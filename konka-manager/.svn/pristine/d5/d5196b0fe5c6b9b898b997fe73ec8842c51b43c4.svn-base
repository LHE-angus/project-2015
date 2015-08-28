package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcStocksDao;
import com.ebiz.mmt.domain.EcStocks;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
@Service
public class EcStocksDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcStocks> implements EcStocksDao {

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-09-13
	 */
	@SuppressWarnings("unchecked")
	public List<EcStocks> selectEcStocksWithGoodsIdAndPindexList(EcStocks t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectEcStocksWithGoodsIdAndPindexList", t);
	}

	/**
	 * @author Pan,Gang
	 * @version 2013-09-14
	 */
	@SuppressWarnings("unchecked")
	public List<EcStocks> selectEcStocksForStoreTypeList(EcStocks t) {
		return super.getSqlMapClientTemplate().queryForList("selectEcStocksForStoreTypeList", t);
	}

	public Long selectEcStocksForYzCount(EcStocks t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectEcStocksForYzCount", t);
	}

	public Long selectEcStocksForStoreTypeCount(EcStocks t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectEcStocksForStoreTypeCount", t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-09-13
	 */
	public int updateEcStocksForSale(EcStocks t) {
		return this.getSqlMapClientTemplate().update("updateEcStocksForSale", t);
	}

	@SuppressWarnings("unchecked")
	public List<EcStocks> selectEcStocksForYzList(EcStocks t) {
		return this.getSqlMapClientTemplate().queryForList("selectEcStocksForYzList", t);
	}

	@Override
	public Long selectEcStocksForStoreTypeNewCount(EcStocks t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectEcStocksForStoreTypeNewCount", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EcStocks> selectEcStocksForStoreTypeNewList(EcStocks t) {
		return this.getSqlMapClientTemplate().queryForList("selectEcStocksForStoreTypeNewList", t);
	}

	@SuppressWarnings("unchecked")
	public List<EcStocks> selectEcStocksWithGoodsIdAndPindexNewList(EcStocks t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectEcStocksWithGoodsIdAndPindexNewList", t);
	}

}
