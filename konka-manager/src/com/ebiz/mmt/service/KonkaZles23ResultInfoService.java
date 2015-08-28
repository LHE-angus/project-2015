package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaZles23ResultInfo;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-03-25 15:50:31
 */
public interface KonkaZles23ResultInfoService {

	Long createKonkaZles23ResultInfo(KonkaZles23ResultInfo t);

	int modifyKonkaZles23ResultInfo(KonkaZles23ResultInfo t);

	int removeKonkaZles23ResultInfo(KonkaZles23ResultInfo t);

	KonkaZles23ResultInfo getKonkaZles23ResultInfo(KonkaZles23ResultInfo t);

	List<KonkaZles23ResultInfo> getKonkaZles23ResultInfoList(KonkaZles23ResultInfo t);

	Long getKonkaZles23ResultInfoCount(KonkaZles23ResultInfo t);

	List<KonkaZles23ResultInfo> getKonkaZles23ResultInfoPaginatedList(KonkaZles23ResultInfo t);

	/**
	 * @author Xiao,GuoJian
	 * @version 2014-06-12
	 * @desc 获取集采订单客户端记录数
	 */
	Long getKonkaZles23ResultInfoForCustomerCount(KonkaZles23ResultInfo t);

	/**
	 * @author Xiao,GuoJian
	 * @version 2014-06-12
	 * @desc 获取集采订单客户端记录
	 */
	List<KonkaZles23ResultInfo> getKonkaZles23ResultInfoForCustomerPaginatedList(KonkaZles23ResultInfo t);

	/**
	 * 获取2个月内,转储单号非空并未完整交货的集采订单
	 * 
	 * @return
	 */
	List<KonkaZles23ResultInfo> getKonkaZles23ResultInfoWithEbelnList();

	
	/**
	 * 获取前一天,完整交货的单子
	 * 
	 * @return
	 */
	List<KonkaZles23ResultInfo> getKonkaZles23ResultInfoForShockInList(
			KonkaZles23ResultInfo resultInfo);

}