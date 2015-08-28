package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxPdPriceLimitApplyDao;
import com.ebiz.mmt.domain.KonkaXxPdPriceLimitApply;
import com.ebiz.mmt.service.KonkaXxPdPriceLimitApplyService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-03-31 14:24:51
 */
@Service
public class KonkaXxPdPriceLimitApplyServiceImpl implements KonkaXxPdPriceLimitApplyService {

	@Resource
	private KonkaXxPdPriceLimitApplyDao konkaXxPdPriceLimitApplyDao;
	

	public Long createKonkaXxPdPriceLimitApply(KonkaXxPdPriceLimitApply t) {
		return this.konkaXxPdPriceLimitApplyDao.insertEntity(t);
	}

	public KonkaXxPdPriceLimitApply getKonkaXxPdPriceLimitApply(KonkaXxPdPriceLimitApply t) {
		return this.konkaXxPdPriceLimitApplyDao.selectEntity(t);
	}

	public Long getKonkaXxPdPriceLimitApplyCount(KonkaXxPdPriceLimitApply t) {
		return this.konkaXxPdPriceLimitApplyDao.selectEntityCount(t);
	}

	public List<KonkaXxPdPriceLimitApply> getKonkaXxPdPriceLimitApplyList(KonkaXxPdPriceLimitApply t) {
		return this.konkaXxPdPriceLimitApplyDao.selectEntityList(t);
	}

	public int modifyKonkaXxPdPriceLimitApply(KonkaXxPdPriceLimitApply t) {
		return this.konkaXxPdPriceLimitApplyDao.updateEntity(t);
	}

	public int removeKonkaXxPdPriceLimitApply(KonkaXxPdPriceLimitApply t) {
		return this.konkaXxPdPriceLimitApplyDao.deleteEntity(t);
	}

	public List<KonkaXxPdPriceLimitApply> getKonkaXxPdPriceLimitApplyPaginatedList(KonkaXxPdPriceLimitApply t) {
		return this.konkaXxPdPriceLimitApplyDao.selectEntityPaginatedList(t);
	}

}
