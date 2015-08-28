package com.ebiz.mmt.service;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.FromDrpOrder;
import com.ebiz.mmt.domain.KonkaOrderInfo;

/**
 * @author Wu,Yang
 * @version 2011-11-25 09:08
 */
public interface KonkaOrderInfoService {

	HashMap<String, String> createKonkaOrderInfo(KonkaOrderInfo t);

	int modifyKonkaOrderInfo(KonkaOrderInfo t);

	int removeKonkaOrderInfo(KonkaOrderInfo t);

	KonkaOrderInfo getKonkaOrderInfo(KonkaOrderInfo t);

	List<KonkaOrderInfo> getKonkaOrderInfoList(KonkaOrderInfo t);

	List<KonkaOrderInfo> getKonkaOrderInfoListWithShopName(KonkaOrderInfo t);

	Long getKonkaOrderInfoCount(KonkaOrderInfo t);

	List<KonkaOrderInfo> getKonkaOrderInfoPaginatedList(KonkaOrderInfo t);

	List<KonkaOrderInfo> getKonkaOrderInfoPaginatedListWithShopName(KonkaOrderInfo t);

	List<KonkaOrderInfo> getKonkaOrderInfoResultForPaginatedList(KonkaOrderInfo t);

	Long createKonkaOrderInfoOrder(KonkaOrderInfo t);

    Long getKonkaOrderInfoAndNextRoleCount(KonkaOrderInfo t);


    Long getKonkaOrderInfoWithNoForFourWeekCount(KonkaOrderInfo t);

	Long getKonkaOrderInfoWithNoSysManCount(KonkaOrderInfo t);

    /**
     * 非系统管理员查询 订单数据 一般为分公司的管理员
     */
	List<KonkaOrderInfo> getKonkaOrderInfoWithNoSysManPaginatedList(KonkaOrderInfo t);


    /**
     * 总部系统管理员查询 订单主表数据并关联了审批节点数据
     * 
     */
	List<KonkaOrderInfo> getKonkaOrderInfoAndNextRoleResultForPaginatedList(KonkaOrderInfo t);

	Long getKonkaOrderInfoSearchforPdNameCount(KonkaOrderInfo t);

	List<KonkaOrderInfo> getKonkaOrderInfoResultForR3CodePaginatedList(KonkaOrderInfo t);

	Long getKonkaOrderInfoSearchforR3CodeCount(KonkaOrderInfo t);

	int modifyKonkaOrderInfoForconfirm(KonkaOrderInfo t);


    /**
     * 订单主表和从表关联,并且查的是明细数据
     *
     */
	List<KonkaOrderInfo> getKonkaOrderInfoAndNextRoleResultForPaginatedList1(KonkaOrderInfo t);

	Long getKonkaOrderInfoAndNextRoleResultForPaginatedListcount1(KonkaOrderInfo entity);

	Long getKonkaOrderInfoSearchforR3CodeandforcbCount(KonkaOrderInfo entity);

	List<KonkaOrderInfo> getKonkaOrderInfoResultForR3CodeforCbPaginatedList(KonkaOrderInfo entity);

	List<KonkaOrderInfo> getKonkaOrderInfoResultForR3CodeforCbList(KonkaOrderInfo entity);

	Long getKonkaOrderInfoDetailsCount(KonkaOrderInfo entity);

	/**
	 * 订单明细表查询
	 *
	 * @param entity
	 * @return
	 */
	List<HashMap> getKonkaOrderInfoDetailsList(KonkaOrderInfo entity);

	/**
	 * 订单明细统计
	 *
	 * @author Liang,HouEn 2014-8-29
	 * @param entity
	 * @return
	 */
	List<HashMap> getKonkaOrderInfoDetailsListCount(KonkaOrderInfo entity);

	/**
	 * 统计所有商品数量，所有商品总价（系统管理员）
	 *
	 * @author Wang,KunLin
	 * @date 2014-9-23
	 * @param entity
	 * @return
	 */
	KonkaOrderInfo getKonkaOrderInfoNumCountMondySum(KonkaOrderInfo entity);

	/**
	 * 统计所有商品数量，所有商品总价(非管理员)
	 *
	 * @author Wang,KunLin
	 * @date 2014-9-23
	 * @param entity
	 * @return
	 */
	KonkaOrderInfo getKonkaOrderInfoNumCountMondySum1(KonkaOrderInfo entity);

	/**
	 * 统计所有商品数量，所有商品总价 详细页面
	 *
	 * @author Wang,KunLin
	 * @date 2014-9-23
	 * @param entity
	 * @return
	 */
	KonkaOrderInfo getKonkaOrderInfoNumCountMondySum2(KonkaOrderInfo entity);

	/**
	 * IHS订单接口
	 *
	 * @author Xiao,GuoJian
	 * @date 2014-10-13
	 */
	Long getKonkaOrderInfoForIhsCount(KonkaOrderInfo t);

	/**
	 * IHS订单接口
	 *
	 * @author Xiao,GuoJian
	 * @date 2014-10-13
	 */
	List<KonkaOrderInfo> getKonkaOrderInfoForIhsPaginatedList(KonkaOrderInfo t);

    /**
     * DRP订单接口
     *
     * @param ko
     */
    void createDrpOrderInfo(KonkaOrderInfo ko, FromDrpOrder fo);

	/**
	 * 首页查询待审订单列表
	 * @param t
	 * @return
	 */
	List<HashMap> getWaitForAuditOrderList(KonkaOrderInfo t);
}
