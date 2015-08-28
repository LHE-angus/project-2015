package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPersonWageDao;
import com.ebiz.mmt.domain.KonkaPersonWage;
import com.ebiz.mmt.service.KonkaPersonWageService;

/**
 * @author Ren,zhong
 * @version 2013-07-26 11:06
 */
@Service
public class KonkaPersonWageServiceImpl implements KonkaPersonWageService {

	@Resource
	private KonkaPersonWageDao konkaPersonWageDao;
	

	public Long createKonkaPersonWage(KonkaPersonWage t) {
		return this.konkaPersonWageDao.insertEntity(t);
	}

	public KonkaPersonWage getKonkaPersonWage(KonkaPersonWage t) {
		return this.konkaPersonWageDao.selectEntity(t);
	}

	public Long getKonkaPersonWageCount(KonkaPersonWage t) {
		return this.konkaPersonWageDao.selectEntityCount(t);
	}

	public List<KonkaPersonWage> getKonkaPersonWageList(KonkaPersonWage t) {
		return this.konkaPersonWageDao.selectEntityList(t);
	}

	public int modifyKonkaPersonWage(KonkaPersonWage t) {
		return this.konkaPersonWageDao.updateEntity(t);
	}

	public int removeKonkaPersonWage(KonkaPersonWage t) {
		return this.konkaPersonWageDao.deleteEntity(t);
	}

	public List<KonkaPersonWage> getKonkaPersonWagePaginatedList(KonkaPersonWage t) {
		return this.konkaPersonWageDao.selectEntityPaginatedList(t);
	}

	public Long createKonkaPersonWageForImport(KonkaPersonWage t) {
		String mark = (String) t.getMap().get("mark");
		List<KonkaPersonWage> list  = t.getKonkaPersonWageList();
		if (null != list && list.size() > 0) {
			if ("insert".equals(mark)) { //新增
				for (KonkaPersonWage wage : list) {
					this.konkaPersonWageDao.insertEntity(wage);
				}
			} else if ("update".equals(mark)) { //修改
				this.konkaPersonWageDao.deleteEntity(t);
				for (KonkaPersonWage wage : list) {
					this.konkaPersonWageDao.insertEntity(wage);
				}
			}
		}
		return 0L;
	}

}
