package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JBillDetailsDao;
import com.ebiz.mmt.domain.JBillDetails;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-08 17:03:35
 */
@Service
public class JBillDetailsDaoSqlMapImpl extends EntityDaoSqlMapImpl<JBillDetails> implements JBillDetailsDao {

	/**
	 * 
	 * @author Wu,ShangLong
	 * @version 2013-6-13
	 */
	@SuppressWarnings("unchecked")
	public List<JBillDetails> selectJBillDetailsAndGoodsList(JBillDetails t) {
		return super.getSqlMapClientTemplate().queryForList("selectJBillDetailsAndGoodsList", t);
	}
	

	/**
	 * 
	 * @author TUDP
	 * @version 2013-11-04
	 */
	public Long selectJBillDetailsForCustomerCount(JBillDetails t) {
		return (Long)super.getSqlMapClientTemplate().queryForObject("selectJBillDetailsForCustomerCount",t);
	}
	
	/**
	 * 
	 * @author TUDP
	 * @version 2013-11-04
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<HashMap> selectJBillDetailsForCustomerPaginatedList(JBillDetails t) {
		return super.getSqlMapClientTemplate().queryForList("selectJBillDetailsForCustomerPaginatedList", t);
	}
	
	/**
	 * 
	 * @author XIAOGJ
	 * @version 2014-03-08
	 */
	public Long selectJBillDetailsForJSubSellRecCount(JBillDetails t) {
		return (Long)super.getSqlMapClientTemplate().queryForObject("selectJBillDetailsForJSubSellRecCount",t);
	}
	
	/**
	 * 
	 * @author XIAOGJ
	 * @version 2014-03-08
	 */
	@SuppressWarnings("unchecked")
	public List<JBillDetails> selectJBillDetailsForJSubSellRecPaginatedList(JBillDetails t) {
		return super.getSqlMapClientTemplate().queryForList("selectJBillDetailsForJSubSellRecPaginatedList", t);
	}
	
	/**
	 * 
	 * @author XIAOGJ
	 * @version 2014-03-08
	 */
	@SuppressWarnings("unchecked")
	public List<JBillDetails> selectJBillDetailsForJSubSellRecForExcelList(JBillDetails t) {
		return super.getSqlMapClientTemplate().queryForList("selectJBillDetailsForJSubSellRecList", t);
	}
	
	/**
	 * 
	 * @author XIAOGJ
	 * @version 2014-05-12
	 */
	public Long selectJBillDetailsForDetailsCount(JBillDetails t){
		return (Long)super.getSqlMapClientTemplate().queryForObject("selectJBillDetailsForDetailsCount",t);
	}

	/**
	 * 
	 * @author XIAOGJ
	 * @version 2014-05-12
	 */
	@SuppressWarnings("unchecked")
	public List<JBillDetails> selectJBillDetailsForDetailsPaginatedList(JBillDetails t){
		return super.getSqlMapClientTemplate().queryForList("selectJBillDetailsForDetailsPaginatedList", t);
	}
	
	/**
	 * 
	 * @author XIAOGJ
	 * @version 2014-05-12
	 */
	@SuppressWarnings("unchecked")
	public List<JBillDetails> selectJBillDetailsForDetailsList(JBillDetails t){
		return super.getSqlMapClientTemplate().queryForList("selectJBillDetailsForDetailsList", t);
	}


	@Override
	public Long selectJBillDetailsForConfirmCount(JBillDetails t) {
		return (Long)super.getSqlMapClientTemplate().queryForObject("selectJBillDetailsForConfirmCount",t);
	}


	@Override
	public List<HashMap> selectJBillDetailsForConfirmList(JBillDetails t) {
		return super.getSqlMapClientTemplate().queryForList("selectJBillDetailsForConfirmList", t);
	}


	@Override
	public List<JBillDetails> selectJBillDetailsForClient(JBillDetails t) {
		return super.getSqlMapClientTemplate().queryForList("selectJBillDetailsForClient", t);
	}


	@Override
	public Long selectJBillDetailsForSalesCount(JBillDetails t) {
		return (Long)super.getSqlMapClientTemplate().queryForObject("selectJBillDetailsForSalesCount", t);
	}


	@Override
	public List<HashMap> selectJBillDetailsForSalesList(JBillDetails t) {
		return super.getSqlMapClientTemplate().queryForList("selectJBillDetailsForSalesList", t);
	}
}
