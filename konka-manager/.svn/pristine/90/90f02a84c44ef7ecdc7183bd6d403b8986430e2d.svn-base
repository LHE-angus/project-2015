package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaOrderInfoTransEnsu;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-24 17:30:54
 */
public interface KonkaOrderInfoTransEnsuDao extends EntityDao<KonkaOrderInfoTransEnsu> {
	//获取历史中已经确认收货数量的总和，即已签收数量
	Long selectKonkaOrderInfoTransEnsurSumEnsured(KonkaOrderInfoTransEnsu t);

	Long selectKonkaOrderInfoTransEnsuAndDetailsCount(KonkaOrderInfoTransEnsu konkaOrderInfoTransEnsu);

	List<KonkaOrderInfoTransEnsu> selectKonkaOrderInfoTransEnsuAndDetailsPaginatedList(
			KonkaOrderInfoTransEnsu konkaOrderInfoTransEnsu);
}
