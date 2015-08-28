package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPlanRatioDao;
import com.ebiz.mmt.domain.KonkaPlanRatio;
import com.ebiz.mmt.service.KonkaPlanRatioService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-09 15:32:09
 */
@Service
public class KonkaPlanRatioServiceImpl implements KonkaPlanRatioService {

	@Resource
	private KonkaPlanRatioDao konkaPlanRatioDao;

	public Long createKonkaPlanRatio(KonkaPlanRatio t) {
		return this.konkaPlanRatioDao.insertEntity(t);
	}

	public KonkaPlanRatio getKonkaPlanRatio(KonkaPlanRatio t) {
		return this.konkaPlanRatioDao.selectEntity(t);
	}

	public Long getKonkaPlanRatioCount(KonkaPlanRatio t) {
		return this.konkaPlanRatioDao.selectEntityCount(t);
	}

	public List<KonkaPlanRatio> getKonkaPlanRatioList(KonkaPlanRatio t) {
		return this.konkaPlanRatioDao.selectEntityList(t);
	}

	public int modifyKonkaPlanRatio(KonkaPlanRatio t) {
		return this.konkaPlanRatioDao.updateEntity(t);
	}

	public int removeKonkaPlanRatio(KonkaPlanRatio t) {
		return this.konkaPlanRatioDao.deleteEntity(t);
	}

	public List<KonkaPlanRatio> getKonkaPlanRatioPaginatedList(KonkaPlanRatio t) {
		return this.konkaPlanRatioDao.selectEntityPaginatedList(t);
	}

	public List<KonkaPlanRatio> selectKonkaPlanRatioListForFgs(KonkaPlanRatio t) {
		return this.konkaPlanRatioDao.selectKonkaPlanRatioListForFgs(t);
	}

	public Long createKonkaPlanRatioForExcel(List<KonkaPlanRatio> entityList) {
		if (null != entityList && entityList.size() > 0) {
			for (KonkaPlanRatio kpt : entityList) {
				KonkaPlanRatio t = new KonkaPlanRatio();
				t.setY(kpt.getY());
				t.setFgs_sn(kpt.getFgs_sn());
				t.setDept_sn(kpt.getDept_sn());
				this.konkaPlanRatioDao.deleteEntity(t);
				this.konkaPlanRatioDao.insertEntity(kpt);
			}
		}
		return null;
	}

}
