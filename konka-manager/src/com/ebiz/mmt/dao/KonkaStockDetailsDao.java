package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.KonkaStockDetails;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-08-25 08:32:14
 */
public interface KonkaStockDetailsDao extends EntityDao<KonkaStockDetails> {

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-25
	 */
	public abstract int deleteKonkaStockDetailsWithSID(KonkaStockDetails t) throws DataAccessException;

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-25
	 */
	public abstract List<KonkaStockDetails> selectKonkaStockDetailsForStatisticsPaginatedList(KonkaStockDetails t) throws DataAccessException;

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-25
	 */
	public abstract Long selectKonkaStockDetailsForStatisticsCount(KonkaStockDetails t) throws DataAccessException;
}
