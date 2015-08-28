package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaOrderPlan;
import com.ebiz.mmt.domain.KonkaR3StoreShow;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-20 10:58:11
 */
public interface KonkaOrderPlanService {

	Long createKonkaOrderPlan(KonkaOrderPlan t);

	int modifyKonkaOrderPlan(KonkaOrderPlan t);

	int removeKonkaOrderPlan(KonkaOrderPlan t);

	KonkaOrderPlan getKonkaOrderPlan(KonkaOrderPlan t);

	List<KonkaOrderPlan> getKonkaOrderPlanList(KonkaOrderPlan t);

	Long getKonkaOrderPlanCount(KonkaOrderPlan t);

	List<KonkaOrderPlan> getKonkaOrderPlanPaginatedList(KonkaOrderPlan t);
	
	Long getKonkaOrderPlanForServerCount(KonkaOrderPlan t);
	
	List<KonkaOrderPlan> getKonkaOrderPlanForServerPaginatedList(KonkaOrderPlan t);
	
	Long getKonkaOrderPlanForCustomerCount(KonkaOrderPlan t);
	
	List<KonkaOrderPlan> getKonkaOrderPlanForCustomerPaginatedList(KonkaOrderPlan t);
	
    Long getKonkaOrderPlanStatementForChannelCount(KonkaOrderPlan t);
	
	List<KonkaOrderPlan> getKonkaOrderPlanStatementForChannelPaginatedList(KonkaOrderPlan t);
	
    Long getKonkaOrderPlanStatementForCustomerCount(KonkaOrderPlan t);
	
	List<KonkaOrderPlan> getKonkaOrderPlanStatementForCustomerPaginatedList(KonkaOrderPlan t);
	
    Long getKonkaOrderPlanStatementForModelsCount(KonkaOrderPlan t);
	
	List<KonkaOrderPlan> getKonkaOrderPlanStatementForModelsPaginatedList(KonkaOrderPlan t);
	
	String createKonkaOrderPlan(List<KonkaOrderPlan> list);

}