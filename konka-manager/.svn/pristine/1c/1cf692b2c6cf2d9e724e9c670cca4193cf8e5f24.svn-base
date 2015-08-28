package com.ebiz.mmt.dao;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.KonkaR3ShopNew;
import com.ebiz.ssi.dao.EntityDao;


public interface KonkaR3ShopNewDao extends EntityDao<KonkaR3ShopNew> {

	/**
	 * 新增开户申请
	 * @author Liang,HouEn
	 * 2014-10-10
	 * @param t
	 * @return
	 */
	Long insertNewCustomer(KonkaR3ShopNew t);
	
	/**
	 * 统计待审客户总记录数
	 * @author Liang,HouEn
	 * 2014-10-10
	 * @param t
	 * @return
	 */
	Long selectWaitAuditCustCount(KonkaR3ShopNew t);
	
	/**
	 * 查询指定页的待审客户数据
	 * @author Liang,HouEn
	 * 2014-10-10
	 * @param t
	 * @return
	 */
	List<HashMap> selectWaitAuditCustList(KonkaR3ShopNew t);
	
	/**
	 * 根据cust_id查询
	 * @author Liang,HouEn
	 * 2014-10-22
	 * @return
	 */
	KonkaR3ShopNew selectKonkaR3ShopNewByCustId(KonkaR3ShopNew t);
}
