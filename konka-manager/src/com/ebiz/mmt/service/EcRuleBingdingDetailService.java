package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcRuleBingdingDetail;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-08-08 15:20:55
 */
public interface EcRuleBingdingDetailService {

	Long createEcRuleBingdingDetail(EcRuleBingdingDetail t);

	int modifyEcRuleBingdingDetail(EcRuleBingdingDetail t);

	int removeEcRuleBingdingDetail(EcRuleBingdingDetail t);

	EcRuleBingdingDetail getEcRuleBingdingDetail(EcRuleBingdingDetail t);

	List<EcRuleBingdingDetail> getEcRuleBingdingDetailList(EcRuleBingdingDetail t);

	Long getEcRuleBingdingDetailCount(EcRuleBingdingDetail t);

	List<EcRuleBingdingDetail> getEcRuleBingdingDetailPaginatedList(EcRuleBingdingDetail t);

}