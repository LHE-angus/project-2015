package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdAccoutsDao;
import com.ebiz.mmt.domain.KonkaXxZmdAccouts;
import com.ebiz.mmt.service.KonkaXxZmdAccoutsService;

/**
 * @author Xu,XiaoYuan
 * @version 2012-03-02 09:12
 */
@Service
public class KonkaXxZmdAccoutsServiceImpl implements KonkaXxZmdAccoutsService {

	@Resource
	private KonkaXxZmdAccoutsDao konkaXxZmdAccoutsDao;

	public Long createKonkaXxZmdAccouts(KonkaXxZmdAccouts t) {
		return this.konkaXxZmdAccoutsDao.insertEntity(t);
	}

	public KonkaXxZmdAccouts getKonkaXxZmdAccouts(KonkaXxZmdAccouts t) {
		return this.konkaXxZmdAccoutsDao.selectEntity(t);
	}

	public Long getKonkaXxZmdAccoutsCount(KonkaXxZmdAccouts t) {
		return this.konkaXxZmdAccoutsDao.selectEntityCount(t);
	}

	public List<KonkaXxZmdAccouts> getKonkaXxZmdAccoutsList(KonkaXxZmdAccouts t) {
		return this.konkaXxZmdAccoutsDao.selectEntityList(t);
	}

	public int modifyKonkaXxZmdAccouts(KonkaXxZmdAccouts t) {
		return this.konkaXxZmdAccoutsDao.updateEntity(t);
	}

	public int removeKonkaXxZmdAccouts(KonkaXxZmdAccouts t) {
		return this.konkaXxZmdAccoutsDao.deleteEntity(t);
	}

	public List<KonkaXxZmdAccouts> getKonkaXxZmdAccoutsPaginatedList(KonkaXxZmdAccouts t) {
		return this.konkaXxZmdAccoutsDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author hu,hao
	 * @version 2012-03-05
	 */
	public Long getKonkaXxZmdAccoutsForZmdAndDeptCount(KonkaXxZmdAccouts t) {
		return this.konkaXxZmdAccoutsDao.selectKonkaXxZmdAccoutsForZmdAndDeptCount(t);
	}

	/**
	 * @author hu,hao
	 * @version 2012-03-05
	 */
	public List<KonkaXxZmdAccouts> getKonkaXxZmdAccoutsForZmdAndDeptPaginatedList(KonkaXxZmdAccouts t) {
		return this.konkaXxZmdAccoutsDao.selectKonkaXxZmdAccoutsForZmdAndDeptPaginatedList(t);
	}
}
