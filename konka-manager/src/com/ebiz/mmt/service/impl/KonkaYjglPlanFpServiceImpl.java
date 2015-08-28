package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaYjglPlanFpDao;
import com.ebiz.mmt.domain.KonkaYjglPlanFp;
import com.ebiz.mmt.service.KonkaYjglPlanFpService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-03 14:08:34
 */
@Service
public class KonkaYjglPlanFpServiceImpl implements KonkaYjglPlanFpService {

	@Resource
	private KonkaYjglPlanFpDao konkaYjglPlanFpDao;

	public Long createKonkaYjglPlanFp(KonkaYjglPlanFp t) {
		return this.konkaYjglPlanFpDao.insertEntity(t);
	}

	public KonkaYjglPlanFp getKonkaYjglPlanFp(KonkaYjglPlanFp t) {
		return this.konkaYjglPlanFpDao.selectEntity(t);
	}

	public Long getKonkaYjglPlanFpCount(KonkaYjglPlanFp t) {
		return this.konkaYjglPlanFpDao.selectEntityCount(t);
	}

	public List<KonkaYjglPlanFp> getKonkaYjglPlanFpList(KonkaYjglPlanFp t) {
		return this.konkaYjglPlanFpDao.selectEntityList(t);
	}

	public int modifyKonkaYjglPlanFp(KonkaYjglPlanFp t) {
		return this.konkaYjglPlanFpDao.updateEntity(t);
	}

	public int removeKonkaYjglPlanFp(KonkaYjglPlanFp t) {
		return this.konkaYjglPlanFpDao.deleteEntity(t);
	}

	public List<KonkaYjglPlanFp> getKonkaYjglPlanFpPaginatedList(KonkaYjglPlanFp t) {
		return this.konkaYjglPlanFpDao.selectEntityPaginatedList(t);
	}

	public Long createKonkaYjglPlanFpList(List<KonkaYjglPlanFp> t) {
		Long counts = 0L;
		if (null != t && t.size() > 0) {
			for (KonkaYjglPlanFp cur : t) {
				Long id = this.konkaYjglPlanFpDao.insertEntity(cur);
			}
			counts = new Long(t.size());
		}

		return counts;
	}

	@Override
	public Long getKonkaYjglPlanFpAndDeptNameCount(KonkaYjglPlanFp t) {
		return this.konkaYjglPlanFpDao.selectKonkaYjglPlanFpAndDeptNameCount(t);
	}

	@Override
	public List<KonkaYjglPlanFp> getKonkaYjglPlanFpAndDeptNamePaginatedList(KonkaYjglPlanFp t) {
		return this.konkaYjglPlanFpDao.selectKonkaYjglPlanFpAndDeptNamePaginatedList(t);
	}

	@Override
	public String createKonkaYjglPlanFp(List<KonkaYjglPlanFp> entityList) {
		return this.konkaYjglPlanFpDao.insertKonkaYjglPlanFp(entityList);
	}

	@Override
	public Long getKonkaYjglPlanFpAndTjCount(KonkaYjglPlanFp t) {
		return this.konkaYjglPlanFpDao.selectKonkaYjglPlanFpAndTjCount(t);
	}

	@Override
	public List<KonkaYjglPlanFp> getKonkaYjglPlanFpAndTjPaginatedList(KonkaYjglPlanFp t) {
		return this.konkaYjglPlanFpDao.selectKonkaYjglPlanFpAndTjPaginatedList(t);
	}

}
