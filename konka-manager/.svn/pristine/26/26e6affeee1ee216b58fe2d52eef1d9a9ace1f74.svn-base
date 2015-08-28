package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaJxcStockBill;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Wu,Yang
 * @version 2011-10-14 20:31
 */
public interface KonkaJxcStockBillDao extends EntityDao<KonkaJxcStockBill> {
	
	/** Li,Ka : 查询包含进货单明细、仓库、上级部门的分页列表 */
	List<KonkaJxcStockBill> selectKonkaJxcStockBillWithDetailsPaginatedList(KonkaJxcStockBill t);
	
	/** Li,Ka : 查询包含上级部门的记录 */
	KonkaJxcStockBill selectKonkaJxcStockBillWithParDept(KonkaJxcStockBill t);
}
