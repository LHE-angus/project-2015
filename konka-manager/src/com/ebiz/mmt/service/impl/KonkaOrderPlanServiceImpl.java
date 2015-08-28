package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaOrderPlanDao;
import com.ebiz.mmt.domain.KonkaOrderPlan;
import com.ebiz.mmt.domain.KonkaR3StoreShow;
import com.ebiz.mmt.service.KonkaOrderPlanService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-20 10:58:11
 */
@Service
public class KonkaOrderPlanServiceImpl implements KonkaOrderPlanService {

	@Resource
	private KonkaOrderPlanDao konkaOrderPlanDao;
	

	public Long createKonkaOrderPlan(KonkaOrderPlan t) {
		return this.konkaOrderPlanDao.insertEntity(t);
	}

	public KonkaOrderPlan getKonkaOrderPlan(KonkaOrderPlan t) {
		return this.konkaOrderPlanDao.selectEntity(t);
	}

	public Long getKonkaOrderPlanCount(KonkaOrderPlan t) {
		return this.konkaOrderPlanDao.selectEntityCount(t);
	}

	public List<KonkaOrderPlan> getKonkaOrderPlanList(KonkaOrderPlan t) {
		return this.konkaOrderPlanDao.selectEntityList(t);
	}

	public int modifyKonkaOrderPlan(KonkaOrderPlan t) {
		return this.konkaOrderPlanDao.updateEntity(t);
	}

	public int removeKonkaOrderPlan(KonkaOrderPlan t) {
		return this.konkaOrderPlanDao.deleteEntity(t);
	}

	public List<KonkaOrderPlan> getKonkaOrderPlanPaginatedList(KonkaOrderPlan t) {
		return this.konkaOrderPlanDao.selectEntityPaginatedList(t);
	}
	
	public Long getKonkaOrderPlanForServerCount(KonkaOrderPlan t){
		return this.konkaOrderPlanDao.selectKonkaOrderPlanForServerCount(t);
	}
	
	public List<KonkaOrderPlan> getKonkaOrderPlanForServerPaginatedList(KonkaOrderPlan t){
		return this.konkaOrderPlanDao.selectKonkaOrderPlanForServerPaginatedList(t);
	}
	
	public Long getKonkaOrderPlanForCustomerCount(KonkaOrderPlan t){
		return this.konkaOrderPlanDao.selectKonkaOrderPlanForCustomerCount(t);
	}
	
	public List<KonkaOrderPlan> getKonkaOrderPlanForCustomerPaginatedList(KonkaOrderPlan t){
		return this.konkaOrderPlanDao.selectKonkaOrderPlanForCustomerPaginatedList(t);
	}
	
	public Long getKonkaOrderPlanStatementForChannelCount(KonkaOrderPlan t){
		return this.konkaOrderPlanDao.selectKonkaOrderPlanStatementForChannelCount(t);
	}
	
	public List<KonkaOrderPlan> getKonkaOrderPlanStatementForChannelPaginatedList(KonkaOrderPlan t){
		return this.konkaOrderPlanDao.selectKonkaOrderPlanStatementForChannelPaginatedList(t);
	}
	
	public Long getKonkaOrderPlanStatementForCustomerCount(KonkaOrderPlan t){
		return this.konkaOrderPlanDao.selectKonkaOrderPlanStatementForCustomerCount(t);
	}
	
	public List<KonkaOrderPlan> getKonkaOrderPlanStatementForCustomerPaginatedList(KonkaOrderPlan t){
		return this.konkaOrderPlanDao.selectKonkaOrderPlanStatementForCustomerPaginatedList(t);
	}
	
	public Long getKonkaOrderPlanStatementForModelsCount(KonkaOrderPlan t){
		return this.konkaOrderPlanDao.selectKonkaOrderPlanStatementForModelsCount(t);
	}
	
	public List<KonkaOrderPlan> getKonkaOrderPlanStatementForModelsPaginatedList(KonkaOrderPlan t){
		return this.konkaOrderPlanDao.selectKonkaOrderPlanStatementForModelsPaginatedList(t);
	}

	//导出
	public String createKonkaOrderPlan(List<KonkaOrderPlan> list) {
		return this.konkaOrderPlanDao.insertKonkaOrderPlanList(list);
	}

}
