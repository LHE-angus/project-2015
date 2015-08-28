package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxZmdQuota;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2012-03-19 15:33:05
 */
public interface KonkaXxZmdQuotaService {

	Long createKonkaXxZmdQuota(KonkaXxZmdQuota t);
	
	Long createKonkaXxZmdQuotaByCustom(KonkaXxZmdQuota t) throws Exception;

	int modifyKonkaXxZmdQuota(KonkaXxZmdQuota t);

	int removeKonkaXxZmdQuota(KonkaXxZmdQuota t);

	KonkaXxZmdQuota getKonkaXxZmdQuota(KonkaXxZmdQuota t);

	List<KonkaXxZmdQuota> getKonkaXxZmdQuotaList(KonkaXxZmdQuota t);

	Long getKonkaXxZmdQuotaCount(KonkaXxZmdQuota t);

	List<KonkaXxZmdQuota> getKonkaXxZmdQuotaPaginatedList(KonkaXxZmdQuota t);

}