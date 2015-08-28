package com.ebiz.mmt.service;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.KonkaR3Store;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-26 10:40:24
 */
public interface KonkaR3StoreService {

	Long createKonkaR3Store(KonkaR3Store t);

	int modifyKonkaR3Store(KonkaR3Store t);

	int removeKonkaR3Store(KonkaR3Store t);

	KonkaR3Store getKonkaR3Store(KonkaR3Store t);

	List<KonkaR3Store> getKonkaR3StoreList(KonkaR3Store t);

	Long getKonkaR3StoreCount(KonkaR3Store t);

	List<KonkaR3Store> getKonkaR3StorePaginatedList(KonkaR3Store t);

	/**
	 * @author Xing,XiuDong 根据业务员用户ID查询门店
	 */
	List<KonkaR3Store> getKonkaR3StoreListWithYwyUserId(KonkaR3Store t);

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-25
	 * @return 根据p_index查询所能获取到的门店数量
	 */
	List<KonkaR3Store> getKonkaR3StoreCountByPIndex(KonkaR3Store t);

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-25
	 * @return 根据p_index查询所能获取到的门店零售额
	 */
	List<KonkaR3Store> getKonkaR3StoreListByPIndex(KonkaR3Store t);

	/**
	 * @author Pan,Gang
	 * @version 2013-11-1
	 * @return 透视部门授权
	 */
	List<KonkaR3Store> getKonkaR3StoreForRoleDataPaginatedList(KonkaR3Store t);

	Long getKonkaR3StoreForRoleDataCount(KonkaR3Store t);

	/**
	 * @author Hu,Hao
	 * @version 2013-11-26
	 */
	List<KonkaR3Store> getKonkaR3StoreForStoresList(KonkaR3Store t);

	List<HashMap> getKonkaR3StoreAndYwyNamePaginatedList(KonkaR3Store t);
	
	List<HashMap> getStoreDataListForVIP(KonkaR3Store t);

	Long getKonkaR3StoreAndYwyNameCount(KonkaR3Store t);
	
	Long getStoreDataCountForVIP(KonkaR3Store t);

	List<KonkaR3Store> getKonkaR3StoreForCustVisit(KonkaR3Store entity);

	/**
	 * 返回map方法
	 * 
	 * @author Angus
	 * @date 2014-7-25
	 * @param t
	 * @return
	 */
	List<HashMap> getKonkaR3StoreAndYwyNamePaginatedNew(KonkaR3Store t);

	Long createKonkaR3StoreAttachment(KonkaR3Store krs, Long lid);

	List<KonkaR3Store> getKonkaR3StoreForYwyAndCxyList(KonkaR3Store t);

	Long getKonkaR3StoreOnlyByCxyCount(KonkaR3Store entity);

	List<HashMap> getKonkaR3StoreOnlyByCxyPaginatedList(KonkaR3Store entity);

	List<HashMap> getKonkaR3StoreAndJbasePartnerForMainPage(KonkaR3Store entity);

	List<HashMap> getKonkaR3StoreAndJbasePartnerForManager(KonkaR3Store entity);

	// 门店销售信息表统计总数
	Long getKonkaR3StoreSaleCount(KonkaR3Store entity);

	// 门店销售信息表统计数据
	List<KonkaR3Store> getKonkaR3StoreSalePaginatedList(KonkaR3Store entity);
	//根据用户信息找门店信息
	List<KonkaR3Store> getKonkaR3StoreForCustVisit1(KonkaR3Store entity);
	
	/**
	 * 根据客户id查询门店
	 * @author Liang Houen
	 * @since 2015-06-23
	 * @param c_id
	 * @return
	 */
	List<HashMap> getStoreListByCID(Long c_id);
}