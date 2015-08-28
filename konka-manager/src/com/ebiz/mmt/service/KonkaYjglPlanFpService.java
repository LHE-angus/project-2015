package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaYjglPlanFp;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-03 14:08:34
 */
public interface KonkaYjglPlanFpService {

	Long createKonkaYjglPlanFp(KonkaYjglPlanFp t);

	int modifyKonkaYjglPlanFp(KonkaYjglPlanFp t);

	int removeKonkaYjglPlanFp(KonkaYjglPlanFp t);

	KonkaYjglPlanFp getKonkaYjglPlanFp(KonkaYjglPlanFp t);

	List<KonkaYjglPlanFp> getKonkaYjglPlanFpList(KonkaYjglPlanFp t);

	Long getKonkaYjglPlanFpCount(KonkaYjglPlanFp t);

	List<KonkaYjglPlanFp> getKonkaYjglPlanFpPaginatedList(KonkaYjglPlanFp t);

	Long createKonkaYjglPlanFpList(List<KonkaYjglPlanFp> t);

	public List<KonkaYjglPlanFp> getKonkaYjglPlanFpAndDeptNamePaginatedList(KonkaYjglPlanFp t);

	public Long getKonkaYjglPlanFpAndDeptNameCount(KonkaYjglPlanFp t);

	String createKonkaYjglPlanFp(List<KonkaYjglPlanFp> entityList);

	public List<KonkaYjglPlanFp> getKonkaYjglPlanFpAndTjPaginatedList(KonkaYjglPlanFp t);

	public Long getKonkaYjglPlanFpAndTjCount(KonkaYjglPlanFp t);
}