package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaOrderPlan;

import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-20 10:58:11
 */
public interface KonkaOrderPlanDao extends EntityDao<KonkaOrderPlan> {

	/**
	 * 进货计划（业务员）
	 * 
	 * @param t
	 * @return
	 */
	//进货计划上报（管理端）
	Long selectKonkaOrderPlanForServerCount(KonkaOrderPlan t);
	
	List<KonkaOrderPlan> selectKonkaOrderPlanForServerPaginatedList(KonkaOrderPlan t);
	
	//进货计划上报（客户端）
    Long selectKonkaOrderPlanForCustomerCount(KonkaOrderPlan t);
	
	List<KonkaOrderPlan> selectKonkaOrderPlanForCustomerPaginatedList(KonkaOrderPlan t);
	
    //进货计划统计（渠道）
	Long selectKonkaOrderPlanStatementForChannelCount(KonkaOrderPlan t);
	
	List<KonkaOrderPlan> selectKonkaOrderPlanStatementForChannelPaginatedList(KonkaOrderPlan t);
	
	 //进货计划统计（客户）
	Long selectKonkaOrderPlanStatementForCustomerCount(KonkaOrderPlan t);
	
	List<KonkaOrderPlan> selectKonkaOrderPlanStatementForCustomerPaginatedList(KonkaOrderPlan t);
	
	 //进货计划统计（机型）
	Long selectKonkaOrderPlanStatementForModelsCount(KonkaOrderPlan t);
	
	List<KonkaOrderPlan> selectKonkaOrderPlanStatementForModelsPaginatedList(KonkaOrderPlan t);
	
	//导入功能
	String insertKonkaOrderPlanList(List<KonkaOrderPlan> list);
}
