package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.JxcStockBill;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-03 09:42:37
 */
public interface JxcStockBillDao extends EntityDao<JxcStockBill> {

	JxcStockBill selectJxcStockBillWithMoney(JxcStockBill t);

	List<JxcStockBill> selectJxcStockBillPaginatedListWithSname(JxcStockBill t);

	List<JxcStockBill> selectJxcStockBillListForSZMX(JxcStockBill t);
}
