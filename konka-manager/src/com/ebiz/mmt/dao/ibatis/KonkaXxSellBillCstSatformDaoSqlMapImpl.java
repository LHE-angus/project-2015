package com.ebiz.mmt.dao.ibatis;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxSellBillCstSatformDao;
import com.ebiz.mmt.domain.KonkaXxSellBillCstSatform;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-04-10 12:30:54
 */
@Service
public class KonkaXxSellBillCstSatformDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaXxSellBillCstSatform> implements KonkaXxSellBillCstSatformDao {

	/**
	 * @author Jiang,JiaYong
	 * @version 2012-04-13
	 */
	public KonkaXxSellBillCstSatform selectKonkaXxSellBillCstSatformBySellBillIdAndMdName(KonkaXxSellBillCstSatform t)
			throws DataAccessException {
		return (KonkaXxSellBillCstSatform) this.getSqlMapClientTemplate().queryForObject("selectKonkaXxSellBillCstSatformBySellBillIdAndMdName", t);
	}

}
