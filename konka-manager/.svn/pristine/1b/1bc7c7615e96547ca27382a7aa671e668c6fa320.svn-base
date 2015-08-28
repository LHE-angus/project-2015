package com.ebiz.mmt.service;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.KonkaR3ShopNew;


public interface KonkaR3ShopNewService {

	Long createKonkaR3ShopNew(KonkaR3ShopNew t);

	int modifyKonkaR3ShopNew(KonkaR3ShopNew t) throws Exception;

	int removeKonkaR3ShopNew(KonkaR3ShopNew t);

	KonkaR3ShopNew getKonkaR3ShopNew(KonkaR3ShopNew t);

	List<KonkaR3ShopNew> getKonkaR3ShopNewList(KonkaR3ShopNew t);

	Long getKonkaR3ShopNewCount(KonkaR3ShopNew t);

	List<KonkaR3ShopNew> getKonkaR3ShopNewPaginatedList(KonkaR3ShopNew t);

	/**
	 * 新增开户申请
	 * @author Liang,HouEn
	 * 2014-10-10
	 * @param t
	 * @return
	 */
	Long saveNewCustomer(KonkaR3ShopNew t) throws Exception;
	
	/**
	 * 统计待审客户总记录数
	 * @author Liang,HouEn
	 * 2014-10-10
	 * @param t
	 * @return
	 */
	Long getWaitAuditCustCount(KonkaR3ShopNew t);
	
	/**
	 * 查询指定页的待审客户数据
	 * @author Liang,HouEn
	 * 2014-10-10
	 * @param t
	 * @return
	 */
	List<HashMap> getWaitAuditCustList(KonkaR3ShopNew t);
	
	/**
	 * 将新开户客户同步至R3客户管理
	 * @author Liang,HouEn
	 * 2014-10-13
	 * @param t
	 * @return
	 */
	int addToKonkaR3Shop(KonkaR3ShopNew t);
}