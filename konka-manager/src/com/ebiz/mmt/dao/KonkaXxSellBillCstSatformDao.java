package com.ebiz.mmt.dao;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.KonkaXxSellBillCstSatform;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-04-10 12:30:54
 */
public interface KonkaXxSellBillCstSatformDao extends EntityDao<KonkaXxSellBillCstSatform> {
	
	/**
	 * @author Jiang,JiaYong
	 * @version 2012-04-13
	 */
	public KonkaXxSellBillCstSatform selectKonkaXxSellBillCstSatformBySellBillIdAndMdName(KonkaXxSellBillCstSatform t)
			throws DataAccessException;
}
