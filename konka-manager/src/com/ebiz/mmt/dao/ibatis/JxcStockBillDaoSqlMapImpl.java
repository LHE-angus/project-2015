package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JxcStockBillDao;
import com.ebiz.mmt.domain.JxcStockBill;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-03 09:42:37
 */
@Service
public class JxcStockBillDaoSqlMapImpl extends EntityDaoSqlMapImpl<JxcStockBill> implements JxcStockBillDao {

	@SuppressWarnings("unchecked")
	public List<JxcStockBill> selectJxcStockBillPaginatedListWithSname(JxcStockBill t) {
		return super.getSqlMapClientTemplate().queryForList("selectJxcStockBillPaginatedListWithSname", t);
	}

	public JxcStockBill selectJxcStockBillWithMoney(JxcStockBill t) {
		return (JxcStockBill) super.getSqlMapClientTemplate().queryForObject("selectJxcStockBillWithMoney", t);
	}

	@SuppressWarnings("unchecked")
	public List<JxcStockBill> selectJxcStockBillListForSZMX(JxcStockBill t) {
		return super.getSqlMapClientTemplate().queryForList("selectJxcStockBillListForSZMX", t);
	}
}
