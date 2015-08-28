package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaOrderInfoTrans;
import com.ebiz.mmt.domain.KonkaOrderInfoTransDetails;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-22 15:41:39
 */
public interface KonkaOrderInfoTransDetailsService {

	Long createKonkaOrderInfoTransDetails(KonkaOrderInfoTransDetails t);

	int modifyKonkaOrderInfoTransDetails(KonkaOrderInfoTransDetails t);

	int removeKonkaOrderInfoTransDetails(KonkaOrderInfoTransDetails t);

	KonkaOrderInfoTransDetails getKonkaOrderInfoTransDetails(KonkaOrderInfoTransDetails t);

	List<KonkaOrderInfoTransDetails> getKonkaOrderInfoTransDetailsList(KonkaOrderInfoTransDetails t);

	Long getKonkaOrderInfoTransDetailsCount(KonkaOrderInfoTransDetails t);

	List<KonkaOrderInfoTransDetails> getKonkaOrderInfoTransDetailsPaginatedList(KonkaOrderInfoTransDetails t);
    
	//查通知单总量
	Long getKonkaOrderInfoTransDetailsPaginatedCountTZD(KonkaOrderInfoTransDetails t);
	//查通知单
	List<KonkaOrderInfoTransDetails> getKonkaOrderInfoTransDetailsPaginatedListTZD(
			KonkaOrderInfoTransDetails entity);
	
	/**
	 * XiaoGuoJian
	 * 取确认页面数据
	 * */
	List<KonkaOrderInfoTransDetails> getKonkaOrderInfoTransDetailsForConfirmList(KonkaOrderInfoTransDetails entity);
	
	/**
	 * XiaoGuoJian
	 * 确认页面数据保存，Details的更新，和Ensu的插入
	 * */
	int modifyKonkaOrderInfoTransDetailsForEnsu(List<KonkaOrderInfoTransDetails> detailsList,KonkaOrderInfoTrans konkaOrderInfoTrans);
	
	/**
	 * XiaoGuoJian
	 * 获取每单的每个型号已经发货的数量总和
	 * */
	Long getKonkaOrderInfoTransDetailsForSumTransNum(KonkaOrderInfoTransDetails t);
	
	/**
	 * XiaoGuoJian
	 * 根据具体的发货单号查询即将结案的数据
	 * */
	List<KonkaOrderInfoTransDetails> getKonkaOrderInfoTransDetailsListForOver(KonkaOrderInfoTransDetails t);



}