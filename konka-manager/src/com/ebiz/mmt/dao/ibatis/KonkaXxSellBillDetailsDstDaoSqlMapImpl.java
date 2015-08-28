package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxSellBillDetailsDstDao;
import com.ebiz.mmt.domain.KonkaXxSellBillDetailsDst;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-01-11 10:56:03
 */
@Service
public class KonkaXxSellBillDetailsDstDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaXxSellBillDetailsDst> implements KonkaXxSellBillDetailsDstDao {

	/**
	 * @author Ren,zhong
	 * @version 2011-04-09
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxSellBillDetailsDst> selectKonkaXxSellBillDetailsDstForPrintOutOrders(KonkaXxSellBillDetailsDst t)
			throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaXxSellBillDetailsDstForPrintOutOrders", t);
	}

}
