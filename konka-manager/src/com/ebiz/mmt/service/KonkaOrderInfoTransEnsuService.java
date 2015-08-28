package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaOrderInfoTransEnsu;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-24 17:30:54
 */
public interface KonkaOrderInfoTransEnsuService {

	Long createKonkaOrderInfoTransEnsu(KonkaOrderInfoTransEnsu t);

	int modifyKonkaOrderInfoTransEnsu(KonkaOrderInfoTransEnsu t);

	int removeKonkaOrderInfoTransEnsu(KonkaOrderInfoTransEnsu t);

	KonkaOrderInfoTransEnsu getKonkaOrderInfoTransEnsu(KonkaOrderInfoTransEnsu t);

	List<KonkaOrderInfoTransEnsu> getKonkaOrderInfoTransEnsuList(KonkaOrderInfoTransEnsu t);

	Long getKonkaOrderInfoTransEnsuCount(KonkaOrderInfoTransEnsu t);
	
	/**
	 * XiaoGuoJian
	 * 获取已签收数量，传入参数包含ensu_id即可
	 */
	Long getKonkaOrderInfoTransEnsurSumEnsured(KonkaOrderInfoTransEnsu t);

	List<KonkaOrderInfoTransEnsu> getKonkaOrderInfoTransEnsuPaginatedList(KonkaOrderInfoTransEnsu t);

	Long getKonkaOrderInfoTransEnsuAndDetailsCount(KonkaOrderInfoTransEnsu konkaOrderInfoTransEnsu);

	List<KonkaOrderInfoTransEnsu> getKonkaOrderInfoTransEnsuAndDetailsPaginatedList(
			KonkaOrderInfoTransEnsu konkaOrderInfoTransEnsu);
	
}