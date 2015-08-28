package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaYjglPlanFp;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-03 14:08:34
 */
public interface KonkaYjglPlanFpDao extends EntityDao<KonkaYjglPlanFp> {
	public List<KonkaYjglPlanFp> selectKonkaYjglPlanFpAndDeptNamePaginatedList(KonkaYjglPlanFp t);

	public Long selectKonkaYjglPlanFpAndDeptNameCount(KonkaYjglPlanFp t);

	String insertKonkaYjglPlanFp(List<KonkaYjglPlanFp> list);

	public List<KonkaYjglPlanFp> selectKonkaYjglPlanFpAndTjPaginatedList(KonkaYjglPlanFp t);

	public Long selectKonkaYjglPlanFpAndTjCount(KonkaYjglPlanFp t);
}
