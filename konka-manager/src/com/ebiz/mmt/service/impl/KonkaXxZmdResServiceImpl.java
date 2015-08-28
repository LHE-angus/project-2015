package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdResDao;
import com.ebiz.mmt.domain.KonkaXxZmdRes;
import com.ebiz.mmt.service.KonkaXxZmdResService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-03-21 16:06:04
 */
@Service
public class KonkaXxZmdResServiceImpl implements KonkaXxZmdResService {

	@Resource
	private KonkaXxZmdResDao konkaXxZmdResDao;
	

	public Long createKonkaXxZmdRes(KonkaXxZmdRes t) {
		return this.konkaXxZmdResDao.insertEntity(t);
	}

	public KonkaXxZmdRes getKonkaXxZmdRes(KonkaXxZmdRes t) {
		return this.konkaXxZmdResDao.selectEntity(t);
	}

	public Long getKonkaXxZmdResCount(KonkaXxZmdRes t) {
		return this.konkaXxZmdResDao.selectEntityCount(t);
	}

	public List<KonkaXxZmdRes> getKonkaXxZmdResList(KonkaXxZmdRes t) {
		return this.konkaXxZmdResDao.selectEntityList(t);
	}

	public int modifyKonkaXxZmdRes(KonkaXxZmdRes t) {
		return this.konkaXxZmdResDao.updateEntity(t);
	}

	public int removeKonkaXxZmdRes(KonkaXxZmdRes t) {
		return this.konkaXxZmdResDao.deleteEntity(t);
	}

	public List<KonkaXxZmdRes> getKonkaXxZmdResPaginatedList(KonkaXxZmdRes t) {
		return this.konkaXxZmdResDao.selectEntityPaginatedList(t);
	}

}
