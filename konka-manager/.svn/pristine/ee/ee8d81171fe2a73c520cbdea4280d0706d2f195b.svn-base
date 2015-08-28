package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxSellBillDao;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-01-10 12:37:18
 */
@Service
public class KonkaXxSellBillDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaXxSellBill> implements KonkaXxSellBillDao {

	/**
	 * @author Li,Yuan
	 * @version 2012-01-11
	 */
	public Long selectKonkaXxSellBillCountForJs(KonkaXxSellBill t) {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectKonkaXxSellBillCountForJs", t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2012-01-11
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxSellBill> selectKonkaXxSellBillForJSPaginatedList(KonkaXxSellBill t) {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaXxSellBillForJSPaginatedList", t);
	}

	/**
	 * @author hu,hao
	 * @version 2012-03-03
	 */
	public Long selectKonkaXxSellBillForDeptCount(KonkaXxSellBill t) {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectKonkaXxSellBillForDeptCount", t);
	}

	/**
	 * @author hu,hao
	 * @version 2012-03-03
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxSellBill> selectKonkaXxSellBillForDeptPaginatedList(KonkaXxSellBill t) {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaXxSellBillForDeptPaginatedList", t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2012-03-20
	 */
	public String selectKonkaXxSellBillTotalMoneySum(KonkaXxSellBill t) {
		return (String) this.getSqlMapClientTemplate().queryForObject("selectKonkaXxSellBillTotalMoneySum", t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2012-03-20
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxSellBill> selectKonkaXxSellBillAndDetailList(KonkaXxSellBill t) {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaXxSellBillAndDetailList", t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2012-04-06
	 */
	public Long selectKonkaXxSellBillNumSum(KonkaXxSellBill t) {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectKonkaXxSellBillNumSum", t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2012-04-06
	 */
	public KonkaXxSellBill selectKonkaXxSellBillMoneySum(KonkaXxSellBill t) {
		return (KonkaXxSellBill) this.getSqlMapClientTemplate().queryForObject("selectKonkaXxSellBillMoneySum", t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2012-04-06
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxSellBill> selectKonkaXxSellBillDeptPdSellNumSumList(KonkaXxSellBill t) {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaXxSellBillDeptPdSellNumSumList", t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2012-04-06
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxSellBill> selectKonkaXxSellBillDeptPdSellMoneySumList(KonkaXxSellBill t) {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaXxSellBillDeptPdSellMoneySumList", t);
	}
	
	/**
	 * @author Jiang,JiaYong
	 * @version 2012-05-07
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxSellBill> selectKonkaXxSellBillListForCountNumSumMoney(KonkaXxSellBill t) {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaXxSellBillListForCountNumSumMoney", t);
	}
	
	/**
	 * @author Hu,Hao
	 * @version 2013-03-23
	 */
	public String selectKonkaXxSellBillTotalMoneySumHd(KonkaXxSellBill t){
		return (String) this.getSqlMapClientTemplate().queryForObject("selectKonkaXxSellBillTotalMoneySumHd", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	
	/**
	 * @author Wang,KunLin
	 * @version 2014-06-11
	 */
	public List<KonkaXxSellBill> selectKonkaXxSellBillPaginatedListfordetails(
			KonkaXxSellBill entity) {
		
		return	this.getSqlMapClientTemplate().queryForList("selectKonkaXxSellBillPaginatedListfordetails", entity);
		
	}

	@Override
	public Long selectKonkaXxSellBillCountfordetails(KonkaXxSellBill entity) {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectKonkaXxSellBillCountfordetails", entity);
	}
}
