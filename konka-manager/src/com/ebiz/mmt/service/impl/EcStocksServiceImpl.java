package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcStocksDao;
import com.ebiz.mmt.domain.EcStocks;
import com.ebiz.mmt.service.EcStocksService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
@Service
public class EcStocksServiceImpl implements EcStocksService {

	@Resource
	private EcStocksDao ecStocksDao;

	public Long createEcStocks(EcStocks t) {
		return this.ecStocksDao.insertEntity(t);
	}

	public EcStocks getEcStocks(EcStocks t) {
		return this.ecStocksDao.selectEntity(t);
	}

	public Long getEcStocksCount(EcStocks t) {
		return this.ecStocksDao.selectEntityCount(t);
	}

	public List<EcStocks> getEcStocksList(EcStocks t) {
		return this.ecStocksDao.selectEntityList(t);
	}

	public int modifyEcStocks(EcStocks t) {
		return this.ecStocksDao.updateEntity(t);
	}

	public int removeEcStocks(EcStocks t) {
		return this.ecStocksDao.deleteEntity(t);
	}

	public List<EcStocks> getEcStocksPaginatedList(EcStocks t) {
		return this.ecStocksDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-09-13
	 */
	public List<EcStocks> getEcStocksWithGoodsIdAndPindexList(EcStocks t) {
		return this.ecStocksDao.selectEcStocksWithGoodsIdAndPindexList(t);
	}

	/**
	 * @author Pan,Gang
	 * @version 2013-09-14
	 */
	public List<EcStocks> getEcStocksForStoreTypeList(EcStocks t) {
		return this.ecStocksDao.selectEcStocksForStoreTypeList(t);
	}

	public Long getEcStocksForYzCount(EcStocks t) {
		return this.ecStocksDao.selectEcStocksForYzCount(t);
	}

	public Long getEcStocksForStoreTypeCount(EcStocks t) {
		return this.ecStocksDao.selectEcStocksForStoreTypeCount(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-09-13
	 */
	public int modifyEcStocksForSale(EcStocks t) {
		return this.ecStocksDao.updateEcStocksForSale(t);
	}

	@Override
	public List<EcStocks> getEcStocksForYzList(EcStocks t) {
		return this.ecStocksDao.selectEcStocksForYzList(t);
	}

	@Override
	public Long getEcStocksForStoreTypeNewCount(EcStocks t) {
		return this.ecStocksDao.selectEcStocksForStoreTypeNewCount(t);
	}

	@Override
	public List<EcStocks> getEcStocksForStoreTypeNewList(EcStocks t) {
		return this.ecStocksDao.selectEcStocksForStoreTypeNewList(t);
	}

	@Override
	public List<EcStocks> getEcStocksWithGoodsIdAndPindexNewList(EcStocks t) throws DataAccessException {
		return this.ecStocksDao.selectEcStocksWithGoodsIdAndPindexNewList(t);
	}

}
