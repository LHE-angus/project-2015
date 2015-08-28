package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JBillDao;
import com.ebiz.mmt.domain.JBill;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-08 17:03:35
 */
@Service
public class JBillDaoSqlMapImpl extends EntityDaoSqlMapImpl<JBill> implements JBillDao {

	/**
	 * @author Wu,ShangLong
	 * @version 2013-6-9
	 */
	public Long selectSeqJBillId() {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectSeqJBillId");
	}

	@Override
	public Long selectCountForFourWeek(JBill t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectJBillCountForFouWeek", t);
	}

	@Override
	/**
	 * @author Wang,KunLin
	 * @date   2014-03-31
	 */
	public Long getJBillCountNameLike(JBill t) {
		
	return (Long) super.getSqlMapClientTemplate().queryForObject("selectJBillCountNameLike", t);
	}

	@Override
	public Long selectFXJbillCount(JBill t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKHJBillCount", t);
	}

	@Override
	public List<JBill> selectFXJbillList(JBill t) {
		return super.getSqlMapClientTemplate().queryForList("selectKHJbillList", t);
	}

	@Override
	public List<HashMap> selectJBillForTH(JBill t) {
		return super.getSqlMapClientTemplate().queryForList("selectJBillForTH", t);
	}

	@Override
	public Long selectJBillForTHCount(JBill t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectJBillForTHCount", t);
	}

	@Override
	public Long getSaleDataForkhCount(JBill t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("getSaleDataForkhCount", t);
	}

	@Override
	public List<HashMap> getSaleDataForkhList(JBill t) {
		return super.getSqlMapClientTemplate().queryForList("getSaleDataForkhList", t);
	}

	@Override
	public HashMap selectInfoForSales(JBill t) {
		return (HashMap) super.getSqlMapClientTemplate().queryForObject("selectInfoForSales", t);
	}

	@Override
	public List<HashMap> selectSaleInfoByDate(HashMap m) {
		List<HashMap> list = super.getSqlMapClientTemplate().queryForList("selectSaleInfoByDate", m);//调用
		return list;
	}

	@Override
	public List<HashMap> selectSaleForMonthList(HashMap m) {
		List<HashMap> list = super.getSqlMapClientTemplate().queryForList("selectSaleForMonthList", m);//调用
		return list;
	}

	@Override
	public HashMap selectBuyInfo(HashMap m) {
		return (HashMap) super.getSqlMapClientTemplate().queryForObject("selectBuyInfo", m);
	}

	@Override
	public String selectMonthMoney(HashMap m) {
		return (String) super.getSqlMapClientTemplate().queryForObject("selectMonthMoney", m);
	}

	@Override
	public List<HashMap> selectMonthInDataList(HashMap m) {
		List<HashMap> list = super.getSqlMapClientTemplate().queryForList("selectMonthInDataList", m);//调用
		return list;
	}
	@Override
	public Long selectOtherSaleDataCount(JBill t){
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectOtherSaleDataCount", t);
	}

	@Override
	public List<HashMap> selectOtherSaleDataList(JBill m) {
		List<HashMap> list = super.getSqlMapClientTemplate().queryForList("selectOtherSaleDataList", m);//调用
		return list;
	}
}
