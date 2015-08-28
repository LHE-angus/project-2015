package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaFgsEdDao;
import com.ebiz.mmt.domain.KonkaFgsEd;
import com.ebiz.mmt.service.KonkaFgsEdService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-21 09:53:26
 */
@Service
public class KonkaFgsEdServiceImpl implements KonkaFgsEdService {

	@Resource
	private KonkaFgsEdDao konkaFgsEdDao;

	public Long createKonkaFgsEd(KonkaFgsEd t) {
		return this.konkaFgsEdDao.insertEntity(t);
	}

	public KonkaFgsEd getKonkaFgsEd(KonkaFgsEd t) {
		return this.konkaFgsEdDao.selectEntity(t);
	}

	public Long getKonkaFgsEdCount(KonkaFgsEd t) {
		return this.konkaFgsEdDao.selectEntityCount(t);
	}

	public List<KonkaFgsEd> getKonkaFgsEdList(KonkaFgsEd t) {
		return this.konkaFgsEdDao.selectEntityList(t);
	}

	public int modifyKonkaFgsEd(KonkaFgsEd t) {
		return this.konkaFgsEdDao.updateEntity(t);
	}

	public int removeKonkaFgsEd(KonkaFgsEd t) {
		return this.konkaFgsEdDao.deleteEntity(t);
	}

	public List<KonkaFgsEd> getKonkaFgsEdPaginatedList(KonkaFgsEd t) {
		return this.konkaFgsEdDao.selectEntityPaginatedList(t);
	}

	@Override
	public List<KonkaFgsEd> getKonkaFgsEdGroupByDeptIdList(KonkaFgsEd t) {
		return this.konkaFgsEdDao.selectKonkaFgsEdGroupByDeptIdList(t);
	}

}
