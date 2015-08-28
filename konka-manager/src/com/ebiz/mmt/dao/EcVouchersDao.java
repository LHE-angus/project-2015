package com.ebiz.mmt.dao;

import com.ebiz.mmt.domain.EcVouchers;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-25 15:36:42
 */
public interface EcVouchersDao extends EntityDao<EcVouchers> {
	int modifyEcVouchersByOrderId(EcVouchers t);

	Long insertBatch(EcVouchers t);
}
