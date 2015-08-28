package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.EcStocks;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
public interface EcStocksDao extends EntityDao<EcStocks> {

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-09-13
	 */
	public List<EcStocks> selectEcStocksWithGoodsIdAndPindexList(EcStocks t) throws DataAccessException;

	/**
	 * @author Pan,Gang
	 * @version 2013-09-14
	 */
	List<EcStocks> selectEcStocksForStoreTypeList(EcStocks t);

	Long selectEcStocksForYzCount(EcStocks t);

	Long selectEcStocksForStoreTypeCount(EcStocks t);

	List<EcStocks> selectEcStocksForStoreTypeNewList(EcStocks t);

	Long selectEcStocksForStoreTypeNewCount(EcStocks t);

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-09-13
	 */
	public int updateEcStocksForSale(EcStocks t);

	List<EcStocks> selectEcStocksForYzList(EcStocks t);

	public List<EcStocks> selectEcStocksWithGoodsIdAndPindexNewList(EcStocks t) throws DataAccessException;
}
