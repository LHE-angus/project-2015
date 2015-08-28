package com.ebiz.mmt.dao;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-26 10:40:24
 */
public interface KonkaR3StoreDao extends EntityDao<KonkaR3Store> {

	/**
	 * @author Xing,XiuDong 根据业务员用户ID查询门店
	 */
	List<KonkaR3Store> selectKonkaR3StoreListWithYwyUserId(KonkaR3Store t);

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-25
	 * @return 根据p_index查询所能获取到的门店数量
	 */
	List<KonkaR3Store> selectKonkaR3StoreCountByPIndex(KonkaR3Store t);

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-23
	 * @return 根据p_index查询所能获取到的门店零售额
	 */
	List<KonkaR3Store> selectKonkaR3StoreListByPIndex(KonkaR3Store t);

	/**
	 * @author Pan,Gang
	 * @version 2013-11-1
	 * @return 透视部门授权
	 */
	List<KonkaR3Store> selectKonkaR3StoreForRoleDataPaginatedList(KonkaR3Store t);

	Long selectKonkaR3StoreForRoleDataCount(KonkaR3Store t);

	/**
	 * @author Hu,Hao
	 * @version 2013-11-26
	 */
	List<KonkaR3Store> selectKonkaR3StoreForStoresList(KonkaR3Store t);

	List<KonkaR3Store> selectKonkaR3StoreAndYwyNamePaginatedList(KonkaR3Store t);

	Long selectKonkaR3StoreAndYwyNameCount(KonkaR3Store t);
	
	Long selectStoreDataCountForVIP(KonkaR3Store t);

	List<KonkaR3Store> selectKonkaR3StoreForCustVisit(KonkaR3Store entity);

	List<KonkaR3Store> selectKonkaR3StoreForYwyAndCxyList(KonkaR3Store t);

	/**
	 * 查询返回map结果
	 * 
	 * @author Angus
	 * @date 2014-7-25
	 * @param t
	 * @return
	 */
	List<HashMap> selectKonkaR3StoreAndYwyNamePaginatedNew(KonkaR3Store t);
	
	List<HashMap> selectStoreDataListForVIP(KonkaR3Store t);

	Long selectKonkaR3StoreOnlyByCxyCount(KonkaR3Store entity);

	List<HashMap> selectKonkaR3StoreOnlyByCxyPaginatedList(KonkaR3Store entity);

	List<HashMap> selectKonkaR3StoreAndJbasePartnerForMainPage(KonkaR3Store entity);

	List<HashMap> selectKonkaR3StoreAndJbasePartnerForManager(KonkaR3Store entity);

	Long selectKonkaR3StoreSaleCount(KonkaR3Store entity);

	List<KonkaR3Store> selectKonkaR3StoreSalePaginatedList(KonkaR3Store entity);
	
	List<KonkaR3Store> selectKonkaR3StoreForCustVisit1(KonkaR3Store entity);
	
	/**
	 * 根据客户id查询门店
	 * @author Liang Houen
	 * @since 2015-06-23
	 * @param c_id
	 * @return
	 */
	List<HashMap> selectStoreListByCID(Long c_id);
}
