package com.ebiz.mmt.dao.ibatis;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaOrderPlanDao;
import com.ebiz.mmt.domain.KonkaOrderPlan;
import com.ebiz.mmt.domain.KonkaR3StoreShow;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-20 10:58:11
 */
@Service
public class KonkaOrderPlanDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaOrderPlan> implements KonkaOrderPlanDao {

	//进货计划上报（管理端）
	public Long selectKonkaOrderPlanForServerCount(KonkaOrderPlan t){
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaOrderPlanForServerCount",t);
	}
	
	@SuppressWarnings("unchecked")
	public List<KonkaOrderPlan> selectKonkaOrderPlanForServerPaginatedList(KonkaOrderPlan t){
		return super.getSqlMapClientTemplate().queryForList("selectKonkaOrderPlanForServerPaginatedList",t);
		
	}
	
	//进货计划上报（客户端）
	public Long selectKonkaOrderPlanForCustomerCount(KonkaOrderPlan t){
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaOrderPlanForCustomerCount",t);
	}
	
	@SuppressWarnings("unchecked")
	public List<KonkaOrderPlan> selectKonkaOrderPlanForCustomerPaginatedList(KonkaOrderPlan t){
		return super.getSqlMapClientTemplate().queryForList("selectKonkaOrderPlanForCustomerPaginatedList",t);
		
	}
	
	//进货计划统计（渠道）
	public Long selectKonkaOrderPlanStatementForChannelCount(KonkaOrderPlan t){
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaOrderPlanStatementForChannelCount",t);
	}
	
	@SuppressWarnings("unchecked")
	public List<KonkaOrderPlan> selectKonkaOrderPlanStatementForChannelPaginatedList(KonkaOrderPlan t){
		return super.getSqlMapClientTemplate().queryForList("selectKonkaOrderPlanStatementForChannelPaginatedList",t);
		
	}
	
	//进货计划统计（客户）
	public Long selectKonkaOrderPlanStatementForCustomerCount(KonkaOrderPlan t){
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaOrderPlanStatementForCustomerCount",t);
	}
	
	@SuppressWarnings("unchecked")
	public List<KonkaOrderPlan> selectKonkaOrderPlanStatementForCustomerPaginatedList(KonkaOrderPlan t){
		return super.getSqlMapClientTemplate().queryForList("selectKonkaOrderPlanStatementForCustomerPaginatedList",t);
		
	}
	
	//进货计划统计（机型）
	public Long selectKonkaOrderPlanStatementForModelsCount(KonkaOrderPlan t){
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaOrderPlanStatementForModelsCount",t);
	}
	
	@SuppressWarnings("unchecked")
	public List<KonkaOrderPlan> selectKonkaOrderPlanStatementForModelsPaginatedList(KonkaOrderPlan t){
		return super.getSqlMapClientTemplate().queryForList("selectKonkaOrderPlanStatementForModelsPaginatedList",t);
		
	}
	
	//导入
	public String insertKonkaOrderPlanList(List<KonkaOrderPlan> list) {
		String msg = "";
		
		for (int i = 0; i < list.size(); i++) {
			KonkaOrderPlan t = list.get(i);
			KonkaOrderPlan ks = new KonkaOrderPlan();
			ks.setPlan_year(t.getPlan_year());
			ks.setPlan_month(t.getPlan_month());
			ks.setR3_code(t.getR3_code());
			ks.setPd_name(t.getPd_name());
			ks.setPlan_stock_num(t.getPlan_stock_num());
			ks.setLast_stock_num(t.getLast_stock_num());
			ks.setPlan_sale_num(t.getPlan_sale_num());
			List<KonkaOrderPlan> entityList = this.selectEntityList(ks);
			if (null != entityList && entityList.size() > 0 ) {
				for (KonkaOrderPlan konkaOrderPlan : entityList) {
					this.getSqlMapClientTemplate().delete("deleteKonkaOrderPlan", konkaOrderPlan);
				}
				this.getSqlMapClientTemplate().insert("insertKonkaOrderPlan",t);
			}else {
				this.getSqlMapClientTemplate().insert("insertKonkaOrderPlan",t);
			}
		}

//		for (KonkaR3StoreShow konkaR3StoreShow1 : list) {
//			KonkaR3StoreShow ks = new KonkaR3StoreShow();
//			ks.setDept_name(konkaR3StoreShow1.getDept_name());
//			ks.setYear(konkaR3StoreShow1.getYear());
//			ks.setMonth(konkaR3StoreShow1.getMonth());
//			ks.setStore_name(konkaR3StoreShow1.getStore_name());
//			ks.setTask_type(konkaR3StoreShow1.getTask_type());
//			List<KonkaR3StoreShow> entityList = this.selectEntityList(ks);
//			if (null != entityList && entityList.size() > 0) {
//				for (KonkaR3StoreShow konkaR3StoreShow : entityList) {
//					this.getSqlMapClientTemplate().delete("deleteKonkaR3StoreShow", konkaR3StoreShow);
//				}
//				this.getSqlMapClientTemplate().insert("insertKonkaR3StoreShow", konkaR3StoreShow1);
//			} else {
//				this.getSqlMapClientTemplate().insert("insertKonkaR3StoreShow", konkaR3StoreShow1);
//			}
//		}

		return msg;
	}

}
