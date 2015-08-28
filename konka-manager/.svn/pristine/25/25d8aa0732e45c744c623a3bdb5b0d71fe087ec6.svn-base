package com.ebiz.mmt.dao;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Wu,Yang
 * @version 2011-11-25 11:00
 */
public interface KonkaOrderInfoDao extends EntityDao<KonkaOrderInfo> {

	/**
	 * @author Ren,zhong
	 * @date 2013-06-09
	 */
	List<KonkaOrderInfo> selectKonkaOrderInfoResultForPaginatedList(KonkaOrderInfo t);

	List<KonkaOrderInfo> selectKonkaOrderInfoPaginatedListWithShopName(KonkaOrderInfo t);

	List<KonkaOrderInfo> selectKonkaOrderInfoListWithShopName(KonkaOrderInfo t);

	/**
	 * @author Wu,ShangLong
	 * @date 2013-08-07
	 */
	Long selectKonkaOrderInfoWithNoSysManCount(KonkaOrderInfo t);

	List<KonkaOrderInfo> selectKonkaOrderInfoWithNoSysManPaginatedList(KonkaOrderInfo t);

	Long selectKonkaOrderInfoAndNextRoleCount(KonkaOrderInfo t);

	List<KonkaOrderInfo> selectKonkaOrderInfoAndNextRoleResultForPaginatedList(KonkaOrderInfo t);

	/**
	 * @author Pan,Gang
	 * @date 2013-08-26
	 */
	Long selectKonkaOrderInfoSearchforPdNameCount(KonkaOrderInfo t);

	List<KonkaOrderInfo> selectKonkaOrderInfoResultForR3CodePaginatedList(KonkaOrderInfo t);

	Long selectKonkaOrderInfoSearchforR3CodeCount(KonkaOrderInfo t);

	/**
	 * @author liujia
	 * @date 2014-03-20
	 */
	Long selectKonkaOrderInfoWithNoForFourWeekCount(KonkaOrderInfo t);

	List<KonkaOrderInfo> selectKonkaOrderInfoAndNextRoleResultForPaginatedList1(KonkaOrderInfo t);

	Long selectKonkaOrderInfoAndNextRoleResultForPaginatedListcount1(KonkaOrderInfo t);

	List<KonkaOrderInfo> selectKonkaOrderInfoResultForR3CodeforCbPaginatedList(KonkaOrderInfo entity);

	Long selectKonkaOrderInfoSearchforR3CodeandforcbCount(KonkaOrderInfo entity);

	List<KonkaOrderInfo> selectKonkaOrderInfoResultForR3CodeforCbList(KonkaOrderInfo entity);

	Long selectKonkaOrderInfoDetailsCount(KonkaOrderInfo entity);

	List<HashMap> selectKonkaOrderInfoDetailsList(KonkaOrderInfo entity);

	List<HashMap> selectKonkaOrderInfoDetailsListCount(KonkaOrderInfo entity);

	/**
	 * 统计所有商品数量，所有商品总价（系统管理员）
	 * 
	 * @author Wang,KunLin
	 * @date 2014-9-23
	 * @param entity
	 * @return
	 */
	KonkaOrderInfo selectKonkaOrderInfoNumCountMondySum(KonkaOrderInfo entity);

	/**
	 * 统计所有商品数量，所有商品总价（非管理员）
	 * 
	 * @author Wang,KunLin
	 * @date 2014-9-23
	 * @param entity
	 * @return
	 */
	KonkaOrderInfo selectKonkaOrderInfoNumCountMondySum1(KonkaOrderInfo entity);

	/**
	 * 统计所有商品数量，所有商品总价（详细页面）
	 * 
	 * @author Wang,KunLin
	 * @date 2014-9-23
	 * @param entity
	 * @return
	 */
	KonkaOrderInfo selectKonkaOrderInfoNumCountMondySum2(KonkaOrderInfo entity);

	/**
	 * IHS订单接口
	 * 
	 * @author Xiao,GuoJian
	 * @date 2014-10-13
	 */
	Long selectKonkaOrderInfoForIhsCount(KonkaOrderInfo t);

	/**
	 * IHS订单接口
	 * 
	 * @author Xiao,GuoJian
	 * @date 2014-10-13
	 */
	List<KonkaOrderInfo> selectKonkaOrderInfoForIhsPaginatedList(KonkaOrderInfo t);

	List<HashMap> selectWaitForAuditOrderList(KonkaOrderInfo t);
}
