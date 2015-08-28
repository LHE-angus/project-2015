package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaJxcStockBillDao;
import com.ebiz.mmt.domain.KonkaJxcStockBill;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Wu,Yang
 * @version 2011-10-14 20:31
 */
@Service
@SuppressWarnings("unchecked")
public class KonkaJxcStockBillDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaJxcStockBill> implements KonkaJxcStockBillDao {

	/** Li,Ka : 查询包含进货单明细、仓库、上级部门的分页列表 */
	public List<KonkaJxcStockBill> selectKonkaJxcStockBillWithDetailsPaginatedList(KonkaJxcStockBill t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaJxcStockBillWithDetailsPaginatedList", t);
	}
	
	/** Li,Ka : 查询包含上级部门的记录 */
	public KonkaJxcStockBill selectKonkaJxcStockBillWithParDept(KonkaJxcStockBill t) {
		return (KonkaJxcStockBill) super.getSqlMapClientTemplate().queryForObject("selectKonkaJxcStockBillWithParDept", t);
	}
}
